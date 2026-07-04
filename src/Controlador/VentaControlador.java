/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Datos.MedicamentoDatos;
import Datos.VentaDatos;
import Datos.ClienteDatos;
import Modelo.DetalleVenta;
import Modelo.Medicamento;
import Modelo.Venta;
import Modelo.Cliente;
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
        ventaActual.setNumComprobante(generarSiguienteCorrelativo());
    }

    // Genera el siguiente número correlativo secuencial para el comprobante
    public String generarSiguienteCorrelativo() {
        int count = ventaDAO.leerTodas().size() + 1;
        return String.format("%06d", count);
    }

    // Asocia un cliente a la venta actual verificando su existencia
    public String asociarCliente(String codigoCliente) {
        if (ventaActual == null)
            return "Primero inicie una nueva venta.";
        
        if (codigoCliente == null || codigoCliente.trim().isEmpty()) {
            ventaActual.setCodigoCliente("");
            return "ok";
        }

        if (!new Datos.ClienteDatos().existeCodigo(codigoCliente)) {
            return "El cliente con código '" + codigoCliente + "' no existe.";
        }

        ventaActual.setCodigoCliente(codigoCliente);
        return "ok";
    }

    // Configura los detalles de facturación de la venta
    public void configurarComprobante(String tipo, String nro, String metodoPago) {
        if (ventaActual != null) {
            ventaActual.setTipoComprobante(tipo);
            ventaActual.setNumComprobante(nro);
            ventaActual.setMetodoPago(metodoPago);
        }
    }

    // Registra un cliente nuevo ingresado al vuelo en la venta y lo asocia
    public String registrarClienteNuevoEnVenta(String dni, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, boolean tieneAlergia, String detalleAlergia, String seguroMedico) {
        if (ventaActual == null)
            return "No hay venta activa.";
        
        if (dni == null || dni.trim().isEmpty()) {
            ventaActual.setCodigoCliente("");
            return "ok";
        }

        ClienteDatos cliDAO = new ClienteDatos();
        // Si no existe, lo agregamos a la base de datos
        if (!cliDAO.existeCodigo(dni)) {
            String apellidoCompleto = (apellidoPaterno.trim() + " " + apellidoMaterno.trim()).trim();
            Cliente nuevoCli = new Cliente(dni, nombre, apellidoCompleto, dni, correo, tieneAlergia, detalleAlergia, seguroMedico);
            cliDAO.agregar(nuevoCli);
        }
        
        ventaActual.setCodigoCliente(dni);
        return "ok";
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
        for (DetalleVenta d : ventaActual.getDetalles()) {
            Medicamento m = medDAO.buscarPorCodigo(d.getCodigoMedicamento());
            if (m != null) {
                m.actualizarStock(d.getCantidad());
                medDAO.actualizar(m);
            }
        }

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
