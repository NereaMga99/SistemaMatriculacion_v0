package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public enum Curso {
    // Representa el primer curso.
    PRIMERO("Primero"),
    // Representa el segundo curso.
    SEGUNDO("Segundo");

    // Cadena que representa el curso de forma legible.
    private String cadenaAMostrar;

    // Constructor privado para inicializar los valores del enumerado.
    private Curso(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Devuelve una representación formateada del curso con un dígito y el nombre del curso.
    public String imprimir() {
        int digito = 0;
        if (cadenaAMostrar == PRIMERO.cadenaAMostrar) {
            digito = 0;
        } else {
            digito = 1;
        }
        return digito + ".-" + cadenaAMostrar;
    }

    // Devuelve una descripción del curso en formato de cadena.
    @Override
    public String toString() {
        return "Curso seleccionado:" + cadenaAMostrar;
    }
}
