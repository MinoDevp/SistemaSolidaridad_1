package Controlador;

import Modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorAltaMedica {

    
    
    public boolean existePaciente(String dni) {
        String sql = "SELECT * FROM pacientes WHERE DNI = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();

            return rs.next(); // Si hay un resultado, el paciente existe

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrarAltaMedica(String dni, String diagnostico, String indicaciones, String medicacion) {
   
     String sql = "INSERT INTO altas_medicas (DNI_Paciente, Diagnostico, Indicaciones, Medicacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            pstmt.setString(2, diagnostico);
            pstmt.setString(3, indicaciones);
            pstmt.setString(4, medicacion);

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
