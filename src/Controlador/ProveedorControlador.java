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

    public String registrar(String codigo, String nombre, String telefono) {
        if (codigo.isEmpty() || nombre.isEmpty() || telefono.isEmpty())
            return "Todos los campos son obligatorios.";

        if (dao.existeCodigo(codigo))
            return "Ya existe un proveedor con ese código.";

        dao.agregar(new Proveedor(codigo, nombre, telefono));
        return "ok";
    }

    public String actualizar(String codigo, String nombre, String telefono) {
        if (codigo.isEmpty() || nombre.isEmpty() || telefono.isEmpty())
            return "Todos los campos son obligatorios.";

        if (!dao.existeCodigo(codigo))
            return "El proveedor no existe.";

        dao.actualizar(new Proveedor(codigo, nombre, telefono));
        return "ok";
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
        return dao.leerTodos();
    }
}
