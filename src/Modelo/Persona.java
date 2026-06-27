/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */

//clase abstracta que sirve como base para las entidades que representan personas en el sistema (Usuario, Cliente)
public abstract class Persona {
    
    protected String codigo;
    protected String nombre;
    // Nuevos campos agregados según el diagrama UML
    protected String apellido;
    protected String dni;
    protected String correo;

    // Constructor completo con todos los nuevos atributos
    public Persona(String codigo, String nombre, String apellido, String dni, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
    }
    
    // Constructor de compatibilidad para evitar romper dependencias heredadas
    public Persona(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public Persona() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        String cleanNombre = nombre.trim();
        if (!cleanNombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+")) {
            throw new IllegalArgumentException("El nombre sólo debe contener letras.");
        }
        this.nombre = cleanNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        String cleanApellido = apellido.trim();
        if (!cleanApellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+")) {
            throw new IllegalArgumentException("El apellido sólo debe contener letras.");
        }
        this.apellido = cleanApellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento de identidad no puede estar vacío.");
        }
        String cleanDoc = dni.trim();
        int len = cleanDoc.length();
        if (len == 8) {
            if (!cleanDoc.matches("\\d{8}")) {
                throw new IllegalArgumentException("El DNI debe contener exactamente 8 números.");
            }
        } else if (len == 11) {
            if (!cleanDoc.matches("\\d{11}")) {
                throw new IllegalArgumentException("El RUC debe contener exactamente 11 números.");
            }
        } else if (len == 9) {
            if (!cleanDoc.matches("[a-zA-Z0-9]{9}")) {
                throw new IllegalArgumentException("El Pasaporte debe contener exactamente 9 caracteres alfanuméricos.");
            }
        } else {
            throw new IllegalArgumentException("El documento debe ser un DNI (8 dígitos), RUC (11 dígitos) o Pasaporte (9 caracteres).");
        }
        this.dni = cleanDoc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío.");
        }
        String cleanCorreo = correo.trim();
        if (!cleanCorreo.contains("@") || !cleanCorreo.contains(".")) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
        this.correo = cleanCorreo;
    }
    
    // Retorna una cadena con la información legible de la persona
    public String obtenerDatos() {
        return "Código: " + codigo + ", Nombre: " + nombre + " " + apellido + ", DNI: " + dni + ", Correo: " + correo;
    }
    
    // Valida si el documento provisto coincide con el DNI de la persona
    public boolean validarIdentificacion(String documento) {
        return this.dni != null && this.dni.equals(documento);
    }
    
    // Método abstracto que cada hijo implementa
    public abstract String getTipo();
    
}

