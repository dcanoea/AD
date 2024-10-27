/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ficheros;

import java.io.File;

/**
 *
 * @author David Cano Escario
 */
public class CrearDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            //Declaracion de variables
            String directorio = "C:/prueba";
            String varios = "C:/carpeta1/carpeta2/carpeta3";
            
            //Crear un directorio
            boolean exito = (new File(directorio)).mkdir();
            if (exito){
                System.out.println("Directorio: " + directorio + " creado");
            }
            
            //Crear varios directorios
            exito = (new File(varios)).mkdirs();
            if(exito){
                System.out.println("Directorios: " + varios + " creados");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
}
