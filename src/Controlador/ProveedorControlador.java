/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.ProveedorDatos;
import Modelo.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class ProveedorControlador {
    private ProveedorDatos dao = new ProveedorDatos();

    public String registrar(String codigo, String razonSocial, String telefono, String empresa, String direccion, String ruc) {
        if (codigo.isEmpty() || razonSocial.isEmpty() || telefono.isEmpty() || empresa.isEmpty() || direccion.isEmpty() || ruc.isEmpty())
            return "Todos los campos son obligatorios.";

        if (dao.existeCodigo(codigo))
            return "Ya existe un proveedor con ese código.";

        Proveedor p = new Proveedor(codigo, razonSocial, telefono, empresa, direccion, ruc);
        try {
            p.setRuc(ruc);
            p.setTelefono(telefono);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        dao.agregar(p);
        return "ok";
    }

    public String registrar(String codigo, String nombre, String telefono) {
        return registrar(codigo, nombre, telefono, nombre, "", codigo);
    }

    public String actualizar(String codigo, String razonSocial, String telefono, String empresa, String direccion, String ruc) {
        if (codigo.isEmpty() || razonSocial.isEmpty() || telefono.isEmpty() || empresa.isEmpty() || direccion.isEmpty() || ruc.isEmpty())
            return "Todos los campos son obligatorios.";

        if (!dao.existeCodigo(codigo))
            return "El proveedor no existe.";

        Proveedor p = new Proveedor(codigo, razonSocial, telefono, empresa, direccion, ruc);
        try {
            p.setRuc(ruc);
            p.setTelefono(telefono);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        dao.actualizar(p);
        return "ok";
    }

    public String actualizar(String codigo, String nombre, String telefono) {
        return actualizar(codigo, nombre, telefono, nombre, "", codigo);
    }

    public String eliminar(String codigo) {
        if (!dao.existeCodigo(codigo))
            return "El proveedor no existe.";

        dao.eliminar(codigo);
        return "ok";
    }

    public Proveedor buscarPorCodigo(String codigo) {
        return dao.buscarPorCodigo(codigo);
    }

    public ArrayList<Proveedor> listarTodos() {
        return dao.listarTodos();
    }
}
