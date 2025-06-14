package Vistas;

import Controlador.ControladorAltaMedica;
import javax.swing.*;
import java.awt.*;

public class AltaMedica extends JPanel{

    private JTextField txtHistoriaClinica;
    private JTextArea txtDiagnostico;
    private JTextArea txtIndicaciones;
    private JTextArea txtMedicacion;

    public AltaMedica() {

        setLayout(new BorderLayout(10, 10));

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda de Paciente"));

        panelBusqueda.add(new JLabel("DNI del Paciente:"));
        txtHistoriaClinica = new JTextField(15);
        panelBusqueda.add(txtHistoriaClinica);

        JButton btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        // Panel de datos
        JPanel panelDatos = new JPanel(new GridLayout(3, 1, 10, 10));
        panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Alta"));

        // Diagnóstico
        JPanel panelDiagnostico = new JPanel(new BorderLayout());
        panelDiagnostico.add(new JLabel("Diagnóstico Final:"), BorderLayout.NORTH);
        txtDiagnostico = new JTextArea(4, 40);
        txtDiagnostico.setLineWrap(true);
        panelDiagnostico.add(new JScrollPane(txtDiagnostico), BorderLayout.CENTER);

        // Indicaciones
        JPanel panelIndicaciones = new JPanel(new BorderLayout());
        panelIndicaciones.add(new JLabel("Indicaciones Post-Alta:"), BorderLayout.NORTH);
        txtIndicaciones = new JTextArea(4, 40);
        txtIndicaciones.setLineWrap(true);
        panelIndicaciones.add(new JScrollPane(txtIndicaciones), BorderLayout.CENTER);

        // Medicación
        JPanel panelMedicacion = new JPanel(new BorderLayout());
        panelMedicacion.add(new JLabel("Medicación Recomendada:"), BorderLayout.NORTH);
        txtMedicacion = new JTextArea(4, 40);
        txtMedicacion.setLineWrap(true);
        panelMedicacion.add(new JScrollPane(txtMedicacion), BorderLayout.CENTER);

        panelDatos.add(panelDiagnostico);
        panelDatos.add(panelIndicaciones);
        panelDatos.add(panelMedicacion);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Generar Alta");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        // Agregar todos los paneles al frame
        add(panelBusqueda, BorderLayout.NORTH);
        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnBuscar.addActionListener(e -> buscarPaciente());
        btnGuardar.addActionListener(e -> generarAlta());


    }

    private void buscarPaciente() {
        String dni = txtHistoriaClinica.getText().trim();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un DNI válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Controlador.ControladorAltaMedica controlador = new ControladorAltaMedica();
        boolean encontrado = controlador.existePaciente(dni);

        if (encontrado) {
            JOptionPane.showMessageDialog(this, "Paciente encontrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Paciente inexistente", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Aquí iría la lógica de búsqueda en la base de datos
    }

    private void generarAlta() {
        String dni = txtHistoriaClinica.getText().trim();
        String diagnostico = txtDiagnostico.getText().trim();
        String indicaciones = txtIndicaciones.getText().trim();
        String medicacion = txtMedicacion.getText().trim();

        if (dni.isEmpty() || diagnostico.isEmpty() || indicaciones.isEmpty() || medicacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ControladorAltaMedica controlador = new ControladorAltaMedica();
        boolean exito = controlador.registrarAltaMedica(dni, diagnostico, indicaciones, medicacion);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Alta médica generada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al generar el alta médica", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
