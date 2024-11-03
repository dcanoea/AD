/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea02;

import java.io.File;
import java.io.RandomAccessFile;

/**
 *
 * @author David Cano Escario
 */
public class CreacionFicheroDAT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variable String para cambiar la ruta del fichero rápidamente
        String ruta = "C:\\AccesoDatos\\Tarea02\\EMPLEADOS.DAT";
        File fichero = new File(ruta);

        //Creando el fichero EMPLEADOS.DAT
        try {
            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            //Bucle para escribir los 5 registros de empleados
            for (int i = 0; i < 5; i++) {
                raf.writeInt(i + 1); //Código int
                raf.writeChars("Nombre 0" + (i + 1)); //Nombre String
                raf.writeChars("Dirección 0" + (i + 1));//Dirección String
                raf.writeFloat(1200 + (i + 1)); //Salario float
                raf.writeFloat(100 + (i + 1));//Comision float
            }
            
            //Cierre del fichero
            raf.close();
            System.out.println("Registros escritos correctamente en: " + fichero.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
