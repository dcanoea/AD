/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author David Cano Escario
 */
public class Sentencias_Preparadas {

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

            String sql = "INSERT INTO baloncesto VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstm = conexion.prepareStatement(sql);
            
            pstm.setInt(1, 6);
            pstm.setString(2, "Devin");
            pstm.setString(3, "Booker");
            pstm.setInt(4, 196);
            pstm.setInt(5, 27);
            pstm.setString(6, "Escolta");
            pstm.setString(7, "Phoenix Suns");
            
            System.out.println("Filas agregadas: " + pstm.executeUpdate());
            
            pstm.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error en BBDD");
            e.printStackTrace();
        }

    }

}
