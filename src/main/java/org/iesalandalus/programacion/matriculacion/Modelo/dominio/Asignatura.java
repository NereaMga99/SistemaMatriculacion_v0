package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

import java.util.Objects;

public class Asignatura {

    public static final int MAX_NUM_HORAS_ANUALES = 300;
    public static final int MAX_NUM_HORAS_DESDOBLES = 6;
    private static final String ER_CODIGO = "\\d{4}";

    // Atributos.
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private Curso curso;
    private int horasDesdoble;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    // Todos los métodos de acceso y modificación.

    // Codigo.
    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        if (codigo == null ) {
            throw new NullPointerException("ERROR: El código de una asignatura no puede ser nulo.");
        }
        if (codigo.isBlank() ) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura no puede estar vacío.");
        }
        if(!codigo.matches(ER_CODIGO)) {
            throw new IllegalArgumentException("ERROR: El código de la asignatura no tiene un formato válido.");
        }
        this.codigo = codigo;
    }

    // Nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new NullPointerException("ERROR: El nombre de una asignatura no puede ser nulo.");
        }
        if (nombre.isBlank() || nombre.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede estar vacío.");
        }

        this.nombre = nombre;
    }

    // Horas anuales.
    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0 || horasAnuales > MAX_NUM_HORAS_ANUALES) {
            throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a " + Asignatura.MAX_NUM_HORAS_ANUALES + ".");
        }
        this.horasAnuales = horasAnuales;
    }

    // Horas desdobles.
    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES) {
            throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a "+ Asignatura.MAX_NUM_HORAS_DESDOBLES+ ".");
        }
        this.horasDesdoble = horasDesdoble;
    }

    // Curso.
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new NullPointerException("ERROR: El curso de una asignatura no puede ser nulo.");
        }
        this.curso = curso;
    }

    // EspecialidadProfesorado.
    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null) {
            throw new NullPointerException("ERROR: La especialidad del profesorado de una asignatura no puede ser nula.");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }

    // cicloFormativo.
    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo de una asignatura no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }

    // Constructor con parámetros.
    public Asignatura (String codigo, String nombre, int horasAnuales, Curso curso, int horasDesdoble, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setCurso(curso);
        setHorasDesdoble(horasDesdoble);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    // Constructor copia.
    public Asignatura (Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No es posible copiar una asignatura nula.");
        }
        this.codigo = asignatura.codigo;
        this.nombre = asignatura.nombre;
        this.horasAnuales = asignatura.horasAnuales;
        this.curso = asignatura.curso;
        this.horasDesdoble = asignatura.horasDesdoble;
        this.especialidadProfesorado = asignatura.especialidadProfesorado;
        this.cicloFormativo = asignatura.cicloFormativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Asignatura a= (Asignatura) o;
        return this.codigo.toString()==a.getCodigo().toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    @Override
    public String toString() {
        return String.format("Código=%s, nombre=%s, horas anuales=%d, curso=%s, horas desdoble=%d, ciclo formativo=%s, especialidad profesorado=%s", this.getCodigo(), this.getNombre(),this.getHorasAnuales(),
                this.getCurso(),this.getHorasDesdoble(), this.getCicloFormativo().imprimir(),this.getEspecialidadProfesorado());
    }

    // Metodo para imprimir los datos.
    public String imprimir(){
        return String.format("Código asignatura=%s, nombre asignatura=%s, ciclo formativo=%s", this.getCodigo(), this.getNombre(), this.getCicloFormativo().imprimir());
    }
}
