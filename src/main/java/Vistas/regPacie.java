package Vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class regPacie extends JPanel {

    private JTextField txtIdPaciente;
    private JTextField txtDNI;
    private JFormattedTextField txtFechaNacimiento;
    private JComboBox<String> comboGrupoSanguineo;
    private JTextArea txtAlergias;
    private JButton btnRegistrar, btnCancelar;

    public regPacie() {
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(crearPanelFormulario(), gbc);

        gbc.gridy = 1;
        mainPanel.add(crearPanelBotones(), gbc);

        JScrollPane scroll = new JScrollPane(mainPanel);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Registro de Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID Paciente
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID Paciente: "), gbc);
        txtIdPaciente = new JTextField(10);
        txtIdPaciente.setToolTipText("Ejemplo: P001, P002, etc.");
        gbc.gridx = 1;
        panel.add(txtIdPaciente, gbc);

        // DNI
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("DNI: "), gbc);
        txtDNI = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtDNI, gbc);

        // Fecha de nacimiento
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Fecha de Nacimiento (YYYY/MM/DD): "), gbc);
        try {
            MaskFormatter formatter = new MaskFormatter("####/##/##");
            formatter.setPlaceholderCharacter('_');
            txtFechaNacimiento = new JFormattedTextField(formatter);
        } catch (Exception e) {
            txtFechaNacimiento = new JFormattedTextField();
        }
        gbc.gridx = 1;
        panel.add(txtFechaNacimiento, gbc);

        // Grupo sanguíneo
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Grupo Sanguíneo: "), gbc);
        comboGrupoSanguineo = new JComboBox<>(new String[]{
                "Seleccionar", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
        });
        gbc.gridx = 1;
        panel.add(comboGrupoSanguineo, gbc);

        // Alergias
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Alergias:"), gbc);
        txtAlergias = new JTextArea(3, 20);
        txtAlergias.setLineWrap(true);
        txtAlergias.setWrapStyleWord(true);
        JScrollPane scrollAlergias = new JScrollPane(txtAlergias);
        gbc.gridx = 1;
        panel.add(scrollAlergias, gbc);

        // Nota de campos obligatorios
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JLabel lblNota = new JLabel("* Campos obligatorios");
        lblNota.setFont(lblNota.getFont().deriveFont(10f));
        panel.add(lblNota, gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout());

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> limpiarCampos());

        panel.add(btnRegistrar);
        panel.add(btnCancelar);

        return panel;
    }

    // Método para limpiar campos (manejado por la vista)
    public void limpiarCampos() {
        txtIdPaciente.setText("");
        txtDNI.setText("");
        txtFechaNacimiento.setText("");
        comboGrupoSanguineo.setSelectedIndex(0);
        txtAlergias.setText("");
    }

    // Métodos de validación que puede usar el controlador
    public boolean validarCamposObligatorios() {
        String idPaciente = txtIdPaciente.getText().trim();
        String dni = txtDNI.getText().trim();
        String fechaNacimientoStr = txtFechaNacimiento.getText().trim();
        String grupo = comboGrupoSanguineo.getSelectedItem().toString();

        return !idPaciente.isEmpty() && !dni.isEmpty() && !fechaNacimientoStr.isEmpty() && 
               !fechaNacimientoStr.contains("_") && !grupo.equals("Seleccionar");
    }

    public boolean validarDNI(String dni) {
        return dni.length() >= 8 && dni.length() <= 15 && dni.matches("\\d+");
    }

    public boolean validarIdPaciente(String idPaciente) {
        // Validar formato del ID: debe tener entre 3-10 caracteres, puede contener letras y números
        return idPaciente.length() >= 3 && idPaciente.length() <= 10 && 
               idPaciente.matches("[A-Za-z0-9]+");
    }

    // Métodos para mostrar mensajes
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    // Getters para el controlador
    public JTextField getTxtIdPaciente() {
        return txtIdPaciente;
    }

    public JTextField getTxtDNI() {
        return txtDNI;
    }

    public JFormattedTextField getTxtFechaNacimiento() {
        return txtFechaNacimiento;
    }

    public JComboBox<String> getComboGrupoSanguineo() {
        return comboGrupoSanguineo;
    }

    public JTextArea getTxtAlergias() {
        return txtAlergias;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    // Métodos de conveniencia para obtener valores
    public String getIdPaciente() {
        return txtIdPaciente.getText().trim().toUpperCase();
    }

    public String getDNI() {
        return txtDNI.getText().trim();
    }

    public String getFechaNacimiento() {
        return txtFechaNacimiento.getText().trim();
    }

    public String getGrupoSanguineo() {
        return comboGrupoSanguineo.getSelectedItem().toString();
    }

    public String getAlergias() {
        String alergias = txtAlergias.getText().trim();
        return alergias.isEmpty() ? "Ninguna" : alergias;
    }
}