/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Streams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author David Cano Escario
 */
public class ByteStreams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try {
            in = new FileInputStream("C:/AccesoDatos/input.txt");
            out = new FileOutputStream("C:/AccesoDatos/output.txt");
            
            int c;
            while ((c = in.read()) != -1){
                out.write(c);
            }
                
        } finally {
            if (in != null){
                in.close();
            }
            if (out != null){
                out.close();
            }
        }
    }
    
}
