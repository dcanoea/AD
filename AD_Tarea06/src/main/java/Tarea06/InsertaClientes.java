/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tarea06;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.bson.Document;

/**
 *
 * @author David Cano Escario
 */
public class InsertaClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        MongoDatabase bbdd = MongoDBConnection.conectar();
        MongoCollection<Document> clientes = bbdd.getCollection("clientes");

        // Formato de fecha (para parsear las fechas)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Document cliente = new Document()
                .append("_id", 103)
                .append("nombre", "David Cano")
                .append("compras", Arrays.asList(
                        new Document("evento_id", 1)
                                .append("fecha_compra", sdf.parse("2025-04-08"))
                                .append("cantidad", 3),
                        new Document("evento_id", 3)
                                .append("fecha_compra", sdf.parse("2025-03-25"))
                                .append("cantidad", 2)));
        
        clientes.insertOne(cliente);

        System.out.println("Cliente " + cliente + " insertado");
        
        MongoDBConnection.desconectar();
    }
}
