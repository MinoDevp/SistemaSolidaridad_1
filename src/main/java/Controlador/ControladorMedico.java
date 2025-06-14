package Controlador;

import Modelo.Medico;
import Modelo.MedicoDAO;
import javax.swing.JOptionPane;
import java.util.List;
/*** Controlador para manejar la lógica de negocio de Médico * Actúa como intermediario entre la Vista y el Modelo * @author Usuario*/
public class ControladorMedico {
    
    private MedicoDAO medicoDAO;
    
    public ControladorMedico() {
        this.medicoDAO = new MedicoDAO();
    }  
    public ControladorMedico(MedicoDAO medicoDAO) {
    this.medicoDAO = medicoDAO;
}

    
    /*** Registra un nuevo médico en el sistema* @param medico Objeto Medico a registrar* @return ResultadoOperacion con el resultado de la operación*/
    public ResultadoOperacion registrarMedico(Medico medico) {
        try {
            // Validar datos de entrada
            ResultadoOperacion validacion = validarDatosMedico(medico);
            if (!validacion.isExito()) {
                return validacion;
            }          
            // Verificar si ya existe el médico
            if (medicoDAO.existeMedico(medico.getIdMedico())) {
                return new ResultadoOperacion(false, 
                    "Ya existe un médico registrado con el ID: " + medico.getIdMedico());
            }
            
            // Intentar registrar el médico
            boolean registrado = medicoDAO.insertarMedico(medico);
            
            if (registrado) {
                return new ResultadoOperacion(true, 
                    "Médico registrado exitosamente:\n" +
                    "ID: " + medico.getIdMedico() + "\n" +
                    "Nombre: " + medico.getNombreCompleto() + "\n" +
                    "Especialidad: " + medico.getEspecialidad());
            } else {
                return new ResultadoOperacion(false, 
                    "No se pudo registrar el médico. Intente nuevamente.");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, 
                "Error al registrar médico: " + e.getMessage());
        }
    }   
    /**
     * Actualiza los datos de un médico existent * @param medico Objeto Medico con los datos actualizados * @return ResultadoOperacion con el resultado de la operación */
    public ResultadoOperacion actualizarMedico(Medico medico) {
        try {
            // Validar datos de entrada
            ResultadoOperacion validacion = validarDatosMedico(medico);
            if (!validacion.isExito()) {
                return validacion;
            }
            
            // Verificar si existe el médico
            if (!medicoDAO.existeMedico(medico.getIdMedico())) {
                return new ResultadoOperacion(false, 
                    "No existe un médico con el ID: " + medico.getIdMedico());
            }
            
            // Intentar actualizar el médico
            boolean actualizado = medicoDAO.actualizarMedico(medico);
            
            if (actualizado) {
                return new ResultadoOperacion(true, 
                    "Médico actualizado exitosamente");
            } else {
                return new ResultadoOperacion(false, 
                    "No se pudo actualizar el médico. Intente nuevamente.");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, 
                "Error al actualizar médico: " + e.getMessage());
        }
    }    
    /**
     * Elimina un médico del sistema
     * @param idMedico ID del médico a eliminar
     * @return ResultadoOperacion con el resultado de la operación
     */
    public ResultadoOperacion eliminarMedico(String idMedico) {
        try {
            // Validar ID
            if (idMedico == null || idMedico.trim().isEmpty()) {
                return new ResultadoOperacion(false, "El ID del médico es requerido");
            }
            
            // Verificar si existe el médico
            Medico medico = medicoDAO.buscarMedicoPorId(idMedico);
            if (medico == null) {
                return new ResultadoOperacion(false, 
                    "No existe un médico con el ID: " + idMedico);
            }
            
            // Intentar eliminar el médico
            boolean eliminado = medicoDAO.eliminarMedico(idMedico);
            
            if (eliminado) {
                return new ResultadoOperacion(true, 
                    "Médico eliminado exitosamente:\n" + medico.getNombreCompleto());
            } else {
                return new ResultadoOperacion(false, 
                    "No se pudo eliminar el médico. Intente nuevamente.");
            }
            
        } catch (Exception e) {
            return new ResultadoOperacion(false, 
                "Error al eliminar médico: " + e.getMessage());
        }
    }  
    /**
     * Busca un médico por su ID
     * @param idMedico ID del médico a buscar
     * @return Objeto Medico encontrado o null si no existe
     */
    public Medico buscarMedico(String idMedico) {
        try {
            if (idMedico == null || idMedico.trim().isEmpty()) {
                return null;
            }
            return medicoDAO.buscarMedicoPorId(idMedico.trim());
        } catch (Exception e) {
            System.err.println("Error al buscar médico: " + e.getMessage());
            return null;
        }
    } 
    /**
     * Obtiene la lista de todos los médicos registrados
     * @return Lista de médicos
     */
    public List<Medico> obtenerTodosMedicos() {
        try {
            return medicoDAO.obtenerTodosMedicos();
        } catch (Exception e) {
            System.err.println("Error al obtener médicos: " + e.getMessage());
            return List.of(); // Lista vacía en caso de error
        }
    }   
    /**
     * Busca médicos por especialidad
     * @param especialidad Especialidad a buscar
     * @return Lista de médicos de la especialidad especificada
     */
    public List<Medico> buscarMedicosPorEspecialidad(String especialidad) {
        try {
            if (especialidad == null || especialidad.trim().isEmpty()) {
                return List.of();
            }
            return medicoDAO.buscarMedicosPorEspecialidad(especialidad.trim());
        } catch (Exception e) {
            System.err.println("Error al buscar médicos por especialidad: " + e.getMessage());
            return List.of();
        }
    }   
    /**
     * Obtiene el número total de médicos registrados
     * @return Número total de médicos
     */
    public int obtenerTotalMedicos() {
        try {
            return medicoDAO.contarMedicos();
        } catch (Exception e) {
            System.err.println("Error al contar médicos: " + e.getMessage());
            return 0;
        }
    }  
    /**
     * Verifica si existe un médico con el ID especificado
     * @param idMedico ID del médico a verificar
     * @return true si existe, false si no existe
     */
    public boolean existeMedico(String idMedico) {
        try {
            if (idMedico == null || idMedico.trim().isEmpty()) {
                return false;
            }
            return medicoDAO.existeMedico(idMedico.trim());
        } catch (Exception e) {
            System.err.println("Error al verificar existencia de médico: " + e.getMessage());
            return false;
        }
    }  
    /**
     * Valida los datos de un médico antes de procesarlo
     * @param medico Médico a validar
     * @return ResultadoOperacion con el resultado de la validación
     */
    private ResultadoOperacion validarDatosMedico(Medico medico) {
        if (medico == null) {
            return new ResultadoOperacion(false, "Los datos del médico son requeridos");
        }
        
        StringBuilder errores = new StringBuilder();
        
        // Validar ID Médico
        if (medico.getIdMedico() == null || medico.getIdMedico().trim().isEmpty()) {
            errores.append("• El ID del médico es obligatorio\n");
        } else if (medico.getIdMedico().trim().length() > 10) {
            errores.append("• El ID del médico no puede exceder 10 caracteres\n");
        }
        
        // Validar Nombre
        if (medico.getNombre() == null || medico.getNombre().trim().isEmpty()) {
            errores.append("• El nombre es obligatorio\n");
        } else if (medico.getNombre().trim().length() > 50) {
            errores.append("• El nombre no puede exceder 50 caracteres\n");
        }
        
        // Validar Apellidos
        if (medico.getApellidos() == null || medico.getApellidos().trim().isEmpty()) {
            errores.append("• Los apellidos son obligatorios\n");
        } else if (medico.getApellidos().trim().length() > 50) {
            errores.append("• Los apellidos no pueden exceder 50 caracteres\n");
        }
        
        // Validar Credenciales (opcional pero con límite)
        if (medico.getCredenciales() != null && medico.getCredenciales().trim().length() > 100) {
            errores.append("• Las credenciales no pueden exceder 100 caracteres\n");
        }
        
        // Validar Especialidad
        if (medico.getEspecialidad() == null || medico.getEspecialidad().trim().isEmpty()) {
            errores.append("• La especialidad es obligatoria\n");
        } else if (medico.getEspecialidad().trim().length() > 50) {
            errores.append("• La especialidad no puede exceder 50 caracteres\n");
        }
        
        // Validar Horario de Consulta (opcional pero con límite)
        if (medico.getHorarioConsulta() != null && medico.getHorarioConsulta().trim().length() > 50) {
            errores.append("• El horario de consulta no puede exceder 50 caracteres\n");
        }
        
        if (errores.length() > 0) {
            return new ResultadoOperacion(false, 
                "Por favor corrija los siguientes errores:\n\n" + errores.toString());
        }
        
        return new ResultadoOperacion(true, "Validación exitosa");
    }
    
    /**
     * Genera un nuevo ID de médico automáticamente
     * @return Nuevo ID de médico único
     */
    public String generarIdMedico() {
        try {
            int totalMedicos = medicoDAO.contarMedicos();
            String nuevoId;
            
            do {
                totalMedicos++;
                nuevoId = String.format("MED%04d", totalMedicos);
            } while (medicoDAO.existeMedico(nuevoId));
            
            return nuevoId;
        } catch (Exception e) {
            System.err.println("Error al generar ID de médico: " + e.getMessage());
            return "MED0001"; // ID por defecto en caso de error
        }
    }
    
    /**
     * Clase interna para representar el resultado de una operación
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
        /**
         * Muestra el resultado en un JOptionPane
         */
        public void mostrarMensaje() {
            if (exito) {
                JOptionPane.showMessageDialog(null, mensaje, "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, mensaje, "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }       
        /**
         * Muestra el resultado en un JOptionPane con tipo específico
         */
        public void mostrarMensaje(int tipoMensaje) {
            JOptionPane.showMessageDialog(null, mensaje, 
                exito ? "Éxito" : "Error", tipoMensaje);
        }
    }
}