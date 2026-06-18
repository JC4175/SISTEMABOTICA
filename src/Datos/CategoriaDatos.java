/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import Modelo.Categoria;
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
public class CategoriaDatos {
    private static final String ARCHIVO = "categorias.txt";

    public void guardarTodos(ArrayList<Categoria> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Categoria c : lista) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar categorías: " + e.getMessage());
        }
    }

    public ArrayList<Categoria> leerTodos() {
        ArrayList<Categoria> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    lista.add(new Categoria(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer categorías: " + e.getMessage());
        }
        return lista;
    }

    public void agregar(Categoria c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(c.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar categoría: " + e.getMessage());
        }
    }

    public boolean existeCodigo(String id) {
        for (Categoria c : leerTodos()) {
            if (c.getIDcategoria().equals(id)) return true;
        }
        return false;
    }

    public void eliminar(String id) {
        ArrayList<Categoria> lista = leerTodos();
        lista.removeIf(c -> c.getIDcategoria().equals(id));
        guardarTodos(lista);
    }

    
}
