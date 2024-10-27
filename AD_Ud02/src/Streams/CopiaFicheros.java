/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author David Cano Escario
 */
public class CopiaFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Copiar Ficheros
        File origen = new File("C:/AccesoDatos/mario.jpg");
        File destino = new File("C:/AccesoDatos/copiamario.jpg");
        
        try {
            InputStream in = new FileInputStream(origen);
            OutputStream out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {                
                out.write(buf, 0, len);
            }
            in.close();
            out.close();          
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}
