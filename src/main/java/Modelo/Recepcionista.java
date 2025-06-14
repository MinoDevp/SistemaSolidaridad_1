/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Usuario
 */
// ========== MODELO RECEPCIONISTA ==========
public class Recepcionista {

    private String idRecepcionista;
    private String nombre;
    private String apellidos;
    private String credenciales;
    private String horarioTrabajo;

    // Constructor vacío
    public Recepcionista() {
    }

    // Constructor con parámetros
    public Recepcionista(String idRecepcionista, String nombre, String apellidos,
            String credenciales, String horarioTrabajo) {
        this.idRecepcionista = idRecepcionista;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.credenciales = credenciales;
        this.horarioTrabajo = horarioTrabajo;
    }

    // Getters y Setters
    public String getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(String idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }

    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    @Override
    public String toString() {
        return "Recepcionista{"
                + "idRecepcionista='" + idRecepcionista + '\''
                + ", nombre='" + nombre + '\''
                + ", apellidos='" + apellidos + '\''
                + ", credenciales='" + credenciales + '\''
                + ", horarioTrabajo='" + horarioTrabajo + '\''
                + '}';
    }
}
