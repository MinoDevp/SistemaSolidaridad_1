package ModeloTesT;

import Modelo.Cita;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CitaDAOTest {
    private static Connection conn;
    private static CitaDAOC citaDAO;

    @BeforeAll
    static void setUpDatabase() throws Exception {
        conn = ConexionDBC.conectar();
        citaDAO = new CitaDAOC(conn);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Cita");
            stmt.execute("CREATE TABLE Cita ("
                    + "idCita VARCHAR(10) PRIMARY KEY,"
                    + "fechaHora TIMESTAMP,"
                    + "estado VARCHAR(20),"
                    + "motivo VARCHAR(255),"
                    + "idPaciente VARCHAR(10),"
                    + "idMedico VARCHAR(10),"
                    + "idRecepcionista VARCHAR(10))");
        }
    }

    @AfterEach
    void limpiarCitas() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Cita");
        }
    }

    @Test
    void insertarYBuscarCita() {
        Cita cita = crearCitaEjemplo("C1");
        assertTrue(citaDAO.insertarCita(cita));

        Cita encontrada = citaDAO.buscarCitaPorId("C1");
        assertNotNull(encontrada);
        assertEquals("C1", encontrada.getIdCita());
    }

    @Test
    void actualizarCita() {
        Cita cita = crearCitaEjemplo("C2");
        citaDAO.insertarCita(cita);
        cita.setEstado("CANCELADA");

        assertTrue(citaDAO.actualizarCita(cita));
        assertEquals("CANCELADA", citaDAO.buscarCitaPorId("C2").getEstado());
    }

    @Test
    void eliminarCita() {
        citaDAO.insertarCita(crearCitaEjemplo("C3"));
        assertTrue(citaDAO.eliminarCita("C3"));
        assertNull(citaDAO.buscarCitaPorId("C3"));
    }

    @Test
    void obtenerTodasLasCitas() {
        citaDAO.insertarCita(crearCitaEjemplo("C4"));
        citaDAO.insertarCita(crearCitaEjemplo("C5"));

        List<Cita> citas = citaDAO.obtenerTodasLasCitas();
        assertEquals(2, citas.size());
    }

    @Test
    void cambiarEstadoCita() {
        citaDAO.insertarCita(crearCitaEjemplo("C6"));
        assertTrue(citaDAO.cambiarEstadoCita("C6", "REPROGRAMADA"));
        assertEquals("REPROGRAMADA", citaDAO.buscarCitaPorId("C6").getEstado());
    }

    private Cita crearCitaEjemplo(String id) {
        Cita cita = new Cita();
        cita.setIdCita(id);
        cita.setFechaHora(Timestamp.valueOf("2025-01-01 10:00:00"));
        cita.setEstado("ACTIVA");
        cita.setMotivo("Chequeo general");
        cita.setIdPaciente("P1");
        cita.setIdMedico("M1");
        cita.setIdRecepcionista("R1");
        return cita;
    }
}

