package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;

public class CiclosFormativos {

    // Atributos.
    private int capacidad;
    private int tamano;
    private CicloFormativo[] coleccionCiclosFormativos;

    // Constructores con parámetros.
    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    // Metodo get para una copia profunda de la colección.
    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copia = new CicloFormativo[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    private boolean tamanoSuperado(int indice) {
        if (indice < 0) {
            throw new IllegalArgumentException("ERROR: El índice no puede ser negativo.");
        }
        return indice >= getTamano();
    }

    public int getCapacidad() {
        return capacidad;
    }

    private boolean capacidadSuperada(int indice) {
        if (indice < 0) {
            throw new IllegalArgumentException("ERROR: El índice no puede ser negativo.");
        }
        return indice >= getCapacidad();
    }

    // Metodo para insertar ciclos formativos.
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }
        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
        tamano++;
    }

    // Metodo para buscar ciclos formativos.
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un ciclo formativo nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return new CicloFormativo(coleccionCiclosFormativos[i]);
            }
        }
        return null;
    }

    // Metodo para borrar ciclos formativos.
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamano - 1] = null;
    }
}

