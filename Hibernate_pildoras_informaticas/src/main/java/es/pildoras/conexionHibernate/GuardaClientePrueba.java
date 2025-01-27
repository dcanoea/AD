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
public class GuardaClientePrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            Clientes cliente1 = new Clientes("Sandra", "Delgado", "Goya");

            //Iniciamos la transacción
            miSession.beginTransaction();

            //Guardamos el objeto creado
            miSession.save(cliente1);

            //Cogemos la transacción y hacemos un commit (podria ser un rollback)
            miSession.getTransaction().commit();

            System.out.println("\n><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n"
                    + "Registro insertado correctamente en BBDD"
                    + "\n><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n");

            //----------------------------------------------------------------------------------------------------
            //Lectura de registro insertado
            miSession.beginTransaction();
            System.out.println("Lectura del registro con ID: " + cliente1.getId());
            Clientes clienteInsertado = miSession.get(Clientes.class, cliente1.getId());// Este objeto almacena los datos del cliente leido

            System.out.println("Registro : " + clienteInsertado);

            miSession.getTransaction().commit();

            System.out.println("Terminado!");
            //---------------------------------------------------------------------------------------------------

            //Cerramos la Session
            miSession.close();
        } finally {

            //Cerramos el SessionFactory
            miFactory.close();
        }
    }

}
