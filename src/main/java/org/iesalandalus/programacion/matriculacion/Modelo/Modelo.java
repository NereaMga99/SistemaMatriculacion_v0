package org.iesalandalus.programacion.matriculacion.Modelo;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Matriculas;

import javax.naming.OperationNotSupportedException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Modelo {

    private Alumnos alumnos;
    private Matriculas matriculas;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;

    // Inicializa las colecciones de alumnos, asignaturas, ciclos formativos y matrículas con la capacidad definida.
    public void comenzar() {
        this.alumnos = new Alumnos();
        this.matriculas = new Matriculas();
        this.asignaturas = new Asignaturas();
        this.ciclosFormativos = new CiclosFormativos();
      /* try {
            Alumno a1 = new Alumno("Pepe", "12345678Z", "a@a.com", "666666666", LocalDate.of(2000, 1, 1));
            insertar(a1);
            CicloFormativo cf1 = new CicloFormativo(1234, "DAW", new GradoE("DAW",1,1), "Desarrollo Aplicaciones Web", 20);
            insertar(cf1);
            Asignatura as1 = new Asignatura("5678", "Programacion", 30, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cf1);
            insertar(as1);
            Matricula m1 = new Matricula(9876, "24-25", LocalDate.now(), a1, this.asignaturas.get());
            insertar(m1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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
    public ArrayList<Alumno> getAlumnos() {
        return alumnos.get();
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
    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas.get();
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
    public ArrayList<CicloFormativo> getCiclosFormativos() {
        return ciclosFormativos.get();
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
    public ArrayList<Matricula> getMatriculas() throws OperationNotSupportedException {
        return matriculas.get();
    }

    // Obtiene todas las matrículas asociadas a un alumno.
    public ArrayList<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return matriculas.get(alumno);
    }

    // Obtiene todas las matrículas asociadas a un ciclo formativo.
    public ArrayList<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return matriculas.get(cicloFormativo);
    }

    // Obtiene todas las matrículas asociadas a un curso académico.
    public ArrayList<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return matriculas.get(cursoAcademico);
    }
}
