/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */
public class Cliente extends Persona {
    
    private boolean tieneAlergia;
    private String detalleAlergia;
    private String seguroMedico;

    public Cliente(String codigo, String nombre, String apellido, String dni, String correo, boolean tieneAlergia, String detalleAlergia, String seguroMedico) {
        super(codigo, nombre, apellido, dni, correo);
        this.tieneAlergia = tieneAlergia;
        this.detalleAlergia = detalleAlergia;
        this.seguroMedico = seguroMedico;
    }

    public Cliente() {
        super();
    }

    public boolean verificarAlergia() {
        return this.tieneAlergia;
    }

    public String evaluarEstadoAlergia() {
        return this.tieneAlergia ? "Alergias: " + this.detalleAlergia : "No presenta alergias conocidas.";
    }

    public boolean validarSeguro() {
        return this.seguroMedico != null && !this.seguroMedico.trim().isEmpty() && !this.seguroMedico.equalsIgnoreCase("Ninguno");
    }

    @Override
    public String getTipo() {
        return "Cliente";
    }

    // Getters and Setters
    public boolean isTieneAlergia() {
        return tieneAlergia;
    }

    public void setTieneAlergia(boolean tieneAlergia) {
        this.tieneAlergia = tieneAlergia;
    }

    public String getDetalleAlergia() {
        return detalleAlergia;
    }

    public void setDetalleAlergia(String detalleAlergia) {
        this.detalleAlergia = detalleAlergia;
    }

    public String getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(String seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + apellido + "|" + dni + "|" + correo + "|" + tieneAlergia + "|" + detalleAlergia + "|" + seguroMedico;
    }
}
