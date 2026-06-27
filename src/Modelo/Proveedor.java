/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author zakkc
 */
// Representa a los proveedores del negocio. Según el nuevo UML, esta clase ya NO hereda de Persona.
public class Proveedor {
    
    private String codigo;
    private String razonSocial;
    private String telefono;
    private String empresa;
    private String direccionEmpresa;
    private String ruc;

    // Constructor completo con la estructura requerida
    public Proveedor(String codigo, String razonSocial, String telefono, String empresa, String direccionEmpresa, String ruc) {
        this.codigo = codigo;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.empresa = empresa;
        this.direccionEmpresa = direccionEmpresa;
        this.ruc = ruc;
    }

    // Constructor de compatibilidad para soportar la creación y lectura desde archivos planos heredados
    public Proveedor(String codigo, String nombre, String telefono) {
        this.codigo = codigo;
        this.razonSocial = nombre;
        this.empresa = nombre;
        this.telefono = telefono;
        this.direccionEmpresa = "";
        this.ruc = codigo;
    }

    public Proveedor() {
    }
    
    // Métodos puente de compatibilidad para evitar romper los formularios Swing
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return razonSocial;
    }

    public void setNombre(String nombre) {
        this.razonSocial = nombre;
        this.empresa = nombre;
    }

    // Getters y Setters estándar
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
        String cleanTel = telefono.trim();
        if (!cleanTel.matches("\\d{7,15}")) {
            throw new IllegalArgumentException("El teléfono debe contener entre 7 y 15 dígitos numéricos.");
        }
        this.telefono = cleanTel;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        if (ruc == null || ruc.trim().isEmpty()) {
            throw new IllegalArgumentException("El RUC no puede estar vacío.");
        }
        String cleanRuc = ruc.trim();
        if (!cleanRuc.matches("\\d{11}")) {
            throw new IllegalArgumentException("El RUC debe contener exactamente 11 números.");
        }
        this.ruc = cleanRuc;
    }
    
    // Métodos definidos en el nuevo diagrama UML
    public void registrar() {}
    public void listar() {}
    public void buscar() {}
    public void actualizar() {}
    
    // Retorna la representación plana separada por pipe para almacenamiento
    @Override
    public String toString() {
        return codigo + "|" + razonSocial + "|" + telefono + "|" + empresa + "|" + direccionEmpresa + "|" + ruc;
    }
}
