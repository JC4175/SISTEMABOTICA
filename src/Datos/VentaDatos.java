/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelo.DetalleVenta;
import Modelo.Venta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class VentaDatos {
    private static final String ARCHIVO_VENTAS   = "ventas.txt";
    private static final String ARCHIVO_DETALLES = "detalles.txt";

    // Guarda cabecera de venta
    public void guardarVenta(Venta v) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS, true))) {
            bw.write(v.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar venta: " + e.getMessage());
        }
    }

    // Guarda los detalles de una venta
    public void guardarDetalles(Venta v) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_DETALLES, true))) {
            for (DetalleVenta d : v.getDetalles()) {
                bw.write(v.getIDVenta() + "|" + d.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar detalles: " + e.getMessage());
        }
    }

    // Guarda venta completa (cabecera + detalles)
    public void registrarVentaCompleta(Venta v) {
        guardarVenta(v);
        guardarDetalles(v);
    }

    // Sobreescribe todos los datos (útil para edición/eliminación de ventas)
    public void guardarTodos(ArrayList<Venta> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_VENTAS))) {
            for (Venta v : lista) {
                bw.write(v.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar todas las ventas: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_DETALLES))) {
            for (Venta v : lista) {
                for (DetalleVenta d : v.getDetalles()) {
                    bw.write(v.getIDVenta() + "|" + d.toString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar todos los detalles: " + e.getMessage());
        }
    }

    // Lee todas las ventas (cabeceras + detalles)
    public ArrayList<Venta> leerTodas() {
        ArrayList<Venta> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_VENTAS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VENTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 8) {
                        Venta v = new Venta(
                            datos[0], // IDVenta
                            datos[1], // fechaVenta
                            Double.parseDouble(datos[2]), // total
                            datos[3], // codigoUsuario
                            datos[4], // tipoComprobante
                            datos[5], // numComprobante
                            datos[6], // metodoPago
                            datos[7]  // codigoCliente
                        );
                        ArrayList<DetalleVenta> detalles = leerDetallesPorVenta(datos[0]);
                        for (DetalleVenta d : detalles) {
                            v.agregarDetalle(d);
                        }
                        v.calcularTotal();
                        lista.add(v);
                    } else if (datos.length >= 4) {
                        Venta v = new Venta(datos[0], datos[3]);
                        v.setFechaVenta(datos[1]);
                        v.setTotal(Double.parseDouble(datos[2]));
                        ArrayList<DetalleVenta> detalles = leerDetallesPorVenta(datos[0]);
                        for (DetalleVenta d : detalles) {
                            v.agregarDetalle(d);
                        }
                        lista.add(v);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer ventas: " + e.getMessage());
        }
        return lista;
    }

    // Lee los detalles de una venta específica
    private ArrayList<DetalleVenta> leerDetallesPorVenta(String idVenta) {
        ArrayList<DetalleVenta> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_DETALLES);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_DETALLES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 7 && datos[0].equals(idVenta)) {
                        DetalleVenta d = new DetalleVenta(
                            datos[1], // codigoMedicamento
                            datos[2], // nombreMedicamento
                            Integer.parseInt(datos[3]), // cantidad
                            Double.parseDouble(datos[4]), // precioUnitario
                            Double.parseDouble(datos[5]), // subtotal
                            Double.parseDouble(datos[6])  // descuento
                        );
                        lista.add(d);
                    } else if (datos.length >= 6 && datos[0].equals(idVenta)) {
                        DetalleVenta d = new DetalleVenta(
                            datos[1],
                            datos[2],
                            Integer.parseInt(datos[3]),
                            Double.parseDouble(datos[4]),
                            Double.parseDouble(datos[5]),
                            0.0
                        );
                        lista.add(d);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer detalles: " + e.getMessage());
        }
        return lista;
    }

    // Genera ID único para nueva venta
    public String generarNuevoID() {
        ArrayList<Venta> lista = leerTodas();
        return "V" + String.format("%04d", lista.size() + 1);
    }

    // Exporta reporte de ventas a TXT con resumen estadístico del periodo
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

            // Escribir resumen estadístico del periodo
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
