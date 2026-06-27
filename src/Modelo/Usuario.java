/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */

//Esta Clase hereda de Persona y representa a los operadores del sistema
public class Usuario extends Persona {
    
    // Nuevos campos UML
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private boolean estado; // Indica si el usuario está activo o suspendido

    // Constructor completo con todos los nuevos atributos del diagrama UML
    public Usuario(String codigo, String nombre, String apellido, String dni, String correo, String nombreUsuario, String contrasena, String rol, boolean estado) {
        super(codigo, nombre, apellido, dni, correo);
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = estado;
    }

    // Constructor heredado para compatibilidad hacia atrás con el controlador de login original
    public Usuario(String codigo, String nombre, String contrasena, String rol) {
        super(codigo, nombre, "", "", "");
        this.nombreUsuario = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estado = true; // Activo por defecto
    }

    public Usuario() {}
    
    // Valida el acceso según el estado del usuario (retorna verdadero si está activo)
    public boolean validarAcceso() {
        return this.estado;
    }
    
    // Método sobrecargado para compatibilidad con la verificación de credenciales del Login
    public boolean validarAcceso(String codigo, String contraseña) {
        return this.codigo.equals(codigo) && this.contrasena.equals(contraseña);
    }
    
    @Override
    public String getTipo() { return "Usuario"; }
    
    //****************************************************
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    // Genera la representación en formato delimitado por pipe para almacenamiento plano
    @Override
    public String toString() {
        return codigo + "|" + nombre + "|" + apellido + "|" + dni + "|" + correo + "|" + nombreUsuario + "|" + contrasena + "|" + rol + "|" + estado;
    }
}
