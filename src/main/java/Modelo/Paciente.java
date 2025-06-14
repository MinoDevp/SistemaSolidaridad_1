package Modelo;
import java.time.LocalDate;

public class Paciente {
    protected String idPaciente;
    protected String dni;
    protected LocalDate fechaNacimiento;
    protected String grupoSanguineo;
    protected String alergias;

    public Paciente() {
    }

    
    // Constructor completo
    public Paciente(String idPaciente, String dni, LocalDate fechaNacimiento, 
                   String grupoSanguineo, String alergias) {
        this.idPaciente = idPaciente;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
    }

    // Constructor sin ID (para cuando se genera automáticamente)
    public Paciente(String dni, LocalDate fechaNacimiento, 
                   String grupoSanguineo, String alergias) {
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.grupoSanguineo = grupoSanguineo;
        this.alergias = alergias;
    }

    // Getters y Setters
    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente='" + idPaciente + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", grupoSanguineo='" + grupoSanguineo + '\'' +
                ", alergias='" + alergias + '\'' +
                '}';
    }
}