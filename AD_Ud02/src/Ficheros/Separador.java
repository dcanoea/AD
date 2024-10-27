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
public class Separador {

    static String substFileSeparator(String ruta) {
        String separador = "\\";
        
        try {
            //Si estamos en Windows
            if (File.separator.equals(separador)) 
                separador = "/";
                // Reemplaza todas las cadenas que coinciden con la expresión 
                // regular dada oldSep por la cadena File.separator
                return ruta.replaceAll(separador, File.separator);
        } catch (Exception e) {
            return ruta.replaceAll(separador + separador, File.separator);
        }
    }

}
