package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Alumnos {

    // Atributos.
    private int capacidad;
    private int tamano = 0;
    private Alumno[] coleccionAlumnos;

    // Constructor que inicializa la colección con una capacidad máxima.
    public Alumnos(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionAlumnos = new Alumno[capacidad];
    }

    // Devuelve una copia profunda de los alumnos almacenados en la colección.
    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    // Crea una copia profunda del array de alumnos.
    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[tamano];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    // Busca el índice de un alumno en la colección.
    private int buscarIndice(Alumno alumno) {
        Objects.requireNonNull(alumno, "ERROR: 1.No se puede buscar un alumno nulo.");

        int indice = 0;
        boolean alumnoEncontrado = false;
        while (!tamanoSuperado(indice) && !alumnoEncontrado) {
            if (get()[indice].equals(alumno)) {
                alumnoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    // Inserta un alumno en la colección.
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        Objects.requireNonNull(alumno, "ERROR: No se puede insertar un alumno nulo.");

        int indice = buscarIndice(alumno);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAlumnos[indice] = new Alumno(alumno);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
    }

    // Comprueba si el índice supera el tamaño actual de la colección.
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    //Comprueba si el índice supera la capacidad máxima de la colección.
    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    // Busca un alumno en la colección.
    public Alumno buscar(Alumno alumno) {
        Objects.requireNonNull(alumno, "ERROR: 2.No se puede buscar un alumno nulo.");
        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Alumno(get()[indice]);
        }
    }

    // Borra un alumno de la colección.
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        Objects.requireNonNull(alumno, "ERROR: No se puede borrar un alumno nulo.");
        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    // Desplaza los alumnos una posición hacia la izquierda a partir del índice dado.
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null;
    }

    // Devuelve el tamaño actual de la colección.
    public int getTamano() {
        return tamano;
    }

    // Devuelve la capacidad máxima de la colección.
    public int getCapacidad() {
        return capacidad;
    }
}
