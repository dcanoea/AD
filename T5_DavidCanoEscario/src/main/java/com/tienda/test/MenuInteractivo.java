/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.tienda.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tienda.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
            session.beginTransaction(); // Iniciar transacción

            // Datos del pedido
            System.out.print("Nombre del cliente: ");
            String cliente = scanner.nextLine();

            // Detalles del pedido (JSONB)
            Map<String, Object> detalles = new HashMap<>();
            System.out.print("Observaciones del pedido: ");
            String observaciones = scanner.nextLine();
            detalles.put("observaciones", observaciones);

            // Convertir detalles a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String detallesJson = objectMapper.writeValueAsString(detalles);

            // Insertar el pedido usando SQL
            String sqlInsertPedido = "INSERT INTO pedidos (cliente, detalles) VALUES (:cliente, :detalles)";
            session.createNativeQuery(sqlInsertPedido)
                    .setParameter("cliente", cliente)
                    .setParameter("detalles", detallesJson)
                    .executeUpdate();

            // Obtener el ID del pedido recién insertado
            String sqlGetLastPedidoId = "SELECT currval('pedidos_id_seq')";
            Long pedidoId = (Long) session.createNativeQuery(sqlGetLastPedidoId).getSingleResult();

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

                // Convertir especificaciones a JSON
                String especificacionesJson = objectMapper.writeValueAsString(especificaciones);

                // Insertar el producto usando SQL
                String sqlInsertProducto = "INSERT INTO productos (nombre, precio, especificaciones) VALUES (:nombre, :precio, :especificaciones)";
                session.createNativeQuery(sqlInsertProducto)
                        .setParameter("nombre", nombre)
                        .setParameter("precio", precio)
                        .setParameter("especificaciones", especificacionesJson)
                        .executeUpdate();

                // Obtener el ID del producto recién insertado
                String sqlGetLastProductoId = "SELECT currval('productos_id_seq')";
                Long productoId = (Long) session.createNativeQuery(sqlGetLastProductoId).getSingleResult();

                // Asociar producto al pedido
                String sqlInsertPedidoProducto = "INSERT INTO pedido_producto (pedido_id, producto_id, cantidad) VALUES (:pedidoId, :productoId, :cantidad)";
                session.createNativeQuery(sqlInsertPedidoProducto)
                        .setParameter("pedidoId", pedidoId)
                        .setParameter("productoId", productoId)
                        .setParameter("cantidad", cantidad)
                        .executeUpdate();
            }

            session.getTransaction().commit(); // Confirmar transacción
            System.out.println("Pedido y productos creados exitosamente.");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Revertir en caso de error
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
            session.beginTransaction(); // Iniciar transacción

            // Obtener los detalles del pedido
            String sqlGetDetalles = "SELECT detalles FROM pedidos WHERE id = :id";
            String detallesJson = (String) session.createNativeQuery(sqlGetDetalles)
                    .setParameter("id", id)
                    .getSingleResult();

            // Convertir detalles a JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode detalles = objectMapper.readTree(detallesJson);

            // Mostrar campos disponibles en un menú numerado
            System.out.println("\nCampos disponibles en el JSONB del pedido:");
            List<String> campos = new ArrayList<>();
            detalles.fieldNames().forEachRemaining(campos::add);

            for (int i = 0; i < campos.size(); i++) {
                System.out.println((i + 1) + ": " + campos.get(i));
            }

            // Solicitar al usuario que elija un campo
            System.out.print("Seleccione el número del campo que desea modificar: ");
            int opcionCampo = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionCampo < 1 || opcionCampo > campos.size()) {
                System.out.println("Opción no válida.");
                session.getTransaction().rollback();
                return;
            }

            String campoSeleccionado = campos.get(opcionCampo - 1);

            // Solicitar el nuevo valor
            System.out.print("Ingrese el nuevo valor para el campo '" + campoSeleccionado + "': ");
            String nuevoValor = scanner.nextLine();

            // Construir la ruta en formato ARRAY de texto
            String rutaJsonb = "ARRAY['" + campoSeleccionado + "']";

            // Sentencia SQL para actualizar el campo en JSONB
            String sqlUpdate = "UPDATE pedidos SET detalles = jsonb_set(detalles, " + rutaJsonb + ", to_jsonb(:nuevoValor)) WHERE id = :id";
            Query query = session.createNativeQuery(sqlUpdate);
            query.setParameter("nuevoValor", nuevoValor);
            query.setParameter("id", id);

            // Ejecutar la actualización
            int filasActualizadas = query.executeUpdate();
            System.out.println("Filas afectadas: " + filasActualizadas);

            session.getTransaction().commit(); // Confirmar transacción
            System.out.println("Campo '" + campoSeleccionado + "' modificado exitosamente.");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Revertir en caso de error
            }
            System.err.println("Error al modificar el campo JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void modificarJsonbDeProducto(Session session, Long id, Scanner scanner) {
        try {
            session.beginTransaction(); // Iniciar transacción

            // Obtener las especificaciones del producto
            String sqlGetEspecificaciones = "SELECT especificaciones FROM productos WHERE id = :id";
            String especificacionesJson = (String) session.createNativeQuery(sqlGetEspecificaciones)
                    .setParameter("id", id)
                    .getSingleResult();

            // Convertir especificaciones a JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode especificaciones = objectMapper.readTree(especificacionesJson);

            // Mostrar campos disponibles en un menú numerado
            System.out.println("\nCampos disponibles en el JSONB del producto:");
            List<String> campos = new ArrayList<>();
            especificaciones.fieldNames().forEachRemaining(campos::add);

            for (int i = 0; i < campos.size(); i++) {
                System.out.println((i + 1) + ": " + campos.get(i));
            }

            // Solicitar al usuario que elija un campo
            System.out.print("Seleccione el número del campo que desea modificar: ");
            int opcionCampo = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionCampo < 1 || opcionCampo > campos.size()) {
                System.out.println("Opción no válida.");
                session.getTransaction().rollback();
                return;
            }

            String campoSeleccionado = campos.get(opcionCampo - 1);

            // Solicitar el nuevo valor
            System.out.print("Ingrese el nuevo valor para el campo '" + campoSeleccionado + "': ");
            String nuevoValor = scanner.nextLine();

            // Construir la ruta en formato ARRAY de texto
            String rutaJsonb = "ARRAY['" + campoSeleccionado + "']";

            // Sentencia SQL para actualizar el campo en JSONB
            String sqlUpdate = "UPDATE productos SET especificaciones = jsonb_set(especificaciones, " + rutaJsonb + ", to_jsonb(:nuevoValor)) WHERE id = :id";
            Query query = session.createNativeQuery(sqlUpdate);
            query.setParameter("nuevoValor", nuevoValor);
            query.setParameter("id", id);

            // Ejecutar la actualización
            int filasActualizadas = query.executeUpdate();
            System.out.println("Filas afectadas: " + filasActualizadas);

            session.getTransaction().commit(); // Confirmar transacción
            System.out.println("Campo '" + campoSeleccionado + "' modificado exitosamente.");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Revertir en caso de error
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
            session.beginTransaction(); // Iniciar transacción

            // Obtener los detalles del pedido
            String sqlGetDetalles = "SELECT detalles FROM pedidos WHERE id = :id";
            String detallesJson = (String) session.createNativeQuery(sqlGetDetalles)
                    .setParameter("id", id)
                    .getSingleResult();

            // Convertir detalles a JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode detalles = objectMapper.readTree(detallesJson);

            // Mostrar campos disponibles en un menú numerado
            System.out.println("\nCampos disponibles en el JSONB del pedido:");
            List<String> campos = new ArrayList<>();
            detalles.fieldNames().forEachRemaining(campos::add);

            for (int i = 0; i < campos.size(); i++) {
                System.out.println((i + 1) + ": " + campos.get(i));
            }

            // Solicitar al usuario que elija un campo
            System.out.print("Seleccione el número del campo que desea eliminar: ");
            int opcionCampo = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionCampo < 1 || opcionCampo > campos.size()) {
                System.out.println("Opción no válida.");
                session.getTransaction().rollback();
                return;
            }

            String campoSeleccionado = campos.get(opcionCampo - 1);

            // Construir la ruta en formato ARRAY de texto
            String rutaJsonb = "ARRAY['" + campoSeleccionado + "']";

            // Sentencia SQL para eliminar el campo en JSONB
            String sqlUpdate = "UPDATE pedidos SET detalles = detalles #- " + rutaJsonb + " WHERE id = :id";
            Query query = session.createNativeQuery(sqlUpdate);
            query.setParameter("id", id);

            // Ejecutar la actualización
            int filasActualizadas = query.executeUpdate();
            System.out.println("Filas afectadas: " + filasActualizadas);

            session.getTransaction().commit(); // Confirmar transacción
            System.out.println("Campo '" + campoSeleccionado + "' eliminado exitosamente.");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Revertir en caso de error
            }
            System.err.println("Error al eliminar la clave JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void eliminarClaveJsonbDeProducto(Session session, Long id, Scanner scanner) {
        try {
            session.beginTransaction(); // Iniciar transacción

            // Obtener las especificaciones del producto
            String sqlGetEspecificaciones = "SELECT especificaciones FROM productos WHERE id = :id";
            String especificacionesJson = (String) session.createNativeQuery(sqlGetEspecificaciones)
                    .setParameter("id", id)
                    .getSingleResult();

            // Convertir especificaciones a JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode especificaciones = objectMapper.readTree(especificacionesJson);

            // Mostrar campos disponibles en un menú numerado
            System.out.println("\nCampos disponibles en el JSONB del producto:");
            List<String> campos = new ArrayList<>();
            especificaciones.fieldNames().forEachRemaining(campos::add);

            for (int i = 0; i < campos.size(); i++) {
                System.out.println((i + 1) + ": " + campos.get(i));
            }

            // Solicitar al usuario que elija un campo
            System.out.print("Seleccione el número del campo que desea eliminar: ");
            int opcionCampo = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcionCampo < 1 || opcionCampo > campos.size()) {
                System.out.println("Opción no válida.");
                session.getTransaction().rollback();
                return;
            }

            String campoSeleccionado = campos.get(opcionCampo - 1);

            // Construir la ruta en formato ARRAY de texto
            String rutaJsonb = "ARRAY['" + campoSeleccionado + "']";

            // Sentencia SQL para eliminar el campo en JSONB
            String sqlUpdate = "UPDATE productos SET especificaciones = especificaciones #- " + rutaJsonb + " WHERE id = :id";
            Query query = session.createNativeQuery(sqlUpdate);
            query.setParameter("id", id);

            // Ejecutar la actualización
            int filasActualizadas = query.executeUpdate();
            System.out.println("Filas afectadas: " + filasActualizadas);

            session.getTransaction().commit(); // Confirmar transacción
            System.out.println("Campo '" + campoSeleccionado + "' eliminado exitosamente.");
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback(); // Revertir en caso de error
            }
            System.err.println("Error al eliminar la clave JSONB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarPedidosYProductos(Session session) {
        try {
            session.beginTransaction();

            // Obtener todos los pedidos
            String sqlGetPedidos = "SELECT * FROM pedidos";
            List<Object[]> pedidos = session.createNativeQuery(sqlGetPedidos).getResultList();

            if (pedidos.isEmpty()) {
                System.out.println("\nNo hay pedidos registrados.");
            } else {
                System.out.println("\n--- Lista de Pedidos ---");
                for (Object[] pedido : pedidos) {
                    // Mostrar detalles del pedido
                    System.out.println("\nPedido ID: " + pedido[0]);
                    System.out.println("Cliente: " + pedido[1]);
                    System.out.println("Fecha: " + pedido[2]);
                    System.out.println("Detalles: " + pedido[3]);

                    // Obtener productos asociados al pedido
                    String sqlGetProductosEnPedido = "SELECT p.id, p.nombre, p.precio, p.especificaciones, pp.cantidad "
                            + "FROM productos p "
                            + "JOIN pedido_producto pp ON p.id = pp.producto_id "
                            + "WHERE pp.pedido_id = :pedidoId";
                    List<Object[]> productosEnPedido = session.createNativeQuery(sqlGetProductosEnPedido)
                            .setParameter("pedidoId", pedido[0])
                            .getResultList();

                    // Mostrar productos asociados al pedido
                    System.out.println("Productos en el pedido:");
                    if (productosEnPedido.isEmpty()) {
                        System.out.println("  - No hay productos en este pedido.");
                    } else {
                        for (Object[] producto : productosEnPedido) {
                            System.out.println("  - Producto ID: " + producto[0]);
                            System.out.println("    Nombre: " + producto[1]);
                            System.out.println("    Precio: " + producto[2]);
                            System.out.println("    Especificaciones: " + producto[3]);
                            System.out.println("    Cantidad: " + producto[4]);
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
