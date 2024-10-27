/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Streams;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author David Cano Escario
 */
public class CharacterStreams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        FileReader in = null;
        FileWriter out = null;
        
        try {
            in = new FileReader("C:/AccesoDatos/input2.txt");
            out = new FileWriter("C:/AccesoDatos/output2.txt");
            
            int c;
            while ((c=in.read()) != -1){
                out.write(c);
            }
        } finally {
            if (in != null) {
            in.close();
         }
         if (out != null) {
            out.close();
         }
        }
    }
    
}
