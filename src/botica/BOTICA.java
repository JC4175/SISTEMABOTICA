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
        // TODO code application logic here
        //Crear el usuario administrador por defecto
        crearUsuarioDefecto();
        // 2. Abrir la pantalla de Login una sola vez de forma correcta
        java.awt.EventQueue.invokeLater(() -> { new Vista.login().setVisible(true);});
        /*login loginn = new login();
        loginn.setVisible(true);*/
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

