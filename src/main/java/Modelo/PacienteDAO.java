package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class PacienteDAO {

    public boolean insertarPaciente(String idPaciente, String dni, LocalDate fechaNacimiento, String grupoSanguineo, String alergias) {
        String sql = "INSERT INTO paciente (idPaciente, dni, fechaNacimiento, grupoSanguineo, alergias) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPaciente);
            stmt.setString(2, dni);
            stmt.setDate(3, Date.valueOf(fechaNacimiento));
            stmt.setString(4, grupoSanguineo);
            stmt.setString(5, alergias);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Método adicional para verificar si un DNI ya existe
    public boolean existeDNI(String dni) {
        String sql = "SELECT COUNT(*) FROM paciente WHERE dni = ?";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, dni);
            var rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar DNI: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }

    // Método para verificar si un ID de paciente ya existe
    public boolean existeIdPaciente(String idPaciente) {
        String sql = "SELECT COUNT(*) FROM paciente WHERE idPaciente = ?";
        
        try (Connection conn = ConexionBD.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, idPaciente);
            var rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar ID Paciente: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
}