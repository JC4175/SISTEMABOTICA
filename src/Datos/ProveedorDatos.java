/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelo.Proveedor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class ProveedorDatos {
    private static final String ARCHIVO = "proveedores.txt";

    public void guardarTodos(ArrayList<Proveedor> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Proveedor p : lista) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar proveedores: " + e.getMessage());
        }
    }

    public ArrayList<Proveedor> leerTodos() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 6) {
                        lista.add(new Proveedor(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]));
                    } else if (datos.length >= 3) {
                        lista.add(new Proveedor(datos[0], datos[1], datos[2]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer proveedores: " + e.getMessage());
        }
        return lista;
    }

    public void agregar(Proveedor p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(p.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    public Proveedor buscarPorCodigo(String codigo) {
        for (Proveedor p : leerTodos()) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    public void eliminar(String codigo) {
        ArrayList<Proveedor> lista = leerTodos();
        lista.removeIf(p -> p.getCodigo().equals(codigo));
        guardarTodos(lista);
    }

    public void actualizar(Proveedor actualizado) {
        ArrayList<Proveedor> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(actualizado.getCodigo())) {
                lista.set(i, actualizado);
                break;
            }
        }
        guardarTodos(lista);
    }
}
