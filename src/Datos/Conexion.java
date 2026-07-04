/*
 * Clase de Conexión a Base de Datos SQLite
 * Patrón Singleton: una sola conexión compartida para todo el sistema
 */
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión con la base de datos SQLite.
 * Usa una ruta RELATIVA para que el archivo botica.db 
 * se cree en la misma carpeta del proyecto para que sea
 * portable al copiar o clonar el proyecto.
 */
public class Conexion {

    // Ruta relativa: el archivo botica.db se crea junto al proyecto
    private static final String URL = "jdbc:sqlite:botica.db";

    /**
     * Retorna una conexión activa a la base de datos.
     * Si el archivo botica.db no existe, SQLite lo crea automáticamente
     */
    public static Connection conectar() {
        try {
            // Cargar el driver de SQLite desde el JAR en la carpeta lib/... ademas se implemta el uso de JDBC
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite no encontrado. Verifica que sqlite-jdbc.jar esté en lib/: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
