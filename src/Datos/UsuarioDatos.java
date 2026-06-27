/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelo.Usuario;
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
public class UsuarioDatos {
    
    private static final String ARCHIVO = "usuarios.txt";

    public void guardarTodos(ArrayList<Usuario> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : lista) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> leerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 9) {
                        lista.add(new Usuario(
                            datos[0], // codigo
                            datos[1], // nombre
                            datos[2], // apellido
                            datos[3], // dni
                            datos[4], // correo
                            datos[5], // nombreUsuario
                            datos[6], // contrasena
                            datos[7], // rol
                            Boolean.parseBoolean(datos[8]) // estado
                        ));
                    } else if (datos.length >= 4) {
                        lista.add(new Usuario(datos[0], datos[1], datos[2], datos[3]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }
        return lista;
    }

    public void agregar(Usuario u) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(u.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    // Valida login — retorna el Usuario si es correcto, null si no
    public Usuario validarLogin(String codigo, String contraseña) {
        for (Usuario u : leerTodos()) {
            if (u.validarAcceso(codigo, contraseña)) return u;
        }
        return null;
    }

    public boolean existeCodigo(String codigo) {
        for (Usuario u : leerTodos()) {
            if (u.getCodigo().equals(codigo)) return true;
        }
        return false;
    }

    public void eliminar(String codigo) {
        ArrayList<Usuario> lista = leerTodos();
        lista.removeIf(u -> u.getCodigo().equals(codigo));
        guardarTodos(lista);
    }

    public void actualizar(Usuario actualizado) {
        ArrayList<Usuario> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(actualizado.getCodigo())) {
                lista.set(i, actualizado);
                break;
            }
        }
        guardarTodos(lista);
    }
}

