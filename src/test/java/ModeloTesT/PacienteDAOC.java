/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloTesT;

import Modelo.Paciente;
import java.sql.*;
import java.time.LocalDate;

public class PacienteDAOC {
    private final Connection conn;

    public PacienteDAOC(Connection conn) {
        this.conn = conn;
    }

    public boolean insertarPaciente(String idPaciente, String dni, LocalDate fechaNacimiento, String grupoSanguineo, String alergias) {
        String sql = "INSERT INTO paciente (idPaciente, dni, fechaNacimiento, grupoSanguineo, alergias) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPaciente);
            stmt.setString(2, dni);
            stmt.setDate(3, Date.valueOf(fechaNacimiento));
            stmt.setString(4, grupoSanguineo);
            stmt.setString(5, alergias);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
            return false;
        }
    }

    public boolean existeDNI(String dni) {
        String sql = "SELECT COUNT(*) FROM paciente WHERE dni = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error al verificar DNI: " + e.getMessage());
            return false;
        }
    }

    public boolean existeIdPaciente(String idPaciente) {
        String sql = "SELECT COUNT(*) FROM paciente WHERE idPaciente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.err.println("Error al verificar ID Paciente: " + e.getMessage());
            return false;
        }
    }
}
