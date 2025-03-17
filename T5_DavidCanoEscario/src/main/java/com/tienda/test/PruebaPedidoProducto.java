package com.tienda.test;

import com.tienda.modelo.Pedido;
import com.tienda.modelo.Producto;
import com.tienda.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author David Cano Escario
 */
public class PruebaPedidoProducto {

    public static void main(String[] args) {
        // Obtener una sesión de Hibernate
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            // Iniciar una transacción
            transaction = session.beginTransaction();

            // Crear productos
            Producto producto1 = new Producto("Laptop", new BigDecimal("1200.00"), Map.of("marca", "Dell", "ram", "16GB"));
            Producto producto2 = new Producto("Smartphone", new BigDecimal("800.00"), Map.of("marca", "Samsung", "almacenamiento", "128GB"));

            // Guardar productos en la base de datos
            session.persist(producto1);
            session.persist(producto2);

            // Crear un pedido
            Pedido pedido = new Pedido("Cliente 1", Map.of("observaciones", "Pedido urgente"));

            // Agregar productos al pedido
            pedido.agregarProducto(producto1, 1); // 1 Laptop
            pedido.agregarProducto(producto2, 2); // 2 Smartphones

            // Guardar el pedido en la base de datos
            session.persist(pedido);

            // Mostrar detalles del pedido
            System.out.println("Pedido creado:");
            System.out.println(pedido);
            System.out.println("Productos en el pedido:");
            pedido.getProductos().forEach(pp -> {
                System.out.println("- " + pp.getProducto().getNombre() + " (Cantidad: " + pp.getCantidad() + ")");
            });

            // Eliminar un producto del pedido
            pedido.eliminarProducto(producto1);

            // Mostrar detalles del pedido después de eliminar un producto
            System.out.println("\nPedido después de eliminar un producto:");
            System.out.println(pedido);
            System.out.println("Productos en el pedido:");
            pedido.getProductos().forEach(pp -> {
                System.out.println("- " + pp.getProducto().getNombre() + " (Cantidad: " + pp.getCantidad() + ")");
            });

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }
}
