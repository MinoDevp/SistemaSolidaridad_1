/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vistas;

import Controlador.ControladorMedico;
import Modelo.Medico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Usuario
 */
public class regMedic extends JPanel {
    
    // Controlador
    private ControladorMedico controladorMedico;
    
    // Componentes de la interfaz
    private JTextField txtIdMedico;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCredenciales;
    private JTextField txtEspecialidad;
    private JTextField txtHorarioConsulta;
    
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCancelar;
    
    // Labels
    private JLabel lblIdMedico;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblCredenciales;
    private JLabel lblEspecialidad;
    private JLabel lblHorarioConsulta;
    private JLabel lblTitulo;
    
    public regMedic() {
        this.controladorMedico = new ControladorMedico();
        initComponents();
        setupLayout();
        setupEventListeners();
    }
    
    private void initComponents() {
        // Inicializar labels
        lblTitulo = new JLabel("REGISTRO DE MÉDICOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblIdMedico = new JLabel("ID Médico:");
        lblNombre = new JLabel("Nombre:");
        lblApellidos = new JLabel("Apellidos:");
        lblCredenciales = new JLabel("Credenciales:");
        lblEspecialidad = new JLabel("Especialidad:");
        lblHorarioConsulta = new JLabel("Horario de Consulta:");
        
        // Inicializar campos de texto
        txtIdMedico = new JTextField(15);
        txtNombre = new JTextField(25);
        txtApellidos = new JTextField(25);
        txtCredenciales = new JTextField(30);
        txtEspecialidad = new JTextField(25);
        txtHorarioConsulta = new JTextField(25);
        
        // Inicializar botones
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnCancelar = new JButton("Cancelar");
        
        // Botón para generar ID automático
        JButton btnGenerarId = new JButton("Generar ID");
        btnGenerarId.addActionListener(e -> {
            String nuevoId = controladorMedico.generarIdMedico();
            txtIdMedico.setText(nuevoId);
        });
        
        // Configurar estilos de botones
        btnGuardar.setBackground(new Color(46, 125, 50));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnLimpiar.setBackground(new Color(255, 152, 0));
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnCancelar.setBackground(new Color(244, 67, 54));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 12));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitulo, gbc);
        
        // Separador
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(new JSeparator(), gbc);
        
        // ID Médico
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblIdMedico, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        JPanel panelId = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelId.add(txtIdMedico);
        panelId.add(Box.createHorizontalStrut(10));;
        
        mainPanel.add(panelId, gbc);
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblNombre, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtNombre, gbc);
        
        // Apellidos
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblApellidos, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtApellidos, gbc);
        
        // Credenciales
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblCredenciales, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtCredenciales, gbc);
        
        // Especialidad
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblEspecialidad, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtEspecialidad, gbc);
        
        // Horario de Consulta
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(lblHorarioConsulta, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(txtHorarioConsulta, gbc);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnLimpiar);
        buttonPanel.add(btnCancelar);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        mainPanel.add(buttonPanel, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void setupEventListeners() {
        // Botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMedico();
            }
        });
        
        // Botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        // Botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarOperacion();
            }
        });
    }
    
    private void guardarMedico() {

        Medico medico = new Medico();
        medico.setIdMedico(txtIdMedico.getText().trim());
        medico.setNombre(txtNombre.getText().trim());
        medico.setApellidos(txtApellidos.getText().trim());
        medico.setCredenciales(txtCredenciales.getText().trim());
        medico.setEspecialidad(txtEspecialidad.getText().trim());
        medico.setHorarioConsulta(txtHorarioConsulta.getText().trim());
        
        ControladorMedico.ResultadoOperacion resultado = controladorMedico.registrarMedico(medico);
        
        resultado.mostrarMensaje();
        
        if (resultado.isExito()) {
            limpiarCampos();
        }
    }
    
    private boolean validarCampos() {
        StringBuilder errores = new StringBuilder();
        
        if (txtIdMedico.getText().trim().isEmpty()) {
            errores.append("- ID Médico es obligatorio\n");
        } else if (txtIdMedico.getText().trim().length() > 10) {
            errores.append("- ID Médico no puede exceder 10 caracteres\n");
        }
        
        if (txtNombre.getText().trim().isEmpty()) {
            errores.append("- Nombre es obligatorio\n");
        } else if (txtNombre.getText().trim().length() > 50) {
            errores.append("- Nombre no puede exceder 50 caracteres\n");
        }
        
        if (txtApellidos.getText().trim().isEmpty()) {
            errores.append("- Apellidos es obligatorio\n");
        } else if (txtApellidos.getText().trim().length() > 50) {
            errores.append("- Apellidos no puede exceder 50 caracteres\n");
        }
        
        if (txtCredenciales.getText().trim().length() > 100) {
            errores.append("- Credenciales no puede exceder 100 caracteres\n");
        }
        
        if (txtEspecialidad.getText().trim().isEmpty()) {
            errores.append("- Especialidad es obligatorio\n");
        } else if (txtEspecialidad.getText().trim().length() > 50) {
            errores.append("- Especialidad no puede exceder 50 caracteres\n");
        }
        
        if (txtHorarioConsulta.getText().trim().length() > 50) {
            errores.append("- Horario de Consulta no puede exceder 50 caracteres\n");
        }
        
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor corrija los siguientes errores:\n\n" + errores.toString(), 
                "Errores de validación", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void limpiarCampos() {
        txtIdMedico.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCredenciales.setText("");
        txtEspecialidad.setText("");
        txtHorarioConsulta.setText("");
        txtIdMedico.requestFocus();
    }
    
    private void cancelarOperacion() {
        int opcion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea cancelar? Se perderán los datos no guardados.", 
            "Confirmar cancelación", 
            JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            limpiarCampos();
        }
    }
    
    public void setIdMedico(String idMedico) {
        txtIdMedico.setText(idMedico);
    }
    
    public String getIdMedico() {
        return txtIdMedico.getText().trim();
    }
    
    public void habilitarEdicion(boolean habilitar) {
        txtIdMedico.setEnabled(habilitar);
        txtNombre.setEnabled(habilitar);
        txtApellidos.setEnabled(habilitar);
        txtCredenciales.setEnabled(habilitar);
        txtEspecialidad.setEnabled(habilitar);
        txtHorarioConsulta.setEnabled(habilitar);
    }
    
    public void cargarMedico(String idMedico) {
        Medico medico = controladorMedico.buscarMedico(idMedico);
        if (medico != null) {
            txtIdMedico.setText(medico.getIdMedico());
            txtNombre.setText(medico.getNombre());
            txtApellidos.setText(medico.getApellidos());
            txtCredenciales.setText(medico.getCredenciales());
            txtEspecialidad.setText(medico.getEspecialidad());
            txtHorarioConsulta.setText(medico.getHorarioConsulta());
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se encontró el médico con ID: " + idMedico, 
                "Médico no encontrado", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public ControladorMedico getControladorMedico() {
        return controladorMedico;
    }
}