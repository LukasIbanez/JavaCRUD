/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author GorillaSetups
 */
public class Vehiculo {
    String patente;
    int nro_motor;
    int nro_chasis;
    String marca;
    String modelo;
    int anio;
    String tpo_combustible;
    String tpo_vehiculo;

    public Vehiculo() {
    }

    public Vehiculo(String patente, int nro_motor, int nro_chasis, String marca, String modelo, int anio, String tpo_combustible, String tpo_vehiculo) {
        this.patente = patente;
        this.nro_motor = nro_motor;
        this.nro_chasis = nro_chasis;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.tpo_combustible = tpo_combustible;
        this.tpo_vehiculo = tpo_vehiculo;
    }

    public String getPatente() {
        return patente;
    }

    public int getNro_motor() {
        return nro_motor;
    }

    public int getNro_chasis() {
        return nro_chasis;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public String getTpo_combustible() {
        return tpo_combustible;
    }

    public String getTpo_vehiculo() {
        return tpo_vehiculo;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setNro_motor(int nro_motor) {
        this.nro_motor = nro_motor;
    }

    public void setNro_chasis(int nro_chasis) {
        this.nro_chasis = nro_chasis;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setTpo_combustible(String tpo_combustible) {
        this.tpo_combustible = tpo_combustible;
    }

    public void setTpo_vehiculo(String tpo_vehiculo) {
        this.tpo_vehiculo = tpo_vehiculo;
    }
    
}
