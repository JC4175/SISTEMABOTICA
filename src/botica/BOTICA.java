/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package botica;

import Datos.UsuarioDatos;
import Modelo.Usuario;
import Vista.login;

/**
 *
 * @author zakkc
 */
public class BOTICA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inicializar el tema moderno FlatLaf Light para todo el sistema
        try {
            com.formdev.flatlaf.FlatIntelliJLaf.setup();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el tema visual FlatLaf: " + e.getMessage());
        }

        // Crear el usuario administrador por defecto si no existe
        crearUsuarioDefecto();
        // Abrir la pantalla de Login
        java.awt.EventQueue.invokeLater(() -> { new Vista.login().setVisible(true);});
    }
    
    
    private static void crearUsuarioDefecto()
    {
        // 4. Ahora usamos las clases directamente gracias a los imports
        UsuarioDatos uda = new UsuarioDatos();
        if (!uda.existeCodigo("admin")) {
            Usuario admin = new Usuario(
                "admin", "Administrador Fabiana", "admin123", "Titular Gerente"
            );
            uda.agregar(admin);
        }
    }
    
    
}

