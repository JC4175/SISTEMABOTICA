/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class Venta {
    private String IDVenta;
    private String fechaVenta;
    private double total;
    private String codigoUsuario;
    private ArrayList<DetalleVenta> detalles; //lista para guardar el registro de cada venta
    
    //formato de fecha a usar 
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Venta(String IDVenta, String fechaVenta) {
        this.IDVenta = IDVenta;
        this.fechaVenta = LocalDate.now().format(FORMATO);
        this.total = total;
        this.codigoUsuario = codigoUsuario;
        this.detalles = new ArrayList<>();
    }
    
    public Venta() {
        this.detalles = new ArrayList<>();
    }

    public String getIDVenta() {
        return IDVenta;
    }

    public void setIDVenta(String IDVenta) {
        this.IDVenta = IDVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
        
    
    
    
    
    // Agrega un detalle y recalcula el total
    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        calcularTotal();
    }
    
    
    // Elimina un detalle y recalcula
    public void eliminarDetalle(int indice) {
        detalles.remove(indice);
        calcularTotal();
    }

    // Recalcula el total sumando subtotales
    public void calcularTotal() {
        total = 0;
        for (DetalleVenta d : detalles) {
            total += d.getSubtotal();
        }
    }

    // Genera línea para exportar al TXT
    public String exportarTXT() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== VENTA ===\n");
        sb.append("ID: ").append(IDVenta).append("\n");
        sb.append("Fecha: ").append(fechaVenta).append("\n");
        sb.append("Usuario: ").append(codigoUsuario).append("\n");
        for (DetalleVenta d : detalles) {
            sb.append(d.toString()).append("\n");
        }
        sb.append("Total: S/ ").append(String.format("%.2f", total)).append("\n");
        return sb.toString();
    }
    
    
    
    @Override
    public String toString() {
        return IDVenta + "|" + fechaVenta + "|" + 
               String.format("%.2f", total) + "|" + codigoUsuario;
    }
}
