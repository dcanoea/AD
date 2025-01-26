/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.pildoras.pruebasHibernate;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author David Cano Escario
 */
public class PruebasJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/pruebasHibernate?useSSL=false";
        String usuario = "root";
        String contra = "";
        
        try {
            System.out.println("Intentando conectar con la BBDD" + jdbcUrl);
            
            Connection miConexion = DriverManager.getConnection(jdbcUrl, usuario, contra);
            
            System.out.println("Conexion exitosa!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
