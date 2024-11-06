/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Cano Escario
 */
public class LecturaArchivoDAT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String con la ruta del fichero y lo instanciamos en un File
        String ruta = "src/resources/EMPLEADOS.DAT";
        File file = new File(ruta);

        try {
            //Creamos el RandomAccesFile con permisos de lectura (r)
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            
            //Mediante un puntero comprobamos si se ha llegado al final del fichero. Si no, sigue leyendo
            while (raf.getFilePointer() < file.length()) {
                Empleado empleado = Empleado.leerEmpleado(raf);
                System.out.println(empleado);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
            Logger.getLogger(LecturaArchivoDAT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al leer el fichero");
            Logger.getLogger(LecturaArchivoDAT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
