/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo4_CopiarDirectorios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Se puede copiar directorios, pero los archivos dentro del directorio no se copian
        Path sourcePath = Paths.get("C:\\AccesoDatos\\Origen");
        Path destinationPath = Paths.get("C:\\AccesoDatos\\Destino");
        try {
            Files.copy(sourcePath, destinationPath);
            //Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            System.out.println("El fichero existe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

