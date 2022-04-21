package com.aplicacion.ejercicio3_1.Clases;

import java.io.Serializable;

public class Empleados implements Serializable {

    private Integer id;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String direccion;
    private String puesto;

    public Empleados() {

    }

    public Empleados (Integer id, String nombres, String apellidos, Integer edad, String direccion, String puesto) {
        this.id=id;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.edad=edad;
        this.direccion=direccion;
        this.puesto=puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
