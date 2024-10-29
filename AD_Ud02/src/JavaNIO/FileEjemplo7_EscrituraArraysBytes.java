/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo7_EscrituraArraysBytes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path inputFile = Paths.get("C:\\AccesoDatos\\Origen\\hola.txt");
        Path outputFile = Paths.get("C:\\AccesoDatos\\Destino\\hola.txt");
        try {
            byte[] contents = Files.readAllBytes(inputFile);
            Files.write(outputFile, contents, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("ERROR : " + e);
            System.exit(1);
        }
    }
    
}
