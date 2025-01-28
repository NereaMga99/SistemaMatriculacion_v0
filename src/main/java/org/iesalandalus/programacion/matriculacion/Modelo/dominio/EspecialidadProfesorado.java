package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

public enum EspecialidadProfesorado {
    // Valores de Especialidad Profesorado.
    INFORMATICA("Informática"),
    SISTEMAS("Sistemas"),
    FOL("Fol");

    // Cadena que representa la especialidad de forma legible.
    private String cadenaAMostrar;

    // Constructor privado para inicializar los valores del enumerado.
    private EspecialidadProfesorado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Devuelve una representación formateada de la especialidad con un dígito y el nombre de la especialidad.
    public String imprimir() {
        int digito = 0;
        if (cadenaAMostrar == INFORMATICA.cadenaAMostrar) {
            digito = 0;
        } else if (cadenaAMostrar == SISTEMAS.cadenaAMostrar) {
            digito = 1;
        } else {
            digito = 2;
        }
        return digito + ".-" + cadenaAMostrar;
    }

    // Devuelve una descripción de la especialidad en formato de cadena.
    @Override
    public String toString() {
        return "La especialidad seleccionada:" + cadenaAMostrar;
    }
}
