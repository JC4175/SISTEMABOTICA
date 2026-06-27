/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelo.Cliente;
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
public class ClienteDatos {
    
    private static final String ARCHIVO = "clientes.txt";

    public void guardarTodos(ArrayList<Cliente> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Cliente c : lista) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> leerTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 8) {
                        lista.add(new Cliente(
                            datos[0], // codigo
                            datos[1], // nombre
                            datos[2], // apellido
                            datos[3], // dni
                            datos[4], // correo
                            Boolean.parseBoolean(datos[5]), // tieneAlergia
                            datos[6], // detalleAlergia
                            datos[7]  // seguroMedico
                        ));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer clientes: " + e.getMessage());
        }
        return lista;
    }

    public void agregar(Cliente c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(c.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    public Cliente buscarPorCodigo(String codigo) {
        for (Cliente c : leerTodos()) {
            if (c.getCodigo().equals(codigo)) return c;
        }
        return null;
    }

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    public void eliminar(String codigo) {
        ArrayList<Cliente> lista = leerTodos();
        lista.removeIf(c -> c.getCodigo().equals(codigo));
        guardarTodos(lista);
    }

    public void actualizar(Cliente actualizado) {
        ArrayList<Cliente> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(actualizado.getCodigo())) {
                lista.set(i, actualizado);
                break;
            }
        }
        guardarTodos(lista);
    }
}
