/*
 * MedicamentoDatos.java - Capa de acceso a datos para Medicamento
 * Reemplaza lectura/escritura de medicamentos.txt por consultas SQL a SQLite
 */
package Datos;

import Modelo.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentoDatos {

    /** Lee un ResultSet y construye un objeto Medicamento (evita duplicar código) */
    private Medicamento construirMedicamento(ResultSet rs) throws SQLException {
        return new Medicamento(
            rs.getString("codigo"),
            rs.getString("nombre"),
            rs.getString("laboratorio") != null ? rs.getString("laboratorio") : "",
            rs.getDouble("precio"),
            rs.getInt("stock"),
            rs.getInt("stockMinimo"),
            rs.getString("fechaVencimiento") != null ? rs.getString("fechaVencimiento") : "",
            rs.getString("IDcategoria") != null ? rs.getString("IDcategoria") : "",
            rs.getString("codigoProveedor") != null ? rs.getString("codigoProveedor") : "",
            rs.getString("requiereReceta").equalsIgnoreCase("Si"),
            rs.getString("presentacion") != null ? rs.getString("presentacion") : ""
        );
    }

    /** Inserta un medicamento nuevo en la base de datos */
    public void agregar(Medicamento m) {
        String sql = "INSERT INTO Medicamento (codigo, nombre, laboratorio, precio, stock, stockMinimo, " +
                     "fechaVencimiento, requiereReceta, presentacion, IDcategoria, codigoProveedor) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getCodigo());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getLaboratorio());
            ps.setDouble(4, m.getPrecio());
            ps.setInt(5, m.getStock());
            ps.setInt(6, m.getStockMinimo());
            ps.setString(7, m.getFechaVencimiento());
            ps.setString(8, m.isRequiereReceta() ? "Si" : "No");
            ps.setString(9, m.getPresentacion());
            ps.setString(10, m.getIdCategoria());
            ps.setString(11, m.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar medicamento: " + e.getMessage());
        }
    }

    /** Retorna todos los medicamentos de la base de datos */
    public ArrayList<Medicamento> leerTodos() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicamento";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(construirMedicamento(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar medicamentos: " + e.getMessage());
        }
        return lista;
    }

    /** Busca un medicamento por su código único */
    public Medicamento buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM Medicamento WHERE codigo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return construirMedicamento(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar medicamento: " + e.getMessage());
        }
        return null;
    }

    /** Verifica si ya existe un medicamento con ese código */
    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /** Actualiza todos los campos de un medicamento existente */
    public void actualizar(Medicamento m) {
        String sql = "UPDATE Medicamento SET nombre=?, laboratorio=?, precio=?, stock=?, stockMinimo=?, " +
                     "fechaVencimiento=?, requiereReceta=?, presentacion=?, IDcategoria=?, codigoProveedor=? " +
                     "WHERE codigo=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getLaboratorio());
            ps.setDouble(3, m.getPrecio());
            ps.setInt(4, m.getStock());
            ps.setInt(5, m.getStockMinimo());
            ps.setString(6, m.getFechaVencimiento());
            ps.setString(7, m.isRequiereReceta() ? "Si" : "No");
            ps.setString(8, m.getPresentacion());
            ps.setString(9, m.getIdCategoria());
            ps.setString(10, m.getIdProveedor());
            ps.setString(11, m.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar medicamento: " + e.getMessage());
        }
    }

    /** Elimina un medicamento por su código */
    public void eliminar(String codigo) {
        String sql = "DELETE FROM Medicamento WHERE codigo = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar medicamento: " + e.getMessage());
        }
    }

    /** Retorna medicamentos con fecha de vencimiento en los próximos 30 días */
    public ArrayList<Medicamento> obtenerProximosVencer() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        for (Medicamento m : leerTodos()) {
            try {
                // Usamos el método del modelo que ya calcula los días restantes
                if (m.proximoVencer()) lista.add(m);
            } catch (Exception e) {
                // Si la fecha tiene formato inválido, ignoramos ese registro
            }
        }
        return lista;
    }

    /** Retorna medicamentos cuyo stock ha llegado al mínimo configurado */
    public ArrayList<Medicamento> obtenerStockBajo() {
        ArrayList<Medicamento> lista = new ArrayList<>();
        for (Medicamento m : leerTodos()) {
            if (m.stockBajo()) lista.add(m);
        }
        return lista;
    }

    /** Genera el siguiente código de medicamento automáticamente (M006, M007...) */
    public String generarNuevoID() {
        String sql = "SELECT COUNT(*) AS total FROM Medicamento";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "M" + String.format("%03d", rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar ID de medicamento: " + e.getMessage());
        }
        return "M001";
    }

    /** Alias de leerTodos() para compatibilidad con controladores */
    public ArrayList<Medicamento> listarTodos() {
        return leerTodos();
    }
}
