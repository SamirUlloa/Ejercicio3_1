package com.aplicacion.ejercicio3_1.Clases;

public class TablaEmpleados {

    public static final String NameDatabase = "emple.db";
    /*Tablas de Base de Datos*/
    public static final String tablaEmpleados = "empleados";

    /*Campos de la tabla personas*/
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";

    /* Transacciones DDL tabla personas*/
    public static final String CreateTableEmpleados = "CREATE TABLE empleados (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombres TEXT, apellidos TEXT, edad INTEGER,  direccion TEXT, puesto TEXT)";

    public static final String DROPTABLEEmpleados ="DROP TABLE IF EXISTS empleados";
}
