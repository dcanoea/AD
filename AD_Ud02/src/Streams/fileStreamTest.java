/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author David Cano Escario
 */
public class fileStreamTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        try {
            byte bWrite[] = {11, 21, 3, 40, 5};
            OutputStream os = new FileOutputStream("C:/AccesoDatos/test.txt");
            for (int x = 0; x < bWrite.length; x++) {
                os.write(bWrite[x]);   // writes the bytes
            }
            os.close();

            InputStream is = new FileInputStream("C:/AccesoDatos/test.txt");
            int size = is.available();

            for (int i = 0; i < size; i++) {
                System.out.print(is.read() + "  ");
            }
            is.close();
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }

}
