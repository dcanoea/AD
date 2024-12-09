/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea03;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author David Cano Escario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionBBDD con = new ConexionBBDD();
        GestionBBDD gestion = new GestionBBDD();
        
        //gestion.mostrarVuelos();   
        //gestion.joinCOD_VUELO();
        //gestion.insertarVuelo("AA-1234-5",Timestamp.valueOf("2024-12-24 22:10:00") , "LIMA", "PARIS", 50, 250, 200, 80);
        
    }
}
