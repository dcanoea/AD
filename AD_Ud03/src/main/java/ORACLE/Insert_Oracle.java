/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author David Cano Escario
 */
public class Insert_Oracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Crea la conexion
            String urljdbc = "jdbc:oracle:thin:@localhost:1521:free";
            Connection conexion = DriverManager.getConnection(urljdbc, "C##user1", "user1");
            System.out.println("Conexión establecida");
            
            //Crea la sentencia
            Statement stm = conexion.createStatement();
            
            //String de la sentencia sql
            String sql = "INSERT INTO baloncesto VALUES(6, 'Allen', 'Iverson', 183, 49, 'Escolta', 'Philadelphia 76ers')";
            
            //Ejecuta el INSERT. Devuelve 1, el número de filas afectadas
            System.out.println(stm.executeUpdate(sql));
            
            //Cerrar sentencia
            stm.close();
            //Cerrar conexion
            conexion.close();
           
        } catch (Exception e) {
            System.out.println("Error al procesar BBDD");
            e.printStackTrace();
        }

    }
}
