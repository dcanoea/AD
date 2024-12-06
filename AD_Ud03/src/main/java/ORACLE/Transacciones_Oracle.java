/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ORACLE;

import SQL.Transacciones_SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class Transacciones_Oracle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String urljdbc = "jdbc:oracle:thin:@localhost:1521:free";
        String usuario = "C##user1";
        String pass = "user1";
        PreparedStatement pstm1 = null;
        PreparedStatement pstm2 = null;

        try {
            Connection conexion = DriverManager.getConnection(urljdbc, usuario, pass);
            System.out.println("Conexión establecida");

            //Desactivar el autocommit para manejar las transacciones manualmente
            conexion.setAutoCommit(false);

            //Vamos a insertar dos jugadores, si uno falla el otro tampoco se agrega
            //Sentencia para agregar jugadores
            String sql1 = "INSERT INTO baloncesto VALUES (?, ?, ?, ?, ?, ?, ?)";

            //Primer jugador
            try {
                pstm1 = conexion.prepareStatement(sql1);
                pstm1.setInt(1, 11);               // ID
                pstm1.setString(2, "James");       // Nombre
                pstm1.setString(3, "Harden");      // Apellidos
                pstm1.setInt(4, 196);              // Altura
                pstm1.setInt(5, 34);               // Edad
                pstm1.setString(6, "Escolta");     // Posición
                pstm1.setString(7, "Clippers");      // Equipo
                pstm1.executeUpdate(); // Ejecutar la inserción
                System.out.println("Jugador 1 agregado");
                //Si todo está bien se confirma la transacción
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error al agregar el primer jugador");
            }

            //Segundo jugador
            try {
                pstm2 = conexion.prepareStatement(sql1);
                pstm2.setInt(1, 12);               // ID
                pstm2.setString(2, "Joel");        // Nombre
                pstm2.setString(3, "Embiid");      // Apellidos
                pstm2.setInt(4, 213);              // Altura
                pstm2.setInt(5, 29);               // Edad
                pstm2.setString(6, "Pivot");       // Posición
                pstm2.setString(7, "76ers");      // Equipo
                pstm2.executeUpdate(); // Ejecutar la inserción
                System.out.println("Jugador 2 agregado");
                //Si todo está bien se confirma la transacción
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                System.out.println("Error al agregar el segundo jugador");
            } finally {
                if (pstm1 != null) {
                    pstm1.close();
                }
                if (pstm2 != null) {
                    pstm2.close();
                }
                conexion.setAutoCommit(true);

                conexion.close();
                System.out.println("Conexion cerrada");
            }

        } catch (SQLException ex) {
            System.out.println("Error al conectar a BBDD");
            Logger.getLogger(Transacciones_SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}