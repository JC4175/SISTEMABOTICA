/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */

//Esta Clase hereda de Persona !
public class Usuario extends Persona {
    
    private String rol;
    private String contrasena;

    public Usuario(String rol, String contrasena, String codigo, String nombre) {
        super(codigo, nombre);
        this.rol = rol;
        this.contrasena = contrasena;
    }

    public Usuario() {}
    
    public boolean validarAcceso(String codigo, String contraseña) {
        return this.codigo.equals(codigo) && this.contrasena.equals(contraseña);
    }
    
    @Override
    public String getTipo() { return "Usuario"; }
    
    //****************************************************
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
        
    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + contrasena + "|" + rol;
    }
}
