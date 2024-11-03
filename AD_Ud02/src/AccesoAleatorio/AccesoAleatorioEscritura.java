package AccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author David Cano Escario
 */
public class AccesoAleatorioEscritura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            //Crear el archivo de acceso aleatorio en modo lectura/escritura("rw")
            File archivo = new File("C:/AccesoDatos/ruleta.dat");
            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
            
            //Escribimos 5 registros de ejemplo con un tamaño fijo
            //Cada registro contiene un identificar (int) y un saldo (double)
            for (int i = 0; i < 5; i++) {
                raf.writeInt(i+1); //Escribimos el identificador (4 bytes)
                raf.writeDouble(100.50 * (i+1)); //Escribimos el saldo (8 bytes)
            }
            
            //Cerrar el archivo
            raf.close();
            System.out.println("Registros escritos correctamente en el fichero");
        } catch (IOException e) {
            e.printStackTrace();
        }
 
          
    }
    
}
