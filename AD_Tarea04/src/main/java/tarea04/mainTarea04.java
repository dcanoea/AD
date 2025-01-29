/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea04;

import java.security.Timestamp;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import tarea04.Departamento;
import tarea04.Empleado;

/**
 *
 * @author David Cano Escario
 */
public class mainTarea04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Creamos el Session factory con las clases mapeadas
        SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empleado.class)
                .addAnnotatedClass(Departamento.class)
                .buildSessionFactory();

        //Abrimos Session
        Session miSession = miFactory.openSession();

        try {
            //---------------------------------------------------------------------------------------------------------------------------
            //INSERTAR UN DEPARTAMENTO
            int numDep = insertarDepartamento(50, "INFORMATICA", "HUESCA", miSession);
            System.out.println("Departamento insertado correctamente con número de Departamento " + numDep);
            //---------------------------------------------------------------------------------------------------------------------------

            //---------------------------------------------------------------------------------------------------------------------------
            //INSERTAR UN EMPLEADO
            insertarEmpleado(1234, "DAVID", "BECARIO", new java.util.Date(), 1000.00, 200.00, 50, 7369, miSession);
            //---------------------------------------------------------------------------------------------------------------------------

            //---------------------------------------------------------------------------------------------------------------------------
            //BORRAR UN EMPLEADO
            borrarEmpleado(1234, miSession);
            //---------------------------------------------------------------------------------------------------------------------------
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } finally {
            //Cerramos Session y SessionFactory para liberar recursos
            miSession.close();
            miFactory.close();
        }
    }

    // Método para insertar un departamento
    public static int insertarDepartamento(int numDep, String nombre, String localizacion, Session session) {
        // Iniciamos la transacción
        session.beginTransaction();

        // Crear el objeto Departamento
        Departamento nuevoDepartamento = new Departamento();
        nuevoDepartamento.setNumeroDep(numDep);
        nuevoDepartamento.setNombreDep(nombre);
        nuevoDepartamento.setLocalizacionDep(localizacion);

        // Guardar el departamento en la base de datos
        session.save(nuevoDepartamento);

        // Confirmamos la transacción
        session.getTransaction().commit();

        return nuevoDepartamento.getNumeroDep();
    }

    //METODO INSERTAR EMPLEADO
    public static void insertarEmpleado(int numeroEmp, String nombreEmp, String puestoTrabajoEmp, Date fechaInicioEmp,
            Double salarioEmp, Double comisionEmp, int numDepartamento, Integer numManager, Session session) {
        // Iniciamos la transacción
        session.beginTransaction();

        // Recuperamos el Departamento (clave foránea)
        Departamento departamento = session.get(Departamento.class, numDepartamento);

        // Si el ID del manager no es null, recuperamos al Manager (clave foránea, puede ser null)
        Empleado manager = null;
        if (numManager != null) {
            manager = session.get(Empleado.class, numManager);
        }

        // Creamos el objeto Empleado y le asignamos los valores
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNumeroEmp(numeroEmp);
        nuevoEmpleado.setNombreEmp(nombreEmp);
        nuevoEmpleado.setPuestoTrabajoEmp(puestoTrabajoEmp);
        nuevoEmpleado.setFechaInicioEmp(fechaInicioEmp);
        nuevoEmpleado.setSalarioEmp(salarioEmp);
        nuevoEmpleado.setComisionEmp(comisionEmp);
        nuevoEmpleado.setDepartamento(departamento);
        nuevoEmpleado.setManager(manager); // Si manager es null, no se asignará un jefe

        // Guardamos el empleado en la base de datos
        session.save(nuevoEmpleado);

        // Confirmamos la transacción
        session.getTransaction().commit();

        // Mensaje de confirmación
        System.out.println("Empleado insertado correctamente");
    }

    //METODO BORRAR EMPLEADO
    public static void borrarEmpleado(int numEmpleado, Session session) {
        //Iniciamos la transacción
        session.beginTransaction();

        //Borramos el cliente mediante su EMPNO
        session.createQuery("delete Empleado where numeroEmp = " + numEmpleado).executeUpdate();

        // Confirmamos la transacción
        session.getTransaction().commit();

        System.out.println("Empleado borrado correctamente");
    }
}
