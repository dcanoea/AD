/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo1_ExistenciaYComprobacionPermisos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path path = Paths.get("C:\\AccesoDatos\\FileEjemplo\\hola.txt");
        System.out.println(" Path = " + path);
        System.out.println(" exists = " + Files.exists(path));
        System.out.println(" readable = " + Files.isReadable(path));
        System.out.println(" writeable = " + Files.isWritable(path));
        System.out.println(" executeable = " + Files.isExecutable(path));
        
    }

}
