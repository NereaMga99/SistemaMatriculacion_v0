package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class Asignaturas {

    // Atributos.
    private ArrayList<Asignatura> coleccionAsignaturas;

    // Constructor de la clase Asignaturas.
    public Asignaturas() {
        this.coleccionAsignaturas = new ArrayList<>();
    }

    // Obtiene una copia profunda de la colección de asignaturas.
    public ArrayList<Asignatura> get() {
        return copiaProfundaAsignaturas();
    }

    // Realiza una copia profunda de las asignaturas almacenadas en la colección.
    private ArrayList<Asignatura> copiaProfundaAsignaturas() {
        ArrayList<Asignatura> copiaAsignaturas = new ArrayList<>();
        for (Asignatura a : coleccionAsignaturas) {
            copiaAsignaturas.add(new Asignatura(a));
        }
        return copiaAsignaturas;
    }

    // Obtiene el tamaño actual de la coleccion (numero de asignaturas almacenadas).
    public int getTamano() {
        return this.coleccionAsignaturas.size();
    }

    // Inserta una asignatura en la colección.
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede insertar una asignatura nula.");

        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice == -1) {
            this.coleccionAsignaturas.add(new Asignatura(asignatura));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    // MBusca una asignatura en la colección.
    public Asignatura buscar(Asignatura asignatura) {
        Objects.requireNonNull(asignatura, "ERROR: No se puede buscar una asignatura nula.");
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice == -1) {
            return null;
        } else {
            return new Asignatura(this.coleccionAsignaturas.get(indice));
        }
    }

    // Borra una asignatura de la colección.
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede borrar una asignatura nula.");

        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        this.coleccionAsignaturas.remove(indice);
        }
}

