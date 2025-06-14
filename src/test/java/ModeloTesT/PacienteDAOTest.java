/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ModeloTesT;

import org.junit.jupiter.api.*;
import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteDAOTest {

    private static Connection conn;
    private static PacienteDAOC pacienteDAO;

    @BeforeAll
    static void setUp() throws Exception {
        conn = ConexionDBC.conectar();
        pacienteDAO = new PacienteDAOC(conn);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE paciente (" +
                    "idPaciente VARCHAR(10) PRIMARY KEY," +
                    "dni VARCHAR(15) NOT NULL," +
                    "fechaNacimiento DATE," +
                    "grupoSanguineo VARCHAR(5)," +
                    "alergias VARCHAR(255))");
        }
    }

    @AfterEach
    void limpiarTabla() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM paciente");
        }
    }

    @AfterAll
    static void cerrarConexion() throws Exception {
        conn.close();
    }

    @Test
    void insertarPacienteExitosamente() {
        boolean resultado = pacienteDAO.insertarPaciente("P001", "12345678", LocalDate.of(2000, 1, 1), "O+", "Ninguna");
        assertTrue(resultado, "El paciente debe insertarse correctamente");
    }

    @Test
    void existeDNI_true_siDniExiste() {
        pacienteDAO.insertarPaciente("P002", "87654321", LocalDate.of(1995, 5, 20), "A-", "Polen");
        assertTrue(pacienteDAO.existeDNI("87654321"));
    }

    @Test
    void existeDNI_false_siDniNoExiste() {
        assertFalse(pacienteDAO.existeDNI("00000000"));
    }

    @Test
    void existeIdPaciente_true_siExiste() {
        pacienteDAO.insertarPaciente("P003", "55555555", LocalDate.of(1999, 8, 15), "B+", "Penicilina");
        assertTrue(pacienteDAO.existeIdPaciente("P003"));
    }

    @Test
    void existeIdPaciente_false_siNoExiste() {
        assertFalse(pacienteDAO.existeIdPaciente("P999"));
    }
}
