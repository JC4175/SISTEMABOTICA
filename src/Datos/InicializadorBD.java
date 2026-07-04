/*
 * Inicializador de Base de Datos - InicializadorBD.java
 * Se ejecuta una sola vez al arrancar el sistema.
 * Si botica.db no existe o está vacía, crea todas las tablas
 * e inserta los datos de prueba automáticamente.
 */
package Datos;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase responsable de preparar la base de datos SQLite al iniciar el sistema.
 * Crea la estructura de tablas y carga datos de prueba si la BD está vacía.
 * Gracias a esto, cualquier computadora que ejecute el proyecto
 * tendrá la BD lista sin configuración manual.
 */
public class InicializadorBD {

    /**
     * Punto de entrada principal. Llama a crear tablas y luego a insertar datos.
     */
    public static void inicializar() {
        crearTablas();
        insertarDatosIniciales();
        System.out.println("✅ Base de datos SQLite inicializada correctamente.");
    }

    /**
     * Crea todas las tablas de la base de datos si aún no existen.
     * Usaremos IF NOT EXISTS para que sea seguro ejecutarlo en cada arranque.
     */
    private static void crearTablas() {
        String[] sentencias = {

            // Tabla Persona: datos comunes a Usuario, Cliente y Proveedor
            "CREATE TABLE IF NOT EXISTS Persona (" +
            "  codigo VARCHAR(10) PRIMARY KEY," +
            "  nombre VARCHAR(100) NOT NULL," +
            "  apellidoPaterno VARCHAR(50) NOT NULL," +
            "  apellidoMaterno VARCHAR(50) NOT NULL," +
            "  tipoDocumento VARCHAR(20) NOT NULL," +
            "  dni VARCHAR(11) NOT NULL," +
            "  correo VARCHAR(100)" +
            ")",

            // Tabla Usuario: hereda datos de Persona
            "CREATE TABLE IF NOT EXISTS Usuario (" +
            "  codigoPersona VARCHAR(10) PRIMARY KEY," +
            "  nombreUsuario VARCHAR(20) NOT NULL," +
            "  rol VARCHAR(30) NOT NULL," +
            "  contrasena VARCHAR(255) NOT NULL," +
            "  estado VARCHAR(10) NOT NULL," +
            "  FOREIGN KEY (codigoPersona) REFERENCES Persona(codigo)" +
            ")",

            // Tabla Cliente: hereda datos de Persona
            "CREATE TABLE IF NOT EXISTS Cliente (" +
            "  codigoPersona VARCHAR(10) PRIMARY KEY," +
            "  tieneAlergia VARCHAR(20) NOT NULL," +
            "  detalleAlergia VARCHAR(200)," +
            "  seguroMedico VARCHAR(100)," +
            "  FOREIGN KEY (codigoPersona) REFERENCES Persona(codigo)" +
            ")",

            // Tabla Proveedor: hereda datos de Persona
            "CREATE TABLE IF NOT EXISTS Proveedor (" +
            "  codigoPersona VARCHAR(10) PRIMARY KEY," +
            "  telefono VARCHAR(20) NOT NULL," +
            "  empresa VARCHAR(150) NOT NULL," +
            "  razonSocial VARCHAR(150) NOT NULL," +
            "  direccionEmpresa VARCHAR(100) NOT NULL," +
            "  ruc VARCHAR(20) NOT NULL UNIQUE," +
            "  FOREIGN KEY (codigoPersona) REFERENCES Persona(codigo)" +
            ")",

            // Tabla Categoria
            "CREATE TABLE IF NOT EXISTS Categoria (" +
            "  IDcategoria VARCHAR(10) PRIMARY KEY," +
            "  nombre VARCHAR(100) NOT NULL" +
            ")",

            // Tabla Medicamento
            "CREATE TABLE IF NOT EXISTS Medicamento (" +
            "  codigo VARCHAR(10) PRIMARY KEY," +
            "  nombre VARCHAR(100) NOT NULL," +
            "  laboratorio VARCHAR(50)," +
            "  precio DECIMAL(10,2) NOT NULL," +
            "  stock INT NOT NULL DEFAULT 0," +
            "  stockMinimo INT NOT NULL DEFAULT 0," +
            "  fechaVencimiento VARCHAR(20)," +
            "  requiereReceta VARCHAR(10) NOT NULL DEFAULT 'No'," +
            "  presentacion VARCHAR(30)," +
            "  IDcategoria VARCHAR(10)," +
            "  codigoProveedor VARCHAR(10)," +
            "  FOREIGN KEY (IDcategoria) REFERENCES Categoria(IDcategoria)," +
            "  FOREIGN KEY (codigoProveedor) REFERENCES Proveedor(codigoPersona)" +
            ")",

            // Tabla Venta (cabecera)
            "CREATE TABLE IF NOT EXISTS Venta (" +
            "  IDventa VARCHAR(10) PRIMARY KEY," +
            "  fechaVenta VARCHAR(20) NOT NULL," +
            "  total DECIMAL(10,2) NOT NULL," +
            "  tipoComprobante VARCHAR(30)," +
            "  numComprobante VARCHAR(50)," +
            "  metodoPago VARCHAR(30)," +
            "  codigoUsuario VARCHAR(10)," +
            "  codigoCliente VARCHAR(10)," +
            "  FOREIGN KEY (codigoUsuario) REFERENCES Usuario(codigoPersona)," +
            "  FOREIGN KEY (codigoCliente) REFERENCES Cliente(codigoPersona)" +
            ")",

            // Tabla DetalleVenta (lineas de cada venta)
            "CREATE TABLE IF NOT EXISTS DetalleVenta (" +
            "  IDdetalle INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  IDventa VARCHAR(10) NOT NULL," +
            "  codigoMedicamento VARCHAR(10) NOT NULL," +
            "  nombreMedicamento VARCHAR(100)," +
            "  cantidad INT NOT NULL," +
            "  precioUnitario DECIMAL(10,2) NOT NULL," +
            "  subtotal DECIMAL(10,2) NOT NULL," +
            "  descuento DECIMAL(10,2) NOT NULL DEFAULT 0," +
            "  FOREIGN KEY (IDventa) REFERENCES Venta(IDventa)," +
            "  FOREIGN KEY (codigoMedicamento) REFERENCES Medicamento(codigo)" +
            ")",

            // Tabla Compra (órdenes de reabastecimiento)
            "CREATE TABLE IF NOT EXISTS Compra (" +
            "  IDcompra VARCHAR(20) PRIMARY KEY," +
            "  fechaCompra VARCHAR(20) NOT NULL," +
            "  totalPagado DECIMAL(10,2) NOT NULL," +
            "  estadoOrden VARCHAR(30)," +
            "  cantidadComprada INT NOT NULL," +
            "  codigoProveedor VARCHAR(10)," +
            "  codigoMedicamento VARCHAR(10)," +
            "  FOREIGN KEY (codigoProveedor) REFERENCES Proveedor(codigoPersona)," +
            "  FOREIGN KEY (codigoMedicamento) REFERENCES Medicamento(codigo)" +
            ")"
        };

        // Ejecutar cada sentencia CREATE TABLE
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement()) {
            for (String sql : sentencias) {
                st.execute(sql);
            }
            System.out.println("✅ Tablas verificadas/creadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }

    /**
     * Inserta datos de prueba SOLO si la tabla Persona está vacía.
     * Esto evita duplicados al reiniciar la aplicación.
     */
    private static void insertarDatosIniciales() {
        // Verificar si ya hay datos en la tabla Persona
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM Persona")) {
            if (rs.next() && rs.getInt("total") > 0) {
                System.out.println("ℹ️ La base de datos ya tiene datos. No se insertarán datos de prueba.");
                return; // Ya hay datos, no insertar de nuevo
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar datos: " + e.getMessage());
            return;
        }

        // Insertar datos de prueba
        String[] inserts = {
            // -- PERSONAS --
            "INSERT INTO Persona VALUES ('P001','Maria','Gomez','Ruiz','DNI','45678912','maria.gomez@gmail.com')",
            "INSERT INTO Persona VALUES ('P002','Carlos','Fernandez','Lira','DNI','41234567','carlos.fernandez@gmail.com')",
            "INSERT INTO Persona VALUES ('P003','Luis','Torres','Mena','DNI','48965321','luis.torres@gmail.com')",
            "INSERT INTO Persona VALUES ('P004','Angela','Ramirez','Solis','DNI','47812365','angela.ramirez@gmail.com')",
            "INSERT INTO Persona VALUES ('P005','Jorge','Ortiz','Vidal','DNI','46789123','jorge.castaneda@gmail.com')",
            "INSERT INTO Persona VALUES ('P006','Rosa','Chavez','Diaz','DNI','42356789','rosa.chavez@gmail.com')",
            "INSERT INTO Persona VALUES ('P007','Pedro','Salazar','Castrejon','DNI','43567891','pedro.salazar@gmail.com')",
            "INSERT INTO Persona VALUES ('P008','Lucia','Vargas','Martines','DNI','44678912','lucia.vargas@gmail.com')",
            "INSERT INTO Persona VALUES ('P009','Miguel','Rojas','Campos','DNI','45789123','miguel.rojas@gmail.com')",
            "INSERT INTO Persona VALUES ('P010','Elena','Quispe','Mamani','DNI','46891234','elena.quispe@gmail.com')",
            "INSERT INTO Persona VALUES ('P011','Distribuidora','Farma','Peru SAC','RUC','20458796123','contacto@farmaperu.com')",
            "INSERT INTO Persona VALUES ('P012','Laboratorios','Portugal','del Norte','RUC','20369852147','ventas@portugal.com')",
            "INSERT INTO Persona VALUES ('P013','Drogueria','Continental','SAC','RUC','20147258963','pedidos@continental.com')",
            "INSERT INTO Persona VALUES ('P014','Corporacion','Medifarma','EIRL','RUC','20258963741','contacto@medifarma.com')",
            "INSERT INTO Persona VALUES ('P015','Distribuidora','Quimica','Suiza','RUC','20369741852','ventas@quimicasuiza.com')",

            // -- USUARIOS --
            "INSERT INTO Usuario VALUES ('P001','gerente','Titular Gerente','gerente123','Activo')",
            "INSERT INTO Usuario VALUES ('P002','admin1','Director Tecnico','admin123','Activo')",
            "INSERT INTO Usuario VALUES ('P003','tecnicoF1','Tecnico en Farmacia','tecnicoF123','Activo')",
            "INSERT INTO Usuario VALUES ('P004','tecnicoF2','Tecnico en Farmacia','tecnicoF456','Activo')",
            "INSERT INTO Usuario VALUES ('P005','admin2','Director Tecnico','admin456','Inactivo')",

            // -- CLIENTES --
            "INSERT INTO Cliente VALUES ('P006','No',NULL,'EsSalud')",
            "INSERT INTO Cliente VALUES ('P007','Si','Alergia a la penicilina','SIS')",
            "INSERT INTO Cliente VALUES ('P008','No',NULL,'Rimac Seguros')",
            "INSERT INTO Cliente VALUES ('P009','Si','Alergia al acido acetilsalicilico','EsSalud')",
            "INSERT INTO Cliente VALUES ('P010','No',NULL,NULL)",

            // -- PROVEEDORES --
            "INSERT INTO Proveedor VALUES ('P011','976458963','Farma Peru','Distribuidora Farma Peru SAC','Av. Industrial 456, Cajamarca','20458796123')",
            "INSERT INTO Proveedor VALUES ('P012','976123456','Portugal','Laboratorios Portugal del Norte','Jr. Comercio 789, Cajamarca','20369852147')",
            "INSERT INTO Proveedor VALUES ('P013','976789456','Continental','Drogueria Continental SAC','Av. Peru 321, Cajamarca','20147258963')",
            "INSERT INTO Proveedor VALUES ('P014','976654321','Medifarma','Corporacion Medifarma EIRL','Calle Los Andes 654, Cajamarca','20258963741')",
            "INSERT INTO Proveedor VALUES ('P015','976987654','Hersil','Laboratorios Hersil','Av. Atahualpa 987, Cajamarca','20369741852')",

            // -- CATEGORÍAS --
            "INSERT INTO Categoria VALUES ('C001','Analgesicos')",
            "INSERT INTO Categoria VALUES ('C002','Antibioticos')",
            "INSERT INTO Categoria VALUES ('C003','Antigripales')",
            "INSERT INTO Categoria VALUES ('C004','Antiinflamatorios')",
            "INSERT INTO Categoria VALUES ('C005','Vitaminas y Suplementos')",

            // -- MEDICAMENTOS --
            "INSERT INTO Medicamento VALUES ('M001','Paracetamol','Genfar',5.50,20,5,'10/05/2027','No','Tabletas','C001','P011')",
            "INSERT INTO Medicamento VALUES ('M002','Amoxicilina','Portugal',12.00,15,5,'15/12/2026','No','Capsulas','C002','P012')",
            "INSERT INTO Medicamento VALUES ('M003','Panadol Antigripal','GSK',8.90,75,5,'20/02/2027','No','Tabletas','C003','P013')",
            "INSERT INTO Medicamento VALUES ('M004','Ibuprofeno','Medifarma',6.20,90,10,'30/01/2027','No','Tabletas','C004','P014')",
            "INSERT INTO Medicamento VALUES ('M005','Complejo B','Quimica Suiza',15.80,10,5,'12/08/2027','No','Frasco x 10 tabletas','C005','P015')",

            // -- VENTAS --
            "INSERT INTO Venta VALUES ('V001','15/06/2026',17.50,'Boleta','B001-000123','Efectivo','P001','P006')",
            "INSERT INTO Venta VALUES ('V002','16/06/2026',12.00,'Factura','F001-000045','Tarjeta','P002','P007')",
            "INSERT INTO Venta VALUES ('V003','18/06/2026',8.90,'Boleta','B001-000124','Efectivo','P003','P008')",
            "INSERT INTO Venta VALUES ('V004','20/06/2026',22.00,'Boleta','B001-000125','Yape','P001','P009')",
            "INSERT INTO Venta VALUES ('V005','22/06/2026',15.80,'Factura','F001-000046','Tarjeta','P002','P010')",

            // -- DETALLES DE VENTA --
            "INSERT INTO DetalleVenta (IDventa,codigoMedicamento,nombreMedicamento,cantidad,precioUnitario,subtotal,descuento) VALUES ('V001','M001','Paracetamol',2,5.50,11.00,0)",
            "INSERT INTO DetalleVenta (IDventa,codigoMedicamento,nombreMedicamento,cantidad,precioUnitario,subtotal,descuento) VALUES ('V001','M003','Panadol Antigripal',1,6.50,6.50,0)",
            "INSERT INTO DetalleVenta (IDventa,codigoMedicamento,nombreMedicamento,cantidad,precioUnitario,subtotal,descuento) VALUES ('V002','M002','Amoxicilina',1,12.00,12.00,0)",
            "INSERT INTO DetalleVenta (IDventa,codigoMedicamento,nombreMedicamento,cantidad,precioUnitario,subtotal,descuento) VALUES ('V003','M003','Panadol Antigripal',1,8.90,8.90,0)",
            "INSERT INTO DetalleVenta (IDventa,codigoMedicamento,nombreMedicamento,cantidad,precioUnitario,subtotal,descuento) VALUES ('V005','M005','Complejo B',1,15.80,15.80,0)",

            // -- COMPRAS --
            "INSERT INTO Compra VALUES ('CO001','01/06/2026',600.00,'Recibida',100,'P011','M002')",
            "INSERT INTO Compra VALUES ('CO002','05/06/2026',275.00,'Pendiente',50,'P011','M001')",
            "INSERT INTO Compra VALUES ('CO003','08/06/2026',445.50,'Recibida',75,'P012','M003')",
            "INSERT INTO Compra VALUES ('CO004','10/06/2026',372.00,'Recibida',90,'P014','M004')",
            "INSERT INTO Compra VALUES ('CO005','12/06/2026',632.00,'Pendiente',60,'P015','M005')"
        };

        // Ejecutar cada INSERT
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement()) {
            for (String sql : inserts) {
                st.execute(sql);
            }
            System.out.println("✅ Datos de prueba insertados correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar datos de prueba: " + e.getMessage());
        }
    }
}
