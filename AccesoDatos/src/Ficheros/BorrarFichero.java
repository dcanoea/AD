/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ficheros;

import java.io.File;

/**
 *
 * @author poker
 */
public class BorrarFichero {
    public static void main(String[] args) {
        
        File fichero = new File("C://AccesoDatos/miFichero.txt");
        
        if(fichero.exists()){
            fichero.delete();
            System.out.println("Fichero borrado");
        }else{
            System.out.println("No existe el fichero");
        }
        
        
    }
    
}
