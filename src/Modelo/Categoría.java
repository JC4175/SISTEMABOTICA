/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */
public class Categoría {
    private String IDcategoria;
    private String nombre;

    public Categoría(String IDcategoria, String nombre) {
        this.IDcategoria = IDcategoria;
        this.nombre = nombre;
    }

    public String getIDcategoria() {
        return IDcategoria;
    }

    public void setIDcategoria(String IDcategoria) {
        this.IDcategoria = IDcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return IDcategoria + "|" + nombre;
    }
    
    public void registrar() {}
    public void listar() {}
}
