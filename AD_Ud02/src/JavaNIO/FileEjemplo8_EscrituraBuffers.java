/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JavaNIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author David Cano Escario
 */
public class FileEjemplo8_EscrituraBuffers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path inputFile = Paths.get("C:\\AccesoDatos\\Origen\\holaBuffer.txt");
        Path outputFile = Paths.get("C:\\AccesoDatos\\Destino\\holaBuffer.txt");
        try {
            BufferedReader inputReader = Files.newBufferedReader(inputFile, Charset.defaultCharset());
            BufferedWriter outputWriter = Files.newBufferedWriter(outputFile, Charset.defaultCharset(), StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            String line;
            while ((line = inputReader.readLine()) != null){
                outputWriter.write(line, 0, line.length());
                outputWriter.newLine();
            }
            inputReader.close();
            outputWriter.close();
        } catch (IOException e) {
            System.err.println(" ERROR : " + e);
            System.exit(1);
        }
    }

}
