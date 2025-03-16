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
public class ActualizarJSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = ConexionPostgreSQL.conectar();
        if (conn == null) {
            return;
        }
        try {
            // SQL para seleccionar los datos
            String sql = "UPDATE productos SET detalles = jsonb_set(detalles, '{precio}', '1499.99'::jsonb) WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Laptop Dell");
            stmt.executeUpdate();

            // Cerrar conexión
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
