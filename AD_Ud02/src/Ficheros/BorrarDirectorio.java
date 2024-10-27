/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ficheros;

import java.io.File;

/**
 *
 * @author David Cano Escario
 */
public class BorrarDirectorio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File directorio = new File("C:/carpeta1");

        File[] ficheros = directorio.listFiles();

        for (File f : ficheros) {
            if (f.isDirectory()) {
                File [] subcarpetas = f.listFiles();
                for (File f2 : subcarpetas){
                    if (f2.isDirectory()){
                        f2.delete();
                    }
                }
                f.delete();
            }
        }
        directorio.delete();
    }

}
