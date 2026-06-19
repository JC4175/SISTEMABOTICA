/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */
//Esta clase tambien hereda de Persona
public class Proveedor extends Persona{
    
    private String Telefono;

    public Proveedor(String codigo, String nombre, String telefono) {
        super(codigo, nombre);
        this.Telefono = telefono;
    }

    public Proveedor() {
    }
    
    @Override
    public String getTipo() { return "Proveedor"; }
    
    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    public void registrar() {}
    public void listar() {}
    public void buscar() {}
    public void actualizar() {}
    
    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + Telefono;
    }
}
