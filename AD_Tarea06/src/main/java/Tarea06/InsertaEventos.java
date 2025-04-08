/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Tarea06;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.bson.Document;

/**
 *
 * @author David Cano Escario
 */
public class InsertaEventos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        MongoDatabase bbdd = MongoDBConnection.conectar();
        MongoCollection<Document> eventos = bbdd.getCollection("eventos");

        // Formato de fecha (para parsear las fechas)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Document evento = new Document()
                .append("_id", 3)
                .append("nombre", "Músical: El Rey León")
                .append("fecha", sdf.parse("2025-05-15")) 
                .append("ubicacion", "Gran Vía Madrid");

        eventos.insertOne(evento);
        System.out.println("Evento " + evento + " insertado");

        MongoDBConnection.desconectar();
    }

}
