/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ModeloTesT;

import Modelo.Medico;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MedicoDAOTest {
    private static Connection conn;
    private static MedicoDAOC medicoDAO;

    @BeforeAll
    static void setUp() throws Exception {
        conn = ConexionDBC.conectar();
        medicoDAO = new MedicoDAOC(conn);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE Medico (" +
                    "idMedico VARCHAR(10) PRIMARY KEY," +
                    "nombre VARCHAR(100)," +
                    "apellidos VARCHAR(100)," +
                    "credenciales VARCHAR(100)," +
                    "especialidad VARCHAR(100)," +
                    "horarioConsulta VARCHAR(100))");
        }
    }

    @AfterEach
    void limpiarTabla() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM Medico");
        }
    }

    @Test
    void insertarYBuscarMedico() {
        Medico m = crearMedico("M1");
        assertTrue(medicoDAO.insertarMedico(m));

        Medico encontrado = medicoDAO.buscarMedicoPorId("M1");
        assertNotNull(encontrado);
        assertEquals("M1", encontrado.getIdMedico());
    }

    @Test
    void actualizarMedico() {
        Medico m = crearMedico("M2");
        medicoDAO.insertarMedico(m);
        m.setEspecialidad("Neurología");

        assertTrue(medicoDAO.actualizarMedico(m));

        Medico actualizado = medicoDAO.buscarMedicoPorId("M2");
        assertEquals("Neurología", actualizado.getEspecialidad());
    }

    @Test
    void eliminarMedico() {
        medicoDAO.insertarMedico(crearMedico("M3"));
        assertTrue(medicoDAO.eliminarMedico("M3"));
        assertNull(medicoDAO.buscarMedicoPorId("M3"));
    }

    @Test
    void obtenerTodosMedicos() {
        medicoDAO.insertarMedico(crearMedico("M4"));
        medicoDAO.insertarMedico(crearMedico("M5"));
        List<Medico> lista = medicoDAO.obtenerTodosMedicos();
        assertEquals(2, lista.size());
    }

    @Test
    void buscarPorEspecialidad() {
        Medico m1 = crearMedico("M6");
        m1.setEspecialidad("Pediatría");
        Medico m2 = crearMedico("M7");
        m2.setEspecialidad("Cardiología");
        medicoDAO.insertarMedico(m1);
        medicoDAO.insertarMedico(m2);

        List<Medico> resultado = medicoDAO.buscarMedicosPorEspecialidad("Ped");
        assertEquals(1, resultado.size());
        assertEquals("Pediatría", resultado.get(0).getEspecialidad());
    }

    @Test
    void existeMedico() {
        medicoDAO.insertarMedico(crearMedico("M8"));
        assertTrue(medicoDAO.existeMedico("M8"));
        assertFalse(medicoDAO.existeMedico("NO_EXISTE"));
    }

    @Test
    void contarMedicos() {
        medicoDAO.insertarMedico(crearMedico("M9"));
        medicoDAO.insertarMedico(crearMedico("M10"));
        assertEquals(2, medicoDAO.contarMedicos());
    }

    private Medico crearMedico(String id) {
        Medico m = new Medico();
        m.setIdMedico(id);
        m.setNombre("Juan");
        m.setApellidos("Pérez");
        m.setCredenciales("CMP12345");
        m.setEspecialidad("Medicina General");
        m.setHorarioConsulta("Lun-Vie 08:00-16:00");
        return m;
    }
}
