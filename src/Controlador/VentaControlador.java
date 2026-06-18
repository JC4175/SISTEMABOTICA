/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.MedicamentoDatos;
import Datos.VentaDatos;
import Modelo.DetalleVenta;
import Modelo.Medicamento;
import Modelo.Venta;
import java.util.ArrayList;

/**
 *
 * @author zakkc
 */
public class VentaControlador {
    private VentaDatos ventaDAO = new VentaDatos();
    private MedicamentoDatos medDAO = new MedicamentoDatos();
    private Venta ventaActual;

    // Inicia una nueva venta
    public void nuevaVenta() {
        String id = ventaDAO.generarNuevoID();
        String usuario = SesionControlador.getUsuarioActivo().getCodigo();
        ventaActual = new Venta(id, usuario);
    }

    // Busca medicamento y valida antes de agregar
    public String agregarDetalle(String codigo, String cantidadStr) {
        if (ventaActual == null)
            return "Primero inicie una nueva venta.";

        Medicamento m = medDAO.buscarPorCodigo(codigo);
        if (m == null)
            return "Medicamento no encontrado.";

        if (m.estaVencido())
            return "No se puede vender un medicamento vencido.";

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0)
                return "La cantidad debe ser mayor a 0.";

            if (cantidad > m.getStock())
                return "Stock insuficiente. Disponible: " + m.getStock();

            DetalleVenta detalle = new DetalleVenta(
                m.getCodigo(), m.getNombre(), cantidad, m.getPrecio()
            );
            ventaActual.agregarDetalle(detalle);
            return "ok";

        } catch (NumberFormatException e) {
            return "La cantidad debe ser un número válido.";
        }
    }

    public String eliminarDetalle(int indice) {
        if (ventaActual == null || ventaActual.getDetalles().isEmpty())
            return "No hay detalles en la venta actual.";

        ventaActual.eliminarDetalle(indice);
        return "ok";
    }

    // Confirma la venta: guarda en TXT y actualiza stock
    public String confirmarVenta() {
        if (ventaActual == null || ventaActual.getDetalles().isEmpty())
            return "No hay productos en la venta.";

        // Actualiza stock de cada medicamento vendido
        ArrayList<Medicamento> lista = medDAO.leerTodos();
        for (DetalleVenta d : ventaActual.getDetalles()) {
            for (Medicamento m : lista) {
                if (m.getCodigo().equals(d.getCodigoMedicamento())) {
                    m.actualizarStock(d.getCantidad());
                    break;
                }
            }
        }
        medDAO.guardarTodos(lista);

        // Guarda la venta
        ventaDAO.registrarVentaCompleta(ventaActual);

        String idGuardado = ventaActual.getIDVenta();
        ventaActual = null;
        return "ok:" + idGuardado;
    }

    public void cancelarVenta() {
        ventaActual = null;
    }

    public Venta getVentaActual() {
        return ventaActual;
    }

    public double getTotalActual() {
        if (ventaActual == null) return 0;
        return ventaActual.getTotal();
    }

    public ArrayList<Venta> listarTodas() {
        return ventaDAO.leerTodas();
    }

    public void exportarReporte(ArrayList<Venta> ventas, String ruta) {
        ventaDAO.exportarReporte(ventas, ruta);
    }

    // Busca medicamento para mostrar info en el form de ventas
    public Medicamento buscarMedicamento(String codigo) {
        return medDAO.buscarPorCodigo(codigo);
    }
}
