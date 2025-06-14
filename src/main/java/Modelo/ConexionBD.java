/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
   
private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=bdHospital;encrypt=true;trustServerCertificate=true";
private static final String USER = "sa";
private static final String PASSWORD = "SQLExpress";


    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
           
        } catch (ClassNotFoundException | SQLException e) {
            
            System.out.println("Error al conectar con la base de datos."+e.toString());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = ConexionBD.conectar();
        if (conn != null) {
            System.out.println("Conexion exitosa.");
        } else {
            System.out.println("Error al conectar.");
        }
    }
}
