/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;

/**
 *
 * @author zakkc
 */
public class SesionControlador {
    private static Usuario usuarioActivo;

    public static void iniciarSesion(Usuario u) {
        usuarioActivo = u;
    }

    public static void cerrarSesion() {
        usuarioActivo = null;
    }

    public static Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public static String getRolActivo() {
        if (usuarioActivo == null) return "";
        return usuarioActivo.getRol();
    }

    public static boolean esTitularGerente() {
        return "Titular Gerente".equals(getRolActivo());
    }

    public static boolean esDirectorTecnico() {
        return "Director Técnico".equals(getRolActivo());
    }

    public static boolean esTecnicoFarmacia() {
        return "Técnico en Farmacia".equals(getRolActivo());
    }

    // Verifica si el usuario activo tiene permiso para una acción
    public static boolean tienePermiso(String rolRequerido) {
        return getRolActivo().equals(rolRequerido) || esTitularGerente();
    }

    public static boolean haySesionActiva() {
        return usuarioActivo != null;
    }

}
