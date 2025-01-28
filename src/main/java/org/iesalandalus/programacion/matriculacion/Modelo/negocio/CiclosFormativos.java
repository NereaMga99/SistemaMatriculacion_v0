package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class CiclosFormativos {

    // Atributos.
    private int capacidad;
    private int tamano = 0;
    private CicloFormativo[] coleccionCiclosFormativos;

    // Constructor de la clase CiclosFormativos.
    public CiclosFormativos(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    // Obtiene una copia profunda de la colección de ciclos formativos.
    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    // Realiza una copia profunda de los ciclos formativos almacenados en la colección.
    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copiaCiclosFormativos = new CicloFormativo[tamano];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCiclosFormativos[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCiclosFormativos;
    }

    // Obtiene el tamaño actual de la colección (número de ciclos formativos almacenados).
    public int getTamano() {
        return tamano;
    }

    // Comprueba si se ha superado el tamaño de la colección.
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    // Obtiene la capacidad máxima de la colección.
    public int getCapacidad() {
        return capacidad;
    }

    // Comprueba si se ha superado la capacidad de la colección.
    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    // Inserta un ciclo formativo en la colección.
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un ciclo formativo nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionCiclosFormativos[indice] = new CicloFormativo(cicloFormativo);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
    }

    // Busca un ciclo formativo en la colección.
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede buscar un Ciclo Formativo nulo.");

        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new CicloFormativo(get()[indice]);
        }
    }

    // Borra un ciclo formativo de la colección.
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede borrar un ciclo formativo nulo.");
        int indice = buscarIndice(cicloFormativo);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    // Busca el índice de un ciclo formativo en la colección.
    private int buscarIndice(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar un Ciclo Formativo nulo.");
        }

        int indice = 0;
        boolean cicloFormativoEncontrado = false;
        while (!tamanoSuperado(indice) && !cicloFormativoEncontrado) {
            if (get()[indice].equals(cicloFormativo)) {
                cicloFormativoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    // Desplaza los elementos de la colección una posición hacia la izquierda, para eliminar el ciclo formativo en la posición indicada.
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        coleccionCiclosFormativos[indice] = null;
        for (int i = indice; !tamanoSuperado(i); i++) {
            if (i < getCapacidad() - 1) {
                coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
            }
        }
        tamano--;
    }
}

