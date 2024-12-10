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
        //gestion.borrarVuelo("AA-1234-5");
        //gestion.modificarFumadores();
        //gestion.crearTablaReservas();
        //gestion.crearReserva(123, "IB-SP-4567", Timestamp.valueOf(LocalDateTime.now()), "01-A");
        //gestion.crearReserva(133, "AI-1289-9", Timestamp.valueOf(LocalDateTime.now()), "14-B");
        //gestion.crearReserva(137, "FR-DC-4667", Timestamp.valueOf(LocalDateTime.now()), "124-C");

        gestion.consultaTresTablas();
    }
}
