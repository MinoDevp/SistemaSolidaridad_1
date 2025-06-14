package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO para operaciones CRUD de Medico
 *
 * @author Usuario
 */
public class MedicoDAO {

    // Método para insertar un nuevo médico
    public boolean insertarMedico(Medico medico) {
        String sql = "INSERT INTO dbo.Medico (idMedico, nombre, apellidos, credenciales, especialidad, horarioConsulta) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, medico.getIdMedico());
            pst.setString(2, medico.getNombre());
            pst.setString(3, medico.getApellidos());
            pst.setString(4, medico.getCredenciales());
            pst.setString(5, medico.getEspecialidad());
            pst.setString(6, medico.getHorarioConsulta());

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar médico: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar un médico por ID
    public Medico buscarMedicoPorId(String idMedico) {
        String sql = "SELECT * FROM dbo.Medico WHERE idMedico = ?";
        Medico medico = null;

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, idMedico);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                medico = new Medico();
                medico.setIdMedico(rs.getString("idMedico"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellidos(rs.getString("apellidos"));
                medico.setCredenciales(rs.getString("credenciales"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setHorarioConsulta(rs.getString("horarioConsulta"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar médico: " + e.getMessage());
        }

        return medico;
    }

    // Método para obtener todos los médicos
    public List<Medico> obtenerTodosMedicos() {
        String sql = "SELECT * FROM dbo.Medico ORDER BY apellidos, nombre";
        List<Medico> listaMedicos = new ArrayList<>();

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdMedico(rs.getString("idMedico"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellidos(rs.getString("apellidos"));
                medico.setCredenciales(rs.getString("credenciales"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setHorarioConsulta(rs.getString("horarioConsulta"));
                listaMedicos.add(medico);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener médicos: " + e.getMessage());
        }

        return listaMedicos;
    }

    // Método para actualizar un médico
    public boolean actualizarMedico(Medico medico) {
        String sql = "UPDATE dbo.Medico SET nombre = ?, apellidos = ?, credenciales = ?, especialidad = ?, horarioConsulta = ? WHERE idMedico = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, medico.getNombre());
            pst.setString(2, medico.getApellidos());
            pst.setString(3, medico.getCredenciales());
            pst.setString(4, medico.getEspecialidad());
            pst.setString(5, medico.getHorarioConsulta());
            pst.setString(6, medico.getIdMedico());

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar médico: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un médico
    public boolean eliminarMedico(String idMedico) {
        String sql = "DELETE FROM dbo.Medico WHERE idMedico = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, idMedico);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar médico: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar médicos por especialidad
    public List<Medico> buscarMedicosPorEspecialidad(String especialidad) {
        String sql = "SELECT * FROM dbo.Medico WHERE especialidad LIKE ? ORDER BY apellidos, nombre";
        List<Medico> listaMedicos = new ArrayList<>();

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "%" + especialidad + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdMedico(rs.getString("idMedico"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellidos(rs.getString("apellidos"));
                medico.setCredenciales(rs.getString("credenciales"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setHorarioConsulta(rs.getString("horarioConsulta"));
                listaMedicos.add(medico);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar médicos por especialidad: " + e.getMessage());
        }

        return listaMedicos;
    }

    // Método para verificar si existe un médico con el ID dado
    public boolean existeMedico(String idMedico) {
        String sql = "SELECT COUNT(*) FROM dbo.Medico WHERE idMedico = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, idMedico);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de médico: " + e.getMessage());
        }

        return false;
    }

    // Método para contar total de médicos
    public int contarMedicos() {
        String sql = "SELECT COUNT(*) FROM dbo.Medico";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al contar médicos: " + e.getMessage());
        }

        return 0;
    }
}
