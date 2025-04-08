/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tarea06;

import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.text.SimpleDateFormat;
import org.bson.Document;

/**
 *
 * @author David Cano Escario
 */

public class ConsultaEventosCliente {

    public static void main(String[] args) {
        // 1. Conexión directa
        MongoDatabase bbdd = MongoDBConnection.conectar();
        
        // 2. Crear StringBuilder para almacenar los resultados
        StringBuilder resultado = new StringBuilder();
        
        // 3. Consultar cliente con ID 103
        Document cliente = bbdd.getCollection("clientes")
                             .find(eq("_id", 103))
                             .first();

        // 4. Formateador de fechas
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // 5. Construir el string con los datos
        resultado.append("Cliente: ").append(cliente.getString("nombre")).append("\n");
        resultado.append("----------------------------------------\n");
        
        for (Document compra : cliente.getList("compras", Document.class)) {
            Document evento = bbdd.getCollection("eventos")
                                .find(eq("_id", compra.getInteger("evento_id")))
                                .first();
            resultado.append("Evento: ").append(evento.getString("nombre")).append("\n");
            resultado.append("Ubicación: ").append(evento.getString("ubicacion")).append("\n");
            resultado.append("Fecha: ").append(sdf.format(evento.getDate("fecha"))).append("\n");
            resultado.append("Cantidad: ").append(compra.getInteger("cantidad")).append("\n");
            resultado.append("----------------------------------------\n");
        }
        
        // 6. Imprimir todo el resultado de una vez
        System.out.println(resultado.toString());
        
        // 7. Cerrar conexión
        MongoDBConnection.desconectar();
    }
}