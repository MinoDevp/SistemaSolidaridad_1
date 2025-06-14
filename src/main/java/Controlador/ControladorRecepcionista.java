package Controlador;

import Modelo.Recepcionista;
import Modelo.RecepcionistaDAO;
import javax.swing.JOptionPane;
import java.util.List;

public class ControladorRecepcionista {

    private RecepcionistaDAO dao;

    public ControladorRecepcionista() {
        this.dao = new RecepcionistaDAO();
    }

    /**
     * Valida los datos de entrada para un recepcionista
     *
     * @param idRecepcionista ID del recepcionista
     * @param nombre Nombre del recepcionista
     * @param apellidos Apellidos del recepcionista
     * @param credenciales Credenciales del recepcionista
     * @param horarioTrabajo Horario de trabajo
     * @return ResultadoValidacion con el resultado de la validación
     */
    public ResultadoValidacion validarDatos(String idRecepcionista, String nombre,
            String apellidos, String credenciales,
            String horarioTrabajo) {

        // Validar ID Recepcionista
        if (idRecepcionista == null || idRecepcionista.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El ID del recepcionista es obligatorio", "idRecepcionista");
        }

        if (idRecepcionista.trim().length() > 10) {
            return new ResultadoValidacion(false, "El ID del recepcionista no puede exceder 10 caracteres", "idRecepcionista");
        }

        // Validar que el ID solo contenga caracteres alfanuméricos
        if (!idRecepcionista.trim().matches("^[a-zA-Z0-9]+$")) {
            return new ResultadoValidacion(false, "El ID del recepcionista solo puede contener letras y números", "idRecepcionista");
        }

        // Validar Nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ResultadoValidacion(false, "El nombre es obligatorio", "nombre");
        }

        if (nombre.trim().length() > 50) {
            return new ResultadoValidacion(false, "El nombre no puede exceder 50 caracteres", "nombre");
        }

        // Validar que el nombre solo contenga letras y espacios
        if (!nombre.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return new ResultadoValidacion(false, "El nombre solo puede contener letras y espacios", "nombre");
        }

        // Validar Apellidos
        if (apellidos == null || apellidos.trim().isEmpty()) {
            return new ResultadoValidacion(false, "Los apellidos son obligatorios", "apellidos");
        }

        if (apellidos.trim().length() > 50) {
            return new ResultadoValidacion(false, "Los apellidos no pueden exceder 50 caracteres", "apellidos");
        }

        // Validar que los apellidos solo contengan letras y espacios
        if (!apellidos.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            return new ResultadoValidacion(false, "Los apellidos solo pueden contener letras y espacios", "apellidos");
        }

        // Validar Credenciales (opcional, pero si se proporciona debe cumplir reglas)
        if (credenciales != null && credenciales.trim().length() > 100) {
            return new ResultadoValidacion(false, "Las credenciales no pueden exceder 100 caracteres", "credenciales");
        }

        // Validar Horario de Trabajo (opcional, pero si se proporciona debe cumplir reglas)
        if (horarioTrabajo != null && horarioTrabajo.trim().length() > 50) {
            return new ResultadoValidacion(false, "El horario de trabajo no puede exceder 50 caracteres", "horarioTrabajo");
        }

