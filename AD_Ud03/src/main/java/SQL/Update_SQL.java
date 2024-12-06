/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Cano Escario
 */
public class Update_SQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String usuario = "root";
        String contrasena = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexion exitosa a MySQL.");

            //Crea la sentencia
            Statement stm = conexion.createStatement();

            //String de la sentencia sql
            String sql = "UPDATE baloncesto SET equipo = 'Golden State Warriors' WHERE equipo = 'warriors'";

            //Ejecuta el UPDATE. Devuelve 1, el número de filas afectadas
            System.out.println("nº filas actualizadas: " + stm.executeUpdate(sql));

            //Cerrar sentencia
            stm.close();
            
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            e.printStackTrace();
        }
    }
}
