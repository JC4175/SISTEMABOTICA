/*
 * VentaDatos.java - Capa de acceso a datos para Venta y DetalleVenta
 * La relación es cabecera-detalle: una Venta tiene muchos DetalleVenta.
 * Reemplaza ventas.txt y detalles.txt por consultas SQL a SQLite.
 */
package Datos;

import Modelo.DetalleVenta;
import Modelo.Venta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentaDatos {

    /**
     * Registra una venta completa (cabecera + todos sus detalles).
     * Usa una transacción para garantizar que ambos inserts sean atómicos:
     * si algo falla, no quedará una venta sin sus detalles.
     */
    public void registrarVentaCompleta(Venta v) {
        String sqlVenta = "INSERT INTO Venta (IDventa, fechaVenta, total, tipoComprobante, numComprobante, metodoPago, codigoUsuario, codigoCliente) " +
                          "VALUES (?,?,?,?,?,?,?,?)";
        String sqlDetalle = "INSERT INTO DetalleVenta (IDventa, codigoMedicamento, nombreMedicamento, cantidad, precioUnitario, subtotal, descuento) " +
                            "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false); // Transacción atómica

            try (PreparedStatement ps1 = con.prepareStatement(sqlVenta)) {
                // Insertar la cabecera de la venta
                ps1.setString(1, v.getIDVenta());
                ps1.setString(2, v.getFechaVenta());
                ps1.setDouble(3, v.getTotal());
                ps1.setString(4, v.getTipoComprobante());
                ps1.setString(5, v.getNumComprobante());
                ps1.setString(6, v.getMetodoPago());
                ps1.setString(7, v.getCodigoUsuario());
                ps1.setString(8, v.getCodigoCliente());
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = con.prepareStatement(sqlDetalle)) {
                // Insertar cada línea del detalle de la venta
                for (DetalleVenta d : v.getDetalles()) {
                    ps2.setString(1, v.getIDVenta());
                    ps2.setString(2, d.getCodigoMedicamento());
                    ps2.setString(3, d.getNombreMedicamento());
                    ps2.setInt(4, d.getCantidad());
                    ps2.setDouble(5, d.getPrecioUnitario());
                    ps2.setDouble(6, d.getSubtotal());
                    ps2.setDouble(7, d.getDescuento());
                    ps2.addBatch(); // Agrupamos todos en un lote para eficiencia
                }
                ps2.executeBatch(); // Ejecutar todos los detalles juntos
            }

            con.commit(); // Confirmar la transacción completa
        } catch (SQLException e) {
            System.out.println("Error al registrar venta completa (rollback ejecutado): " + e.getMessage());
        }
    }

    /** Retorna todas las ventas con sus detalles asociados */
    public ArrayList<Venta> leerTodas() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sqlVentas = "SELECT * FROM Venta ORDER BY IDventa";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sqlVentas);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta v = new Venta(
                    rs.getString("IDventa"),
                    rs.getString("fechaVenta"),
                    rs.getDouble("total"),
                    rs.getString("codigoUsuario") != null ? rs.getString("codigoUsuario") : "",
                    rs.getString("tipoComprobante") != null ? rs.getString("tipoComprobante") : "",
                    rs.getString("numComprobante") != null ? rs.getString("numComprobante") : "",
                    rs.getString("metodoPago") != null ? rs.getString("metodoPago") : "",
                    rs.getString("codigoCliente") != null ? rs.getString("codigoCliente") : ""
                );

                // Cargar los detalles de esta venta específica
                for (DetalleVenta d : leerDetallesPorVenta(rs.getString("IDventa"), con)) {
                    v.agregarDetalle(d);
                }
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lee los detalles de una venta específica reutilizando la conexión activa.
     * Separar esto en un método evita abrir múltiples conexiones simultáneas.
     */
    private ArrayList<DetalleVenta> leerDetallesPorVenta(String idVenta, Connection con) {
        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DetalleVenta WHERE IDventa = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new DetalleVenta(
                        rs.getString("codigoMedicamento"),
                        rs.getString("nombreMedicamento") != null ? rs.getString("nombreMedicamento") : "",
                        rs.getInt("cantidad"),
                        rs.getDouble("precioUnitario"),
                        rs.getDouble("subtotal"),
                        rs.getDouble("descuento")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al leer detalles de venta: " + e.getMessage());
        }
        return detalles;
    }

    /** Genera el siguiente ID de venta automáticamente (V0006, V0007...) */
    public String generarNuevoID() {
        String sql = "SELECT COUNT(*) AS total FROM Venta";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "V" + String.format("%04d", rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar ID de venta: " + e.getMessage());
        }
        return "V0001";
    }

    /**
     * Genera el siguiente número de comprobante secuencial.
     * Cuenta las ventas del mismo tipo de comprobante para calcular el correlativo.
     */
    public String generarNumComprobante(String tipoComprobante) {
        String sql = "SELECT COUNT(*) AS total FROM Venta WHERE tipoComprobante = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipoComprobante);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return String.format("%06d", rs.getInt("total") + 1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar número de comprobante: " + e.getMessage());
        }
        return "000001";
    }

    /**
     * Exporta las ventas filtradas a un archivo TXT con resumen estadístico.
     * Este método se mantiene igual ya que el exportador TXT sigue siendo válido.
     */
    public void exportarReporte(ArrayList<Venta> ventas, String rutaSalida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
            bw.write("====== REPORTE DE VENTAS - BOTICA ABISAI ======\n");
            double totalGeneral = 0;
            int totalUnidades = 0;
            java.util.HashMap<String, Integer> conteoMed = new java.util.HashMap<>();
            java.util.HashMap<String, Integer> conteoPago = new java.util.HashMap<>();

            for (Venta v : ventas) {
                bw.write(v.exportarTXT());
                bw.write("----------------------------\n");
                totalGeneral += v.getTotal();
                for (DetalleVenta d : v.getDetalles()) {
                    totalUnidades += d.getCantidad();
                    conteoMed.put(d.getNombreMedicamento(), conteoMed.getOrDefault(d.getNombreMedicamento(), 0) + d.getCantidad());
                }
                String mp = v.getMetodoPago();
                if (mp != null && !mp.isEmpty()) {
                    conteoPago.put(mp, conteoPago.getOrDefault(mp, 0) + 1);
                }
            }

            bw.write("TOTAL GENERAL: S/ " + String.format("%.2f", totalGeneral) + "\n\n");
            bw.write("====== RESUMEN ESTADÍSTICO DEL PERIODO ======\n");
            bw.write("Ventas Totales Realizadas: " + ventas.size() + "\n");
            bw.write("Unidades Totales Vendidas: " + totalUnidades + "\n");

            double ticketPromedio = ventas.isEmpty() ? 0 : totalGeneral / ventas.size();
            bw.write("Ticket Promedio de Venta: S/ " + String.format("%.2f", ticketPromedio) + "\n");

            String topMed = "-";
            int maxCant = 0;
            for (java.util.Map.Entry<String, Integer> entry : conteoMed.entrySet()) {
                if (entry.getValue() > maxCant) {
                    maxCant = entry.getValue();
                    topMed = entry.getKey() + " (" + maxCant + " unidades)";
                }
            }
            bw.write("Medicamento Estrella (Más Vendido): " + topMed + "\n");

            String topPago = "-";
            int maxPago = 0;
            for (java.util.Map.Entry<String, Integer> entry : conteoPago.entrySet()) {
                if (entry.getValue() > maxPago) {
                    maxPago = entry.getValue();
                    topPago = entry.getKey() + " (" + maxPago + " transacciones)";
                }
            }
            bw.write("Método de Pago más Utilizado: " + topPago + "\n");
            bw.write("=============================================\n");

        } catch (IOException e) {
            System.out.println("Error al exportar reporte: " + e.getMessage());
        }
    }
}
