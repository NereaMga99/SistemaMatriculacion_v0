package org.iesalandalus.programacion.matriculacion.Controlador;

import org.iesalandalus.programacion.matriculacion.Modelo.Modelo;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class Controlador {
    private static Modelo modelo;
    private static Vista vista;

    // Constructor del controlador que recibe las instancias de Modelo y Vista.
    public Controlador(Modelo modelo, Vista vista) {
        Objects.requireNonNull(modelo, "ERROR: El modelo no puede ser nulo");
        Objects.requireNonNull(vista, "ERROR: La vista no puede ser nula");
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    // Inicia la ejecución de la aplicación, iniciando tanto el modelo como la vista.
    public static void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();
    }

    // Finaliza la ejecución de la aplicación, cerrando tanto el modelo como la vista.
    public static void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    // Inserta un nuevo alumno en el modelo.
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        modelo.insertar(alumno);
    }

    // Busca un alumno en el modelo.
    public Alumno buscar(Alumno alumno) {
        return modelo.buscar(alumno);
    }

    // Borra un alumno del modelo.
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        modelo.borrar(alumno);
    }

    // Obtiene todos los alumnos registrados en el modelo.
    public ArrayList<Alumno> getAlumnos() {
        return modelo.getAlumnos();
    }

    // Inserta una nueva asignatura en el modelo.
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        modelo.insertar(asignatura);
    }

    // Busca una asignatura en el modelo.
    public Asignatura buscar(Asignatura asignatura) {
        return modelo.buscar(asignatura);
    }

    // Borra una asignatura del modelo.
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        modelo.borrar(asignatura);
    }

    // Obtiene todas las asignaturas registradas en el modelo.
    public ArrayList<Asignatura> getAsignaturas() {
        return modelo.getAsignaturas();
    }

    // Inserta un nuevo ciclo formativo en el modelo.
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        modelo.insertar(cicloFormativo);
    }

    // Busca un ciclo formativo en el modelo.
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return modelo.buscar(cicloFormativo);
    }

    // Borra un ciclo formativo del modelo.
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        modelo.borrar(cicloFormativo);
    }

    // Obtiene todos los ciclos formativos registrados en el modelo.
    public ArrayList<CicloFormativo> getCicloFormativos() {
        return modelo.getCiclosFormativos();
    }

    // Inserta una nueva matrícula en el modelo.
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        modelo.insertar(matricula);
    }

    // Busca una matrícula en el modelo.
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        return modelo.buscar(matricula);
    }

    // Borra una matrícula del modelo.
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        modelo.borrar(matricula);
    }

    // Obtiene todas las matrículas registradas en el modelo.
    public ArrayList<Matricula> getMatriculas() throws OperationNotSupportedException {
        return modelo.getMatriculas();
    }

    // Obtiene las matrículas de un alumno específico.
    public ArrayList<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return modelo.getMatriculas(alumno);
    }

    // Obtiene las matrículas de un ciclo formativo específico.
    public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return modelo.getMatriculas(cicloFormativo);
    }

    // Obtiene las matrículas de un curso académico específico.
    public ArrayList<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return modelo.getMatriculas(cursoAcademico);
    }
}

