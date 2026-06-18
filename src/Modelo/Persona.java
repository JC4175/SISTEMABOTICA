/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */

//clase abstracta
public abstract class Persona {
    
    protected String codigo;
    protected String nombre;

    public Persona(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public Persona ()
    {
        
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
    
    // Método abstracto que cada hijo implementa
    public abstract String getTipo();
    
}
