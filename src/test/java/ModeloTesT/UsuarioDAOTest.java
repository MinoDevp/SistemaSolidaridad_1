package ModeloTesT;


import Modelo.Usuario;
import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {
    private static Connection conn;
    private static UsuarioDAOC usuarioDAO;

    @BeforeAll
    static void init() throws SQLException {
        conn = ConexionDBC.conectar();
        usuarioDAO = new UsuarioDAOC(conn);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS recepcionista");
            stmt.execute("CREATE TABLE recepcionista ("
                    + "idRecepcionista VARCHAR(10) PRIMARY KEY,"
                    + "credenciales VARCHAR(100))");

            stmt.execute("INSERT INTO recepcionista (idRecepcionista, credenciales) "
                       + "VALUES ('user1', '1234')");
        }
    }

    @Test
    void validarUsuarioExistente() {
        Usuario usuario = usuarioDAO.validarUsuario("user1", "1234"); // Password no se compara realmente
        assertNotNull(usuario);
        assertEquals("user1", usuario.getIdUsuario());
    }

    @Test
    void validarUsuarioNoExistente() {
        Usuario usuario = usuarioDAO.validarUsuario("noExiste", "1234");
        assertNull(usuario);
    }

    @Test
    void validarUsuarioConPasswordNulo() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO recepcionista VALUES (?, ?)")) {
            stmt.setString(1, "user2");
            stmt.setNull(2, Types.VARCHAR);
            stmt.executeUpdate();
        }

        Usuario usuario = usuarioDAO.validarUsuario("user2", "cualquiera");
        assertNull(usuario);
    }
}


