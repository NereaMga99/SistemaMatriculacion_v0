package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public enum Curso {
    // Valores de curso.
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    // Atributo cadenaAMostrar.
    private final String cadenaAMostrar;

    // Constructor
    private Curso(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Metodo Imprimir los datos.
    public String imprimir() {
        return this.ordinal() + ".-" + this.cadenaAMostrar;
    }

    // Metodo toString().
    @Override
    public String toString() {
        return String.format("%d.- %s", this.ordinal(), this.cadenaAMostrar);
    }
}
