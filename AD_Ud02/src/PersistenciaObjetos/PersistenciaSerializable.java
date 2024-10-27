/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PersistenciaObjetos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author David Cano Escario
 */


class Persona implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }


}



public class PersistenciaSerializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Crear un objeto Persona
        Persona persona = new Persona("David", 38);
        
        //Guardar el objeto en un archivo
        try {
            FileOutputStream archivo = new FileOutputStream("C:\\AccesoDatos\\persona.dat");
            ObjectOutputStream salida = new ObjectOutputStream(archivo);
            salida.writeObject(persona);
            System.out.println("Objeto Persona guardado en archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Leer el objeto desde el archivo
        try {
            FileInputStream archivo = new FileInputStream("C:\\AccesoDatos\\persona.dat");
            ObjectInputStream entrada = new ObjectInputStream(archivo);
            Persona personaLeida = (Persona) entrada.readObject();
            System.out.println("Objeto leído desde el archivo: " + personaLeida);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
