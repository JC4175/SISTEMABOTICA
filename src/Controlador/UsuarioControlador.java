/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.UsuarioDatos;
import Modelo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class UsuarioControlador {
    private UsuarioDatos dao = new UsuarioDatos();

    public Usuario login(String codigo, String contraseña) {
        return dao.validarLogin(codigo, contraseña);
    }

    public String registrar(String codigo, String nombre, 
                            String contraseña, String rol) {
        if (codigo.isEmpty() || nombre.isEmpty() || 
            contraseña.isEmpty() || rol.isEmpty())
            return "Todos los campos son obligatorios.";

        if (dao.existeCodigo(codigo))
            return "Ya existe un usuario con ese código.";

        dao.agregar(new Usuario(codigo, nombre, contraseña, rol));
        return "ok";
    }

    public String actualizar(String codigo, String nombre, 
                             String contraseña, String rol) {
        if (codigo.isEmpty() || nombre.isEmpty() || 
            contraseña.isEmpty() || rol.isEmpty())
            return "Todos los campos son obligatorios.";

        if (!dao.existeCodigo(codigo))
            return "El usuario no existe.";

        dao.actualizar(new Usuario(codigo, nombre, contraseña, rol));
        return "ok";
    }

    public String eliminar(String codigo) {
        if (!dao.existeCodigo(codigo))
            return "El usuario no existe.";

        dao.eliminar(codigo);
        return "ok";
    }

    public ArrayList<Usuario> listarTodos() {
        return dao.leerTodos();
    }
}
