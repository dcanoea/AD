/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SQL;

import ORACLE.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Cano Escario
 */
public class Delete_SQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/test";
        String usuario = "root";
        String contrasena = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexion exitosa a MySQL.");

            Statement stm = conexion.createStatement();

            //Sentencia sql
            String sql = "delete from baloncesto where ID = 6";

            //Ejecuta la sentencia. Devuelve el número de filas afectadas
            System.out.println("nº filas borradas: " + stm.executeUpdate(sql));

            stm.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            e.printStackTrace();
        }
    }

}
