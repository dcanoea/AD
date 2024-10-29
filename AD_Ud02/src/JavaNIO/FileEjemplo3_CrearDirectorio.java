/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo3_CrearDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path path = Paths.get("C:\\AccesoDatos\\FileEjemplo");
        try {
            Path newDir = Files.createDirectory(path);
        } catch (FileAlreadyExistsException e) {
            System.out.println("El directorio ya existe");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error I/O");
            e.printStackTrace();
        }

    }
}
