package com.tienda.modelo;

import jakarta.persistence.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author David Cano Escario
 */

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode detalles;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoProducto> productos = new HashSet<>();

    // Constructores
    public Pedido() {
    }

    public Pedido(String cliente, Map<String, Object> detalles) {
        this.cliente = cliente;
        this.fecha = LocalDateTime.now();
        setDetalles(detalles);
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public JsonNode getDetalles() {
        return detalles;
    }

    public void setDetalles(JsonNode detalles) {
        this.detalles = detalles;
    }

    public void setDetalles(Map<String, Object> detalles) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.detalles = objectMapper.valueToTree(detalles);
    }

    public Map<String, Object> getDetallesMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(detalles, Map.class);
    }

    public Set<PedidoProducto> getProductos() {
        return productos;
    }

    public void setProductos(Set<PedidoProducto> productos) {
        this.productos = productos;
    }

    // Métodos para manejar la relación muchos a muchos
    public void agregarProducto(Producto producto, int cantidad) {
        PedidoProducto pedidoProducto = new PedidoProducto(this, producto, cantidad);
        productos.add(pedidoProducto);
        producto.getPedidos().add(pedidoProducto);
    }

    public void eliminarProducto(Producto producto) {
        PedidoProducto pedidoProducto = this.productos.stream()
                .filter(pp -> pp.getProducto().equals(producto))
                .findFirst()
                .orElse(null);

        if (pedidoProducto != null) {
            this.productos.remove(pedidoProducto);
            producto.getPedidos().remove(pedidoProducto);
        }
    }

    @Override
    public String toString() {
        return "Pedido{"
                + "id=" + id
                + ", cliente='" + cliente + '\''
                + ", fecha=" + fecha
                + ", detalles=" + detalles
                + '}';
    }
}
