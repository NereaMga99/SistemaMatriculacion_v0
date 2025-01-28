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

    // Constructor que inicializa un nuevo Ciclo Formativo con los datos proporcionados.
    public CicloFormativo(int codigo, String familiaProfesional, Grado grado, String nombre, int horas) {
        setCodigo(codigo);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    // Constructor copia que crea un nuevo Ciclo Formativo a partir de otro existente.
    public CicloFormativo(CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo, "ERROR: No es posible copiar un ciclo formativo nulo.");
        setCodigo(cicloFormativo.codigo);
        setFamiliaProfesional(cicloFormativo.familiaProfesional);
        setGrado(cicloFormativo.grado);
        setNombre(cicloFormativo.nombre);
        setHoras(cicloFormativo.horas);
    }

    // Devuelve el código del ciclo formativo.
    public int getCodigo() {
        return codigo;
    }

    // Establece el código del ciclo formativo.
    private void setCodigo(int codigo) {
        if (codigo < 1000 || codigo > 9999) {
            throw new IllegalArgumentException("ERROR: El código de un ciclo formativo debe ser un número de 4 dígitos.");
        }
        this.codigo = codigo;
    }

    // Devuelve la familia profesional del ciclo formativo.
    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    // Establece la familia profesional del ciclo formativo.
    public void setFamiliaProfesional(String familiaProfesional) {
        Objects.requireNonNull(familiaProfesional, "ERROR: La familia profesional de un ciclo formativo no puede ser nula.");
        if (familiaProfesional.isBlank()) {
            throw new IllegalArgumentException("ERROR: La familia profesional no puede estar vacía.");
        }
        this.familiaProfesional = familiaProfesional;
    }

    // Devuelve el grado del ciclo formativo.
    public Grado getGrado() {
        return grado;
    }

    // Establece el grado del ciclo formativo.
    public void setGrado(Grado grado) {
        Objects.requireNonNull(grado, "ERROR: El grado de un ciclo formativo no puede ser nulo.");
        this.grado = grado;
    }

    // Devuelve el nombre del ciclo formativo.
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre del ciclo formativo.
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "ERROR: El nombre de un ciclo formativo no puede ser nulo.");
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un ciclo formativo no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    // Devuelve el número de horas del ciclo formativo.
    public int getHoras() {
        return horas;
    }

    // Establece el número de horas del ciclo formativo.
    public void setHoras(int horas) {
        if (horas <= 0 || horas > MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException(
                    "ERROR: El número de horas de un ciclo formativo no puede ser menor o igual a 0 ni mayor a 2000.");
        }
        this.horas = horas;
    }

    // Verifica si dos ciclos formativos son iguales basándose en su código.
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CicloFormativo that = (CicloFormativo) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    // Devuelve una representación en forma de cadena del ciclo formativo.
    @Override
    public String toString() {
        return "Código ciclo formativo=" + codigo + ", familia profesional=" + familiaProfesional + ", grado=" + grado + ", nombre ciclo formativo=" + nombre + ", horas=" + horas;
    }

    // Devuelve una representación breve del ciclo formativo.
    public String imprimir() {
        return "Código ciclo formativo=" + codigo + ", nombre ciclo formativo=" + nombre;
    }
}
