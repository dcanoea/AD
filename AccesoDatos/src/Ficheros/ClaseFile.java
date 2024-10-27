/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ficheros;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author poker
 */
public class ClaseFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File d = new File("C:/AccesoDatos");
        File f = new File("C:/AccesoDatos/hola.txt");
        File f2 = new File("C:/AccesoDatos/adios.txt");
        File f3 = new File("C:/AccesoDatos/borrar.txt");

        System.out.println(f);
        System.out.println("Existe: " + f.exists());
        System.out.println("Se puede leer: " + f.canRead());
        System.out.println("Se puede escribir: " + f.canWrite());
        System.out.println("Nombre fichero: " + f.getName());
        System.out.println("Ruta fichero: " + f.getAbsolutePath());
        System.out.println("Directorio padre: " + f.getParent());
        System.out.println("Objeto File padre: " + f.getParentFile());
        System.out.println("¿Es un directorio? " + f.isDirectory());
        System.out.println("¿Es un fichero? " + f.isFile());
        System.out.println("Ultima vez modificado (milisegundos desde 1/1/1970): " + f.lastModified());
        System.out.println("Tamanyo en bytes: " + f.length());
        System.out.println("");


        try {
            System.out.println("Creando fichero: " + f2);
            if (f2.createNewFile() == true) {
                System.out.println("Fichero creado\n");
            } else {
                System.out.println("Fichero no creado");
                if (f2.exists()) {
                    System.out.println("El fichero ya existe\n");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClaseFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Borrando " + f3);
        if (f3.delete()) {
            System.out.println("Borrado\n");
        } else {
            System.out.println("El fichero no existe\n");
        }
 
        
        System.out.println("Contenido directorio " + d.getAbsolutePath()
        );
        String [] array = d.list();
        for (String s : array){
            System.out.println(s);
        }
    }

}
