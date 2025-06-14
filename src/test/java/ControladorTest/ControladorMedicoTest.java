package ControladorTest;

import Controlador.ControladorMedico;
import Controlador.ControladorMedico.ResultadoOperacion;
import Modelo.Medico;
import Modelo.MedicoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorMedicoTest {

    private MedicoDAO medicoDAOMock;
    private ControladorMedico controlador;

    @BeforeEach
    public void setUp() {
        medicoDAOMock = mock(MedicoDAO.class);
        controlador = new ControladorMedico(medicoDAOMock); // CORRECTO: se inyecta el mock
    }

    @Test
    public void testRegistrarMedico_Exito() {
        Medico medico = new Medico("MED001", "Carlos", "Pérez", "CMP123", "Cardiología", "9am-1pm");

        when(medicoDAOMock.existeMedico("MED001")).thenReturn(false);
        when(medicoDAOMock.insertarMedico(medico)).thenReturn(true);

        ResultadoOperacion resultado = controlador.registrarMedico(medico);

        assertTrue(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("registrado exitosamente"));
    }

    @Test
    public void testRegistrarMedico_Duplicado() {
        Medico medico = new Medico("MED001", "Carlos", "Pérez", "CMP123", "Cardiología", "9am-1pm");

        when(medicoDAOMock.existeMedico("MED001")).thenReturn(true);

        ResultadoOperacion resultado = controlador.registrarMedico(medico);

        assertFalse(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("Ya existe un médico"));
    }

    @Test
    public void testActualizarMedico_NoExiste() {
        Medico medico = new Medico("MED002", "Ana", "Torres", "CMP456", "Pediatría", "2pm-6pm");

        when(medicoDAOMock.existeMedico("MED002")).thenReturn(false);

        ResultadoOperacion resultado = controlador.actualizarMedico(medico);

        assertFalse(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("No existe un médico"));
    }
}
