/*
 * CategoriaDatos.java - Capa de acceso a datos para Categoría
 * Reemplaza la lectura/escritura de archivos .txt por consultas SQL a SQLite
 */
package Datos;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDatos {

    /** Inserta una nueva categoría en la base de datos */
    public void agregar(Categoria c) {
        String sql = "INSERT INTO Categoria (IDcategoria, nombre) VALUES (?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getIDcategoria());
            ps.setString(2, c.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al agregar categoría: " + e.getMessage());
        }
    }

    /** Retorna todas las categorías registradas */
    public ArrayList<Categoria> listarTodos() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT IDcategoria, nombre FROM Categoria";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Categoria(
                    rs.getString("IDcategoria"),
                    rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }

    /** Actualiza el nombre de una categoría existente */
    public void actualizar(Categoria c) {
        String sql = "UPDATE Categoria SET nombre = ? WHERE IDcategoria = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIDcategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        }
    }

    /** Elimina una categoría por su código */
    public void eliminar(String idCategoria) {
        String sql = "DELETE FROM Categoria WHERE IDcategoria = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idCategoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
        }
    }

    /** Genera el siguiente código de categoría de forma automática (C001, C002...) */
    public String generarNuevoID() {
        String sql = "SELECT COUNT(*) AS total FROM Categoria";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "C" + String.format("%03d", rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar ID de categoría: " + e.getMessage());
        }
        return "C001";
    }

    /** Verifica si ya existe una categoría con ese ID */
    public boolean existeCodigo(String id) {
        String sql = "SELECT COUNT(*) FROM Categoria WHERE IDcategoria = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar categoría: " + e.getMessage());
        }
        return false;
    }
}
