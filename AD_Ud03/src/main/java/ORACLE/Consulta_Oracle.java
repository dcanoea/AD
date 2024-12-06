/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Cano Escario
 */
import java.sql.*;

public class Consulta_Oracle {

    public static void main(String[] args) {
        String urljdbc = "jdbc:oracle:thin:@localhost:1521:free";
        String usuario = "C##user1";
        String pass = "user1";

        try {
            // Establecemos la conexión con la base de datos
            Connection conexion = DriverManager.getConnection(urljdbc, usuario, pass);
            System.out.println("Conexión establecida a BBDD");

            // Creamos el objeto Statement
            Statement sentencia = conexion.createStatement();

            // Ejecutamos la consulta
            String sql = "SELECT * FROM baloncesto ORDER BY id";
            ResultSet rs = sentencia.executeQuery(sql);

            // Comprobamos si hay datos
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay datos en la tabla.");
            } else {
                while (rs.next()) {
                    System.out.println("Jugador ID: " + rs.getInt(1));
                    System.out.println("  Nombre: " + rs.getString(2));
                    System.out.println("  Apellido: " + rs.getString(3));
                    System.out.println("  Altura: " + rs.getInt(4));
                    System.out.println("  Edad: " + rs.getInt(5));
                    System.out.println("  Posición: " + rs.getString(6));
                    System.out.println("  Equipo: " + rs.getString(7) + "\n");
                }
            }

            rs.close();
            sentencia.close();
            conexion.close();
            
        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            e.printStackTrace();
        }
    }
}
