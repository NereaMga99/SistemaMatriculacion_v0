package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public enum Grado {
    // Valores de grado.
    GDCFGB("Grado Básico"),
    GDCFGM("Grado Medio"),
    GDCFGS("Grado Superior");

    // Cadena que representa el grado de forma legible.
    private String cadenaAMostrar;

    // Constructor privado para inicializar los valores del enumerado.
    private Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Devuelve una representación formateada del grado con un dígito y el nombre del grado.
    public String imprimir() {
        int digito = 0;
        if (cadenaAMostrar == GDCFGB.cadenaAMostrar) {
            digito = 0;
        } else if (cadenaAMostrar == GDCFGM.cadenaAMostrar) {
            digito = 1;
        } else {
            digito = 2;
        }
        return digito + ".-" + cadenaAMostrar;
    }

    // Devuelve una descripción del grado en formato de cadena.
    public String toString() {
        return "Grado seleccionado:" + cadenaAMostrar;
    }
}
