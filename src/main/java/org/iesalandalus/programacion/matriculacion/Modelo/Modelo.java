package org.iesalandalus.programacion.matriculacion.Modelo;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;

import static org.iesalandalus.programacion.matriculacion.MainApp.CAPACIDAD;

public class Modelo {

    public final int CAPACIDAD = 10;
    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;

    // Inicializa las colecciones de alumnos, asignaturas, ciclos formativos y matrículas con la capacidad definida.
    public void comenzar() {
        this.alumnos = new Alumnos(CAPACIDAD);
        this.matriculas = new Matriculas(CAPACIDAD);
        this.asignaturas = new Asignaturas(CAPACIDAD);
        this.ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    }

    // Finaliza la aplicación mostrando un mensaje por consola.
    public void terminar() {
        System.out.println("Aplicación terminada.");
    }

    // Inserta un alumno en la colección de alumnos.
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        this.alumnos.insertar(alumno);
    }

    // Busca un alumno en la colección de alumnos.
    public Alumno buscar(Alumno alumno) {
        return this.alumnos.buscar(alumno);
    }

    // Borra un alumno de la colección de alumnos.
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        this.alumnos.borrar(alumno);
    }

    // Obtiene todos los alumnos de la colección.
    public Alumno[] getAlumnos() {
        return this.alumnos.get();
    }

    // Inserta una asignatura en la colección de asignaturas.
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        this.asignaturas.insertar(asignatura);
    }

    // Busca una asignatura en la colección de asignaturas.
    public Asignatura buscar(Asignatura asignatura) {
        return this.asignaturas.buscar(asignatura);
    }

    // Borra una asignatura de la colección de asignaturas.
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        this.asignaturas.borrar(asignatura);
    }

    // Obtiene todas las asignaturas de la colección.
    public Asignatura[] getAsignaturas() {
        return this.asignaturas.get();
    }

    // Inserta un ciclo formativo en la colección de ciclos formativos.
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.ciclosFormativos.insertar(cicloFormativo);
    }

    // Busca un ciclo formativo en la colección de ciclos formativos.
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return this.ciclosFormativos.buscar(cicloFormativo);
    }

    // Borra un ciclo formativo de la colección de ciclos formativos.
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.ciclosFormativos.borrar(cicloFormativo);
    }

    // Obtiene todos los ciclos formativos de la colección.
    public CicloFormativo[] getCiclosFormativos() {
        return this.ciclosFormativos.get();
    }

    // Inserta una matrícula en la colección de matrículas.
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        this.matriculas.insertar(matricula);
    }

    // Busca una matrícula en la colección de matrículas.
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        return this.matriculas.buscar(matricula);
    }

    // Borra una matrícula de la colección de matrículas.
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        this.matriculas.borrar(matricula);
    }

    // Obtiene todas las matrículas de la colección.
    public Matricula[] getMatriculas() throws OperationNotSupportedException {
        return this.matriculas.get();
    }

    // Obtiene todas las matrículas asociadas a un alumno.
    public Matricula[] getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return this.matriculas.get(alumno);
    }

    // Obtiene todas las matrículas asociadas a un ciclo formativo.
    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return this.matriculas.get(cicloFormativo);
    }

    // Obtiene todas las matrículas asociadas a un curso académico.
    public Matricula[] getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return this.matriculas.get(cursoAcademico);
    }
}
