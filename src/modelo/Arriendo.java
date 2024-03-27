
package modelo;

import java.sql.Timestamp;

public class Arriendo {
    int id;
    Timestamp fecha_inicio;
    String rut_vendedor;
    String rut_cliente;
    Timestamp fecha_termino;
    String patente;

    public Arriendo() {
    }

    public Arriendo(int id,Timestamp fecha_inicio, String rut_vendedor, String rut_cliente, Timestamp fecha_termino, String patente) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.rut_vendedor = rut_vendedor;
        this.rut_cliente = rut_cliente;
        this.fecha_termino = fecha_termino;
        this.patente = patente;
    }

    public int getId() {
        return id;
    }

    public Timestamp getFecha_inicio() {
        return fecha_inicio;
    }

    public String getRut_vendedor() {
        return rut_vendedor;
    }

    public String getRut_cliente() {
        return rut_cliente;
    }

    public Timestamp getFecha_termino() {
        return fecha_termino;
    }

    public String getPatente() {
        return patente;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public void setFecha_inicio(Timestamp fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setRut_vendedor(String rut_vendedor) {
        this.rut_vendedor = rut_vendedor;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public void setFecha_termino(Timestamp fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
    
}
