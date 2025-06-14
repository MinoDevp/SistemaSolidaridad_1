package Controlador;

import Modelo.Paciente;
import Modelo.PacienteDAO;
import Vistas.regPacie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ControladorRegistroPaciente {
    private regPacie vista;
    private PacienteDAO modelo;
    
    // Constructor normal (desde winPrincipal)
    public ControladorRegistroPaciente(regPacie vista) {
        this(vista, new PacienteDAO()); 
    }

    // Constructor para testing o inyección de dependencias
    public ControladorRegistroPaciente(regPacie vista, PacienteDAO modelo) {
        this.vista = vista;
        this.modelo = modelo;
        configurarEventos();
    }

    private void configurarEventos() {
        // Listener para el botón registrar
        vista.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPaciente();
            }
        });
    }

    public void registrarPaciente() {
        try {
            // 1. Validar campos obligatorios
            if (!vista.validarCamposObligatorios()) {
                vista.mostrarMensajeError("Complete todos los campos obligatorios");
                return;
            }

            // 2. Obtener datos de la vista
            String idPaciente = vista.getIdPaciente();
            String dni = vista.getDNI();
            String fechaNacimientoStr = vista.getFechaNacimiento();
            String grupoSanguineo = vista.getGrupoSanguineo();
            String alergias = vista.getAlergias();

            // 3. Validar ID de paciente
            if (!vista.validarIdPaciente(idPaciente)) {
                vista.mostrarMensajeError("El ID del paciente debe tener entre 3-10 caracteres alfanuméricos\nEjemplo: P001, PAC001, etc.");
                return;
            }

            // 4. Verificar si el ID de paciente ya existe
            if (modelo.existeIdPaciente(idPaciente)) {
                vista.mostrarMensajeError("Ya existe un paciente registrado con este ID: " + idPaciente);
                return;
            }

            // 5. Validar DNI
            if (!vista.validarDNI(dni)) {
                vista.mostrarMensajeError("El DNI debe tener entre 8 y 15 dígitos numéricos");
                return;
            }

            // 6. Verificar si el DNI ya existe
            if (modelo.existeDNI(dni)) {
                vista.mostrarMensajeError("Ya existe un paciente registrado con este DNI");
                return;
            }

            // 7. Validar y parsear fecha de nacimiento
            LocalDate fechaNacimiento = validarFechaNacimiento(fechaNacimientoStr);
            if (fechaNacimiento == null) {
                return; // El error ya fue mostrado en validarFechaNacimiento
            }

            // 8. Crear el paciente
            Paciente paciente = new Paciente(idPaciente, dni, fechaNacimiento, grupoSanguineo, alergias);

            // 9. Guardar en la base de datos
            boolean exito = modelo.insertarPaciente(
                paciente.getIdPaciente(),
                paciente.getDni(),
                paciente.getFechaNacimiento(),
                paciente.getGrupoSanguineo(),
                paciente.getAlergias()
            );

            // 10. Mostrar resultado
            if (exito) {
                vista.mostrarMensajeExito("Paciente registrado correctamente\nID: " + idPaciente);
                vista.limpiarCampos();
            } else {
                vista.mostrarMensajeError("Error al registrar paciente. Verifique la conexión a la base de datos.");
            }

        } catch (Exception e) {
            vista.mostrarMensajeError("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para obtener la vista (útil para testing o integración)
    public regPacie getVista() {
        return vista;
    }

    // Método para obtener el modelo (útil para testing)
    public PacienteDAO getModelo() {
        return modelo;
    }

    private LocalDate validarFechaNacimiento(String fechaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

            // Validar que la fecha no sea futura
            if (fecha.isAfter(LocalDate.now())) {
                vista.mostrarMensajeError("La fecha de nacimiento no puede ser futura");
                return null;
            }

            // Validar edad razonable (no mayor a 120 años)
            if (fecha.isBefore(LocalDate.now().minusYears(120))) {
                vista.mostrarMensajeError("La fecha de nacimiento no es válida (muy antigua)");
                return null;
            }

            // Validar edad mínima (al menos nacido)
            if (fecha.isAfter(LocalDate.now().minusDays(1))) {
                vista.mostrarMensajeError("La fecha de nacimiento debe ser anterior a hoy");
                return null;
            }

            return fecha;

        } catch (DateTimeParseException e) {
            vista.mostrarMensajeError("Formato de fecha inválido. Use YYYY/MM/DD\nEjemplo: 1990/12/25");
            return null;
        }
    }

    // Método para validar datos sin registrar (útil para validaciones en tiempo real)
    public boolean validarDatos() {
        return vista.validarCamposObligatorios() && 
               vista.validarIdPaciente(vista.getIdPaciente()) &&
               vista.validarDNI(vista.getDNI()) &&
               validarFechaNacimiento(vista.getFechaNacimiento()) != null;
    }
}