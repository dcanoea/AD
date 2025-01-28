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
public class consultaTablaDEPT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SessionFactory miFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Departamento.class)
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();

        Session miSession = miFactory.openSession();

        try {

            //comenzar sesion
            //Iniciamos la transacción
            miSession.beginTransaction();

            System.out.println("------------------------------------\nDepartamentos:");
            // consulta de departamentos
            List<Departamento> listaDepartamentos = miSession.createQuery("from Departamento", Departamento.class).getResultList();
            //mostrar los departamentos
            for (Departamento dep : listaDepartamentos) {
                System.out.println(dep);
            }
            miSession.close();
        } finally {
            //Cerramos el SessionFactory
            miFactory.close();
        }
    }
}
