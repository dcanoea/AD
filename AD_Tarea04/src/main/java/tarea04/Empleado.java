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
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMP") // Nombre de la tabla
public class Empleado {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPNO") // Columna EMPNO
    private int numeroEmp;

    @Column(name = "ENAME") // Columna ENAME
    private String nombreEmp;

    @Column(name = "JOB") // Columna JOB
    private String puestoTrabajoEmp;

    @Temporal(TemporalType.DATE) // Para manejar fechas
    @Column(name = "HIREDATE") // Columna HIREDATE
    private Date fechaInicioEmp;

    @Column(name = "SAL") // Columna SAL
    private Double salarioEmp;

    @Column(name = "COMM") // Columna COMM
    private Double comisionEmp;

    // Relación con Departamento
    @ManyToOne
    @JoinColumn(name = "DEPTNO") // Relaciona con DEPTNO en EMP
    private Departamento departamento;

    // Relación con el Jefe (manager)
    @ManyToOne
    @JoinColumn(name = "MGR") // Relaciona con MGR en EMP
    private Empleado manager;

    // Relación inversa: Subordinados
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Empleado> subordinados;

    // Constructores
    public Empleado() {
    }

    public Empleado(int numeroEmp, String nombreEmp, String puestoTrabajoEmp, Date fechaInicioEmp, Double salarioEmp, Double comisionEmp, Departamento departamento, Empleado manager) {
        this.numeroEmp = numeroEmp;
        this.nombreEmp = nombreEmp;
        this.puestoTrabajoEmp = puestoTrabajoEmp;
        this.fechaInicioEmp = fechaInicioEmp;
        this.salarioEmp = salarioEmp;
        this.comisionEmp = comisionEmp;
        this.departamento = departamento;
        this.manager = manager;
    }

    // Getters y Setters
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado getManager() {
        return manager;
    }

    public void setManager(Empleado manager) {
        this.manager = manager;
    }

    public List<Empleado> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(List<Empleado> subordinados) {
        this.subordinados = subordinados;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "numeroEmp=" + numeroEmp +
                ", nombreEmp='" + nombreEmp + '\'' +
                ", puestoTrabajoEmp='" + puestoTrabajoEmp + '\'' +
                ", fechaInicioEmp=" + fechaInicioEmp +
                ", salarioEmp=" + salarioEmp +
                ", comisionEmp=" + comisionEmp +
                ", departamento=" + (departamento != null ? departamento.getNombreDep() : "N/A") +
                ", manager=" + (manager != null ? manager.getNombreEmp() : "N/A") +
                '}';
    }
}

