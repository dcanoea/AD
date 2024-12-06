/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class GestionBBDD {

    private static ConexionBBDD conexion = new ConexionBBDD();

    public void mostrarVuelos() {
        try {
            Connection con = conexion.conectar();
            String consulta = "SELECT * FROM vuelos";
            PreparedStatement pstmt = con.prepareStatement(consulta);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo Vuelo -> " + rs.getString(1));
                System.out.println("Dia y Hora Salida -> " + rs.getDate(2) + " " + rs.getTime(2));
                System.out.println("Destino -> " + rs.getString(3));
                System.out.println("Procedencia -> " + rs.getString(4));
                System.out.println("Plazas fumador -> " + rs.getInt(5));
                System.out.println("Plazas NO fumador -> " + rs.getInt(6));
                System.out.println("Plazas Turista -> " + rs.getInt(7));
                System.out.println("Plazas Primera -> " + rs.getInt(8) + "\n");
                
            }

            rs.close();
            pstmt.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
