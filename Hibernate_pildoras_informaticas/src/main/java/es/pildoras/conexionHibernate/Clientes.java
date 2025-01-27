/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.pildoras.conexionHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author David Cano Escario
 */
@Entity//Entidad
@Table(name = "clientes")//Nombre de la tabla
public class Clientes {

    //PROPIEDADES DE LA TABLA
    @Id//Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")//Columna Id
    private int id;

    @Column(name = "Nombre")//Columna Nombre
    private String nombre;

    @Column(name = "Apellidos")//Columna Apellidos
    private String apellidos;

    @Column(name = "Direccion")//Columna Direccion
    private String direccion;

    //---------------------------------------------------------------------------------------------------------------
    //Constructor sin parametros
    public Clientes() {
    }

    //Constructor con parametros (Id es autoincremental en la tabla, no debemos pasarlo nunca)
    public Clientes(String nombre, String apellidos, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    //---------------------------------------------------------------------------------------------------------------
    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //---------------------------------------------------------------------------------------------------------------
    //Método toSring()
    @Override
    public String toString() {
        return "Clientes{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + '}';
    }
}
