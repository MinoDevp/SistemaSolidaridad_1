package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controlador.ControladorRecepcionista;
import Controlador.ControladorRecepcionista.ResultadoOperacion;
import Modelo.Recepcionista;

public class regRecep extends JPanel {

    // Componentes de la interfaz
    private JTextField txtIdRecepcionista;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextArea txtCredenciales;
    private JTextField txtHorarioTrabajo;

    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCancelar;

    // Controlador
    private ControladorRecepcionista controlador;

    public regRecep() {
        this.controlador = new ControladorRecepcionista();
        initComponents();
        setupLayout();
        setupEvents();
    }

    private void initComponents() {
        // Inicializar campos de texto
        txtIdRecepcionista = new JTextField(10);
        txtNombre = new JTextField(25);
        txtApellidos = new JTextField(25);
        txtCredenciales = new JTextArea(3, 25);
        txtCredenciales.setLineWrap(true);
        txtCredenciales.setWrapStyleWord(true);
        txtHorarioTrabajo = new JTextField(25);

        // Inicializar botones
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnCancelar = new JButton("Cancelar");

        // Configurar scroll para el área de credenciales
        JScrollPane scrollCredenciales = new JScrollPane(txtCredenciales);
        scrollCredenciales.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Registro de Recepcionista"));

        // Panel principal con los campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // ID Recepcionista
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(new JLabel("ID Recepcionista:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtIdRecepcionista, gbc);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtNombre, gbc);

        // Apellidos
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCampos.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtApellidos, gbc);

        // Credenciales
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelCampos.add(new JLabel("Credenciales:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane scrollCredenciales = new JScrollPane(txtCredenciales);
        panelCampos.add(scrollCredenciales, gbc);

        // Horario de Trabajo
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        panelCampos.add(new JLabel("Horario de Trabajo:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(txtHorarioTrabajo, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);

        // Agregar paneles al panel principal
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupEvents() {
        // Evento para el botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarGuardado();
            }
        });

        // Evento para el botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // Evento para el botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
    }

    private void guardarRecepcionista() {
        // Obtener datos de los campos
        String idRecepcionista = txtIdRecepcionista.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String credenciales = txtCredenciales.getText();
        String horarioTrabajo = txtHorarioTrabajo.getText();

        // Usar el controlador para guardar
        ResultadoOperacion resultado = controlador.guardarRecepcionista(
                idRecepcionista, nombre, apellidos, credenciales, horarioTrabajo
        );

        if (resultado.isExitoso()) {
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

            // Enfocar el campo con error si se especifica
            if (resultado.getCampoError() != null) {
                enfocarCampo(resultado.getCampoError());
            }
        }
    }

    private boolean validarCampos() {
        // Ahora esta validación se maneja en el controlador
        // Este método se mantiene para compatibilidad, pero no es necesario
        return true;
    }

    /**
     * Enfoca el campo especificado según el nombre del campo
     */
    private void enfocarCampo(String nombreCampo) {
        switch (nombreCampo) {
            case "idRecepcionista":
                txtIdRecepcionista.requestFocus();
                break;
            case "nombre":
                txtNombre.requestFocus();
                break;
            case "apellidos":
                txtApellidos.requestFocus();
                break;
            case "credenciales":
                txtCredenciales.requestFocus();
                break;
            case "horarioTrabajo":
                txtHorarioTrabajo.requestFocus();
                break;
        }
    }

    private void limpiarCampos() {
        txtIdRecepcionista.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCredenciales.setText("");
        txtHorarioTrabajo.setText("");
        txtIdRecepcionista.setEnabled(true); // Habilitar el campo ID
        btnGuardar.setText("Guardar"); // Resetear el texto del botón
        txtIdRecepcionista.requestFocus();
    }

    private void cancelar() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea cancelar? Se perderán los datos no guardados.",
                "Confirmar Cancelación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            limpiarCampos();
            // Aquí podrías cerrar la ventana o regresar a la ventana anterior
            // Por ejemplo: this.getParent().setVisible(false);
        }
    }

    // Métodos getters para obtener los valores de los campos (útil para otras clases)
    public String getIdRecepcionista() {
        return txtIdRecepcionista.getText().trim();
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getApellidos() {
        return txtApellidos.getText().trim();
    }

    public String getCredenciales() {
        return txtCredenciales.getText().trim();
    }

    public String getHorarioTrabajo() {
        return txtHorarioTrabajo.getText().trim();
    }

    // Métodos setters para establecer valores en los campos (útil para edición)
    public void setIdRecepcionista(String idRecepcionista) {
        txtIdRecepcionista.setText(idRecepcionista);
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setApellidos(String apellidos) {
        txtApellidos.setText(apellidos);
    }

    public void setCredenciales(String credenciales) {
        txtCredenciales.setText(credenciales);
    }

    public void setHorarioTrabajo(String horarioTrabajo) {
        txtHorarioTrabajo.setText(horarioTrabajo);
    }

    /**
     * Carga los datos de un recepcionista en la interfaz para edición
     */
    public void cargarDatosParaEdicion(String idRecepcionista) {
        Modelo.Recepcionista recepcionista = controlador.obtenerRecepcionista(idRecepcionista);
        if (recepcionista != null) {
            txtIdRecepcionista.setText(recepcionista.getIdRecepcionista());
            txtIdRecepcionista.setEnabled(false); // No permitir cambiar el ID en edición
            txtNombre.setText(recepcionista.getNombre());
            txtApellidos.setText(recepcionista.getApellidos());
            txtCredenciales.setText(recepcionista.getCredenciales());
            txtHorarioTrabajo.setText(recepcionista.getHorarioTrabajo());

            // Cambiar el texto del botón para indicar que es edición
            btnGuardar.setText("Actualizar");
        }
    }

    /**
     * Habilita el modo de edición
     */
    public void habilitarModoEdicion() {
        btnGuardar.setText("Actualizar");
        txtIdRecepcionista.setEnabled(false);
    }

    /**
     * Habilita el modo de inserción
     */
    public void habilitarModoInsercion() {
        btnGuardar.setText("Guardar");
        txtIdRecepcionista.setEnabled(true);
        limpiarCampos();
    }

    /**
     * Verifica si está en modo edición
     */
    public boolean esModoEdicion() {
        return "Actualizar".equals(btnGuardar.getText());
    }

    /**
     * Método mejorado para guardar/actualizar según el modo
     */
    private void procesarGuardado() {
        if (esModoEdicion()) {
            actualizarRecepcionista();
        } else {
            guardarRecepcionista();
        }
    }

    /**
     * Actualiza un recepcionista existente
     */
    private void actualizarRecepcionista() {
        // Obtener datos de los campos
        String idRecepcionista = txtIdRecepcionista.getText();
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String credenciales = txtCredenciales.getText();
        String horarioTrabajo = txtHorarioTrabajo.getText();

        // Usar el controlador para actualizar
        ResultadoOperacion resultado = controlador.actualizarRecepcionista(
                idRecepcionista, nombre, apellidos, credenciales, horarioTrabajo
        );

        if (resultado.isExitoso()) {
            JOptionPane.showMessageDialog(this,
                    resultado.getMensaje(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            habilitarModoInsercion(); // Volver al modo inserción
        } else {
            JOptionPane.showMessageDialog(this,
                    resultado.getMensaje(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            // Enfocar el campo con error si se especifica
            if (resultado.getCampoError() != null) {
                enfocarCampo(resultado.getCampoError());
            }
        }
    }
}
