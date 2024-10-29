/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo2_CreacionBorradoDirectorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path path = Paths.get("C:\\AccesoDatos\\FileEjemplo");

        try {
            if (Files.exists(path)) {
                try {
                    Files.delete(path);//Método deleteIfExists(path) tambien borra el fichero pero no lanza ningun error en caso de que no exista
                } catch (NoSuchFileException x) {
                    System.err.format("%s: no such" + " file or directory%n", path);
                } catch (DirectoryNotEmptyException x) {
                    System.err.format("%s not empty%n", path);
                } catch (IOException x) {
                    // File permission problems are caught here.
                    System.err.println(x);
                }
            } else {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

}
