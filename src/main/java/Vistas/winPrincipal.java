package Vistas;

import Controlador.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class winPrincipal extends javax.swing.JFrame {
    JPprincipal pl = new JPprincipal();

    public winPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDate();
        panel(pl);
    }

    //Metodo para colocar un JPanel dentro de otro JPanel.

    //establecer 
    private void panel(JPanel pl){
        
        pl.setSize(content.getWidth(), content.getHeight());
        pl.setLocation(0, 0);
        
        content.removeAll();
        content.add(pl, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }
    //Metodo para saber la fecha exacta del dia.
    private void setDate() {
        LocalDate now = LocalDate.now();
        Locale spanishLocale = new Locale("es", "ES");
        dateText.setText(now.format(DateTimeFormatter.ofPattern("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy", spanishLocale)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        background = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegistrarPaciente = new javax.swing.JButton();
        btnRegistroCita = new javax.swing.JButton();
        btnHistoriaClinica = new javax.swing.JButton();
        btnPlanTratamiento = new javax.swing.JButton();
        btnDisponibilidad = new javax.swing.JButton();
        btnRecepcionistas = new javax.swing.JButton();
        btnMedicos = new javax.swing.JButton();
        cabecera = new javax.swing.JPanel();
        dateText = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        background.setBackground(new java.awt.Color(109, 213, 237));

        menu.setBackground(new java.awt.Color(41, 72, 255));
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Solidaridad de Lima");
        menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 46, -1, -1));
        menu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 82, 237, 10));

        btnRegistrarPaciente.setBackground(new java.awt.Color(51, 51, 255));
        btnRegistrarPaciente.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRegistrarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/formulario-de-consentimiento-del-donante.png"))); // NOI18N
        btnRegistrarPaciente.setText("Registrar Paciente");
        btnRegistrarPaciente.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -30, 1, 1, new java.awt.Color(0, 0, 0)));
        btnRegistrarPaciente.setBorderPainted(false);
        btnRegistrarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarPaciente.setIconTextGap(10);
        btnRegistrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPacienteActionPerformed(evt);
            }
        });
        menu.add(btnRegistrarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 260, 80));

        btnRegistroCita.setBackground(new java.awt.Color(51, 51, 255));
        btnRegistroCita.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRegistroCita.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroCita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cita.png"))); // NOI18N
        btnRegistroCita.setText("RegistroCita");
        btnRegistroCita.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -80, 1, 1, new java.awt.Color(0, 0, 0)));
        btnRegistroCita.setBorderPainted(false);
        btnRegistroCita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistroCita.setIconTextGap(10);
        btnRegistroCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroCitaActionPerformed(evt);
            }
        });
        menu.add(btnRegistroCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 260, 80));

        btnHistoriaClinica.setBackground(new java.awt.Color(51, 51, 255));
        btnHistoriaClinica.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnHistoriaClinica.setForeground(new java.awt.Color(255, 255, 255));
        btnHistoriaClinica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carpeta-medica.png"))); // NOI18N
        btnHistoriaClinica.setText("Historia Clinica");
        btnHistoriaClinica.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -65, 1, 1, new java.awt.Color(0, 0, 0)));
        btnHistoriaClinica.setBorderPainted(false);
        btnHistoriaClinica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHistoriaClinica.setIconTextGap(10);
        btnHistoriaClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriaClinicaActionPerformed(evt);
            }
        });
        menu.add(btnHistoriaClinica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 260, 80));

        btnPlanTratamiento.setBackground(new java.awt.Color(51, 51, 255));
        btnPlanTratamiento.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnPlanTratamiento.setForeground(new java.awt.Color(255, 255, 255));
        btnPlanTratamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/medico.png"))); // NOI18N
        btnPlanTratamiento.setText("Plan de Tratamiento");
        btnPlanTratamiento.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -20, 1, 1, new java.awt.Color(0, 0, 0)));
        btnPlanTratamiento.setBorderPainted(false);
        btnPlanTratamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPlanTratamiento.setIconTextGap(10);
        btnPlanTratamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanTratamientoActionPerformed(evt);
            }
        });
        menu.add(btnPlanTratamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 260, 80));

        btnDisponibilidad.setBackground(new java.awt.Color(51, 51, 255));
        btnDisponibilidad.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnDisponibilidad.setForeground(new java.awt.Color(255, 255, 255));
        btnDisponibilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disponibilidad.png"))); // NOI18N
        btnDisponibilidad.setText("Disponibilidad");
        btnDisponibilidad.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -65, 1, 1, new java.awt.Color(0, 0, 0)));
        btnDisponibilidad.setBorderPainted(false);
        btnDisponibilidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDisponibilidad.setIconTextGap(10);
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });
        menu.add(btnDisponibilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 260, 80));

        btnRecepcionistas.setBackground(new java.awt.Color(51, 51, 255));
        btnRecepcionistas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnRecepcionistas.setForeground(new java.awt.Color(255, 255, 255));
        btnRecepcionistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/recepcion-del-hospital.png"))); // NOI18N
        btnRecepcionistas.setText("Recepcionistas");
        btnRecepcionistas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -50, 1, 1, new java.awt.Color(0, 0, 0)));
        btnRecepcionistas.setBorderPainted(false);
        btnRecepcionistas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecepcionistas.setIconTextGap(10);
        btnRecepcionistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionistasActionPerformed(evt);
            }
        });
        menu.add(btnRecepcionistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 260, 80));

        btnMedicos.setBackground(new java.awt.Color(51, 51, 255));
        btnMedicos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnMedicos.setForeground(new java.awt.Color(255, 255, 255));
        btnMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/doctor.png"))); // NOI18N
        btnMedicos.setText("Medicos");
        btnMedicos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, -120, 1, 1, new java.awt.Color(0, 0, 0)));
        btnMedicos.setBorderPainted(false);
        btnMedicos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMedicos.setIconTextGap(10);
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });
        menu.add(btnMedicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 260, 80));

        cabecera.setBackground(new java.awt.Color(28, 146, 210));

        dateText.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        dateText.setForeground(new java.awt.Color(255, 255, 255));
        dateText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateText.setText("Hoy es {dayname} {day} de {month} del {year}");

        javax.swing.GroupLayout cabeceraLayout = new javax.swing.GroupLayout(cabecera);
        cabecera.setLayout(cabeceraLayout);
        cabeceraLayout.setHorizontalGroup(
            cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cabeceraLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(dateText)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        cabeceraLayout.setVerticalGroup(
            cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cabeceraLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(dateText)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        content.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPacienteActionPerformed
        regPacie pacie = new regPacie();
        ControladorRegistroPaciente controlador = new ControladorRegistroPaciente(pacie);
        panel(pacie);
    }//GEN-LAST:event_btnRegistrarPacienteActionPerformed

    private void btnRegistroCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroCitaActionPerformed
        regCita cit = new regCita();
        panel(cit);
    }//GEN-LAST:event_btnRegistroCitaActionPerformed

    private void btnHistoriaClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriaClinicaActionPerformed
        crearHistoriaClinica hsclin= new crearHistoriaClinica();
        panel(hsclin);
    }//GEN-LAST:event_btnHistoriaClinicaActionPerformed

    private void btnPlanTratamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanTratamientoActionPerformed
        crearPlanTrat plantrat = new crearPlanTrat();
        panel(plantrat);
    }//GEN-LAST:event_btnPlanTratamientoActionPerformed

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
        ConsultaDisponibilidad disponibilidad = new ConsultaDisponibilidad();
        panel(disponibilidad);
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void btnRecepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionistasActionPerformed
        regRecep recep = new regRecep();
        panel(recep);
    }//GEN-LAST:event_btnRecepcionistasActionPerformed

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicosActionPerformed
        // TODO add your handling code here:
        regMedic medic = new regMedic();
        panel(medic);
    }//GEN-LAST:event_btnMedicosActionPerformed
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(winPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(winPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(winPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(winPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new winPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnDisponibilidad;
    private javax.swing.JButton btnHistoriaClinica;
    private javax.swing.JButton btnMedicos;
    private javax.swing.JButton btnPlanTratamiento;
    private javax.swing.JButton btnRecepcionistas;
    private javax.swing.JButton btnRegistrarPaciente;
    private javax.swing.JButton btnRegistroCita;
    private javax.swing.JPanel cabecera;
    private javax.swing.JPanel content;
    private javax.swing.JLabel dateText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables
}
