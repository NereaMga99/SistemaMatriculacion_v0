package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

import java.util.Objects;

public class Asignatura {

    public static final int MAX_NUM_HORAS_ANUALES = 300;
    public static final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "^\\d{4}$";

    // Atributos.
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    // Crea una nueva asignatura con los datos proporcionados.
    public Asignatura(String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    // Crea una copia de la asignatura proporcionada.
    public Asignatura(Asignatura asignatura) {
        Objects.requireNonNull(asignatura, "ERROR: No es posible copiar una asignatura nula.");
        setCodigo(asignatura.codigo);
        setNombre(asignatura.nombre);
        setHorasAnuales(asignatura.horasAnuales);
        setCurso(asignatura.curso);
        setHorasDesdoble(asignatura.horasDesdoble);
        setEspecialidadProfesorado(asignatura.especialidadProfesorado);
        setCicloFormativo(asignatura.cicloFormativo);
    }

    // Devuelve el código de la asignatura.
    public String getCodigo() {
        return codigo;
    }

    // Establece el código de la asignatura.
    private void setCodigo(String codigo) {
        Objects.requireNonNull(codigo, "ERROR: El código de una asignatura no puede ser nulo.");
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura no puede estar vacío.");
        }
        if (!codigo.matches(ER_CODIGO)) {
            throw new IllegalArgumentException("ERROR: El código de la asignatura no tiene un formato válido.");
        }

        this.codigo = codigo;
    }

    // Devuelve el nombre de la asignatura.
    public String getNombre() {
        return nombre;
    }

    // Establece el nombre de la asignatura.
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "ERROR: El nombre de una asignatura no puede ser nulo.");
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    // Devuelve el número de horas anuales de la asignatura.
    public int getHorasAnuales() {
        return horasAnuales;
    }

    // Establece el número de horas anuales de la asignatura.
    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0 || horasAnuales > MAX_NUM_HORAS_ANUALES) {
            throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a 300.");
        }
        this.horasAnuales = horasAnuales;
    }

    // Devuelve el número de horas de desdoble de la asignatura.
    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    // Establece el número de horas de desdoble de la asignatura.
    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES) {
            throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a 6.");
        }
        this.horasDesdoble = horasDesdoble;
    }

    // Devuelve el curso de la asignatura.
    public Curso getCurso() {
        return curso;
    }

    // Establece el curso de la asignatura.
    public void setCurso(Curso curso) {
        Objects.requireNonNull(curso, "ERROR: El curso de una asignatura no puede ser nulo.");
        if (curso != Curso.PRIMERO && curso != Curso.SEGUNDO) {
            throw new IllegalArgumentException("ERROR: El curso de una asignatura debe ser Primero o Segundo.");
        }
        this.curso = curso;
    }

    // Devuelve la especialidad del profesorado de la asignatura.
    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    // Establece la especialidad del profesorado de la asignatura.
    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        Objects.requireNonNull(especialidadProfesorado, "ERROR: La especialidad del profesorado de una asignatura no puede ser nula.");
        this.especialidadProfesorado = especialidadProfesorado;
    }

    // Devuelve el ciclo formativo al que pertenece la asignatura.
    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    //Establece el ciclo formativo al que pertenece la asignatura.
    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        Objects.requireNonNull(cicloFormativo, "ERROR: El ciclo formativo de una asignatura no puede ser nulo.");
        this.cicloFormativo = cicloFormativo;
    }

    // Verifica si dos asignaturas son iguales comparando sus códigos.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    // Devuelve una representación en forma de cadena de la asignatura.
    @Override
    public String toString() {
        return "Código=" + codigo + ", nombre=" + nombre + ", horas anuales=" + horasAnuales + ", curso=" + curso + ", horas desdoble=" + horasDesdoble
                + ", ciclo formativo=Código ciclo formativo=" + cicloFormativo.getCodigo() + ", nombre ciclo formativo=" + cicloFormativo.getNombre()
                + ", especialidad profesorado=" + especialidadProfesorado;
    }

    // Imprime una representación simplificada de la asignatura.
    public String imprimir() {
        return "Código asignatura=" + codigo + ", nombre asignatura=" + nombre + ", ciclo formativo=Código ciclo formativo="
                + cicloFormativo.getCodigo() + ", nombre ciclo formativo=" + cicloFormativo.getNombre();
    }
}
