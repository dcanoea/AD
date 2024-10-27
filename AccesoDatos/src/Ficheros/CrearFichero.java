/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ficheros;

import java.io.File;

/**
 *
 * @author poker
 */
public class CrearFichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            File fichero = new File("C://AccesoDatos/miFichero.txt");
            
            if (fichero.mkdir()){
                System.out.println("El fichero se ha creado correctamente");
            }else {
                System.out.println("No se ha podido crear el fichero");
            }

        } catch (Exception e) {
            e.getMessage();
        }

    }

}
