package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public enum EspecialidadProfesorado {
    // Valores de Especialidad Profesorado.
    INFORMATICA("Informática"),
    SISTEMAS("Sistemas"),
    FOL("FOL");

    // Atributo cadenaAMostrar.
    private final String cadenaAMostrar;

    // Constructor.
    private EspecialidadProfesorado(String cadenaAMostrar) {
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
