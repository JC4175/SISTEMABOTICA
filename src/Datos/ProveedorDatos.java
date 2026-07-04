/*
 * ProveedorDatos.java - Capa de acceso a datos para Proveedor
 * La tabla Proveedor usa herencia relacional con Persona:
 * al insertar un proveedor, primero se inserta en Persona y luego en Proveedor.
 */
package Datos;

import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorDatos {

    /**
     * Registra un proveedor nuevo.
     * Paso 1: Inserta los datos comunes en la tabla Persona.
     * Paso 2: Inserta los datos específicos del proveedor en la tabla Proveedor.
     */
    public void agregar(Proveedor p) {
        String sqlPersona = "INSERT INTO Persona (codigo, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, dni, correo) VALUES (?,?,?,?,?,?,?)";
        String sqlProveedor = "INSERT INTO Proveedor (codigoPersona, telefono, empresa, razonSocial, direccionEmpresa, ruc) VALUES (?,?,?,?,?,?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false); // Usamos transacción para que ambos inserts sean atómicos

            try (PreparedStatement ps1 = con.prepareStatement(sqlPersona);
                 PreparedStatement ps2 = con.prepareStatement(sqlProveedor)) {

                // Insertar en Persona (apellido almacenado como paterno/materno)
                // Proveedor no tiene nombre/apellido - usamos razonSocial como nombre
                ps1.setString(1, p.getCodigo());
                ps1.setString(2, p.getRazonSocial());
                ps1.setString(3, "");
                ps1.setString(4, "");
                ps1.setString(5, "RUC");
                ps1.setString(6, p.getRuc());
                ps1.setString(7, "");
                ps1.executeUpdate();

                // Insertar en Proveedor
                ps2.setString(1, p.getCodigo());
                ps2.setString(2, p.getTelefono());
                ps2.setString(3, p.getEmpresa());
                ps2.setString(4, p.getRazonSocial());
                ps2.setString(5, p.getDireccionEmpresa());
                ps2.setString(6, p.getRuc());
                ps2.executeUpdate();

                con.commit(); // Confirmar la transacción
            } catch (SQLException e) {
                con.rollback(); // Si algo falla, revertir ambos inserts
                System.out.println("Error al registrar proveedor (rollback ejecutado): " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión al registrar proveedor: " + e.getMessage());
        }
    }

    /** Retorna todos los proveedores haciendo JOIN entre Persona y Proveedor */
    public ArrayList<Proveedor> listarTodos() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT p.codigo, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.correo, " +
                     "pr.telefono, pr.empresa, pr.razonSocial, pr.direccionEmpresa, pr.ruc " +
                     "FROM Persona p INNER JOIN Proveedor pr ON p.codigo = pr.codigoPersona";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setRazonSocial(rs.getString("razonSocial"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmpresa(rs.getString("empresa"));
                p.setRazonSocial(rs.getString("razonSocial"));
                p.setDireccionEmpresa(rs.getString("direccionEmpresa"));
                p.setRuc(rs.getString("ruc"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    /** Actualiza los datos de un proveedor en ambas tablas (Persona y Proveedor) */
    public void actualizar(Proveedor p) {
        String sqlPersona = "UPDATE Persona SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, correo=? WHERE codigo=?";
        String sqlProveedor = "UPDATE Proveedor SET telefono=?, empresa=?, razonSocial=?, direccionEmpresa=?, ruc=? WHERE codigoPersona=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps1 = con.prepareStatement(sqlPersona);
             PreparedStatement ps2 = con.prepareStatement(sqlProveedor)) {

            String[] apellidos = p.getRazonSocial().split(" ", 2);
            ps1.setString(1, p.getRazonSocial());
            ps1.setString(2, apellidos.length > 0 ? apellidos[0] : "");
            ps1.setString(3, apellidos.length > 1 ? apellidos[1] : "");
            ps1.setString(4, "");
            ps1.setString(5, p.getCodigo());
            ps1.executeUpdate();

            ps2.setString(1, p.getTelefono());
            ps2.setString(2, p.getEmpresa());
            ps2.setString(3, p.getRazonSocial());
            ps2.setString(4, p.getDireccionEmpresa());
            ps2.setString(5, p.getRuc());
            ps2.setString(6, p.getCodigo());
            ps2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    /** Elimina un proveedor de ambas tablas (primero Proveedor, luego Persona) */
    public void eliminar(String codigo) {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM Proveedor WHERE codigoPersona=?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM Persona WHERE codigo=?")) {
                ps1.setString(1, codigo);
                ps1.executeUpdate();
                ps2.setString(1, codigo);
                ps2.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al eliminar proveedor: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión al eliminar proveedor: " + e.getMessage());
        }
    }

    /** Genera el siguiente código de proveedor automáticamente (P001, P016...) */
    public String generarNuevoID() {
        String sql = "SELECT COUNT(*) AS total FROM Proveedor";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return "P" + String.format("%03d", rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.out.println("Error al generar ID de proveedor: " + e.getMessage());
        }
        return "P001";
    }

    /** Verifica si ya existe un proveedor con ese código */
    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /** Busca un proveedor por su código */
    public Proveedor buscarPorCodigo(String codigo) {
        String sql = "SELECT pr.codigoPersona AS codigo, p.nombre, pr.telefono, pr.empresa, pr.razonSocial, pr.direccionEmpresa, pr.ruc " +
                     "FROM Proveedor pr INNER JOIN Persona p ON p.codigo = pr.codigoPersona " +
                     "WHERE pr.codigoPersona = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Proveedor p = new Proveedor();
                    p.setCodigo(rs.getString("codigo"));
                    p.setNombre(rs.getString("nombre"));
                    p.setRazonSocial(rs.getString("razonSocial"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setEmpresa(rs.getString("empresa"));
                    p.setDireccionEmpresa(rs.getString("direccionEmpresa"));
                    p.setRuc(rs.getString("ruc"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar proveedor: " + e.getMessage());
        }
        return null;
    }

    /** Alias de listarTodos() para compatibilidad con controladores */
    public ArrayList<Proveedor> leerTodos() {
        return listarTodos();
    }
}
