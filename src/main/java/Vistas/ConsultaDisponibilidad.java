package Vistas;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import com.toedter.calendar.JCalendar;

public class ConsultaDisponibilidad extends JPanel {

    private JComboBox<String> comboEspecialidad;
    private JComboBox<String> comboMedico;
    private JCalendar calendario;
    private JPanel panelHorarios;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private LocalDate fechaSeleccionada;
    private final Map<String, String[]> medicosPorEspecialidad;

    public ConsultaDisponibilidad() {

        setLayout(new BorderLayout(10, 10));

        // Inicializar datos de especialidades y médicos
        medicosPorEspecialidad = new HashMap<>();
        medicosPorEspecialidad.put("Medicina General", new String[]{"Dr. Juan Pérez", "Dra. María García"});
        medicosPorEspecialidad.put("Cardiología", new String[]{"Dr. Carlos Rodríguez", "Dra. Ana Martínez"});
        medicosPorEspecialidad.put("Pediatría", new String[]{"Dr. Luis Fernández", "Dra. Sofía Ramírez"});
        medicosPorEspecialidad.put("Dermatología", new String[]{"Dr. Andrés Castro", "Dra. Paula Muñoz"});
        medicosPorEspecialidad.put("Traumatología", new String[]{"Dr. Enrique Gómez", "Dra. Laura Torres"});

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de filtros
        mainPanel.add(crearPanelFiltros(), BorderLayout.NORTH);

        // Panel central con calendario y horarios
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                crearPanelCalendario(),
                crearPanelHorarios());
        splitPane.setDividerLocation(400);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        add(mainPanel);

    }

    private JPanel crearPanelFiltros() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Filtros de Búsqueda"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Especialidad
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Especialidad:"), gbc);
        comboEspecialidad = new JComboBox<>();
        comboEspecialidad.addItem("Seleccione especialidad");
        medicosPorEspecialidad.keySet().forEach(comboEspecialidad::addItem);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(comboEspecialidad, gbc);

        // Médico
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        panel.add(new JLabel("Médico:"), gbc);
        comboMedico = new JComboBox<>();
        comboMedico.addItem("Seleccione médico");
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        panel.add(comboMedico, gbc);

        // Listener para cambio de especialidad
        comboEspecialidad.addActionListener(e -> actualizarMedicos());

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarDisponibilidad());
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarFiltros());

        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        panel.add(panelBotones, gbc);

        return panel;
    }

    private JPanel crearPanelCalendario() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Calendario"));

        // Inicializar JCalendar correctamente
        calendario = new JCalendar();
        calendario.addPropertyChangeListener("calendar", evt -> {
            java.util.Calendar calendar = (java.util.Calendar) evt.getNewValue();
            fechaSeleccionada = LocalDate.of(calendar.get(java.util.Calendar.YEAR),
                    calendar.get(java.util.Calendar.MONTH) + 1,
                    calendar.get(java.util.Calendar.DAY_OF_MONTH));
        });

        panel.add(calendario, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelHorarios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Horarios Disponibles"));

        panelHorarios = new JPanel();
        panelHorarios.setLayout(new BoxLayout(panelHorarios, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panelHorarios);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void actualizarMedicos() {
        comboMedico.removeAllItems();
        comboMedico.addItem("Seleccione médico");

        String especialidad = (String) comboEspecialidad.getSelectedItem();
        if (especialidad != null && medicosPorEspecialidad.containsKey(especialidad)) {
            for (String medico : medicosPorEspecialidad.get(especialidad)) {
                comboMedico.addItem(medico);
            }
        }
    }

    private void buscarDisponibilidad() {
        if (comboEspecialidad.getSelectedIndex() == 0 || comboMedico.getSelectedIndex() == 0) {
            mostrarError("Debe seleccionar especialidad y médico");
            return;
        }

        panelHorarios.removeAll();
        LocalTime horaInicio = LocalTime.of(8, 0);

        for (int i = 0; i < 8; i++) {
            LocalTime hora = horaInicio.plusHours(i);
            JPanel horarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            horarioPanel.add(new JLabel(hora.format(DateTimeFormatter.ofPattern("HH:mm"))));

            JButton btnSeleccionar = new JButton("Seleccionar");
            btnSeleccionar.addActionListener(e -> seleccionarHorario(hora));
            horarioPanel.add(btnSeleccionar);

            panelHorarios.add(horarioPanel);
        }

        panelHorarios.revalidate();
        panelHorarios.repaint();
    }

    private void limpiarFiltros() {
        comboEspecialidad.setSelectedIndex(0);
        comboMedico.setSelectedIndex(0);
        panelHorarios.removeAll();
        panelHorarios.revalidate();
        panelHorarios.repaint();
    }

    private void seleccionarHorario(LocalTime hora) {
        if (fechaSeleccionada == null) {
            mostrarError("Seleccione una fecha en el calendario");
            return;
        }

        String mensaje = String.format("¿Desea reservar la cita para el día %s a las %s?",
                fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                hora.format(DateTimeFormatter.ofPattern("HH:mm")));

        int opcion = JOptionPane.showConfirmDialog(this,
                mensaje,
                "Confirmar horario",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Horario reservado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
