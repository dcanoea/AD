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
public class ActualizaClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {
            //int clienteId = 1;
            
            //Iniciamos la transacción
            miSession.beginTransaction();
            
            //Creamos objeto tipo Clientes, que vamos a modificar (Cliente con id 1)
            //Clientes miCliente = miSession.get(Clientes.class, clienteId);
           
            //Cambiamos el nombre del cliente
            //miCliente.setNombre("Juan Ma");
            
            //Cambiamos el apellido a Dominguez todos los clientes que su apellido empieza por D
            miSession.createQuery("update Clientes set apellidos = 'Dominguez' where apellidos LIKE 'D%'").executeUpdate();
            
            //Cogemos la transacción y hacemos un commit (podria ser un rollback)
            miSession.getTransaction().commit();

            System.out.println("\n><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n"
                    + "Registro actualizado correctamente en BBDD"
                    + "\n><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n");

            //Cerramos la Session
            miSession.close();
        } finally {

            //Cerramos el SessionFactory
            miFactory.close();
        }
    }

}
