/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.tienda.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tienda.modelo.Pedido;
import com.tienda.modelo.PedidoProducto;
import com.tienda.modelo.Producto;
import com.tienda.util.HibernateUtil;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author David Cano Escario
 */
public class MenuInteractivo {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession(); Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- Menú de Gestión de Pedidos y Productos ---");
                System.out.println("1. Insertar Pedido con Productos");
                System.out.println("2. Modificar un campo JSONB");
                System.out.println("3. Eliminar una clave dentro del JSONB");
                System.out.println("4. Listar Pedidos y Productos");
                System.out.println("5. Borrar todas las tablas");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        insertarPedidoConProductos(session, scanner);
                        break;
                    case 2:
                        modificarCampoJsonb(session, scanner);
                        break;
                    case 3:
                        eliminarClaveJsonb(session, scanner);
                        break;
                    case 4:
                        listarPedidosYProductos(session);
                        break;
                    case 5:
                        borrarTodasLasTablas(session, scanner);
                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertarPedidoConProductos(Session session, Scanner scanner) {
        try {
            // Datos del pedido
            System.out.print("Nombre del cliente: ");
            String cliente = scanner.nextLine();

            // Detalles del pedido (JSONB)
            Map<String, Object> detalles = new HashMap<>();
            System.out.print("Observaciones del pedido: ");
            String observaciones = scanner.nextLine();
            detalles.put("observaciones", observaciones);

            // Crear pedido
            Pedido pedido = new Pedido(cliente, detalles);

            // Guardar el pedido para obtener su ID
            session.beginTransaction();
            session.persist(pedido);
            session.getTransaction().commit();

            // Crear dos productos
            for (int i = 1; i <= 2; i++) {
                System.out.println("\nIngrese los datos del producto " + i + ":");
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Precio: ");
                BigDecimal precio = scanner.nextBigDecimal();
                scanner.nextLine(); // Consumir el salto de línea

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                // Especificaciones del producto (JSONB)
                Map<String, Object> especificaciones = new HashMap<>();
                System.out.print("Marca del producto: ");
                String marca = scanner.nextLine();
                especificaciones.put("marca", marca);

                System.out.print("Modelo del producto: ");
                String modelo = scanner.nextLine();
                especificaciones.put("modelo", modelo);

                // Crear producto
                Producto producto = new Producto(nombre, precio, especificaciones);

                // Guardar el producto para obtener su ID
                session.beginTransaction();
                session.persist(producto);
                session.getTransaction().commit();

                // Asociar producto al pedido
                PedidoProducto pedidoProducto = new PedidoProducto(pedido, producto, cantidad);

                // Actualizar las colecciones en Pedido y Producto
                pedido.getProductos().add(pedidoProducto);
                producto.getPedidos().add(pedidoProducto);

                // Guardar la relación PedidoProducto
                session.beginTransaction();
                session.persist(pedidoProducto);
                session.getTransaction().commit();
            }

            System.out.println("Pedido y productos creados exitosamente.");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al insertar el pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void modificarCampoJsonb(Session session, Scanner scanner) {
        System.out.print("¿Qué desea modificar? (1: Pedido, 2: Producto): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("ID del registro a modificar: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Entrada no válida. Ingrese un número.");
            scanner.next(); // Limpiar el buffer
        }
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea

        if (tipo == 1) {
            modificarJsonbDePedido(session, id, scanner);
        } else if (tipo == 2) {
            modificarJsonbDeProducto(session, id, scanner);
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void modificarJsonbDePedido(Session session, Long id, Scanner scanner) {
        try {
            session.beginTransaction();
            Pedido pedido = session.get(Pedido.class, id);
            if (pedido != null) {
                JsonNode detalles = pedido.getDetalles();
                System.out.println("\nCampos disponibles en el JSONB del pedido:");
                detalles.fieldNames().forEachRemaining(System.out::println);

                System.out.print("Ingrese el nombre del campo que desea modificar: ");
                String campo = scanner.nextLine();

                if (detalles.has(campo)) {
                    System.out.print("Ingrese el nuevo valor para el campo '" + campo + "': ");
                    String nuevoValor = scanner.nextLine();

                    ((ObjectNode) detalles).put(campo, nuevoValor);
                    pedido.setDetalles(detalles);
                    session.merge(pedido);
                    System.out.println("Campo '" + campo + "' modificado exitosamente.");
                } else {
                    System.out.println("El campo '" + campo + "' no existe en el JSONB.");
                }
            } else {
                System.out.println("No se encontró el pedido con ID " + id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al modificar el campo JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void modificarJsonbDeProducto(Session session, Long id, Scanner scanner) {
        try {
            session.beginTransaction();
            Producto producto = session.get(Producto.class, id);
            if (producto != null) {
                JsonNode especificaciones = producto.getEspecificaciones();
                System.out.println("\nCampos disponibles en el JSONB del producto:");
                especificaciones.fieldNames().forEachRemaining(System.out::println);

                System.out.print("Ingrese el nombre del campo que desea modificar: ");
                String campo = scanner.nextLine();

                if (especificaciones.has(campo)) {
                    System.out.print("Ingrese el nuevo valor para el campo '" + campo + "': ");
                    String nuevoValor = scanner.nextLine();

                    ((ObjectNode) especificaciones).put(campo, nuevoValor);
                    producto.setEspecificaciones(especificaciones);
                    session.merge(producto);
                    System.out.println("Campo '" + campo + "' modificado exitosamente.");
                } else {
                    System.out.println("El campo '" + campo + "' no existe en el JSONB.");
                }
            } else {
                System.out.println("No se encontró el producto con ID " + id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al modificar el campo JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void eliminarClaveJsonb(Session session, Scanner scanner) {
        System.out.print("¿Qué desea modificar? (1: Pedido, 2: Producto): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        System.out.print("ID del registro a modificar: ");
        while (!scanner.hasNextLong()) {
            System.out.println("Entrada no válida. Ingrese un número.");
            scanner.next(); // Limpiar el buffer
        }
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea

        if (tipo == 1) {
            eliminarClaveJsonbDePedido(session, id, scanner);
        } else if (tipo == 2) {
            eliminarClaveJsonbDeProducto(session, id, scanner);
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private static void eliminarClaveJsonbDePedido(Session session, Long id, Scanner scanner) {
        try {
            session.beginTransaction();
            Pedido pedido = session.get(Pedido.class, id);
            if (pedido != null) {
                JsonNode detalles = pedido.getDetalles();
                System.out.println("\nCampos disponibles en el JSONB del pedido:");
                detalles.fieldNames().forEachRemaining(System.out::println);

                System.out.print("Ingrese el nombre del campo que desea eliminar: ");
                String campo = scanner.nextLine();

                if (detalles.has(campo)) {
                    ((ObjectNode) detalles).remove(campo);
                    pedido.setDetalles(detalles);
                    session.merge(pedido);
                    System.out.println("Campo '" + campo + "' eliminado exitosamente.");
                } else {
                    System.out.println("El campo '" + campo + "' no existe en el JSONB.");
                }
            } else {
                System.out.println("No se encontró el pedido con ID " + id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al eliminar la clave JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void eliminarClaveJsonbDeProducto(Session session, Long id, Scanner scanner) {
        try {
            session.beginTransaction();
            Producto producto = session.get(Producto.class, id);
            if (producto != null) {
                JsonNode especificaciones = producto.getEspecificaciones();
                System.out.println("\nCampos disponibles en el JSONB del producto:");
                especificaciones.fieldNames().forEachRemaining(System.out::println);

                System.out.print("Ingrese el nombre del campo que desea eliminar: ");
                String campo = scanner.nextLine();

                if (especificaciones.has(campo)) {
                    ((ObjectNode) especificaciones).remove(campo);
                    producto.setEspecificaciones(especificaciones);
                    session.merge(producto);
                    System.out.println("Campo '" + campo + "' eliminado exitosamente.");
                } else {
                    System.out.println("El campo '" + campo + "' no existe en el JSONB.");
                }
            } else {
                System.out.println("No se encontró el producto con ID " + id);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al eliminar la clave JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarPedidosYProductos(Session session) {
        try {
            session.beginTransaction();

            // Obtener todos los pedidos
            List<Pedido> pedidos = session.createQuery("FROM Pedido", Pedido.class).getResultList();

            if (pedidos.isEmpty()) {
                System.out.println("\nNo hay pedidos registrados.");
            } else {
                System.out.println("\n--- Lista de Pedidos ---");
                for (Pedido pedido : pedidos) {
                    // Mostrar detalles del pedido
                    System.out.println("\nPedido ID: " + pedido.getId());
                    System.out.println("Cliente: " + pedido.getCliente());
                    System.out.println("Fecha: " + pedido.getFecha());
                    System.out.println("Detalles: " + pedido.getDetalles());

                    // Mostrar productos asociados al pedido
                    System.out.println("Productos en el pedido:");
                    Set<PedidoProducto> productosEnPedido = pedido.getProductos();
                    if (productosEnPedido.isEmpty()) {
                        System.out.println("  - No hay productos en este pedido.");
                    } else {
                        for (PedidoProducto pp : productosEnPedido) {
                            Producto producto = pp.getProducto();
                            System.out.println("  - Producto ID: " + producto.getId());
                            System.out.println("    Nombre: " + producto.getNombre());
                            System.out.println("    Precio: " + producto.getPrecio());
                            System.out.println("    Especificaciones: " + producto.getEspecificaciones());
                            System.out.println("    Cantidad: " + pp.getCantidad());
                        }
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error al listar pedidos y productos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void borrarTodasLasTablas(Session session, Scanner scanner) {
        System.out.print("¿Está seguro de que desea borrar todas las tablas? (s/n): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            System.out.print("¿Está absolutamente seguro? Esta acción no se puede deshacer. (s/n): ");
            String confirmacionFinal = scanner.nextLine();

            if (confirmacionFinal.equalsIgnoreCase("s")) {
                try {
                    session.beginTransaction();
                    session.createNativeQuery("TRUNCATE TABLE pedido_producto RESTART IDENTITY CASCADE").executeUpdate();
                    session.createNativeQuery("TRUNCATE TABLE pedidos RESTART IDENTITY CASCADE").executeUpdate();
                    session.createNativeQuery("TRUNCATE TABLE productos RESTART IDENTITY CASCADE").executeUpdate();
                    session.getTransaction().commit();
                    System.out.println("Todas las tablas han sido borradas.");
                } catch (Exception e) {
                    if (session.getTransaction() != null) {
                        session.getTransaction().rollback();
                    }
                    System.err.println("Error al borrar las tablas: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}
