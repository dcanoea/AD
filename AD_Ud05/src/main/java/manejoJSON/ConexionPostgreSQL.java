/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manejoJSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author David Cano Escario
 */
public class ConexionPostgreSQL {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // postgres ee el nombre de la BD
    private static final String USUARIO = "postgres";
    private static final String CONTRASEÑA = "admin";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            System.out.println("Conectado a PostgreSQL correctamente!");
        } else {
            System.out.println("Error en la conexión.");
        }
    }
}
