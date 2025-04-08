/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tarea06;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author David Cano Escario
 */
public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static final String CONNECTION__STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "tienda";

    // Método para obtener la conexión a la base de datos
    public static MongoDatabase conectar() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION__STRING);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
    
    // Método para cerrar la conexión
    public static void desconectar(){
        if(mongoClient != null){
            mongoClient.close();
            mongoClient = null;
        }
    }
}
