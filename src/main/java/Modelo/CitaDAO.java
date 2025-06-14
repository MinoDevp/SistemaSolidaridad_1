package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    // Método para insertar una nueva cita
    public boolean insertarCita(Cita cita) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "INSERT INTO dbo.Cita (idCita, fechaHora, estado, motivo, idPaciente, idMedico, idRecepcionista) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, cita.getIdCita());
                pstmt.setTimestamp(2, cita.getFechaHora());
                pstmt.setString(3, cita.getEstado());
                pstmt.setString(4, cita.getMotivo());
                pstmt.setString(5, cita.getIdPaciente());
                pstmt.setString(6, cita.getIdMedico());
                pstmt.setString(7, cita.getIdRecepcionista());

                int filasAfectadas = pstmt.executeUpdate();
                resultado = filasAfectadas > 0;

                if (resultado) {
                    System.out.println("Cita insertada exitosamente");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    // Método para buscar una cita por ID
    public Cita buscarCitaPorId(String idCita) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cita cita = null;

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "SELECT * FROM dbo.Cita WHERE idCita = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, idCita);

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    cita = new Cita();
                    cita.setIdCita(rs.getString("idCita"));
                    cita.setFechaHora(rs.getTimestamp("fechaHora"));
                    cita.setEstado(rs.getString("estado"));
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setIdPaciente(rs.getString("idPaciente"));
                    cita.setIdMedico(rs.getString("idMedico"));
                    cita.setIdRecepcionista(rs.getString("idRecepcionista"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return cita;
    }

    // Método para actualizar una cita
    public boolean actualizarCita(Cita cita) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "UPDATE dbo.Cita SET fechaHora = ?, estado = ?, motivo = ?, "
                        + "idPaciente = ?, idMedico = ?, idRecepcionista = ? WHERE idCita = ?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setTimestamp(1, cita.getFechaHora());
                pstmt.setString(2, cita.getEstado());
                pstmt.setString(3, cita.getMotivo());
                pstmt.setString(4, cita.getIdPaciente());
                pstmt.setString(5, cita.getIdMedico());
                pstmt.setString(6, cita.getIdRecepcionista());
                pstmt.setString(7, cita.getIdCita());

                int filasAfectadas = pstmt.executeUpdate();
                resultado = filasAfectadas > 0;

                if (resultado) {
                    System.out.println("Cita actualizada exitosamente");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    // Método para eliminar una cita
    public boolean eliminarCita(String idCita) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "DELETE FROM dbo.Cita WHERE idCita = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, idCita);

                int filasAfectadas = pstmt.executeUpdate();
                resultado = filasAfectadas > 0;

                if (resultado) {
                    System.out.println("Cita eliminada exitosamente");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    // Método para obtener todas las citas
    public List<Cita> obtenerTodasLasCitas() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Cita> citas = new ArrayList<>();

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "SELECT * FROM dbo.Cita ORDER BY fechaHora";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

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
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return citas;
    }

    // Método para obtener citas por paciente
    public List<Cita> obtenerCitasPorPaciente(String idPaciente) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Cita> citas = new ArrayList<>();

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "SELECT * FROM dbo.Cita WHERE idPaciente = ? ORDER BY fechaHora";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, idPaciente);
                rs = pstmt.executeQuery();

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
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas por paciente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return citas;
    }

    // Método para obtener citas por médico
    public List<Cita> obtenerCitasPorMedico(String idMedico) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Cita> citas = new ArrayList<>();

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "SELECT * FROM dbo.Cita WHERE idMedico = ? ORDER BY fechaHora";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, idMedico);
                rs = pstmt.executeQuery();

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
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener citas por médico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, rs);
        }

        return citas;
    }

    // Método para verificar si existe una cita
    public boolean existeCita(String idCita) {
        return buscarCitaPorId(idCita) != null;
    }

    // Método para cambiar el estado de una cita
    public boolean cambiarEstadoCita(String idCita, String nuevoEstado) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;

        try {
            conn = ConexionBD.conectar();
            if (conn != null) {
                String sql = "UPDATE dbo.Cita SET estado = ? WHERE idCita = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nuevoEstado);
                pstmt.setString(2, idCita);

                int filasAfectadas = pstmt.executeUpdate();
                resultado = filasAfectadas > 0;

                if (resultado) {
                    System.out.println("Estado de cita actualizado exitosamente");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al cambiar estado de cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(conn, pstmt, null);
        }

        return resultado;
    }

    // Método utilitario para cerrar recursos
    private void cerrarRecursos(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
