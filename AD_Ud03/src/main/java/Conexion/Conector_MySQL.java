/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author David Cano Escario
 */
public class Conector_MySQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String usuario = "root";
        String contrasena = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexion exitosa a MySQL.");
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
