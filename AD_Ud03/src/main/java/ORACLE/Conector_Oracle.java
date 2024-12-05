/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author David Cano Escario
 */
public class Conector_Oracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String urljdbc = "jdbc:oracle:thin:@localhost:1521:free";
            Connection conexion = DriverManager.getConnection(urljdbc,"C##user1","user1");
            
            System.out.println("Conexión establecida");
        } catch (Exception e) {
            System.out.println("No se ha podido conectar a BBDD");
        }

        
    }

}
