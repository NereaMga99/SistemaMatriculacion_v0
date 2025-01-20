package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public enum Grado {
    // Valores de grado.
    GDCFGB("Grado Básico"),
    GDCFGM("Grado Medio"),
    GDCFGS("Grado Superior");

    // Atributo CadenaAMostrar.
    private final String cadenaAMostrar;

    // Constructor
    private Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Metodo Imprimir los datos.
    public String imprimir() {
        return this.ordinal() + ".-" + this.cadenaAMostrar;
    }

    // Metodo toString().
    public String toString() {
        return "Grado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
