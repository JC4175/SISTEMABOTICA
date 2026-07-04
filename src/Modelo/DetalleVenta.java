/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */
public class DetalleVenta {
    private String codigoMedicamento;
    private String nombreMedicamento;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double descuento;

    public DetalleVenta(String codigoMedicamento, String nombreMedicamento, int cantidad, double precioUnitario, double subtotal, double descuento) {
        this.codigoMedicamento = codigoMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subtotal = (cantidad * precioUnitario) - descuento;
    }

    public DetalleVenta(String codigoMedicamento, String nombreMedicamento, int cantidad, double precioUnitario, double subtotal) {
        this(codigoMedicamento, nombreMedicamento, cantidad, precioUnitario, subtotal, 0.0);
    }

    public DetalleVenta(String codigo, String nombre, int cantidad, double preciouni) {
        this.codigoMedicamento = codigo;
        this.nombreMedicamento = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = preciouni;
        this.descuento = 0.0;
        this.subtotal = cantidad * preciouni;
    }

    public DetalleVenta() {
    }
    
    public void guardarVentas(java.util.ArrayList<Venta> lista) {
        // DESHABILITADO: VentaDatos.guardarTodos() fue eliminado al migrar a SQLite
        // new Datos.VentaDatos().guardarTodos(lista);
    }

    public String getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(String codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = (cantidad * precioUnitario) - descuento;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = (cantidad * precioUnitario) - descuento;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        this.subtotal = (cantidad * precioUnitario) - descuento;
    }
    
    @Override
    public String toString() {
        return codigoMedicamento + "|" + nombreMedicamento + "|" + 
               cantidad + "|" + precioUnitario + "|" + subtotal + "|" + descuento;
    }
}
