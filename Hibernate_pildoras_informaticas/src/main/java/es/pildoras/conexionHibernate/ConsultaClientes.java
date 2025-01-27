/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.pildoras.conexionHibernate;

import es.pildoras.conexionHibernate.Clientes;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David Cano Escario
 */
public class ConsultaClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {

            //comenzar sesion
            //Iniciamos la transacción
            miSession.beginTransaction();

            System.out.println("------------------------------------\nClientes:");
            // consulta de clientes
            List<Clientes> listaClientes = miSession.createQuery("from Clientes").getResultList();
            //mostrar los clientes
            recorreclientesconsultados(listaClientes);

            System.out.println("------------------------------------\nClientes con apellido Gómez");
            //consulta: dame los Gómez (siempre hacemos referencia a la propiedad de la clase, no a la de la BBDD)
            listaClientes = miSession.createQuery("from Clientes cl where cl.apellidos='Gómez'").getResultList();
            //mostrar los Gómez
            recorreclientesconsultados(listaClientes);

            System.out.println("------------------------------------\nClientes con apellido Delgado o que viven en Gran Vía");
            //consulta: muestra los que viven en Gran Via o se apellidan Delgado
            listaClientes = miSession.createQuery("from Clientes cl where cl.apellidos='Delgado'"
                    + " or cl.direccion='Gran Vía'").getResultList();
            //mostrar los clientes
            recorreclientesconsultados(listaClientes);

            // commit
            miSession.getTransaction().commit();

            //cierre Session
            miSession.close();
        } finally {
            //Cerramos el SessionFactory
            miFactory.close();
        }

    }

    private static void recorreclientesconsultados(List<Clientes> listaClientes) {
        for (Clientes c : listaClientes) {
            System.out.println(c);
        }
    }
}
