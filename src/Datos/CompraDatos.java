/*
 * CompraDatos.java - Capa de acceso a datos para Compra
 * Reemplaza compras.txt por consultas SQL a SQLite
 */
package Datos;

import Modelo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompraDatos {

    /** Inserta una nueva orden de compra en la base de datos */
    public void agregar(Compra c) {
        String sql = "INSERT INTO Compra (IDcompra, fechaCompra, totalPagado, estadoOrden, cantidadComprada, codigoProveedor, codigoMedicamento) " +
                     "VALUES (?,?,?,?,?,?,?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getIDcompra());
            ps.setString(2, c.getFechaCompra());
            ps.setDouble(3, c.getTotalPagado());
            ps.setString(4, c.getEstadoOrden());
            ps.setInt(5, c.getCantidadComprada());
            ps.setString(6, c.getIdProveedor());
            ps.setString(7, c.getCodigoMedicamento());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al registrar compra: " + e.getMessage());
        }
    }

    /** Retorna todas las órdenes de compra registradas */
    public ArrayList<Compra> leerTodos() {
        ArrayList<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM Compra";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Compra(
                    rs.getString("IDcompra"),
                    rs.getString("fechaCompra"),
                    rs.getDouble("totalPagado"),
                    rs.getString("estadoOrden"),
                    rs.getInt("cantidadComprada"),
                    rs.getString("codigoProveedor") != null ? rs.getString("codigoProveedor") : "",
                    rs.getString("codigoMedicamento") != null ? rs.getString("codigoMedicamento") : ""
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar compras: " + e.getMessage());
        }
        return lista;
    }

    /** Busca una compra por su ID único */
    public Compra buscarPorID(String idCompra) {
        String sql = "SELECT * FROM Compra WHERE IDcompra = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idCompra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Compra(
                        rs.getString("IDcompra"),
                        rs.getString("fechaCompra"),
                        rs.getDouble("totalPagado"),
                        rs.getString("estadoOrden"),
                        rs.getInt("cantidadComprada"),
                        rs.getString("codigoProveedor") != null ? rs.getString("codigoProveedor") : "",
                        rs.getString("codigoMedicamento") != null ? rs.getString("codigoMedicamento") : ""
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar compra: " + e.getMessage());
        }
        return null;
    }

    /** Verifica si ya existe una compra con ese ID */
    public boolean existeCodigo(String idCompra) {
        return buscarPorID(idCompra) != null;
    }

    /** Actualiza el estado y datos de una compra (especialmente para marcarla como Recibida) */
    public void actualizar(Compra c) {
        String sql = "UPDATE Compra SET fechaCompra=?, totalPagado=?, estadoOrden=?, cantidadComprada=?, " +
                     "codigoProveedor=?, codigoMedicamento=? WHERE IDcompra=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getFechaCompra());
            ps.setDouble(2, c.getTotalPagado());
            ps.setString(3, c.getEstadoOrden());
            ps.setInt(4, c.getCantidadComprada());
            ps.setString(5, c.getIdProveedor());
            ps.setString(6, c.getCodigoMedicamento());
            ps.setString(7, c.getIDcompra());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar compra: " + e.getMessage());
        }
    }

    /** Elimina una compra por su ID */
    public void eliminar(String idCompra) {
        String sql = "DELETE FROM Compra WHERE IDcompra = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idCompra);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar compra: " + e.getMessage());
        }
    }

    /** Genera el siguiente ID de compra automáticamente (CO006, CO007...) */
    public String generarNuevoID() {
        String sql = "SELECT COUNT(*) AS total FROM Compra";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "CO" + String.format("%03d", rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar ID de compra: " + e.getMessage());
        }
        return "CO001";
    }
}
