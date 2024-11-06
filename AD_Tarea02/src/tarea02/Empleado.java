/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea02;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author David Cano Escario
 */
public class Empleado {
    private int codigo;
    private String nombre;
    private String direccion;
    private float salario;
    private float comision;

    public Empleado(int codigo, String nombre, String direccion, float salario, float comision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.salario = salario;
        this.comision = comision;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    
    //metodo para escribir el Empleado a un fichero mediante RandomAccessFile
    public void escribirEmpleado(RandomAccessFile file) throws IOException{
        file.writeInt(codigo);
        file.writeUTF(nombre);
        file.writeUTF(direccion);
        file.writeFloat(salario);
        file.writeFloat(comision);
    }
    
    //metodo para leer el Empleado de un fichero mediante RandomAccessFile
    public static Empleado leerEmpleado(RandomAccessFile file) throws IOException{
        int codigo = file.readInt();
        String nombre = file.readUTF();
        String direccion = file.readUTF();
        float salario = file.readFloat();
        float comision = file.readFloat();
        
        Empleado empleado = new Empleado(codigo, nombre, direccion, salario, comision);
        return empleado;
    }
    
    
    @Override
    public String toString() {
        return "Empleado\n" + "codigo: " + codigo + "\nnombre: " + nombre + "\ndireccion: " + direccion + "\nsalario: " + salario + "\ncomision: " + comision + "\n";
    }
    
    
}
