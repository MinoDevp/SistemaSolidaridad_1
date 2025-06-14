/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ModeloTesT;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para validar la conexión a la base de datos H2 en memoria
 * y operaciones básicas como creación de tablas.
 */
public class ConexionDBTest {

    private static Connection conn;

    @BeforeAll
    public static void iniciarConexion() {
        conn = ConexionDBC.conectar(); // Se asume que ConexionDBC está correctamente implementado en ModeloTest
        assertNotNull(conn, "La conexión a la base de datos H2 no debe ser nula.");
    }

    @AfterAll
    public static void cerrarConexion() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                fail("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    @Test
    public void testCreacionTablaCita() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Cita (
                idCita VARCHAR(10) PRIMARY KEY,
                fechaHora TIMESTAMP,
                estado VARCHAR(20),
                motivo VARCHAR(255),
                idPaciente VARCHAR(10),
                idMedico VARCHAR(10),
                idRecepcionista VARCHAR(10)
            )
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            assertTrue(true, "Tabla Cita creada exitosamente.");
        } catch (SQLException e) {
            fail("Fallo al crear la tabla Cita: " + e.getMessage());
        }
    }
}
