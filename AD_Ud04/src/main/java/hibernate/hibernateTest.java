/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hibernate;

/**
 *
 * @author David Cano Escario
 */
import org.hibernate.Session;
public class hibernateTest {
    public static void main(String[] args) {
        // Obtener la sesión desde hibernateUtil
        Session session = hibernateUtil.getSessionFactory().openSession();
        // Probar la conexión
        System.out.println("Conexión exitosa con Hibernate y la base de datos");
        // Cerrar la sesión
        session.close();
        hibernateUtil.shutdown();
    }
}
