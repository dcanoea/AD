/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manejoJSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author David Cano Escario
 */
public class InsertarJSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        insertar();
    }

    public static void insertar() {
        Connection conn = ConexionPostgreSQL.conectar();
        if (conn == null) {
            return;
        }
        try {
            // Crear un objeto Producto
            Producto producto = new Producto("Dell", "XPS 13",
                    1299.99);
            // Convertir objeto a JSON con Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonProducto
                    = objectMapper.writeValueAsString(producto);
            // SQL para insertar datos
            String sql = "INSERT INTO productos (nombre, detalles) VALUES( ?,  ?::jsonb)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Laptop Dell");
            stmt.setString(2, jsonProducto);
            // Ejecutar consulta
            int filasAfectadas = stmt.executeUpdate();
            System.out.println("✅ Filas insertadas: " + filasAfectadas);
            // Cerrar conexión
            stmt.close();
            conn.close();
        } catch (SQLException
                | com.fasterxml.jackson.core.JsonProcessingException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
