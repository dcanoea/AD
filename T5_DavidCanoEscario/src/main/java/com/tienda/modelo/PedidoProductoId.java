package com.tienda.modelo;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author David Cano Escario
 */


@Embeddable
public class PedidoProductoId implements Serializable {

    private Long pedidoId;
    private Long productoId;

    // Constructores
    public PedidoProductoId() {
    }

    public PedidoProductoId(Long pedidoId, Long productoId) {
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    // Getters y setters
    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoProductoId that = (PedidoProductoId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
               Objects.equals(productoId, that.productoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, productoId);
    }
}
