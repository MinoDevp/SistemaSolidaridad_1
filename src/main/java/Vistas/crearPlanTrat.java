/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class crearPlanTrat extends JPanel{
    private JTextField txtBusquedaPaciente;
    private JTextField txtPaciente;
    private JTextField txtDiagnostico;
    private JComboBox<String> comboMedicamentos;
    private JSpinner spinnerCantidad;
    private JComboBox<String> comboDosis;
    private JSpinner spinnerDuracion;
    private JComboBox<String> comboPeriodo;
    private JTable tablaMedicamentos;
    private DefaultTableModel modeloMedicamentos;
    private JTextArea txtIndicaciones;
    private JButton btnAgregarMedicamento;
    private JButton btnRegistrar;

    public crearPlanTrat() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(crearPanelPaciente(), BorderLayout.NORTH);
        add(crearPanelTratamiento(), BorderLayout.CENTER);
        add(crearPanelBotones(), BorderLayout.SOUTH);

        inicializarComponentes();
    }

    private JPanel crearPanelPaciente() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Información del Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("DNI del paciente:"), gbc);
        txtBusquedaPaciente = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtBusquedaPaciente, gbc);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarPaciente());
        gbc.gridx = 2;
        panel.add(btnBuscar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Paciente:"), gbc);
        txtPaciente = new JTextField(30);
        txtPaciente.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(txtPaciente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Diagnóstico:"), gbc);
        txtDiagnostico = new JTextField(30);
        txtDiagnostico.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(txtDiagnostico, gbc);

        return panel;
    }

    private JPanel crearPanelTratamiento() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Medicamentos"));

        JPanel panelAgregar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        comboMedicamentos = new JComboBox<>();
        panelAgregar.add(new JLabel("Medicamento:"));
        panelAgregar.add(comboMedicamentos);

        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        panelAgregar.add(new JLabel("Cantidad:"));
        panelAgregar.add(spinnerCantidad);

        comboDosis = new JComboBox<>(new String[]{"Cada 8 horas", "Cada 12 horas", "Una vez al día"});
        panelAgregar.add(new JLabel("Dosis:"));
        panelAgregar.add(comboDosis);

        spinnerDuracion = new JSpinner(new SpinnerNumberModel(1, 1, 90, 1));
        panelAgregar.add(new JLabel("Duración:"));
        panelAgregar.add(spinnerDuracion);

        comboPeriodo = new JComboBox<>(new String[]{"Días", "Semanas", "Meses"});
        panelAgregar.add(comboPeriodo);

        btnAgregarMedicamento = new JButton("Agregar");
        btnAgregarMedicamento.addActionListener(e -> agregarMedicamento());
        panelAgregar.add(btnAgregarMedicamento);

        panel.add(panelAgregar, BorderLayout.NORTH);

        String[] columnNames = {"Medicamento", "Cantidad", "Dosis", "Duración"};
        modeloMedicamentos = new DefaultTableModel(columnNames, 0);
        tablaMedicamentos = new JTable(modeloMedicamentos);
        panel.add(new JScrollPane(tablaMedicamentos), BorderLayout.CENTER);

        txtIndicaciones = new JTextArea(4, 50);
        txtIndicaciones.setLineWrap(true);
        JScrollPane scrollIndicaciones = new JScrollPane(txtIndicaciones);
        scrollIndicaciones.setBorder(BorderFactory.createTitledBorder("Indicaciones Adicionales"));
        panel.add(scrollIndicaciones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnRegistrar = new JButton("Registrar Plan");
        btnRegistrar.addActionListener(e -> registrarPlan());
        btnRegistrar.setEnabled(false);
        panel.add(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> cancelarOperacion());
        panel.add(btnCancelar);

        return panel;
    }

    private void inicializarComponentes() {
        String[] medicamentos = {
            "Paracetamol", "Ibuprofeno", "Amoxicilina",
            "Aspirina", "Omeprazol", "Metformina"
        };
        for (String med : medicamentos) {
            comboMedicamentos.addItem(med);
        }
    }

    private void buscarPaciente() {
        String dniBusqueda = txtBusquedaPaciente.getText().trim();

        if (dniBusqueda.isEmpty()) {
            mostrarError("Ingrese un DNI o número de historia clínica");
            return;
        }

        if (dniBusqueda.equals("12345678")) {
            txtPaciente.setText("Juan Pérez García");
            txtDiagnostico.setText("Hipertensión Arterial");
            btnRegistrar.setEnabled(true);
        } else {
            mostrarError("Paciente no encontrado");
            limpiarCamposPaciente();
        }
    }

    private void agregarMedicamento() {
        String medicamento = (String) comboMedicamentos.getSelectedItem();
        int cantidad = (int) spinnerCantidad.getValue();
        String dosis = (String) comboDosis.getSelectedItem();
        int duracion = (int) spinnerDuracion.getValue();
        String periodo = (String) comboPeriodo.getSelectedItem();

        Vector<String> fila = new Vector<>();
        fila.add(medicamento);
        fila.add(String.valueOf(cantidad));
        fila.add(dosis);
        fila.add(duracion + " " + periodo);

        modeloMedicamentos.addRow(fila);
    }

    private void registrarPlan() {
        if (txtPaciente.getText().isEmpty()) {
            mostrarError("Debe buscar un paciente");
            return;
        }

        if (modeloMedicamentos.getRowCount() == 0) {
            mostrarError("Debe agregar al menos un medicamento");
            return;
        }

        StringBuilder planTratamiento = new StringBuilder();
        planTratamiento.append("Plan de Tratamiento para: ").append(txtPaciente.getText()).append("\n");
        planTratamiento.append("Diagnóstico: ").append(txtDiagnostico.getText()).append("\n\n");

        planTratamiento.append("Medicamentos:\n");
        for (int i = 0; i < modeloMedicamentos.getRowCount(); i++) {
            planTratamiento.append("- ").append(modeloMedicamentos.getValueAt(i, 0)).append(": ")
                    .append(modeloMedicamentos.getValueAt(i, 1)).append(" - ")
                    .append(modeloMedicamentos.getValueAt(i, 2)).append(" - ")
                    .append(modeloMedicamentos.getValueAt(i, 3)).append("\n");
        }

        planTratamiento.append("\nIndicaciones adicionales:\n")
                .append(txtIndicaciones.getText());

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Confirma el registro del plan de tratamiento?",
                "Confirmar Plan",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                    this,
                    "Plan de tratamiento registrado exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpiarCamposPaciente();
            modeloMedicamentos.setRowCount(0);
            txtIndicaciones.setText("");
        }
    }

    private void cancelarOperacion() {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cancelar el registro del plan?",
                "Cancelar",
                JOptionPane.YES_NO_OPTION
        );
        if (respuesta == JOptionPane.YES_OPTION) {
            limpiarCamposPaciente();
            modeloMedicamentos.setRowCount(0);
            txtIndicaciones.setText("");
        }
    }

    private void limpiarCamposPaciente() {
        txtPaciente.setText("");
        txtDiagnostico.setText("");
        btnRegistrar.setEnabled(false);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
