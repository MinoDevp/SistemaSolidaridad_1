/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ControladorTest;

import Controlador.ControladorRegistroPaciente;
import Modelo.PacienteDAO;
import Vistas.regPacie;

import javax.swing.*;
import java.time.LocalDate;

public class ControladorRegistroPacienteTest {


        // Vista simulada (mock)
        regPacie vistaMock = new regPacie() {
            @Override public String getIdPaciente() { return "PAC123"; }
            @Override public String getDNI() { return "12345678"; }
            @Override public String getFechaNacimiento() { return "2000/05/20"; }
            @Override public String getGrupoSanguineo() { return "O+"; }
            @Override public String getAlergias() { return "Ninguna"; }

            @Override public boolean validarCamposObligatorios() { return true; }
            @Override public boolean validarIdPaciente(String id) { return id.matches("[a-zA-Z0-9]{3,10}"); }
            @Override public boolean validarDNI(String dni) { return dni.matches("\\d{8,15}"); }

            @Override public void mostrarMensajeError(String mensaje) {
                System.out.println("❌ ERROR: " + mensaje);
            }

            @Override public void mostrarMensajeExito(String mensaje) {
                System.out.println("✅ ÉXITO: " + mensaje);
            }

            @Override public void limpiarCampos() {
                System.out.println("🧹 Campos limpiados");
            }

            @Override public JButton getBtnRegistrar() {
                return new JButton(); // Evita NullPointerException
            }
        };

        // DAO simulado (mock)
        PacienteDAO daoMock = new PacienteDAO() {
            @Override public boolean existeIdPaciente(String id) {
                return false; // No existe, OK
            }

            @Override public boolean existeDNI(String dni) {
                return false; // No existe, OK
            }

            @Override public boolean insertarPaciente(String id, String dni, LocalDate fecha, String grupo, String alergias) {
                System.out.println("💾 Simulando inserción de paciente...");
                return true; // Éxito
            }
        };

        // Controlador con mocks
        ControladorRegistroPaciente controlador = new ControladorRegistroPaciente(vistaMock, daoMock);

        // Ejecutar método de prueba
     
    
}

