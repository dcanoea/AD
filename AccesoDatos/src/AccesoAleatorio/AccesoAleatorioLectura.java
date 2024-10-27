/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AccesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author David Cano Escario
 */
public class AccesoAleatorioLectura {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            //Abrimos el fichero de acceso aleatorio en modo lectura ("r")
            File fichero = new File("C:/AccesoDatos/ruleta.dat");
            RandomAccessFile raf = new RandomAccessFile(fichero, "r");
            
            //Calcular el tamaño del registro (4 bytes de int + 8 bytes de double = 12 bytes)
            int tamanoRegistro = 12;
            
            //Leer el tercer registro (índice 2, ya que empieza desde 0)
            int registro = 2;
            raf.seek(registro * tamanoRegistro);//Posicionarse en el tercer registro
            
            //Leer los datos del tercer registro
            int id = raf.readInt(); //Leer identificador
            double saldo = raf.readDouble(); //Leer saldo
            
            //Imprimir el contenido del tercer registro
            System.out.println("ID: " + id + ", Saldo: " + saldo);
            
            //Cerrar el archivo
            raf.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
