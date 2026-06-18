/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.CategoriaDatos;
import Modelo.Categoria;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class CategoriaControlador {
    private CategoriaDatos ct = new CategoriaDatos();

    public String registrar(String id, String nombre) {
        if (id.isEmpty() || nombre.isEmpty())
            return "Todos los campos son obligatorios.";

        if (ct.existeCodigo(id))
            return "Ya existe una categoría con ese ID.";

        ct.agregar(new Categoria(id, nombre));
        return "ok";
    }

    public String eliminar(String id) {
        if (!ct.existeCodigo(id))
            return "La categoría no existe.";

        ct.eliminar(id);
        return "ok";
    }

    public ArrayList<Categoria> listarTodos() {
        return ct.leerTodos();
    }

    // Retorna solo los nombres para cargar en JComboBox
    public ArrayList<String> listarNombres() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Categoria c : ct.leerTodos()) {
            nombres.add(c.getNombre());
        }
        return nombres;
    }
}
