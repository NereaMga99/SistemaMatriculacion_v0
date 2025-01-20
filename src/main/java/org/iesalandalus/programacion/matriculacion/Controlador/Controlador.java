package org.iesalandalus.programacion.matriculacion.Controlador;

import org.iesalandalus.programacion.matriculacion.Modelo.Modelo;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void comenzar() {
        this.modelo.comenzar();
    }

    public void terminar() {
        this.modelo.terminar();
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        this.modelo.insertar(alumno);
    }

    public Alumno buscar(Alumno alumno) {
        return this.modelo.buscar(alumno);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        this.modelo.borrar(alumno);
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        this.modelo.insertar(asignatura);
    }

    public Asignatura buscar(Asignatura asignatura) {
        return this.modelo.buscar(asignatura);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        this.modelo.borrar(asignatura);
    }

    public Asignatura[] getAsignaturas() {
        return this.modelo.getAsignaturas();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.modelo.insertar(cicloFormativo);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return this.modelo.buscar(cicloFormativo);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.modelo.borrar(cicloFormativo);
    }

    public CicloFormativo[] getCiclosFormativos() {
        return this.modelo.getCiclosFormativos();
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        this.modelo.insertar(matricula);
    }

    public Matricula buscar(Matricula matricula) {
        return this.modelo.buscar(matricula);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        this.modelo.borrar(matricula);
    }

    public Matricula[] getMatriculas() throws OperationNotSupportedException {
        return this.modelo.getMatriculas();
    }

    public Matricula[] getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return this.modelo.getMatriculas(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return this.modelo.getMatriculas(cicloFormativo);
    }

    public Matricula[] getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return this.modelo.getMatriculas(cursoAcademico);
    }
}

