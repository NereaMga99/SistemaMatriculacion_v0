package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

import java.util.Objects;

public class CicloFormativo {
    public static final int MAXIMO_NUMERO_HORAS = 2000;

    // Atributos
    private int codigo;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    // Todos los métodos de acceso y modificación.

    // Codigo.
    public int getCodigo() {
        return codigo;
    }

    private void setCodigo(int codigo) {
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("El código debe ser un número de cuatro dígitos.");
        }
        this.codigo = codigo;
    }

    // Familia profesional.
    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional == null) {
            throw new NullPointerException("ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        } else if (familiaProfesional.isBlank()) {
            throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
        }

        this.familiaProfesional = familiaProfesional;
    }

    // Grado.
    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado == null) {
            throw new NullPointerException("ERROR: El grado de un ciclo formativo no puede ser nulo.");
        }
        this.grado = grado;
    }

    // Nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        } else if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
        }

        this.nombre = nombre;
    }

    // Horas.
    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas <= 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException("ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a " + MAXIMO_NUMERO_HORAS + ".");
        }
        this.horas = horas;
    }

    // Constructor con parámetros.
    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    // Constructor copia.
    public CicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No es posible copiar un ciclo formativo nulo.");
        }
        this.codigo = cicloFormativo.codigo;
        this.familiaProfesional = cicloFormativo.familiaProfesional;
        this.grado = cicloFormativo.grado;
        this.nombre = cicloFormativo.nombre;
        this.horas = cicloFormativo.horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CicloFormativo that)) return false;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, familiaProfesional, grado, nombre, horas);
    }

    @Override
    public String toString() {
        return "Código ciclo formativo=" + codigo + ", familia profesional=" + familiaProfesional + ", grado=" + grado + ", nombre ciclo formativo=" + nombre + ", horas=" + horas;
    }

    // Metodo para imprimir los datos.
    public String imprimir() {
        return "Código ciclo formativo=" + codigo + ", nombre ciclo formativo=" + nombre;
    }
}
