package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Asignaturas {

    // Atributos.
    private int capacidad;
    private int tamano = 0;
    private Asignatura[] coleccionAsignaturas;

    // Constructor de la clase Asignaturas.
    public Asignaturas(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionAsignaturas = new Asignatura[capacidad];
    }

    // Obtiene una copia profunda de la colección de asignaturas.
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    // Realiza una copia profunda de las asignaturas almacenadas en la colección.
    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copiaAsignaturas = new Asignatura[tamano];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAsignaturas[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copiaAsignaturas;
    }

    // Busca el índice de una asignatura en la colección.
    private int buscarIndice(Asignatura asignatura) {
        Objects.requireNonNull(asignatura, "ERROR: No se puede buscar una Asignatura nula.");

        int indice = 0;
        boolean asignaturaEncontrado = false;
        while (!tamanoSuperado(indice) && !asignaturaEncontrado) {
            if (get()[indice].equals(asignatura)) {
                asignaturaEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    // Obtiene el tamaño actual de la colección (número de asignaturas almacenadas).
    public int getTamano() {
        return tamano;
    }

    // Obtiene la capacidad máxima de la colección.
    public int getCapacidad() {
        return capacidad;
    }


    // Inserta una asignatura en la colección.
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAsignaturas[indice] = new Asignatura(asignatura);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    // MBusca una asignatura en la colección.
    public Asignatura buscar(Asignatura asignatura) {
        Objects.requireNonNull(asignatura, "ERROR: No se puede buscar una asignatura nula.");
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Asignatura(get()[indice]);
        }
    }

    // Borra una asignatura de la colección.
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede borrar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    // Comprueba si se ha superado el tamaño de la colección.
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    // Comprueba si se ha superado la capacidad de la colección.
    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    // Desplaza los elementos de la colección una posición hacia la izquierda, para eliminar la asignatura en la posición indicada.
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        coleccionAsignaturas[indice] = null;
        for (int i = indice; !tamanoSuperado(i); i++) {
            if (i < getCapacidad() - 1) {
                coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
            }
        }
        tamano--;
    }
}
