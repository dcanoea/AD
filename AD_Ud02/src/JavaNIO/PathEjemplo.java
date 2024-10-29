/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author David Cano Escario
 */
public class PathEjemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path path = Paths.get("C:\\AccesoDatos\\PathEjemplo");
        System.out.println("Path = " + path);
        System.out.println("Is absolute? = " + path.isAbsolute());
        System.out.println("File short name = " + path.getFileName());
        System.out.println("Parent = " + path.getParent());
        System.out.println("URI = " + path.toUri() + "\n");
        
        path = Paths.get("/home/PathEjemplo");
        System.out.println("Path = " + path);
        System.out.println("Is absolute? = " + path.isAbsolute());
        System.out.println("File short name = " + path.getFileName());
        System.out.println("Parent = " + path.getParent());
        System.out.println("URI = " + path.toUri() + "\n");
    }
    
}
