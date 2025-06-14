package ControladorTest;

import Controlador.ControladorAltaMedica;
import Modelo.ConexionBD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorAltaMedicaTest {

    private ControladorAltaMedica controlador;

    @BeforeEach
    public void setUp() {
        controlador = new ControladorAltaMedica();
    }

    @Test
    public void testExistePaciente_Existe() throws Exception {
        // Mock JDBC
        Connection mockConn = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        // Configurar mocks
        when(mockRs.next()).thenReturn(true);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

        // Mockear método estático ConexionBD.conectar()
        try (MockedStatic<ConexionBD> conexionBDMocked = mockStatic(ConexionBD.class)) {
            conexionBDMocked.when(ConexionBD::conectar).thenReturn(mockConn);

            boolean existe = controlador.existePaciente("12345678");
            assertTrue(existe);
        }
    }

    @Test
    public void testExistePaciente_NoExiste() throws Exception {
        Connection mockConn = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        when(mockRs.next()).thenReturn(false);
        when(mockStmt.executeQuery()).thenReturn(mockRs);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

        try (MockedStatic<ConexionBD> conexionBDMocked = mockStatic(ConexionBD.class)) {
            conexionBDMocked.when(ConexionBD::conectar).thenReturn(mockConn);

            boolean existe = controlador.existePaciente("99999999");
            assertFalse(existe);
        }
    }

    @Test
    public void testRegistrarAltaMedica_Exito() throws Exception {
        Connection mockConn = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);

        when(mockStmt.executeUpdate()).thenReturn(1);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

        try (MockedStatic<ConexionBD> conexionBDMocked = mockStatic(ConexionBD.class)) {
            conexionBDMocked.when(ConexionBD::conectar).thenReturn(mockConn);

            boolean resultado = controlador.registrarAltaMedica("12345678", "Fiebre", "Reposo", "Paracetamol");
            assertTrue(resultado);
        }
    }

    @Test
    public void testRegistrarAltaMedica_Fallo() throws Exception {
        Connection mockConn = mock(Connection.class);
        PreparedStatement mockStmt = mock(PreparedStatement.class);

        when(mockStmt.executeUpdate()).thenReturn(0);
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStmt);

        try (MockedStatic<ConexionBD> conexionBDMocked = mockStatic(ConexionBD.class)) {
            conexionBDMocked.when(ConexionBD::conectar).thenReturn(mockConn);

            boolean resultado = controlador.registrarAltaMedica("12345678", "Dolor", "Reposo", "Ibuprofeno");
            assertFalse(resultado);
        }
    }
}
