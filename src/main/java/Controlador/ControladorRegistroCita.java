package Controlador;

import Modelo.Cita;
import Modelo.CitaDAO;
import Vistas.regCita;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.List;

public class ControladorRegistroCita {
    
    private regCita vista;
    private CitaDAO citaDAO;
    
    // Constructor que recibe la vista
    public ControladorRegistroCita(regCita vista) {
        this.vista = vista;
        this.citaDAO = new CitaDAO();        
        // Configurar eventos de la vista
        configurarEventos();
    }
    
    // Constructor alternativo sin vista (para uso independiente)
    public ControladorRegistroCita() {
        this.citaDAO = new CitaDAO();
    }
    
    // Configurar los eventos de los botones de la vista
    private void configurarEventos() {
        if (vista != null) {
            // Los eventos se configuran desde la vista, pero el controlador maneja la lógica
            // Este método puede expandirse si necesitas configurar eventos adicionales
        }
    }
    
    /**
     * Registrar una nueva cita
     * @param idCita ID único de la cita
     * @param fechaHora Fecha y hora de la cita
     * @param estado Estado de la cita
     * @param motivo Motivo de la consulta
     * @param idPaciente ID del paciente
     * @param idMedico ID del médico
     * @param idRecepcionista ID de la recepcionista
     * @return ResultadoOperacion con el resultado de la operación
     */
    public ResultadoOperacion registrarCita(String idCita, Date fechaHora, String estado, 
                                          String motivo, String idPaciente, String idMedico, 
                                          String idRecepcionista) {
        
        // Validar datos de entrada
        ResultadoValidacion validacion = validarDatosCita(idCita, fechaHora, estado, 
                                                         motivo, idPaciente, idMedico, idRecepcionista);
        
        if (!validacion.esValido()) {
            return new ResultadoOperacion(false, validacion.getMensaje());
        }
        
        try {
            // Verificar si ya existe una cita con ese ID
            if (citaDAO.existeCita(idCita)) {
                return new ResultadoOperacion(false, "Ya existe una cita con el ID: " + idCita);
            }
            
            // Crear objeto Cita
            Cita nuevaCita = new Cita(idCita, fechaHora, estado, motivo, 
                                    idPaciente, idMedico, idRecepcionista);
            
            // Intentar insertar la cita
            boolean exito = citaDAO.insertarCita(nuevaCita);
            
            if (exito) {
                return new ResultadoOperacion(true, "Cita registrada exitosamente");
            } else {
                return new ResultadoOperacion(false, "Error al registrar la cita en la base de datos");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Buscar una cita por su ID
     * @param idCita ID de la cita a buscar
     * @return La cita encontrada o null si no existe
     */
    public Cita buscarCita(String idCita) {
        if (idCita == null || idCita.trim().isEmpty()) {
            return null;
        }
        
        try {
            return citaDAO.buscarCitaPorId(idCita.trim());
        } catch (Exception e) {
            System.err.println("Error al buscar cita: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Actualizar una cita existente
     * @param cita Objeto Cita con los datos actualizados
     * @return ResultadoOperacion con el resultado de la operación
     */
    public ResultadoOperacion actualizarCita(Cita cita) {
        if (cita == null) {
            return new ResultadoOperacion(false, "Los datos de la cita no pueden ser nulos");
        }
        
        // Validar que la cita existe
        if (!citaDAO.existeCita(cita.getIdCita())) {
            return new ResultadoOperacion(false, "No existe una cita con el ID: " + cita.getIdCita());
        }
        
        try {
            boolean exito = citaDAO.actualizarCita(cita);
            
            if (exito) {
                return new ResultadoOperacion(true, "Cita actualizada exitosamente");
            } else {
                return new ResultadoOperacion(false, "Error al actualizar la cita");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Eliminar una cita
     * @param idCita ID de la cita a eliminar
     * @return ResultadoOperacion con el resultado de la operación
     */
    public ResultadoOperacion eliminarCita(String idCita) {
        if (idCita == null || idCita.trim().isEmpty()) {
            return new ResultadoOperacion(false, "El ID de la cita es obligatorio");
        }
        
        try {
            // Verificar que la cita existe antes de eliminar
            if (!citaDAO.existeCita(idCita)) {
                return new ResultadoOperacion(false, "No existe una cita con el ID: " + idCita);
            }
            
            boolean exito = citaDAO.eliminarCita(idCita);
            
            if (exito) {
                return new ResultadoOperacion(true, "Cita eliminada exitosamente");
            } else {
                return new ResultadoOperacion(false, "Error al eliminar la cita");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Cambiar el estado de una cita
     * @param idCita ID de la cita
     * @param nuevoEstado Nuevo estado para la cita
     * @return ResultadoOperacion con el resultado de la operación
     */
    public ResultadoOperacion cambiarEstadoCita(String idCita, String nuevoEstado) {
        if (idCita == null || idCita.trim().isEmpty()) {
            return new ResultadoOperacion(false, "El ID de la cita es obligatorio");
        }
        
        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            return new ResultadoOperacion(false, "El nuevo estado es obligatorio");
        }
        
        // Validar que el estado es válido
        if (!esEstadoValido(nuevoEstado)) {
            return new ResultadoOperacion(false, "Estado no válido: " + nuevoEstado);
        }
        
        try {
            boolean exito = citaDAO.cambiarEstadoCita(idCita, nuevoEstado);
            
            if (exito) {
                return new ResultadoOperacion(true, "Estado de la cita actualizado exitosamente");
            } else {
                return new ResultadoOperacion(false, "Error al cambiar el estado de la cita");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, "Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Obtener todas las citas
     * @return Lista de todas las citas
     */
    public List<Cita> obtenerTodasLasCitas() {
        try {
            return citaDAO.obtenerTodasLasCitas();
        } catch (Exception e) {
            System.err.println("Error al obtener todas las citas: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Obtener citas de un paciente específico
     * @param idPaciente ID del paciente
     * @return Lista de citas del paciente
     */
    public List<Cita> obtenerCitasPorPaciente(String idPaciente) {
        if (idPaciente == null || idPaciente.trim().isEmpty()) {
            return null;
        }
        
        try {
            return citaDAO.obtenerCitasPorPaciente(idPaciente);
        } catch (Exception e) {
            System.err.println("Error al obtener citas por paciente: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Obtener citas de un médico específico
     * @param idMedico ID del médico
     * @return Lista de citas del médico
     */
    public List<Cita> obtenerCitasPorMedico(String idMedico) {
        if (idMedico == null || idMedico.trim().isEmpty()) {
            return null;
        }
        
        try {
            return citaDAO.obtenerCitasPorMedico(idMedico);
        } catch (Exception e) {
            System.err.println("Error al obtener citas por médico: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Validar los datos necesarios para crear/actualizar una cita
     */
    private ResultadoValidacion validarDatosCita(String idCita, Date fechaHora, String estado,
                                               String motivo, String idPaciente, String idMedico,
                                               String idRecepcionista) {
        
        // Validar ID Cita
        if (idCita == null || idCita.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El ID de la cita es obligatorio");
        }
        if (idCita.trim().length() > 10) {
            return new ResultadoValidacion(false, "El ID de la cita no puede tener más de 10 caracteres");
        }
        
        // Validar fecha y hora
        if (fechaHora == null) {
            return new ResultadoValidacion(false, "La fecha y hora son obligatorias");
        }
        
        // Validar estado
        if (estado == null || estado.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El estado es obligatorio");
        }
        if (!esEstadoValido(estado)) {
            return new ResultadoValidacion(false, "Estado no válido: " + estado);
        }
        
        // Validar motivo
        if (motivo == null || motivo.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El motivo de la cita es obligatorio");
        }
        
        // Validar ID Paciente
        if (idPaciente == null || idPaciente.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El ID del paciente es obligatorio");
        }
        if (idPaciente.trim().length() > 10) {
            return new ResultadoValidacion(false, "El ID del paciente no puede tener más de 10 caracteres");
        }
        
        // Validar ID Médico
        if (idMedico == null || idMedico.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El ID del médico es obligatorio");
        }
        if (idMedico.trim().length() > 10) {
            return new ResultadoValidacion(false, "El ID del médico no puede tener más de 10 caracteres");
        }
        
        // Validar ID Recepcionista
        if (idRecepcionista == null || idRecepcionista.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El ID de la recepcionista es obligatorio");
        }
        if (idRecepcionista.trim().length() > 10) {
            return new ResultadoValidacion(false, "El ID de la recepcionista no puede tener más de 10 caracteres");
        }
        
        return new ResultadoValidacion(true, "Datos válidos");
    }
    
    /**
     * Validar si un estado es válido
     */
    private boolean esEstadoValido(String estado) {
        String[] estadosValidos = {"Programada", "Confirmada", "En curso", "Completada", "Cancelada", "No asistió"};
        
        for (String estadoValido : estadosValidos) {
            if (estadoValido.equalsIgnoreCase(estado.trim())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtener los estados válidos para una cita
     * @return Array con los estados válidos
     */
    public String[] obtenerEstadosValidos() {
        return new String[]{"Programada", "Confirmada", "En curso", "Completada", "Cancelada", "No asistió"};
    }
    
    // Clases internas para manejo de resultados
    
    /**
     * Clase para representar el resultado de una operación
     */
    public static class ResultadoOperacion {
        private boolean exito;
        private String mensaje;
        
        public ResultadoOperacion(boolean exito, String mensaje) {
            this.exito = exito;
            this.mensaje = mensaje;
        }
        
        public boolean isExito() {
            return exito;
        }
        
        public String getMensaje() {
            return mensaje;
        }
    }
    
    /**
     * Clase para representar el resultado de una validación
     */
    private static class ResultadoValidacion {
        private boolean valido;
        private String mensaje;
        
        public ResultadoValidacion(boolean valido, String mensaje) {
            this.valido = valido;
            this.mensaje = mensaje;
        }
        
        public boolean esValido() {
            return valido;
        }
        
        public String getMensaje() {
            return mensaje;
        }
    }
}