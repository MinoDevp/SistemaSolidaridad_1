package ModeloTesT;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOC {
    private final Connection conn;

    public UsuarioDAOC(Connection conn) {
        this.conn = conn;
    }

    public Usuario validarUsuario(String username, String password) {
        String sql = "SELECT idRecepcionista, credenciales FROM recepcionista WHERE idRecepcionista = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String pass = rs.getString("credenciales");
                    String idUsuario = rs.getString("idRecepcionista");

                    // Aquí normalmente compararías contra una contraseña hasheada,
                    // pero en esta versión simple solo verificamos que no sea null.
                    if (pass != null) {
                        return new Usuario(idUsuario, pass);
                    } else {
                        System.out.println("Contraseña incorrecta");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return null;
    }
}
