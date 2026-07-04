/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.ClienteDatos;
import Modelo.Cliente;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class ClienteControlador {
    private ClienteDatos dao = new ClienteDatos();

    public String registrar(String codigo, String nombre, String apellido, String dni, String correo, boolean tieneAlergia, String detalleAlergia, String seguroMedico) {
        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty()) {
            return "Código, Nombre, Apellido, DNI y Correo son obligatorios.";
        }

        if (dao.existeCodigo(codigo)) {
            return "Ya existe un cliente con ese código.";
        }

        Cliente cli = new Cliente(codigo, nombre, apellido, dni, correo, tieneAlergia, detalleAlergia, seguroMedico);
        try {
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setDni(dni);
            cli.setCorreo(correo);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        dao.agregar(cli);
        return "ok";
    }

    public String actualizar(String codigo, String nombre, String apellido, String dni, String correo, boolean tieneAlergia, String detalleAlergia, String seguroMedico) {
        if (codigo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty()) {
            return "Código, Nombre, Apellido, DNI y Correo son obligatorios.";
        }

        if (!dao.existeCodigo(codigo)) {
            return "El cliente no existe.";
        }

        Cliente cli = new Cliente(codigo, nombre, apellido, dni, correo, tieneAlergia, detalleAlergia, seguroMedico);
        try {
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setDni(dni);
            cli.setCorreo(correo);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        dao.actualizar(cli);
        return "ok";
    }

    public String eliminar(String codigo) {
        if (!dao.existeCodigo(codigo)) {
            return "El cliente no existe.";
        }

        dao.eliminar(codigo);
        return "ok";
    }

    public Cliente buscarPorCodigo(String codigo) {
        return dao.buscarPorCodigo(codigo);
    }

    public ArrayList<Cliente> listarTodos() {
        return dao.listarTodos();
    }
}
