package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

public class Asignaturas {

    // Atributos.
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    // Constructor con parametros.
    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    // Metodo get para una copia profunda de asignatura.
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }


    // Metodo para insertar asignatura.
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        coleccionAsignaturas[tamano] = new Asignatura(asignatura);
        tamano++;
    }

    // Metodo para buscar asignaturas.
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede buscar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice != -1) {
            return new Asignatura(coleccionAsignaturas[indice]);
        }
        return null;
    }

    // Metodo para borrar asignatura.
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar el índice de una asignatura nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i] != null && coleccionAsignaturas[i].equals(asignatura)) {
                return i;
            }
        }
        return -1;
    }

    //Verifica si el tamaño está superado.
    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    //Verifica si la capacidad está superada.
    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null;
    }
}
