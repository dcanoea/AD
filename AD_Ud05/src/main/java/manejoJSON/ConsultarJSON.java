/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manejoJSON;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Cano Escario
 */
public class ConsultarJSON {

    public static void main(String[] args) {
        Connection conn = ConexionPostgreSQL.conectar();
        if (conn == null) {
            return;
        }
        try {
            // SQL para seleccionar los datos
            String sql = "SELECT nombre, detalles FROM productos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String detalles = rs.getString("detalles");

                // JSON en formato String
                System.out.println("Producto: " + nombre);
                System.out.println("JSON Detalles: " + detalles);
            }
            // Cerrar conexión
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
