/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Daniel
 */
public class crearHistoriaClinica extends JPanel{
        private JTextField txtBusqueda, txtPaciente, txtFecha;
    private JTextArea txtMotivo, txtAntecedentes, txtExamenFisico, txtDiagnostico, txtTratamiento;
    private JTextField txtPeso, txtAltura, txtPresion, txtTemperatura;
    private JButton btnBuscar, btnGuardar, btnImprimir, btnCancelar;

    public crearHistoriaClinica() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior de búsqueda
        add(crearPanelBusqueda(), BorderLayout.NORTH);

        // Panel central con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Datos de Consulta", crearPanelDatosConsulta());
        tabbedPane.addTab("Examen Físico", crearPanelExamenFisico());
        tabbedPane.addTab("Diagnóstico y Tratamiento", crearPanelDiagnosticoTratamiento());
        add(tabbedPane, BorderLayout.CENTER);

        // Panel inferior con botones
        add(crearPanelBotones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Búsqueda de Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("DNI:"), gbc);

        txtBusqueda = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtBusqueda, gbc);

        btnBuscar = new JButton("Buscar");
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
        panel.add(new JLabel("Fecha:"), gbc);

        txtFecha = new JTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(LocalDateTime.now()));
        txtFecha.setEditable(false);
        gbc.gridx = 1;
        panel.add(txtFecha, gbc);

        return panel;
    }

    private JPanel crearPanelDatosConsulta() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Motivo de Consulta:"), gbc);

        txtMotivo = new JTextArea();
        txtMotivo.setLineWrap(true);
        gbc.gridy = 1;
        panel.add(new JScrollPane(txtMotivo), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Antecedentes Relevantes:"), gbc);

        txtAntecedentes = new JTextArea();
        txtAntecedentes.setLineWrap(true);
        gbc.gridy = 3;
        panel.add(new JScrollPane(txtAntecedentes), gbc);

        return panel;
    }

    private JPanel crearPanelExamenFisico() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panelSignos = new JPanel(new GridLayout(2, 4, 5, 5));
        panelSignos.setBorder(BorderFactory.createTitledBorder("Signos Vitales"));

        panelSignos.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField(10);
        panelSignos.add(txtPeso);

        panelSignos.add(new JLabel("Altura (cm):"));
        txtAltura = new JTextField(10);
        panelSignos.add(txtAltura);

        panelSignos.add(new JLabel("Presión Arterial:"));
        txtPresion = new JTextField(10);
        panelSignos.add(txtPresion);

        panelSignos.add(new JLabel("Temperatura (°C):"));
        txtTemperatura = new JTextField(10);
        panelSignos.add(txtTemperatura);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(panelSignos, gbc);

        gbc.gridy = 1;
        panel.add(new JLabel("Examen Físico Detallado:"), gbc);

        txtExamenFisico = new JTextArea();
        txtExamenFisico.setLineWrap(true);
        JScrollPane scrollExamen = new JScrollPane(txtExamenFisico);
        scrollExamen.setPreferredSize(new Dimension(400, 300));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        panel.add(scrollExamen, gbc);

        return panel;
    }

    private JPanel crearPanelDiagnosticoTratamiento() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Diagnóstico:"), gbc);

        txtDiagnostico = new JTextArea();
        txtDiagnostico.setLineWrap(true);
        gbc.gridy = 1;
        panel.add(new JScrollPane(txtDiagnostico), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Plan de Tratamiento:"), gbc);

        txtTratamiento = new JTextArea();
        txtTratamiento.setLineWrap(true);
        gbc.gridy = 3;
        panel.add(new JScrollPane(txtTratamiento), gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarHistoria());

        btnImprimir = new JButton("Imprimir");
        btnImprimir.addActionListener(e -> imprimirHistoria());

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> limpiarCampos());

        panel.add(btnGuardar);
        panel.add(btnImprimir);
        panel.add(btnCancelar);

        return panel;
    }

    private void buscarPaciente() {
        String busqueda = txtBusqueda.getText().trim();
        if (busqueda.isEmpty()) {
            mostrarError("Ingrese un DNI o número de historia clínica");
            return;
        }

        // Lógica de búsqueda pendiente
    }

    private void guardarHistoria() {
        if (txtPaciente.getText().isEmpty()) {
            mostrarError("Debe buscar un paciente primero");
            return;
        }

        if (txtMotivo.getText().isEmpty() || txtDiagnostico.getText().isEmpty()) {
            mostrarError("Debe completar el motivo de consulta y diagnóstico");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Historia clínica guardada exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void imprimirHistoria() {
        if (txtPaciente.getText().isEmpty()) {
            mostrarError("Debe buscar un paciente primero");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Preparando documento para impresión...",
                "Imprimir",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarCampos() {
        txtBusqueda.setText("");
        txtPaciente.setText("");
        txtFecha.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(LocalDateTime.now()));
        txtMotivo.setText("");
        txtAntecedentes.setText("");
        txtPeso.setText("");
        txtAltura.setText("");
        txtPresion.setText("");
        txtTemperatura.setText("");
        txtExamenFisico.setText("");
        txtDiagnostico.setText("");
        txtTratamiento.setText("");
    }
}
