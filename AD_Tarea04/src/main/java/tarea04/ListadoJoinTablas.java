/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea04;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import tarea04.Departamento;
import tarea04.Empleado;

/**
 *
 * @author David Cano Escario
 */
public class ListadoJoinTablas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Creamos el SessionFactory con las clases mapeadas
        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empleado.class)
                .addAnnotatedClass(Departamento.class)
                .buildSessionFactory();

        //Abrimos Session
        Session miSession = miFactory.openSession();

        try {
            //Iniciamos la transacción
            miSession.beginTransaction();

            //CONSULTA HQL JOIN EMP y DEPT
            String hql = "SELECT e.numeroEmp, e.nombreEmp, e.salarioEmp, d.nombreDep, d.localizacionDep "
                    + "FROM Empleado e JOIN e.departamento d";

            //Ejecutar la consulta
            //Se guardaran los datos en un array de Objetos. Cada posición de ese objeto será una columna
            Query<Object[]> query = miSession.createQuery(hql, Object[].class);
            List<Object[]> lista = query.getResultList();

            //Mediante un for each mostramos los resultados
            for (Object[] obj : lista) {
                int EMPNO = (int) obj[0];
                String ENAME = (String) obj[1];
                Double SAL = (Double) obj[2];
                String DNAME = (String) obj[3];
                String LOC = (String) obj[4];

                System.out.println("empno: " + EMPNO + ", ename: " + ENAME + ", sal: " + SAL + ", dname: " + DNAME + ", loc: " + LOC);
            }

            //Confirmamos la transaccion
            miSession.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en la consulta");
            e.printStackTrace();

        } finally {
            //Cerramos Session y SessionFactory
            miSession.close();
            miFactory.close();
        }
    }
}
