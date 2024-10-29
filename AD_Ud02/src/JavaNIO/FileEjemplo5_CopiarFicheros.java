/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo5_CopiarFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path sourcePath = Paths.get("C:\\AccesoDatos\\Origen\\11.txt");
        Path destinationPath = Paths.get("C:\\AccesoDatos\\Destino\\11.txt");
        try {
            //Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(sourcePath, destinationPath);
        } catch (FileAlreadyExistsException e) {
            System.out.println("El destino existe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
