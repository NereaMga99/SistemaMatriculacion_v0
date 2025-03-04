package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class Alumnos {

    // Atributos.
    private ArrayList<Alumno> coleccionAlumnos;

    // Constructor que inicializa la colección con una capacidad máxima.
    public Alumnos() {
        this.coleccionAlumnos = new ArrayList<>();
    }

    // Devuelve una copia profunda de los alumnos almacenados en la colección.
    public ArrayList<Alumno> get() {
        return copiaProfundaAlumnos();
    }

    // Crea una copia profunda del array de alumnos.
    private ArrayList<Alumno> copiaProfundaAlumnos() {
        ArrayList<Alumno> copiaAlumnos = new ArrayList<>();
        for (Alumno a : coleccionAlumnos) {
            copiaAlumnos.add(new Alumno(a));
        }
        return copiaAlumnos;
    }

    // Devuelve el tamaño actual de la colección.
    public int getTamano() {
        return this.coleccionAlumnos.size();
    }

    // Inserta un alumno en la colección.
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        Objects.requireNonNull(alumno, "ERROR: No se puede insertar un alumno nulo.");

        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice == -1) {
            this.coleccionAlumnos.add(new Alumno(alumno));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
    }

    // Busca un alumno en la colección.
    public Alumno buscar(Alumno alumno) {
        Objects.requireNonNull(alumno, "ERROR: 2.No se puede buscar un alumno nulo.");
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice == -1) {
            return null;
        } else {
            return new Alumno(this.coleccionAlumnos.get(indice));
        }
    }

    // Borra un alumno de la colección.
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        Objects.requireNonNull(alumno, "ERROR: No se puede borrar un alumno nulo.");
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        this.coleccionAlumnos.remove(indice);
    }
}
