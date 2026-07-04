/*
 * UsuarioDatos.java - Capa de acceso a datos para Usuario
 * La tabla Usuario usa herencia relacional con Persona:
 * inserta primero en Persona y luego en Usuario.
 */
package Datos;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDatos {

    /**
     * Registra un usuario nuevo en la BD.
     * Paso 1: Inserta datos comunes en Persona.
     * Paso 2: Inserta credenciales y rol en Usuario.
     * Si el código ya existe en Persona (ej. admin de prueba), solo inserta en Usuario.
     */
    public void agregar(Usuario u) {
        String sqlCheckPersona = "SELECT COUNT(*) FROM Persona WHERE codigo = ?";
        String sqlPersona = "INSERT INTO Persona (codigo, nombre, apellidoPaterno, apellidoMaterno, tipoDocumento, dni, correo) VALUES (?,?,?,?,?,?,?)";
        String sqlUsuario = "INSERT INTO Usuario (codigoPersona, nombreUsuario, rol, contrasena, estado) VALUES (?,?,?,?,?)";

        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false); // Transacción atómica

            try {
                // Verificar si la persona ya existe para no duplicarla
                boolean personaExiste = false;
                try (PreparedStatement psCheck = con.prepareStatement(sqlCheckPersona)) {
                    psCheck.setString(1, u.getCodigo());
                    try (ResultSet rs = psCheck.executeQuery()) {
                        if (rs.next() && rs.getInt(1) > 0) personaExiste = true;
                    }
                }

                // Solo insertar en Persona si no existe
                if (!personaExiste) {
                    try (PreparedStatement ps1 = con.prepareStatement(sqlPersona)) {
                        String[] apellidos = u.getApellido() != null ? u.getApellido().split(" ", 2) : new String[]{"", ""};
                        ps1.setString(1, u.getCodigo());
                        ps1.setString(2, u.getNombre());
                        ps1.setString(3, apellidos.length > 0 ? apellidos[0] : "");
                        ps1.setString(4, apellidos.length > 1 ? apellidos[1] : "");
                        ps1.setString(5, "DNI");
                        ps1.setString(6, u.getDni() != null ? u.getDni() : "");
                        ps1.setString(7, u.getCorreo() != null ? u.getCorreo() : "");
                        ps1.executeUpdate();
                    }
                }

                // Insertar en Usuario (credenciales y rol)
                try (PreparedStatement ps2 = con.prepareStatement(sqlUsuario)) {
                    ps2.setString(1, u.getCodigo());
                    ps2.setString(2, u.getNombreUsuario() != null ? u.getNombreUsuario() : u.getNombre());
                    ps2.setString(3, u.getRol());
                    ps2.setString(4, u.getContrasena());
                    ps2.setString(5, u.isEstado() ? "Activo" : "Inactivo");
                    ps2.executeUpdate();
                }

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al registrar usuario (rollback): " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión al registrar usuario: " + e.getMessage());
        }
    }

    /** Retorna todos los usuarios con sus datos personales completos (JOIN con Persona) */
    public ArrayList<Usuario> leerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT p.codigo, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.dni, p.correo, " +
                     "u.nombreUsuario, u.contrasena, u.rol, u.estado " +
                     "FROM Persona p INNER JOIN Usuario u ON p.codigo = u.codigoPersona";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellidoPaterno") + " " + rs.getString("apellidoMaterno"),
                    rs.getString("dni") != null ? rs.getString("dni") : "",
                    rs.getString("correo") != null ? rs.getString("correo") : "",
                    rs.getString("nombreUsuario"),
                    rs.getString("contrasena"),
                    rs.getString("rol"),
                    rs.getString("estado").equalsIgnoreCase("Activo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Valida el login buscando por nombreUsuario y contraseña.
     * Retorna el Usuario si las credenciales son correctas, null si no.
     */
    public Usuario validarLogin(String nombreUsuario, String contrasena) {
        String sql = "SELECT p.codigo, p.nombre, p.apellidoPaterno, p.apellidoMaterno, p.dni, p.correo, " +
                     "u.nombreUsuario, u.contrasena, u.rol, u.estado " +
                     "FROM Persona p INNER JOIN Usuario u ON p.codigo = u.codigoPersona " +
                     "WHERE u.nombreUsuario = ? AND u.contrasena = ? AND u.estado = 'Activo'";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno") + " " + rs.getString("apellidoMaterno"),
                        rs.getString("dni") != null ? rs.getString("dni") : "",
                        rs.getString("correo") != null ? rs.getString("correo") : "",
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasena"),
                        rs.getString("rol"),
                        true
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar login: " + e.getMessage());
        }
        return null;
    }

    /** Verifica si ya existe un usuario con ese código en la BD */
    public boolean existeCodigo(String codigo) {
        String sql = "SELECT COUNT(*) FROM Usuario WHERE codigoPersona = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar usuario: " + e.getMessage());
        }
        return false;
    }

    /** Actualiza los datos personales y credenciales de un usuario */
    public void actualizar(Usuario u) {
        String sqlPersona = "UPDATE Persona SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, dni=?, correo=? WHERE codigo=?";
        String sqlUsuario = "UPDATE Usuario SET nombreUsuario=?, rol=?, contrasena=?, estado=? WHERE codigoPersona=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps1 = con.prepareStatement(sqlPersona);
             PreparedStatement ps2 = con.prepareStatement(sqlUsuario)) {

            String[] apellidos = u.getApellido() != null ? u.getApellido().split(" ", 2) : new String[]{"", ""};
            ps1.setString(1, u.getNombre());
            ps1.setString(2, apellidos.length > 0 ? apellidos[0] : "");
            ps1.setString(3, apellidos.length > 1 ? apellidos[1] : "");
            ps1.setString(4, u.getDni() != null ? u.getDni() : "");
            ps1.setString(5, u.getCorreo() != null ? u.getCorreo() : "");
            ps1.setString(6, u.getCodigo());
            ps1.executeUpdate();

            ps2.setString(1, u.getNombreUsuario());
            ps2.setString(2, u.getRol());
            ps2.setString(3, u.getContrasena());
            ps2.setString(4, u.isEstado() ? "Activo" : "Inactivo");
            ps2.setString(5, u.getCodigo());
            ps2.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    /** Elimina un usuario de ambas tablas (primero Usuario, luego Persona) */
    public void eliminar(String codigo) {
        try (Connection con = Conexion.conectar()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement("DELETE FROM Usuario WHERE codigoPersona=?");
                 PreparedStatement ps2 = con.prepareStatement("DELETE FROM Persona WHERE codigo=?")) {
                ps1.setString(1, codigo);
                ps1.executeUpdate();
                ps2.setString(1, codigo);
                ps2.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.out.println("Error al eliminar usuario: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión al eliminar usuario: " + e.getMessage());
        }
    }
}