        return new ResultadoValidacion(true, "Validación exitosa", null);
    }

    /**
     * Guarda un nuevo recepcionista en la base de datos
     *
     * @param idRecepcionista ID del recepcionista
     * @param nombre Nombre del recepcionista
     * @param apellidos Apellidos del recepcionista
     * @param credenciales Credenciales del recepcionista
     * @param horarioTrabajo Horario de trabajo
     * @return ResultadoOperacion con el resultado de la operación
     */
    public ResultadoOperacion guardarRecepcionista(String idRecepcionista, String nombre,
            String apellidos, String credenciales,
            String horarioTrabajo) {
        try {
            // Primero validar los datos
            ResultadoValidacion validacion = validarDatos(idRecepcionista, nombre, apellidos, credenciales, horarioTrabajo);
            if (!validacion.isExitoso()) {
                return new ResultadoOperacion(false, validacion.getMensaje(), validacion.getCampoError());
            }

            // Verificar si el ID ya existe
            if (dao.existeId(idRecepcionista.trim())) {
                return new ResultadoOperacion(false, "Ya existe un recepcionista con el ID: " + idRecepcionista, "idRecepcionista");
            }

            // Crear objeto Recepcionista
            Recepcionista recepcionista = new Recepcionista(
                    idRecepcionista.trim(),
                    nombre.trim(),
                    apellidos.trim(),
                    credenciales != null ? credenciales.trim() : "",
                    horarioTrabajo != null ? horarioTrabajo.trim() : ""
            );

            // Intentar insertar en la base de datos
            if (dao.insertar(recepcionista)) {
                return new ResultadoOperacion(true, "Recepcionista guardado exitosamente", null);
            } else {
                return new ResultadoOperacion(false, "No se pudo guardar el recepcionista. Verifique los datos e intente nuevamente.", null);
            }

        } catch (Exception ex) {
            return new ResultadoOperacion(false, "Error al guardar: " + ex.getMessage(), null);
        }
    }

    /**
     * Actualiza un recepcionista existente
     */
    public ResultadoOperacion actualizarRecepcionista(String idRecepcionista, String nombre,
            String apellidos, String credenciales,
            String horarioTrabajo) {
        try {
            // Validar los datos (excluyendo la validación de ID existente)
            ResultadoValidacion validacion = validarDatos(idRecepcionista, nombre, apellidos, credenciales, horarioTrabajo);
            if (!validacion.isExitoso()) {
                return new ResultadoOperacion(false, validacion.getMensaje(), validacion.getCampoError());
            }

            // Verificar si el recepcionista existe
            if (!dao.existeId(idRecepcionista.trim())) {
                return new ResultadoOperacion(false, "No existe un recepcionista con el ID: " + idRecepcionista, "idRecepcionista");
            }

            // Crear objeto Recepcionista
            Recepcionista recepcionista = new Recepcionista(
                    idRecepcionista.trim(),
                    nombre.trim(),
                    apellidos.trim(),
                    credenciales != null ? credenciales.trim() : "",
                    horarioTrabajo != null ? horarioTrabajo.trim() : ""
            );

            // Intentar actualizar en la base de datos
            if (dao.actualizar(recepcionista)) {
                return new ResultadoOperacion(true, "Recepcionista actualizado exitosamente", null);
            } else {
                return new ResultadoOperacion(false, "No se pudo actualizar el recepcionista.", null);
            }

        } catch (Exception ex) {
            return new ResultadoOperacion(false, "Error al actualizar: " + ex.getMessage(), null);
        }
    }

    /**
     * Obtiene un recepcionista por su ID
     */
    public Recepcionista obtenerRecepcionista(String idRecepcionista) {
        try {
            if (idRecepcionista == null || idRecepcionista.trim().isEmpty()) {
                return null;
            }
            return dao.obtenerPorId(idRecepcionista.trim());
        } catch (Exception ex) {
            System.err.println("Error al obtener recepcionista: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los recepcionistas
     */
    public List<Recepcionista> obtenerTodosLosRecepcionistas() {
        try {
            return dao.obtenerTodos();
        } catch (Exception ex) {
            System.err.println("Error al obtener lista de recepcionistas: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Elimina un recepcionista
     */
    public ResultadoOperacion eliminarRecepcionista(String idRecepcionista) {
        try {
            if (idRecepcionista == null || idRecepcionista.trim().isEmpty()) {
                return new ResultadoOperacion(false, "El ID del recepcionista es requerido", "idRecepcionista");
            }

            // Verificar si el recepcionista existe
            if (!dao.existeId(idRecepcionista.trim())) {
                return new ResultadoOperacion(false, "No existe un recepcionista con el ID: " + idRecepcionista, "idRecepcionista");
            }

            if (dao.eliminar(idRecepcionista.trim())) {
                return new ResultadoOperacion(true, "Recepcionista eliminado exitosamente", null);
            } else {
                return new ResultadoOperacion(false, "No se pudo eliminar el recepcionista.", null);
            }

        } catch (Exception ex) {
            return new ResultadoOperacion(false, "Error al eliminar: " + ex.getMessage(), null);
        }
    }

    /**
     * Busca recepcionistas por nombre o apellido
     */
    public List<Recepcionista> buscarRecepcionistas(String termino) {
        try {
            if (termino == null || termino.trim().isEmpty()) {
                return obtenerTodosLosRecepcionistas();
            }
            return dao.buscarPorNombre(termino.trim());
        } catch (Exception ex) {
            System.err.println("Error al buscar recepcionistas: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Obtiene el total de recepcionistas registrados
     */
    public int obtenerTotalRecepcionistas() {
        try {
            return dao.obtenerTotal();
        } catch (Exception ex) {
            System.err.println("Error al obtener total: " + ex.getMessage());
            return 0;
        }
    }

    // ========== CLASES INTERNAS PARA RESULTADOS ==========
    /**
     * Clase para manejar resultados de validación
     */
    public static class ResultadoValidacion {

        private boolean exitoso;
        private String mensaje;
        private String campoError;

        public ResultadoValidacion(boolean exitoso, String mensaje, String campoError) {
            this.exitoso = exitoso;
            this.mensaje = mensaje;
            this.campoError = campoError;
        }

        public boolean isExitoso() {
            return exitoso;
        }

        public String getMensaje() {
            return mensaje;
        }

        public String getCampoError() {
            return campoError;
        }
    }

    /**
     * Clase para manejar resultados de operaciones
     */
    public static class ResultadoOperacion {

        private boolean exitoso;
        private String mensaje;
        private String campoError;

        public ResultadoOperacion(boolean exitoso, String mensaje, String campoError) {
            this.exitoso = exitoso;
            this.mensaje = mensaje;
            this.campoError = campoError;
        }

        public boolean isExitoso() {
            return exitoso;
        }

        public String getMensaje() {
            return mensaje;
        }

        public String getCampoError() {
            return campoError;
        }
    }
}
