/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Modelo.Compra;
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
public class CompraDatos {
    
    private static final String ARCHIVO = "compras.txt";

    public void guardarTodos(ArrayList<Compra> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Compra c : lista) {
                bw.write(c.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar compras: " + e.getMessage());
        }
    }

    public ArrayList<Compra> leerTodos() {
        ArrayList<Compra> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split("\\|");
                    if (datos.length >= 7) {
                        lista.add(new Compra(
                            datos[0], // IDcompra
                            datos[1], // fechaCompra
                            Double.parseDouble(datos[2]), // totalPagado
                            datos[3], // estadoOrden
                            Integer.parseInt(datos[4]), // cantidadComprada
                            datos[5], // idProveedor
                            datos[6]  // codigoMedicamento
                        ));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer compras: " + e.getMessage());
        }
        return lista;
    }

    public void agregar(Compra c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            bw.write(c.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al agregar compra: " + e.getMessage());
        }
    }

    public Compra buscarPorID(String idCompra) {
        for (Compra c : leerTodos()) {
            if (c.getIDcompra().equals(idCompra)) return c;
        }
        return null;
    }

    public boolean existeCodigo(String idCompra) {
        return buscarPorID(idCompra) != null;
    }

    public void eliminar(String idCompra) {
        ArrayList<Compra> lista = leerTodos();
        lista.removeIf(c -> c.getIDcompra().equals(idCompra));
        guardarTodos(lista);
    }

    public void actualizar(Compra actualizado) {
        ArrayList<Compra> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIDcompra().equals(actualizado.getIDcompra())) {
                lista.set(i, actualizado);
                break;
            }
        }
        guardarTodos(lista);
    }
}
