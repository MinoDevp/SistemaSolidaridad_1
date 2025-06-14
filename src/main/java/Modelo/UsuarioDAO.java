
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {


    public Usuario validarUsuario(String username, String password) {
        String sql = "SELECT idRecepcionista,credenciales FROM recepcionista WHERE idRecepcionista = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);  // Usamos el username para buscar al usuario
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String pass= rs.getString("credenciales");
         
                    String idUsuario = rs.getString("idRecepcionista");

                    // Comparar la contraseña ingresada con la almacenada (hasheada)
                    if (pass!=null) {
                        // Si las contraseñas coinciden, se crea el objeto usuario con sus datos
                        Usuario usuario = new Usuario(idUsuario, pass);
                        return usuario;
                    } else {
                        System.out.println("contraseña incorrecta");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return null;  // Si no se encuentra el usuario o las contraseñas no coinciden
    }
}




