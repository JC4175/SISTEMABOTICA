/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */
public class Compra {
    
    private String IDcompra;
    private String fechaCompra;
    private double totalPagado;
    private String estadoOrden;
    private int cantidadComprada;
    private String idProveedor;
    private String codigoMedicamento;

    public Compra(String IDcompra, String fechaCompra, double totalPagado, String estadoOrden, int cantidadComprada, String idProveedor, String codigoMedicamento) {
        this.IDcompra = IDcompra;
        this.fechaCompra = fechaCompra;
        this.totalPagado = totalPagado;
        this.estadoOrden = estadoOrden;
        this.cantidadComprada = cantidadComprada;
        this.idProveedor = idProveedor;
        this.codigoMedicamento = codigoMedicamento;
    }

    public Compra() {
    }

    public void registrarCompra() {
        new Datos.CompraDatos().agregar(this);
    }

    public void recibirMercaderia() {
        this.estadoOrden = "Recibida";
        actualizarStockMedicamento();
        new Datos.CompraDatos().actualizar(this);
    }

    public void actualizarStockMedicamento() {
        Medicamento m = Medicamento.buscarPorID(this.codigoMedicamento);
        if (m != null) {
            m.setStock(m.getStock() + this.cantidadComprada);
            new Datos.MedicamentoDatos().actualizar(m);
        }
    }

    // Getters and Setters
    public String getIDcompra() {
        return IDcompra;
    }

    public void setIDcompra(String IDcompra) {
        this.IDcompra = IDcompra;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(int cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(String codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
    }

    @Override
    public String toString() {
        return IDcompra + "|" + fechaCompra + "|" + totalPagado + "|" + estadoOrden + "|" + cantidadComprada + "|" + idProveedor + "|" + codigoMedicamento;
    }
}
