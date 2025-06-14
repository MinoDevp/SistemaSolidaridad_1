package Modelo;

import java.sql.Timestamp;
import java.util.Date;

public class Cita {

    private String idCita;
    private Timestamp fechaHora;
    private String estado;
    private String motivo;
    private String idPaciente;
    private String idMedico;
    private String idRecepcionista;

    // Constructor vacío
    public Cita() {
    }

    // Constructor con todos los parámetros
    public Cita(String idCita, Timestamp fechaHora, String estado, String motivo,
            String idPaciente, String idMedico, String idRecepcionista) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.motivo = motivo;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idRecepcionista = idRecepcionista;
    }

    // Constructor alternativo con Date
    public Cita(String idCita, Date fechaHora, String estado, String motivo,
            String idPaciente, String idMedico, String idRecepcionista) {
        this.idCita = idCita;
        this.fechaHora = new Timestamp(fechaHora.getTime());
        this.estado = estado;
        this.motivo = motivo;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.idRecepcionista = idRecepcionista;
    }

    // Getters y Setters
    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = new Timestamp(fechaHora.getTime());
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(String idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    @Override
    public String toString() {
        return "Cita{"
                + "idCita='" + idCita + '\''
                + ", fechaHora=" + fechaHora
                + ", estado='" + estado + '\''
                + ", motivo='" + motivo + '\''
                + ", idPaciente='" + idPaciente + '\''
                + ", idMedico='" + idMedico + '\''
                + ", idRecepcionista='" + idRecepcionista + '\''
                + '}';
    }
}
