/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;

/**
 *
 * @author David Cano Escario
 */
public class PathEjemplo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileSystem sistemaFicheros = FileSystems.getDefault();
        Path rutaFichero = sistemaFicheros.getPath("C:\\Users\\alumno\\PathEjemplo");
        System.out.println(rutaFichero.getFileName());
        System.out.println(rutaFichero.getParent().getFileName());
        Path rutaDirectorio = sistemaFicheros.getPath("C:\\Users\\alumno");
        Iterator<Path> it = rutaDirectorio.iterator();
        while (it.hasNext()){
            System.out.println(it.next().getFileName());
        }
    }
    
}
