/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Streams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author David Cano Escario
 */
public class LeerFicheroEOF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:/AccesoDatos/origen.txt"));
        int codigo = br.read();//lee el primer caracter
        char caracter;
        
        //mientras el código no sea -1(End Of File) continuo leyendo
        while (codigo!=-1) {
            caracter = (char) codigo; //casting
            System.out.print(caracter);
            codigo = br.read();//lee el siguiente caracter
        }
    }
}