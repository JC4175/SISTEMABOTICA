/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author zakkc
 */
public class Medicamento {
    private String codigo;
    private String nombre;
    private String laboratorio;
    private double precio;
    private int stock;
    private int stockMinimo;
    private String fechaVencimiento; // d/m/a
    private String idCategoria;
    private String idProveedor;
    
    //establecemos el formato de fecha que vamos a usar en el proyecto
    private static final DateTimeFormatter FORMATO =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //Constructor
    public Medicamento(String codigo, String nombre, String laboratorio, double precio, int stock, int stockMinimo, String fechaVencimiento, String idCategoria, String idProveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.laboratorio = laboratorio;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.fechaVencimiento = fechaVencimiento;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }
        
    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + laboratorio + "|" + precio + "|" +
               stock + "|" + stockMinimo + "|" + fechaVencimiento + "|" +
               idCategoria + "|" + idProveedor;
    }
    
    // -----------------------------METODOS PARA HCER VERIFICACIONES DE LOS MEDICAMENTOS---------------------
    //Verificar si un medicamento ya esta vencido usamos boolean para respuesta si o  no
    
    public boolean estaVencido() {
        LocalDate fechaVence = LocalDate.parse(fechaVencimiento, FORMATO);
        return LocalDate.now().isAfter(fechaVence);
    }
    
    // Verificamos si vence en 30 días o menos
    public boolean proximoVencer() {
        LocalDate fechaVence = LocalDate.parse(fechaVencimiento, FORMATO);
        long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), fechaVence); //ChronoUnit
        return diasRestantes >= 0 && diasRestantes <= 30;
    }
    
    // Reduce el stock al vender
    public boolean actualizarStock(int cantidad) {
        if (cantidad > stock) return false;
        this.stock -= cantidad;
        return true;
    }
    
    // Verifica si stock llegó al mínimo
    public boolean stockBajo() {
        return stock <= stockMinimo;
    }
}
