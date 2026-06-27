package Controlador;

import Datos.CompraDatos;
import Modelo.Compra;
import java.util.ArrayList;

public class CompraControlador {
    private CompraDatos dao = new CompraDatos();

    public ArrayList<Compra> listarTodas() {
        return dao.leerTodos();
    }

    public String registrar(String idCompra, String fecha, String totalStr, String estado, String cantidadStr, String idProveedor, String codigoMed) {
        if (idCompra.isEmpty() || fecha.isEmpty() || totalStr.isEmpty() || cantidadStr.isEmpty() || idProveedor.isEmpty() || codigoMed.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }

        if (dao.existeCodigo(idCompra)) {
            return "Ya existe una compra con ese código/ID.";
        }

        double total;
        int cantidad;
        try {
            total = Double.parseDouble(totalStr);
            if (total < 0) return "El total no puede ser negativo.";
        } catch (NumberFormatException e) {
            return "El total pagado debe ser un número decimal válido.";
        }

        try {
            cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) return "La cantidad debe ser mayor a 0.";
        } catch (NumberFormatException e) {
            return "La cantidad debe ser un número entero válido.";
        }

        Compra c = new Compra(idCompra, fecha, total, estado, cantidad, idProveedor, codigoMed);
        
        if (estado.equals("Recibida")) {
            // Si entra de frente como Recibida, actualizar el stock del medicamento
            c.actualizarStockMedicamento();
        }
        
        dao.agregar(c);
        return "ok";
    }

    public String recibirMercaderia(String idCompra) {
        Compra c = dao.buscarPorID(idCompra);
        if (c == null) {
            return "La compra seleccionada no existe.";
        }

        if (c.getEstadoOrden().equals("Recibida")) {
            return "Esta compra ya fue recibida anteriormente (stock ya incrementado).";
        }

        c.recibirMercaderia(); // Cambia estado a Recibida y actualiza stock automáticamente
        return "ok";
    }

    public String generarNuevoID() {
        ArrayList<Compra> lista = dao.leerTodos();
        return "C" + String.format("%04d", lista.size() + 1);
    }
}
