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
public class CrearArchivoDAT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creamos los 5 empleados
        Empleado empleado1 = new Empleado(1, "David Cano", "Calle Niagara", 1000, 100);
        Empleado empleado2 = new Empleado(2, "Miguel Cano", "Avda Martinez de Velasco", 3000, 300);
        Empleado empleado3 = new Empleado(3, "Jimena Cornejo", "Calle Huertas", 2222, 123);
        Empleado empleado4 = new Empleado(4, "Juan Carlos Carito", "Avda Monegros", 4121, 10);
        Empleado empleado5 = new Empleado(5, "Teo Cancor", "Avda Ramon y Cajal", 300, 5);

        //String con la ruta donde guardaremos el fichero y lo instanciamos en un File
        String ruta = "src/resources/EMPLEADOS.DAT";
        File file = new File(ruta);

        try {
            // Creamos el RandomAccesFile con permisos de lectura y escritura (RW)
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            //Escribimos cada empleado en el archivo mediante el método escribirEmpleado
            empleado1.escribirEmpleado(raf);
            empleado2.escribirEmpleado(raf);
            empleado3.escribirEmpleado(raf);
            empleado4.escribirEmpleado(raf);
            empleado5.escribirEmpleado(raf);

            raf.close();
            System.out.println("Empleados escritos en el fichero " + file + "\n\n\n");

            //----------------------------------------------------------------------------------------------------------
            // Lectura del archivo para comprobación
            RandomAccessFile rafLectura = new RandomAccessFile(file, "r");
            //Mediante un puntero comprobamos si se ha llegado al final del fichero. Si no, sigue leyendo
            while (rafLectura.getFilePointer() < file.length()) {
                Empleado empleado = Empleado.leerEmpleado(rafLectura);
                System.out.println(empleado);
            }
            rafLectura.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
            Logger.getLogger(CrearArchivoDAT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error al escribir en fichero");
            Logger.getLogger(CrearArchivoDAT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
