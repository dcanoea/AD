package com.tienda.modelo;

import jakarta.persistence.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author David Cano Escario
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode especificaciones;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoProducto> pedidos = new HashSet<>();

    // Constructores
    public Producto() {
    }

    public Producto(String nombre, BigDecimal precio, Map<String, Object> especificaciones) {
        this.nombre = nombre;
        this.precio = precio;
        setEspecificaciones(especificaciones);
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public JsonNode getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(JsonNode especificaciones) {
        this.especificaciones = especificaciones;
    }

    public void setEspecificaciones(Map<String, Object> especificaciones) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.especificaciones = objectMapper.valueToTree(especificaciones);
    }

    public Map<String, Object> getEspecificacionesMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(especificaciones, Map.class);
    }

    public Set<PedidoProducto> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<PedidoProducto> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Producto{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", precio=" + precio
                + ", especificaciones=" + especificaciones
                + '}';
    }
}
