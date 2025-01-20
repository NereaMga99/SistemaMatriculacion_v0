package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.Modelo.Modelo;

public class Vista {

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
    }
        this.controlador = controlador;
    }

    public void comenzar() {
        setControlador(new Controlador(this, new Modelo()));
        Opcion opcion;
        do {

    }
}
