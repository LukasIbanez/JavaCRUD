
package modelo;

import java.sql.Date;

public class Cliente {
    String rut;
    String nombre;
    Date fecha;
    String direccion;
    String correo;
    String telefono;

    public Cliente() {
    }

    public Cliente(String rut, String nombre, Date fecha, String direccion, String correo, String telefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.fecha = fecha;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
  
}
