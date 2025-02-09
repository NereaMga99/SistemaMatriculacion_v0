package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.Modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;

import static org.iesalandalus.programacion.matriculacion.vista.Opcion.INSERTAR_ALUMNO;
import static org.iesalandalus.programacion.matriculacion.vista.Opcion.SALIR;

public class MainApp {

    // Atributos.
    private static final Alumnos alumnos = new Alumnos();
    private static final Asignaturas asignaturas = new Asignaturas();
    private static final CiclosFormativos ciclosFormativos = new CiclosFormativos();
    private static final Matriculas matriculas = new Matriculas();


    public static void main(String[] args) throws OperationNotSupportedException {
        Modelo modelo = new Modelo();

        Vista vista = new Vista();

        Controlador controlador = new Controlador(modelo, vista);

        Controlador.comenzar();

        Controlador.terminar();
    }
}