/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.modelo;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author David Cano Escario
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    private Long id;
    
    private String nombre;

    @JdbcTypeCode(SqlTypes.JSON) //Hibernate 6.2: Indica que es JSON
    @Column(columnDefinition = "jsonb")
    private JsonNode detalles; //Ahora es un JSONB real, no una String

    public Producto() {
    }

    public Producto(Long id, String nombre, Map<String, Object> detalles) throws IOException {
        this.id = id;
        this.nombre = nombre;
        this.detalles = new ObjectMapper().valueToTree(detalles);
    }

    //Getters y Setters
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

    public JsonNode getDetalles() {
        return detalles;
    }

    public void setDetalles(Map<String, Object> detalles) throws
            IOException {
        this.detalles = new ObjectMapper().valueToTree(detalles); //Convierte Map a JSONB
    }

    @JsonIgnore
    public Map<String, Object> getDetallesMap() throws IOException {
        return new ObjectMapper().treeToValue(detalles, Map.class); //Convierte JSONB a Map
    }
}
