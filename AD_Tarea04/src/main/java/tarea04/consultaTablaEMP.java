/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea04;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David Cano Escario
 */
public class consultaTablaEMP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class).buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {

            //comenzar sesion
            //Iniciamos la transacción
            miSession.beginTransaction();

            System.out.println("------------------------------------\nEmpleados:");
            // consulta de empleados
            List<Empleado> listaEmpleados = miSession.createQuery("from Empleado").getResultList();
            //mostrar los empleados
            for(Empleado emp : listaEmpleados){
                System.out.println(emp);
            }
            miSession.close();
        } finally {
            //Cerramos el SessionFactory
            miFactory.close();
        }
    }
}
