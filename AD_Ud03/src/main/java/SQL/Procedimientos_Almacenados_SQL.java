/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author David Cano Escario
 */
public class Procedimientos_Almacenados_SQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
            Cargar el driver de mysql
            Class.forName("com.mysql.jdbc.Driver");
         */

        String url = "jdbc:mysql://localhost:3306/test";
        String usuario = "root";
        String contrasena = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexion exitosa a MySQL.");

            // El procedimiento almacenado tendrá siete parámetros
            CallableStatement procedimiento = conexion.prepareCall("{CALL Agregar_Jugador(?, ?, ?, ?, ?, ?, ?)}");

            // cargar parametros en el procedimiento almacenado
            procedimiento.setInt(1, 7);//id
            procedimiento.setString(2, "Andre");//nombre
            procedimiento.setString(3, "Iguodala");//apellidos
            procedimiento.setInt(4, 198);//altura
            procedimiento.setInt(5, 40);//edad
            procedimiento.setString(6, "Alero");//posicion
            procedimiento.setString(7, "warriors");//equipo

            // ejecutar el procedimiento
            procedimiento.execute();
            System.out.println("Procedimiento completado");
            
            procedimiento.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            System.out.println("SQL Exception: " + e.toString());
        }

    }

}
