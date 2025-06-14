/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author Usuario
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecepcionistaDAO {

    // Insertar un nuevo recepcionista
    public boolean insertar(Recepcionista recepcionista) {
        String sql = "INSERT INTO dbo.Recepcionista (idRecepcionista, nombre, apellidos, credenciales, horarioTrabajo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, recepcionista.getIdRecepcionista());
            pstmt.setString(2, recepcionista.getNombre());
            pstmt.setString(3, recepcionista.getApellidos());
            pstmt.setString(4, recepcionista.getCredenciales());
            pstmt.setString(5, recepcionista.getHorarioTrabajo());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar recepcionista: " + e.getMessage());
            return false;
        }
    }

    // Obtener un recepcionista por ID
    public Recepcionista obtenerPorId(String idRecepcionista) {
        String sql = "SELECT * FROM dbo.Recepcionista WHERE idRecepcionista = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idRecepcionista);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Recepcionista(
                        rs.getString("idRecepcionista"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("credenciales"),
                        rs.getString("horarioTrabajo")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener recepcionista: " + e.getMessage());
        }

        return null;
    }

    // Obtener todos los recepcionistas
    public List<Recepcionista> obtenerTodos() {
        List<Recepcionista> recepcionistas = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Recepcionista ORDER BY apellidos, nombre";

        try (Connection conn = ConexionBD.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Recepcionista recepcionista = new Recepcionista(
                        rs.getString("idRecepcionista"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("credenciales"),
                        rs.getString("horarioTrabajo")
                );
                recepcionistas.add(recepcionista);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener lista de recepcionistas: " + e.getMessage());
        }

        return recepcionistas;
    }

    // Actualizar un recepcionista existente
    public boolean actualizar(Recepcionista recepcionista) {
        String sql = "UPDATE dbo.Recepcionista SET nombre = ?, apellidos = ?, credenciales = ?, horarioTrabajo = ? WHERE idRecepcionista = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, recepcionista.getNombre());
            pstmt.setString(2, recepcionista.getApellidos());
            pstmt.setString(3, recepcionista.getCredenciales());
            pstmt.setString(4, recepcionista.getHorarioTrabajo());
            pstmt.setString(5, recepcionista.getIdRecepcionista());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar recepcionista: " + e.getMessage());
            return false;
        }
    }

    // Eliminar un recepcionista
    public boolean eliminar(String idRecepcionista) {
        String sql = "DELETE FROM dbo.Recepcionista WHERE idRecepcionista = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idRecepcionista);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar recepcionista: " + e.getMessage());
            return false;
        }
    }

    // Verificar si existe un recepcionista con el ID dado
    public boolean existeId(String idRecepcionista) {
        String sql = "SELECT COUNT(*) FROM dbo.Recepcionista WHERE idRecepcionista = ?";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idRecepcionista);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de ID: " + e.getMessage());
        }

        return false;
    }

    // Buscar recepcionistas por nombre o apellido
    public List<Recepcionista> buscarPorNombre(String termino) {
        List<Recepcionista> recepcionistas = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Recepcionista WHERE nombre LIKE ? OR apellidos LIKE ? ORDER BY apellidos, nombre";

        try (Connection conn = ConexionBD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String patron = "%" + termino + "%";
            pstmt.setString(1, patron);
            pstmt.setString(2, patron);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Recepcionista recepcionista = new Recepcionista(
                        rs.getString("idRecepcionista"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("credenciales"),
                        rs.getString("horarioTrabajo")
                );
                recepcionistas.add(recepcionista);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar recepcionistas: " + e.getMessage());
        }

        return recepcionistas;
    }

    // Obtener el conteo total de recepcionistas
    public int obtenerTotal() {
        String sql = "SELECT COUNT(*) FROM dbo.Recepcionista";

        try (Connection conn = ConexionBD.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener total de recepcionistas: " + e.getMessage());
        }

        return 0;
    }
}
