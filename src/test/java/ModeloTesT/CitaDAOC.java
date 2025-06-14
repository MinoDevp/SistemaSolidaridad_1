/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloTesT;

/**
 * ManuelDev
 * esta CitaDAOC viene hacer la Clonacion de CitaDAO  que se encuentra en el paquete Modelo
 */


import Modelo.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAOC {
    private final Connection conn;

    public CitaDAOC(Connection conn) {
        this.conn = conn;
    }

    public boolean insertarCita(Cita cita) {
        String sql = "INSERT INTO Cita (idCita, fechaHora, estado, motivo, idPaciente, idMedico, idRecepcionista) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cita.getIdCita());
            stmt.setTimestamp(2, cita.getFechaHora());
            stmt.setString(3, cita.getEstado());
            stmt.setString(4, cita.getMotivo());
            stmt.setString(5, cita.getIdPaciente());
            stmt.setString(6, cita.getIdMedico());
            stmt.setString(7, cita.getIdRecepcionista());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertando cita: " + e.getMessage());
            return false;
        }
    }

    public Cita buscarCitaPorId(String id) {
        String sql = "SELECT * FROM Cita WHERE idCita = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getString("idCita"));
                cita.setFechaHora(rs.getTimestamp("fechaHora"));
                cita.setEstado(rs.getString("estado"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setIdPaciente(rs.getString("idPaciente"));
                cita.setIdMedico(rs.getString("idMedico"));
                cita.setIdRecepcionista(rs.getString("idRecepcionista"));
                return cita;
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cita: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarCita(Cita cita) {
        String sql = "UPDATE Cita SET fechaHora=?, estado=?, motivo=?, idPaciente=?, idMedico=?, idRecepcionista=? WHERE idCita=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, cita.getFechaHora());
            stmt.setString(2, cita.getEstado());
            stmt.setString(3, cita.getMotivo());
            stmt.setString(4, cita.getIdPaciente());
            stmt.setString(5, cita.getIdMedico());
            stmt.setString(6, cita.getIdRecepcionista());
            stmt.setString(7, cita.getIdCita());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizando cita: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCita(String id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cita WHERE idCita = ?")) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminando cita: " + e.getMessage());
            return false;
        }
    }

    public List<Cita> obtenerTodasLasCitas() {
        List<Cita> citas = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cita")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setIdCita(rs.getString("idCita"));
                cita.setFechaHora(rs.getTimestamp("fechaHora"));
                cita.setEstado(rs.getString("estado"));
                cita.setMotivo(rs.getString("motivo"));
                cita.setIdPaciente(rs.getString("idPaciente"));
                cita.setIdMedico(rs.getString("idMedico"));
                cita.setIdRecepcionista(rs.getString("idRecepcionista"));
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("Error obteniendo citas: " + e.getMessage());
        }
        return citas;
    }

    public boolean cambiarEstadoCita(String id, String nuevoEstado) {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE Cita SET estado = ? WHERE idCita = ?")) {
            stmt.setString(1, nuevoEstado);
            stmt.setString(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error cambiando estado: " + e.getMessage());
            return false;
        }
    }
}

