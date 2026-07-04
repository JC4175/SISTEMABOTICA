/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package botica;



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
            com.formdev.flatlaf.FlatLightLaf.setup();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el tema visual FlatLaf: " + e.getMessage());
        }

        // Inicializar la base de datos SQLite:
        // Si botica.db no existe, la crea y carga todas las tablas y datos de prueba automáticamente
        // Si ya existe, verifica que las tablas estén presentes y asi no duplicar datos
        Datos.InicializadorBD.inicializar();

        // Abrir la pantalla de Login
        java.awt.EventQueue.invokeLater(() -> { new Vista.login().setVisible(true); });
    }
    
    
}

