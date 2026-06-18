/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelo.Medicamento;
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
public class MedicamentoDatos {
    
    private static final String ARCHIVO = "medicamentos.txt";
    
    // Guarda toda la lista (sobreescribe el archivo)
    public void guardarTodos(ArrayList<Medicamento> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Medicamento m : lista) {
                bw.write(m.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar medicamentos: " + e.getMessage());
        }
    }

    // Lee todos los medicamentos del TXT
    public ArrayList<Medicamento> leerTodos() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    Medicamento m = new Medicamento(
                        datos[0], datos[1], datos[2],
                        Double.parseDouble(datos[3]),
                        Integer.parseInt(datos[4]),
                        Integer.parseInt(datos[5]),
                        datos[6], datos[7], datos[8]
                    );
                    lista.add(m);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer medicamentos: " + e.getMessage());
        }
        return lista;
    }

    // Agrega un medicamento nuevo al archivo
    public void agregar(Medicamento m) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(m.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar medicamento: " + e.getMessage());
        }
    }

    // Busca por código
    public Medicamento buscarPorCodigo(String codigo) {
        for (Medicamento m : leerTodos()) {
            if (m.getCodigo().equals(codigo)) return m;
        }
        return null;
    }

    // Verifica si el código ya existe
    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    // Elimina por código
    public void eliminar(String codigo) {
        ArrayList<Medicamento> lista = leerTodos();
        lista.removeIf(m -> m.getCodigo().equals(codigo));
        guardarTodos(lista);
    }

    // Actualiza un medicamento existente
    public void actualizar(Medicamento actualizado) {
        ArrayList<Medicamento> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(actualizado.getCodigo())) {
                lista.set(i, actualizado);
                break;
            }
        }
        guardarTodos(lista);
    }

    // Retorna medicamentos próximos a vencer
    public ArrayList<Medicamento> obtenerProximosVencer() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        for (Medicamento m : leerTodos()) {
            if (m.proximoVencer()) lista.add(m);
        }
        return lista;
    }

    // Retorna medicamentos con stock bajo
    public ArrayList<Medicamento> obtenerStockBajo() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        for (Medicamento m : leerTodos()) {
            if (m.stockBajo()) lista.add(m);
        }
        return lista;
    }
}

