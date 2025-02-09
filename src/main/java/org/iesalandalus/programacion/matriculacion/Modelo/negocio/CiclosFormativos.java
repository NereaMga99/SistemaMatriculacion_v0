package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class CiclosFormativos {

    // Atributos.
    private ArrayList<CicloFormativo> coleccionCiclosFormativos;

    // Constructor de la clase CiclosFormativos.
    public CiclosFormativos() {
    this.coleccionCiclosFormativos = new ArrayList<>();
    }

    // Obtiene una copia profunda de la colección de ciclos formativos.
    public ArrayList<CicloFormativo> get() {
        return copiaProfundaCiclosFormativos();
    }

    // Realiza una copia profunda de los ciclos formativos almacenados en la colección.
    private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {
        ArrayList<CicloFormativo> copiaCiclosFormativos = new ArrayList<>();
        for (CicloFormativo c : coleccionCiclosFormativos) {
            copiaCiclosFormativos.add(new CicloFormativo(c));
        }
        return copiaCiclosFormativos;
    }

    // Inserta un ciclo formativo en la colección.
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede insertar un ciclo formativo nulo.");

        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice == -1) {
            coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
    }

    // Busca un ciclo formativo en la colección.
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede buscar un Ciclo Formativo nulo.");

        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice == -1) {
            return null;
        } else {
            return new CicloFormativo(this.coleccionCiclosFormativos.get(indice));
        }
    }

    // Borra un ciclo formativo de la colección.
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Objects.requireNonNull(cicloFormativo, "ERROR: No se puede borrar un ciclo formativo nulo.");
        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        this.coleccionCiclosFormativos.remove(indice);
    }

    // Obtiene el tamaño acgual de la collección (número de ciclos formativos almacenados).
    public int getTamano() {
        return this.coleccionCiclosFormativos.size();
    }
}

