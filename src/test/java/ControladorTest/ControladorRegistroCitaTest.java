package ControladorTest;

import Controlador.ControladorRegistroCita;
import Controlador.ControladorRegistroCita.ResultadoOperacion;
import Modelo.Cita;
import Modelo.CitaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorRegistroCitaTest {

    private CitaDAO citaDAOMock;
    private ControladorRegistroCita controlador;

    @BeforeEach
    void setUp() throws Exception {
        citaDAOMock = mock(CitaDAO.class);
        controlador = new ControladorRegistroCita();
        
        // Inyección del mock a través de reflexión
        var field = ControladorRegistroCita.class.getDeclaredField("citaDAO");
        field.setAccessible(true);
        field.set(controlador, citaDAOMock);
    }

    @Test
    void testRegistrarCita_Exito() {
        // Arrange
        String id = "CIT123";
        Date fecha = new Date();
        when(citaDAOMock.existeCita(id)).thenReturn(false);
        when(citaDAOMock.insertarCita(any())).thenReturn(true);

        // Act
        ResultadoOperacion resultado = controlador.registrarCita(id, fecha, "Programada",
                "Chequeo", "PAC001", "MED001", "REC001");

        // Assert
        assertTrue(resultado.isExito());
        assertEquals("Cita registrada exitosamente", resultado.getMensaje());
    }

    @Test
    void testRegistrarCita_ExisteYa() {
        when(citaDAOMock.existeCita("CIT999")).thenReturn(true);

        ResultadoOperacion resultado = controlador.registrarCita("CIT999", new Date(), "Programada",
                "Consulta", "PAC123", "MED321", "REC456");

        assertFalse(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("Ya existe una cita"));
    }

    @Test
    void testBuscarCita() {
        Cita citaMock = new Cita("CIT200", new Date(), "Confirmada", "Dolor cabeza",
                "PAC001", "MED001", "REC001");

        when(citaDAOMock.buscarCitaPorId("CIT200")).thenReturn(citaMock);

        Cita result = controlador.buscarCita("CIT200");

        assertNotNull(result);
        assertEquals("CIT200", result.getIdCita());
    }

    @Test
    void testActualizarCita_CitaNoExiste() {
        Cita cita = new Cita("CIT300", new Date(), "En curso", "Seguimiento",
                "PAC001", "MED001", "REC001");

        when(citaDAOMock.existeCita("CIT300")).thenReturn(false);

        ResultadoOperacion resultado = controlador.actualizarCita(cita);

        assertFalse(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("No existe"));
    }

    @Test
    void testEliminarCita_OK() {
        when(citaDAOMock.existeCita("CITDEL")).thenReturn(true);
        when(citaDAOMock.eliminarCita("CITDEL")).thenReturn(true);

        ResultadoOperacion resultado = controlador.eliminarCita("CITDEL");

        assertTrue(resultado.isExito());
    }

    @Test
    void testCambiarEstadoCita_Valido() {
        when(citaDAOMock.cambiarEstadoCita("CIT444", "Completada")).thenReturn(true);

        ResultadoOperacion resultado = controlador.cambiarEstadoCita("CIT444", "Completada");

        assertTrue(resultado.isExito());
        assertEquals("Estado de la cita actualizado exitosamente", resultado.getMensaje());
    }

    @Test
    void testCambiarEstadoCita_EstadoInvalido() {
        ResultadoOperacion resultado = controlador.cambiarEstadoCita("CIT555", "Terminado");

        assertFalse(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("Estado no válido"));
    }
}
