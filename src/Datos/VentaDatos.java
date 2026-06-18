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

    // Lee todas las ventas (solo cabeceras)
    public ArrayList<Venta> leerTodas() {
        ArrayList<Venta> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_VENTAS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_VENTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    Venta v = new Venta(datos[0], datos[3]);
                    v.setFechaVenta(datos[1]);
                    lista.add(v);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer ventas: " + e.getMessage());
        }
        return lista;
    }

    // Genera ID único para nueva venta
    public String generarNuevoID() {
        ArrayList<Venta> lista = leerTodas();
        return "V" + String.format("%04d", lista.size() + 1);
    }

    // Exporta reporte de ventas a TXT
    public void exportarReporte(ArrayList<Venta> ventas, String rutaSalida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida))) {
            bw.write("====== REPORTE DE VENTAS - BOTICA ABISAI ======\n");
            double totalGeneral = 0;
            for (Venta v : ventas) {
                bw.write(v.exportarTXT());
                bw.write("----------------------------\n");
                totalGeneral += v.getTotal();
            }
            bw.write("TOTAL GENERAL: S/ " + String.format("%.2f", totalGeneral) + "\n");
        } catch (IOException e) {
            System.out.println("Error al exportar reporte: " + e.getMessage());
        }
    }
}
