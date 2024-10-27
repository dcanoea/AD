/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PersistenciaObjetos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author David Cano Escario
 */
class PersonaSinSerializable {

    private String nombre;
    private int edad;

    public PersonaSinSerializable(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }

    public void guardarEnArchivo(String nombreArchivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            escritor.write(nombre);
            escritor.newLine();
            escritor.write(Integer.toString(edad));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //Método que lee los atributos de PersonaSinSerializable guardados en el archivo, y los usa para crear un nuevo Objeto
    public static PersonaSinSerializable leerDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String nombre = lector.readLine();
            int edad = Integer.parseInt(lector.readLine());
            return new PersonaSinSerializable(nombre, edad);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class PersistenciaSinSerializable {

    public static void main(String[] args) {
// Crear un objeto PersonaSinSerializable
        PersonaSinSerializable persona = new PersonaSinSerializable("Miguel", 67);
// Guardar el objeto en un archivo
        persona.guardarEnArchivo("C:\\AccesoDatos\\persona.txt");
        System.out.println("Objeto Persona guardado en archivo.");
// Leer el objeto desde el archivo
        PersonaSinSerializable personaLeida
                = PersonaSinSerializable.leerDesdeArchivo("C:\\AccesoDatos\\persona.txt");
        if (personaLeida != null) {
            System.out.println("Objeto leído desde el archivo: "
                    + personaLeida);
        }
    }
}
