/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import SQL.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author David Cano Escario
 */
public class Modificar_ResultSet_Oracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String usuario = "root";
        String contrasena = "";
        Statement stmt = null;
        
        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexion exitosa a MySQL.");

            //Para poder utilizar el ResultSet para actualizaciones de tablas, 
            //tendremos que crear la consulta con la opción ResultSet.CONCUR_UPDATABLE
            //Con la opción ResultSet.TYPE_SCROLL_SENSITIVE se crea un objeto ResultSet que 
            //puede recorrerse hacia adelante y hacia atrás en relación con la posición actual y con una posición absoluta. 
            stmt = conexion.createStatement();
            stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet uprs = stmt.executeQuery("SELECT * FROM baloncesto");
            
            //Vamos a modificar la edad en +1 año en todos los jugadores
            while (uprs.next()){
                int edad = uprs.getInt("edad"); //recupera la columna con nombre "edad"
                uprs.updateInt("edad", edad + 1); //actualiza la edad en un año más
                uprs.updateRow();
            }
            
            stmt.close();
            uprs.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            e.printStackTrace();
        }
    }
}
