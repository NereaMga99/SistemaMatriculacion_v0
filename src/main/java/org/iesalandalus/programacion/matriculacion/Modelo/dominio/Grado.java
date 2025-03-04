package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public abstract class Grado {

    protected String nombre;
    protected String iniciales;
    protected int numAnios;

    public Grado (String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre del grado no puede ser nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre del grado no puede estar vacio.");
        }
        this.nombre = nombre;
        setIniciales();
    }

    private void setIniciales() {
        String[] palabras = nombre.split("[ ]+");
        String S = "";
        for (String palabra : palabras) {
            S += palabra.charAt(0);
        }
        this.iniciales = S.toUpperCase();
    }

    public abstract void setNumAnios(int numAnios);

    public String toString() {
        return "(" + iniciales + ") - " + nombre;
    }

}
