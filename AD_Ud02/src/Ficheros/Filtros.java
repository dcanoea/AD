/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ficheros;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author David Cano Escario
 */
public class Filtros implements FilenameFilter {

    String extension;
    
    //Constructor
    Filtros(String extension){
        this.extension = extension;
    }
    public boolean accept(File dir, String name){
        return name.endsWith(extension);
    }
    
    
    
    public static void main(String[] args) {
   
        try {
            //Obtendremos el listado de los archivos de ese directorio
            File fichero = new File("C:/Users/poker/Downloads/.");
            String [] listaArchivos = fichero.list();
            
            //Filtraremos por los de extension .exe
            listaArchivos = fichero.list(new Filtros(".exe"));
            
            //Comprobamos el número de archivos en el listado
            int numArchivos = listaArchivos.length;
            
            //Si no hay ninguno avisamos por consola
            if(numArchivos < 1){
                System.out.println("No hay archivos que listar");
            }else{
                //Y si hay, escribimos su nombre
                for (String s : listaArchivos){
                    System.out.println(s);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al buscar en la ruta indicada");
        }
        
        
    }

    
}
