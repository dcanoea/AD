/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea03;

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
        gestion.joinCOD_VUELO();
    }
}
