/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David Cano Escario
 */
public class pruebaConexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Creamos el Sessionfactory utilizando el xml con la configuración de hibernate
            SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

            //Abrimos un Session
            Session session = miFactory.openSession();
            
            System.out.println("Conectado a BBDD");
            
            //Cerramos el Session y Session Factory
            session.close();
            miFactory.close();
        } catch (Exception e) {
            System.out.println("No se pudo conectar a BBDD");
            e.printStackTrace();
        }
    }
}
