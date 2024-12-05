/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Cano Escario
 */
public class Delete_Oracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String urljdbc = "jdbc:oracle:thin:@localhost:1521:free";
        String usuario = "C##user1";
        String pass = "user1";

        try {
            Connection conexion = DriverManager.getConnection(urljdbc, usuario, pass);
            System.out.println("Conexión establecida");

            Statement stm = conexion.createStatement();
            
            //Sentencia sql
            String oracle = "DELETE FROM baloncesto WHERE id=6";
            
            //Ejecuta la sentencia. Devuelve el número de filas afectadas
            System.out.println("nº filas borradas: " + stm.executeUpdate(oracle));
            
            
            stm.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            e.printStackTrace();
        }
    }

}
