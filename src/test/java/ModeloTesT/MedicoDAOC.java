/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloTesT;

import Modelo.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clon de MedicoDAO para pruebas unitarias con H2.
 * NO depende de ConexionBD.
 */
public class MedicoDAOC {
    private final Connection conn;

    public MedicoDAOC(Connection conn) {
        this.conn = conn;
    }

    public boolean insertarMedico(Medico medico) {
        String sql = "INSERT INTO Medico (idMedico, nombre, apellidos, credenciales, especialidad, horarioConsulta) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, medico.getIdMedico());
            pst.setString(2, medico.getNombre());
            pst.setString(3, medico.getApellidos());
            pst.setString(4, medico.getCredenciales());
            pst.setString(5, medico.getEspecialidad());
            pst.setString(6, medico.getHorarioConsulta());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error insertando médico: " + e.getMessage());
            return false;
        }
    }

    public Medico buscarMedicoPorId(String idMedico) {
        String sql = "SELECT * FROM Medico WHERE idMedico = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idMedico);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Medico m = new Medico();
                m.setIdMedico(rs.getString("idMedico"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidos(rs.getString("apellidos"));
                m.setCredenciales(rs.getString("credenciales"));
                m.setEspecialidad(rs.getString("especialidad"));
                m.setHorarioConsulta(rs.getString("horarioConsulta"));
                return m;
            }
        } catch (SQLException e) {
            System.err.println("Error buscando médico: " + e.getMessage());
        }
        return null;
    }

    public List<Medico> obtenerTodosMedicos() {
        String sql = "SELECT * FROM Medico ORDER BY apellidos, nombre";
        List<Medico> lista = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Medico m = new Medico();
                m.setIdMedico(rs.getString("idMedico"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidos(rs.getString("apellidos"));
                m.setCredenciales(rs.getString("credenciales"));
                m.setEspecialidad(rs.getString("especialidad"));
                m.setHorarioConsulta(rs.getString("horarioConsulta"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error obteniendo médicos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarMedico(Medico medico) {
        String sql = "UPDATE Medico SET nombre=?, apellidos=?, credenciales=?, especialidad=?, horarioConsulta=? WHERE idMedico=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, medico.getNombre());
            pst.setString(2, medico.getApellidos());
            pst.setString(3, medico.getCredenciales());
            pst.setString(4, medico.getEspecialidad());
            pst.setString(5, medico.getHorarioConsulta());
            pst.setString(6, medico.getIdMedico());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error actualizando médico: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarMedico(String idMedico) {
        String sql = "DELETE FROM Medico WHERE idMedico = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idMedico);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error eliminando médico: " + e.getMessage());
            return false;
        }
    }

    public List<Medico> buscarMedicosPorEspecialidad(String especialidad) {
        String sql = "SELECT * FROM Medico WHERE especialidad LIKE ? ORDER BY apellidos, nombre";
        List<Medico> lista = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + especialidad + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Medico m = new Medico();
                m.setIdMedico(rs.getString("idMedico"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidos(rs.getString("apellidos"));
                m.setCredenciales(rs.getString("credenciales"));
                m.setEspecialidad(rs.getString("especialidad"));
                m.setHorarioConsulta(rs.getString("horarioConsulta"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error buscando médicos por especialidad: " + e.getMessage());
        }
        return lista;
    }

    public boolean existeMedico(String idMedico) {
        String sql = "SELECT COUNT(*) FROM Medico WHERE idMedico = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, idMedico);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error verificando existencia: " + e.getMessage());
        }
        return false;
    }

    public int contarMedicos() {
        String sql = "SELECT COUNT(*) FROM Medico";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error contando médicos: " + e.getMessage());
        }
        return 0;
    }
}
