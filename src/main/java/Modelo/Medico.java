package Modelo;

/**
 * Clase modelo para la entidad Medico
 *
 * @author Usuario
 */
public class Medico {

    private String idMedico;
    private String nombre;
    private String apellidos;
    private String credenciales;
    private String especialidad;
    private String horarioConsulta;

    // Constructor vacío
    public Medico() {
    }

    // Constructor con parámetros
    public Medico(String idMedico, String nombre, String apellidos,
            String credenciales, String especialidad, String horarioConsulta) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.credenciales = credenciales;
        this.especialidad = especialidad;
        this.horarioConsulta = horarioConsulta;
    }

    // Getters y Setters
    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    // Método toString para facilitar debugging
    @Override
    public String toString() {
        return "Medico{"
                + "idMedico='" + idMedico + '\''
                + ", nombre='" + nombre + '\''
                + ", apellidos='" + apellidos + '\''
                + ", credenciales='" + credenciales + '\''
                + ", especialidad='" + especialidad + '\''
                + ", horarioConsulta='" + horarioConsulta + '\''
                + '}';
    }

    // Método para obtener nombre completo
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}
