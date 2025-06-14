package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import Modelo.Cita;
import Modelo.CitaDAO;
import Controlador.ControladorRegistroCita;

public class regCita extends JPanel {

    // Componentes de la interfaz
    private JTextField txtIdCita;
    private JSpinner spnFechaHora;
    private JComboBox<String> cmbEstado;
    private JTextArea txtMotivo;
    private JTextField txtIdPaciente;
    private JTextField txtIdMedico;
    private JTextField txtIdRecepcionista;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCancelar;

    // Controlador para manejar la lógica de negocio
    private ControladorRegistroCita controlador;

    public regCita() {
        controlador = new ControladorRegistroCita(this);
        initComponents();
        setupLayout();
        setupEvents();
    }

    private void initComponents() {
        // Inicializar componentes
        txtIdCita = new JTextField(10);

        // Configurar spinner para fecha y hora
        spnFechaHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnFechaHora, "dd/MM/yyyy HH:mm");
        spnFechaHora.setEditor(dateEditor);
        spnFechaHora.setValue(new Date());

        // ComboBox para estado - usar los estados del controlador
        String[] estados = controlador.obtenerEstadosValidos();
        cmbEstado = new JComboBox<>(estados);

        // Área de texto para motivo
        txtMotivo = new JTextArea(4, 20);
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);

        txtIdPaciente = new JTextField(10);
        txtIdMedico = new JTextField(10);
        txtIdRecepcionista = new JTextField(10);

        // Botones
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnCancelar = new JButton("Cancelar");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Registro de Cita Médica"));

        // Panel principal con GridBagLayout para mejor control
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ID Cita
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(new JLabel("ID Cita:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtIdCita, gbc);

        // Fecha y Hora
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(new JLabel("Fecha y Hora:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(spnFechaHora, gbc);

        // Estado
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(cmbEstado, gbc);

        // ID Paciente
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(new JLabel("ID Paciente:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtIdPaciente, gbc);

        // ID Médico
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelPrincipal.add(new JLabel("ID Médico:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtIdMedico, gbc);

        // ID Recepcionista
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelPrincipal.add(new JLabel("ID Recepcionista:"), gbc);
        gbc.gridx = 1;
        panelPrincipal.add(txtIdRecepcionista, gbc);

        // Motivo
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelPrincipal.add(new JLabel("Motivo:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelPrincipal.add(new JScrollPane(txtMotivo), gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al panel principal
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupEvents() {
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCita();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar lógica para cerrar la ventana
                // o volver al menú principal
                Container parent = getParent();
                while (parent != null && !(parent instanceof JFrame)) {
                    parent = parent.getParent();
                }
                if (parent instanceof JFrame) {
                    ((JFrame) parent).dispose();
                }
            }
        });
    }

    private void guardarCita() {
        // Validar campos básicos de la interfaz
        if (!validarCamposUI()) {
            return;
        }

        try {
            // Usar el controlador para registrar la cita
            ControladorRegistroCita.ResultadoOperacion resultado = controlador.registrarCita(
                    txtIdCita.getText().trim(),
                    (Date) spnFechaHora.getValue(),
                    (String) cmbEstado.getSelectedItem(),
                    txtMotivo.getText().trim(),
                    txtIdPaciente.getText().trim(),
                    txtIdMedico.getText().trim(),
                    txtIdRecepcionista.getText().trim()
            );

            if (resultado.isExito()) {
                JOptionPane.showMessageDialog(this,
                        resultado.getMensaje(),
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this,
                        resultado.getMensaje(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                // Si es error de ID duplicado, enfocar en el campo ID
                if (resultado.getMensaje().contains("Ya existe una cita")) {
                    txtIdCita.requestFocus();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error inesperado: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private boolean validarCamposUI() {
        // Validaciones básicas de la interfaz (campos vacíos)
        // La validación completa la hace el controlador

        if (txtIdCita.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID de la cita es obligatorio", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtIdCita.requestFocus();
            return false;
        }

        if (txtIdPaciente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID del paciente es obligatorio", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtIdPaciente.requestFocus();
            return false;
        }

        if (txtIdMedico.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID del médico es obligatorio", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtIdMedico.requestFocus();
            return false;
        }

        if (txtIdRecepcionista.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID de la recepcionista es obligatorio", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtIdRecepcionista.requestFocus();
            return false;
        }

        if (txtMotivo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El motivo de la cita es obligatorio", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            txtMotivo.requestFocus();
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        txtIdCita.setText("");
        spnFechaHora.setValue(new Date());
        cmbEstado.setSelectedIndex(0);
        txtMotivo.setText("");
        txtIdPaciente.setText("");
        txtIdMedico.setText("");
        txtIdRecepcionista.setText("");
        txtIdCita.requestFocus();
    }

    // Método para probar la clase independientemente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Registro de Citas Médicas");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new regCita());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
