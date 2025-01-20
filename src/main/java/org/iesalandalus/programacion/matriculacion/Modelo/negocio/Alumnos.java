package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {

    // Atributos.
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    // Constructor con parámetros.
    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }

    // Copia profunda.
    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    // Insertar alumnos.
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }
        if (buscarIndice(alumno) >= 0) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        coleccionAlumnos[tamano] = new Alumno(alumno);
        tamano++;
    }

    // Metodo para buscar alumnos por el dni.
    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            return null;
        }
        for (Alumno a : coleccionAlumnos) {
            if (a.equals(alumno)) {
                return new Alumno(a);
            }
        }
        return null;
    }

    // Metodo para borrar alumnos.
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice < 0) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }

        // coleccionAlumnos[indice] = null; //
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alumno nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamano - 1] = null;
    }
    public int getTamano() {
        return tamano;
    }

    // Metodo para validar si el índice supera el tamaño
    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    // Metodo para saber la capacidad maxima de la coleccion de alumnos.
    public int getCapacidad() {
        return capacidad;
    }


    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }


















}
