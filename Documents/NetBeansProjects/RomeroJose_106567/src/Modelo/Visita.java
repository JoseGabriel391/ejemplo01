/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jose.romero
 */
public class Visita {
    private int id;
    private Paciente idPaciente;
    private Empleado legajoRecepcionista;
    private String nombre;
    private int duracion;

    public Visita() {
    }

    public Visita(Paciente idPaciente, Empleado legajoRecepcionista, String nombre, int duracion) {
        
        this.idPaciente = idPaciente;
        this.legajoRecepcionista = legajoRecepcionista;
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Empleado getLegajoRecepcionista() {
        return legajoRecepcionista;
    }

    public void setLegajoRecepcionista(Empleado legajoRecepcionista) {
        this.legajoRecepcionista = legajoRecepcionista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Visita{" + "id=" + id + ", idPaciente=" + idPaciente + ", legajoRecepcionista=" + legajoRecepcionista + ", nombre=" + nombre + ", duracion=" + duracion + '}';
    }
    
    
}
