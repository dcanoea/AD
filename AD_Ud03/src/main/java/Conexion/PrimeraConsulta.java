/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Conexion;

import java.sql.*;

/**
 *
 * @author David Cano Escario
 */
public class PrimeraConsulta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String pass = "";
            // Establecemos la conexion con la BD
            Connection conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida a BBDD");
            // creamos el objeto Statement
            Statement sentencia = conexion.createStatement();
            //ejecutamos la consulta
            String sql = "SELECT * FROM baloncesto";
            ResultSet rs = sentencia.executeQuery(sql);
            // Recorremos el resultado para visualizar cada fila     

            // Se hace un bucle mientras haya registros y se van visualizando 
            while (rs.next()) {
                System.out.println("Jugador ID " + rs.getInt(1));
                System.out.println("  Nombre: " + rs.getString(2));
                System.out.println("  Apellido: " + rs.getString(3));
                System.out.println("  Altura: " + rs.getInt(4));
                System.out.println("  Edad: " + rs.getInt(5));
                System.out.println("  Posición: " + rs.getString(6));
                System.out.println("  Equipo: " + rs.getString(7) + "\n");
                
                //Lo mismo en una línea
                //System.out.printf("%d, %s, %s, %d, %d, %s, %s \n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
            }
            rs.close(); // Cerrar ResultSet
            sentencia.close(); // Cerrar Statement
            conexion.close(); // Cerrar conexión
        } catch (SQLException cn) {
            cn.printStackTrace();
        }
    }
}
