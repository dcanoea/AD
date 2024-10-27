/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Streams;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author David Cano Escario
 */
public class StandardStreams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        InputStreamReader isr = null;

        try {
            isr = new InputStreamReader(System.in);
            System.out.println("Enter characters, 'q' to quit.");
            char c;
            do {
                c = (char) isr.read();
                System.out.print(c);
            } while (c != 'q');
        } finally {
            if (isr != null) {
                isr.close();
            }
        }
    }
}
