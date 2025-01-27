/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David Cano Escario
 */
public class BorraClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
              
            //Iniciamos la transacción
            miSession.beginTransaction();
            
            //Borramos los clientes que vivan en Goya
            miSession.createQuery("delete Clientes where direccion ='Goya'").executeUpdate();
            
            //Cogemos la transacción y hacemos un commit (podria ser un rollback)
            miSession.getTransaction().commit();

            System.out.println("\n><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n"
                    + "Registro borrado correctamente en BBDD"
                    + "\n><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n");

            //Cerramos la Session
            miSession.close();
        } finally {

            //Cerramos el SessionFactory
            miFactory.close();
        }
    }

}
