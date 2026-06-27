/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.MedicamentoDatos;
import Modelo.Medicamento;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class MedicamentoControlador {
    
    private MedicamentoDatos dao =  new MedicamentoDatos();
    
    public String registrar(String codigo, String nombre, String laboratorio,
                            String precio, String stock, String stockMin,
                            String fechaVenc, String idCat, String idProv,
                            boolean requiereReceta, String presentacion) 
    {
        if (codigo.isEmpty() || nombre.isEmpty() || laboratorio.isEmpty() ||
            precio.isEmpty() || stock.isEmpty() || stockMin.isEmpty() ||
            fechaVenc.isEmpty() || idCat.isEmpty() || idProv.isEmpty() || presentacion.isEmpty())
            return "Todos los campos son obligatorios.";

        if (dao.existeCodigo(codigo))
            return "Ya existe un medicamento con ese código.";

        if (!new Datos.ProveedorDatos().existeCodigo(idProv))
            return "El código de proveedor '" + idProv + "' no existe.";

        try {
            double p = Double.parseDouble(precio);
            int s = Integer.parseInt(stock);
            int sm = Integer.parseInt(stockMin);

            if (p <= 0) return "El precio debe ser mayor a 0.";
            if (s < 0)  return "El stock no puede ser negativo.";
            if (sm < 0) return "El stock mínimo no puede ser negativo.";

            Medicamento m = new Medicamento(codigo, nombre, laboratorio,
                                            p, s, sm, fechaVenc, idCat, idProv, requiereReceta, presentacion);

            if (m.estaVencido())
                return "No se puede registrar un medicamento ya vencido.";

            dao.agregar(m);
            return "ok";

        } catch (NumberFormatException e) {
            return "Precio, stock y stock mínimo deben ser números válidos.";
        }
    }

    public String registrar(String codigo, String nombre, String laboratorio,
                            String precio, String stock, String stockMin,
                            String fechaVenc, String idCat, String idProv) 
    {
        return registrar(codigo, nombre, laboratorio, precio, stock, stockMin, fechaVenc, idCat, idProv, false, "General");
    }
    
    public String actualizar(String codigo, String nombre, String laboratorio,
                             String precio, String stock, String stockMin,
                             String fechaVenc, String idCat, String idProv,
                             boolean requiereReceta, String presentacion) {
        if (!dao.existeCodigo(codigo))
            return "El medicamento no existe.";

        if (!new Datos.ProveedorDatos().existeCodigo(idProv))
            return "El código de proveedor '" + idProv + "' no existe.";

        try {
            double p = Double.parseDouble(precio);
            int s = Integer.parseInt(stock);
            int sm = Integer.parseInt(stockMin);

            dao.actualizar(new Medicamento(codigo, nombre, laboratorio,
                                           p, s, sm, fechaVenc, idCat, idProv, requiereReceta, presentacion));
            return "ok";

        } catch (NumberFormatException e) {
            return "Precio, stock y stock mínimo deben ser números válidos.";
        }
    }

    public String actualizar(String codigo, String nombre, String laboratorio,
                             String precio, String stock, String stockMin,
                             String fechaVenc, String idCat, String idProv) {
        return actualizar(codigo, nombre, laboratorio, precio, stock, stockMin, fechaVenc, idCat, idProv, false, "General");
    }

    public String eliminar(String codigo) {
        if (!dao.existeCodigo(codigo))
            return "El medicamento no existe.";

        dao.eliminar(codigo);
        return "ok";
    }

    public Medicamento buscarPorCodigo(String codigo) {
        return dao.buscarPorCodigo(codigo);
    }

    public ArrayList<Medicamento> listarTodos() {
        return dao.leerTodos();
    }

    public ArrayList<Medicamento> listarProximosVencer() {
        return dao.obtenerProximosVencer();
    }

    public ArrayList<Medicamento> listarStockBajo() {
        return dao.obtenerStockBajo();
    }

    public int contarProximosVencer() {
        return dao.obtenerProximosVencer().size();
    }
    
}
