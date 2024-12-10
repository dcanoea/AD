/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author David Cano Escario
 */
public class ConexionBBDD {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "";
    private static Connection conexion;
    
    public static Connection conectar(){
        try {
            System.out.println("Conexion a BBDD establecida\n");
            return conexion = DriverManager.getConnection(url, user, password);  
        } catch (SQLException ex) {         
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void desconectar(){
        try {
            conexion.close();
            System.out.println("Desconectado de BBDD\n");
        } catch (SQLException ex) {
            System.out.println("Error al desconectar BBDD\n");
            ex.printStackTrace();
        }
    }
}
