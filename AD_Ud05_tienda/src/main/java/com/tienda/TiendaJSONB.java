package com.tienda;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.tienda.modelo.Producto;
import com.tienda.util.HibernateUtil;
import java.util.Map;

/**
 *
 * @author David Cano Escario
 */
public class TiendaJSONB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //insertarProductos();
        //listarProductos();
        //actualizarCampoJSONB();
        //listarProductos();
        //agregarCampoJSONB();
        //listarProductos();
        //actualizarCampoAnidadoJSONB();
        listarProductos();
    }

    public static void insertarProductos() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        try {
            //Producto 1: Laptop Dell con características estándar
            Producto producto1 = new Producto(101L, "Laptop Dell", Map.of(
                    "marca", "Dell", //Sintaxis es ("etiqueta", "valor")
                    "modelo", "XPS 13",
                    "precio", 1500,
                    "especificaciones", Map.of(
                            "RAM", "16GB",
                            "SSD", "512GB")
            ));

            //Producto 2: MacBook con datos completamente distintos
            Producto producto2 = new Producto(102L, "MacBook Pro", Map.of(
                    "marca", "Apple",
                    "modelo", "M1 Pro",
                    "precio", 2200,
                    "especificaciones", Map.of(
                            "RAM", "32GB",
                            "SSD", "1TB",
                            "color", "Gris Espacial"),
                    "accesorios", new String[]{"Cargador MagSafe", "Funda protectora"}
            ));

            //Producto 3: ThinkPad con detalles de garantía
            Producto producto3 = new Producto(103L, "ThinkPad X1 Carbon", Map.of(
                    "marca", "Lenovo",
                    "modelo", "X1 Carbon",
                    "precio", 1800,
                    "garantia", Map.of(
                            "tipo", "Extendida",
                            "duracion", "3 años"),
                    "teclado", "Retroiluminado",
                    "pantalla", "4K UHD"
            ));

            //Producto 4: Smartwatch sin muchas especificaciones
            Producto producto4 = new Producto(104L, "Smartwatch Samsung", Map.of(
                    "marca", "Samsung",
                    "precio", 300,
                    "resistente_agua", true,
                    "sensores", new String[]{"Frecuencia cardíaca", "Oxígeno en sangre"}
            ));

            //Producto 5: Cámara con lista de modos de disparo
            Producto producto5 = new Producto(105L, "Canon EOS R6", Map.of(
                    "marca", "Canon",
                    "modelo", "EOS R6",
                    "precio", 2500,
                    "modos_disparo", new String[]{"Automático", "Manual", "HDR", "Nocturno"},
                    "lentes_compatibles", Map.of(
                            "Canon RF 50mm", true,
                            "Canon EF 24-70mm", false)
            ));

            //Guardar todos los productos en la base de datos
            session.save(producto1);
            session.save(producto2);
            session.save(producto3);
            session.save(producto4);
            session.save(producto5);
            tx.commit();

            System.out.println("Productos insertados correctamente con estructuras JSONB diferentes.");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }//de insertarProductos

    public static void listarProductos() {
        Session session = HibernateUtil.getSession();
        try {
            var productos = session.createQuery("FROM Producto", Producto.class).list(); //sentencia HQL
            System.out.println("Lista de Productos:");
            for (Producto p : productos) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Detalles (JSONB): " + p.getDetalles());
                System.out.println("---------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }// de listarProductos

    public static void actualizarCampoJSONB() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        // Sentencia SQL para actualizar un campo JSONB
        String sql = "UPDATE productos SET detalles = jsonb_set(detalles,'{modelo}', to_jsonb(:nuevoModelo)) WHERE nombre = :nombre";

        // Crear la consulta nativa
        Query query = session.createNativeQuery(sql);
        query.setParameter("nombre", "Laptop Dell");
        query.setParameter("nuevoModelo", "XPS 15.2"); //Pasar como parámetro 

        //Ejecutar la actualización
        int filasActualizadas = query.executeUpdate();
        System.out.println("✅ Filas afectadas: "
                + filasActualizadas);

        tx.commit();
    }//de actualizarProducto

    private static void agregarCampoJSONB() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        //Sentencia SQL para agregar un nuevo campo en JSONB
        String sql = "UPDATE productos SET detalles = jsonb_set(detalles,'{garantia}', to_jsonb(:nuevoValor)) WHERE nombre = :nombre";

        //Crear la consulta nativa
        Query query = session.createNativeQuery(sql);
        query.setParameter("nombre", "Laptop Dell");
        query.setParameter("nuevoValor", "2 años"); //Se pasa como string, pero se convierte en JSONB 

        //Ejecutar la actualización
        int filasActualizadas = query.executeUpdate();
        System.out.println("Filas afectadas: " + filasActualizadas);
        tx.commit();
    }

    private static void actualizarCampoAnidadoJSONB() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();

        //Sentencia SQL para actualizar un campo JSONB anidado
        String sql = "UPDATE productos SET detalles = jsonb_set(detalles,'{especificaciones, RAM}', to_jsonb(:nuevoValor)) WHERE nombre = :nombre";

        //Crear la consulta nativa
        Query query = session.createNativeQuery(sql);
        query.setParameter("nombre", "Laptop Dell");
        query.setParameter("nuevoValor", "32GB"); //Se pasa como string, pero se convierte en JSONB 

        //Ejecutar la actualización
        int filasActualizadas = query.executeUpdate();
        System.out.println("Filas afectadas: " + filasActualizadas);

        tx.commit();
    }
}