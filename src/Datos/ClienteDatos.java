/*
 * ClienteDatos.java - Capa de acceso a datos para Cliente
 * Usa herencia relacional: Persona (datos comunes) + Cliente (datos específicos)
 */
package Datos;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDatos {

    /**
     * Registra un cliente nuevo en la BD.
     * Paso 1: Inserta datos comunes en Persona.
     * Paso 2: Inserta datos específicos del cliente en Cliente.
     */
    public void agregar(Cliente c) {
        String sqlPersona = "INSERT INTO Persona (codigo, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, dni, correo) VALUES (?,?,?,?,?,?,?)";
        String sqlCliente = "INSERT INTO Cliente (codigoPersona, tieneAlergia, detalleAlergia, seguroMedico) VALUES (?,?,?,?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false); // Transacción atómica

            try (PreparedStatement ps1 = con.prepareStatement(sqlPersona);
                 PreparedStatement ps2 = con.prepareStatement(sqlCliente)) {

                // Separar apellido compuesto en paterno y materno
                String[] apellidos = c.getApellido().split(" ", 2);
                ps1.setString(1, c.getCodigo());
                ps1.setString(2, c.getNombre());
                ps1.setString(3, apellidos.length > 0 ? apellidos[0] : c.getApellido());
                ps1.setString(4, apellidos.length > 1 ? apellidos[1] : "");
                ps1.setString(5, "DNI");
                ps1.setString(6, c.getDni());
                ps1.setString(7, c.getCorreo());
                ps1.executeUpdate();

                // Datos específicos del cliente
                ps2.setString(1, c.getCodigo());
                ps2.setString(2, c.isTieneAlergia() ? "Si" : "No");
                ps2.setString(3, c.getDetalleAlergia());
                ps2.setString(4, c.getSeguroMedico());
                ps2.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al registrar cliente (rollback ejecutado): " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión al registrar cliente: " + e.getMessage());
        }
    }

    /** Retorna todos los clientes haciendo JOIN entre Persona y Cliente */
    public ArrayList<Cliente> listarTodos() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT p.codigo, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.dni, p.correo, " +
                     "c.tieneAlergia, c.detalleAlergia, c.seguroMedico " +
                     "FROM Persona p INNER JOIN Cliente c ON p.codigo = c.codigoPersona";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellidoPaterno") + " " + rs.getString("apellidoMaterno"),
                    rs.getString("dni"),
                    rs.getString("correo") != null ? rs.getString("correo") : "",
                    rs.getString("tieneAlergia").equalsIgnoreCase("Si"),
                    rs.getString("detalleAlergia") != null ? rs.getString("detalleAlergia") : "",
                    rs.getString("seguroMedico") != null ? rs.getString("seguroMedico") : "Ninguno"
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    /** Busca un cliente por su código o DNI */
    public Cliente buscarPorCodigo(String codigo) {
        String sql = "SELECT p.codigo, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.dni, p.correo, " +
                     "c.tieneAlergia, c.detalleAlergia, c.seguroMedico " +
                     "FROM Persona p INNER JOIN Cliente c ON p.codigo = c.codigoPersona " +
                     "WHERE p.codigo = ? OR p.dni = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno") + " " + rs.getString("apellidoMaterno"),
                        rs.getString("dni"),
                        rs.getString("correo") != null ? rs.getString("correo") : "",
                        rs.getString("tieneAlergia").equalsIgnoreCase("Si"),
                        rs.getString("detalleAlergia") != null ? rs.getString("detalleAlergia") : "",
                        rs.getString("seguroMedico") != null ? rs.getString("seguroMedico") : "Ninguno"
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    /** Actualiza los datos de un cliente en ambas tablas */
    public void actualizar(Cliente c) {
        String sqlPersona = "UPDATE Persona SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, dni=?, correo=? WHERE codigo=?";
        String sqlCliente = "UPDATE Cliente SET tieneAlergia=?, detalleAlergia=?, seguroMedico=? WHERE codigoPersona=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps1 = con.prepareStatement(sqlPersona);
             PreparedStatement ps2 = con.prepareStatement(sqlCliente)) {

            String[] apellidos = c.getApellido().split(" ", 2);
            ps1.setString(1, c.getNombre());
            ps1.setString(2, apellidos.length > 0 ? apellidos[0] : c.getApellido());
            ps1.setString(3, apellidos.length > 1 ? apellidos[1] : "");
            ps1.setString(4, c.getDni());
            ps1.setString(5, c.getCorreo());
            ps1.setString(6, c.getCodigo());
            ps1.executeUpdate();

            ps2.setString(1, c.isTieneAlergia() ? "Si" : "No");
            ps2.setString(2, c.getDetalleAlergia());
            ps2.setString(3, c.getSeguroMedico());
            ps2.setString(4, c.getCodigo());
            ps2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    /** Elimina un cliente de ambas tablas */
    public void eliminar(String codigo) {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM Cliente WHERE codigoPersona=?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM Persona WHERE codigo=?")) {
                ps1.setString(1, codigo);
                ps1.executeUpdate();
                ps2.setString(1, codigo);
                ps2.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión al eliminar cliente: " + e.getMessage());
        }
    }

    /** Genera el siguiente código de persona disponible */
    public String generarNuevoID() {
        String sql = "SELECT COUNT(*) AS total FROM Persona";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "P" + String.format("%03d", rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar ID de cliente: " + e.getMessage());
        }
        return "P001";
    }

    /** Verifica si ya existe un cliente con ese código o DNI */
    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /** Alias de listarTodos() para compatibilidad */
    public ArrayList<Cliente> leerTodos() {
        return listarTodos();
    }
}
