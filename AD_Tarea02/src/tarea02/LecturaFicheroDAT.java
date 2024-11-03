/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class LecturaFicheroDAT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variable String para cambiar la ruta del fichero rápidamente
        String ruta = "C:\\AccesoDatos\\Tarea02\\EMPLEADOS.DAT";
        File fichero = new File(ruta);

        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "r");//Abrimos el fichero en modo lectura

            for (int i = 0; i < 5; i++) {
                int codigo = raf.readInt();
                //String nombre = raf.readLine();
                
                System.out.printf("Código %d ", codigo);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
