/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class GestionBBDD {

    private static final ConexionBBDD conexion = new ConexionBBDD();

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

    public void joinCOD_VUELO() {
        try {
            Connection con = conexion.conectar();

            String sentenciaSQL = "SELECT vuelos.COD_VUELO, \n"
                    + "		vuelos.DESTINO, \n"
                    + "         pasajeros.NUM, \n"
                    + "         pasajeros.TIPO_PLAZA, \n"
                    + "         pasajeros.FUMADOR \n"
                    + "FROM pasajeros\n"
                    + "INNER JOIN vuelos ON vuelos.COD_VUELO = pasajeros.COD_VUELO \n"
                    + "WHERE pasajeros.COD_VUELO = ?";
            PreparedStatement pstmt = con.prepareStatement(sentenciaSQL);
            pstmt.setString(1, "AI-1289-9");

            ResultSet rs = pstmt.executeQuery();
            System.out.println("Sentencia ejecutada\n");
            while (rs.next()) {
                System.out.println("Codigo Vuelo -> " + rs.getString(1));
                System.out.println("Destino -> " + rs.getString(2));
                System.out.println("Num Pasajero -> " + rs.getInt(3));
                System.out.println("Tipo plaza -> " + rs.getString(4));
                System.out.println("Fumador -> " + rs.getString(5) + "\n");
            }

            rs.close();
            pstmt.close();
            conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertarVuelo(String codVuelo, Timestamp horaSalida, String destino, 
            String procedencia, int plzFum, int plzNoFum, int plzTur, int plzPr) {
        try {
            Connection con = conexion.conectar();

            String sentenciaSQL = "INSERT INTO vuelos VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sentenciaSQL);
            pstmt.setString(1, codVuelo);
            pstmt.setTimestamp(2, horaSalida);
            pstmt.setString(3, destino);
            pstmt.setString(4, procedencia);
            pstmt.setInt(5, plzFum);
            pstmt.setInt(6, plzNoFum);
            pstmt.setInt(7, plzTur);
            pstmt.setInt(8, plzPr);

            pstmt.executeUpdate();
            System.out.println("Vuelo insertado");
            
            pstmt.close();
            conexion.desconectar();
            
        } catch (SQLException ex) {
            System.out.println("Vuelo no insertado");
            ex.printStackTrace();
        }
    }
}
