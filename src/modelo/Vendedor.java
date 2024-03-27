
package modelo;
import java.sql.Date;


public class Vendedor {
    String rut;
    String nombre;
    Date fechanac;
    String telefono;
    String correo;
    String direccion;

    public Vendedor() {
    }

    public Vendedor(String rut, String nombre, Date fechanac, String telefono, String correo, String direccion) {
        this.rut = rut;
        this.nombre = nombre;
        this.fechanac = fechanac;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
