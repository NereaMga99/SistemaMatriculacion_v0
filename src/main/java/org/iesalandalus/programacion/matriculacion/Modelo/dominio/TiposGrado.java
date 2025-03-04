package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

import java.security.PrivateKey;

public enum TiposGrado {

    GRADOD ("Grado D"),
    GRADOE ("Grado E");

    private String cadenaAMostrar;

    private TiposGrado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        int digito = 0;
        if (cadenaAMostrar == GRADOD.cadenaAMostrar) {
            digito = 0;
        } else if (cadenaAMostrar == GRADOE.cadenaAMostrar) {
            digito = 1;
        } else {
            digito = 2;
        }
        return digito + ".-" + cadenaAMostrar;
    }

    public String toString() {
        return "Grado selecionado:" + cadenaAMostrar;
    }
}
