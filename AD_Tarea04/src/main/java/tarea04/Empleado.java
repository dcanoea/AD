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
import java.util.Date;

/**
 *
 * @author David Cano Escario
 */
@Entity
@Table(name = "EMP") //Nombre de la tabla
public class Empleado {

    //PROPIEDADES DE LA TABLA
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPNO") //Columna EMPNO
    private int numeroEmp;

    @Column(name = "ENAME") //Columna ENAME
    private String nombreEmp;

    @Column(name = "JOB") //Columna JOB
    private String puestoTrabajoEmp;

    @Column(name = "MGR") //Columna MGR
    private Integer managerEmp;

    @Column(name = "HIREDATE") //Columna HIREDATE
    private Date fechaInicioEmp;

    @Column(name = "SAL") //Columna SAL
    private Double salarioEmp;

    @Column(name = "COMM") //Columna COMM
    private Double comisionEmp;

    @Column(name = "DEPTNO") //Columna DEPTNO
    private int numDepartamentoEmp;

    public Empleado() {
    }

    public Empleado(int numeroEmp, String nombreEmp, String puestoTrabajoEmp, Integer managerEmp, Date fechaInicioEmp, Double salarioEmp, Double comisionEmp, int numDepartamentoEmp) {
        this.numeroEmp = numeroEmp;
        this.nombreEmp = nombreEmp;
        this.puestoTrabajoEmp = puestoTrabajoEmp;
        this.managerEmp = managerEmp;
        this.fechaInicioEmp = fechaInicioEmp;
        this.salarioEmp = salarioEmp;
        this.comisionEmp = comisionEmp;
        this.numDepartamentoEmp = numDepartamentoEmp;
    }

    public int getNumeroEmp() {
        return numeroEmp;
    }

    public void setNumeroEmp(int numeroEmp) {
        this.numeroEmp = numeroEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getPuestoTrabajoEmp() {
        return puestoTrabajoEmp;
    }

    public void setPuestoTrabajoEmp(String puestoTrabajoEmp) {
        this.puestoTrabajoEmp = puestoTrabajoEmp;
    }

    public Integer getManagerEmp() {
        return managerEmp;
    }

    public void setManagerEmp(Integer managerEmp) {
        this.managerEmp = managerEmp;
    }

    public Date getFechaInicioEmp() {
        return fechaInicioEmp;
    }

    public void setFechaInicioEmp(Date fechaInicioEmp) {
        this.fechaInicioEmp = fechaInicioEmp;
    }

    public Double getSalarioEmp() {
        return salarioEmp;
    }

    public void setSalarioEmp(Double salarioEmp) {
        this.salarioEmp = salarioEmp;
    }

    public Double getComisionEmp() {
        return comisionEmp;
    }

    public void setComisionEmp(Double comisionEmp) {
        this.comisionEmp = comisionEmp;
    }

    public int getNumDepartamentoEmp() {
        return numDepartamentoEmp;
    }

    public void setNumDepartamentoEmp(int numDepartamentoEmp) {
        this.numDepartamentoEmp = numDepartamentoEmp;
    }

    @Override
    public String toString() {
        return "Empleado{" + "numeroEmp=" + numeroEmp + ", nombreEmp=" + nombreEmp + ", puestoTrabajoEmp=" + puestoTrabajoEmp + ", managerEmp=" + managerEmp + ", fechaInicioEmp=" + fechaInicioEmp + ", salarioEmp=" + salarioEmp + ", comisionEmp=" + comisionEmp + ", numDepartamentoEmp=" + numDepartamentoEmp + '}';
    }

    


    
}
