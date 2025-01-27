/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea04;

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

@Entity
@Table(name ="DEPT")//Nombre de la tabla
public class Departamento {
   
    //PROPIEDADES DE LA TABLA 
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEPTNO") //Columna DEPTNO
    private int numeroDep;
    
    @Column(name = "DNAME") //Columna DNAME
    private String nombreDep;
    
    @Column(name = "LOC") //Columna LOC
    private String localizacionDep;

    public Departamento() {
    }

    public Departamento(int numeroDep, String nombreDep, String localizacionDep) {
        this.numeroDep = numeroDep;
        this.nombreDep = nombreDep;
        this.localizacionDep = localizacionDep;
    }

    public int getNumeroDep() {
        return numeroDep;
    }

    public void setNumeroDep(int numeroDep) {
        this.numeroDep = numeroDep;
    }

    public String getNombreDep() {
        return nombreDep;
    }

    public void setNombreDep(String nombreDep) {
        this.nombreDep = nombreDep;
    }

    public String getLocalizacionDep() {
        return localizacionDep;
    }

    public void setLocalizacionDep(String localizacionDep) {
        this.localizacionDep = localizacionDep;
    }

    @Override
    public String toString() {
        return "Departamento{" + "numeroDep=" + numeroDep + ", nombreDep=" + nombreDep + ", localizacionDep=" + localizacionDep + '}';
    } 
    
}


