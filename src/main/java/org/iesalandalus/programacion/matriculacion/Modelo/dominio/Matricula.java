package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Matricula {
    public static final int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static final int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static final int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static final int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    private static final String ER_CURSO_ACADEMICO = "\\d{2}-\\d{2}";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";

    // Atributos.
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Alumno alumno;
    private ArrayList<Asignatura> coleccionAsignaturas;

    // Constructor principal para inicializar una matrícula con todos los datos.
    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno,
                     ArrayList<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }


    // Constructor de copia para crear una nueva matrícula basada en otra existente.
    public Matricula(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No es posible copiar una matrícula nula.");
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());
    }

    // Devuelve el identificador único de la matrícula.
    public int getIdMatricula() {
        return idMatricula;
    }

    // Establece el identificador único de la matrícula.
    public void setIdMatricula(int idMatricula) {
        if (idMatricula <= 0) {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
        this.idMatricula = idMatricula;
    }

    // Devuelve el curso académico asociado a la matrícula.
    public String getCursoAcademico() {
        return this.cursoAcademico;
    }

    // Establece el curso académico asociado a la matrícula.
    public void setCursoAcademico(String cursoAcademico) {
        Objects.requireNonNull(cursoAcademico, "ERROR: El curso académico de una matrícula no puede ser nulo.");
        if (cursoAcademico.isBlank()) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        }
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    // Devuelve la fecha de matriculación.
    public LocalDate getFechaMatriculacion() {
        return this.fechaMatriculacion;
    }

    // Establece la fecha de matriculación.
    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        Objects.requireNonNull(fechaMatriculacion, "ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }
        if (fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a "
                    + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        this.fechaMatriculacion = fechaMatriculacion;
    }

    // Devuelve la fecha de anulación.
    public LocalDate getFechaAnulacion() {
        return this.fechaAnulacion;
    }

    // Establece la fecha de anulacion.
    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        Objects.requireNonNull(fechaAnulacion, "ERROR: La fecha de anulación de una matrícula no puede ser nula.");
        if (fechaAnulacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }
        if (fechaAnulacion.isBefore(getFechaMatriculacion())) {
            throw new IllegalArgumentException(
                    "ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }

        this.fechaAnulacion = fechaAnulacion;
    }

    // Devuelve el alumno asociado a la matrícula.
    public Alumno getAlumno() {
        return this.alumno;
    }

    // Establece el alumno asociado a la matrícula.
    public void setAlumno(Alumno alumno) {
        Objects.requireNonNull(alumno, "ERROR: El alumno de una matrícula no puede ser nulo.");
        this.alumno = alumno;
    }

    // Devuelve la lista de asignaturas matriculadas.
    public ArrayList<Asignatura> getColeccionAsignaturas() {
        return new ArrayList<>(this.coleccionAsignaturas);
    }

    // Establece la lista de asignaturas matriculadas.
    public void setColeccionAsignaturas(ArrayList<Asignatura> coleccionAsignaturas) throws OperationNotSupportedException {
        Objects.requireNonNull(coleccionAsignaturas, "ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas ("
                    + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }
        this.coleccionAsignaturas = new ArrayList<>();
        for (Asignatura a : coleccionAsignaturas) {
            if (a == null) continue;
            this.coleccionAsignaturas.add(new Asignatura(a));
        }
    }

    // Calcula si la suma de horas de las asignaturas supera el máximo permitido.
    private boolean superaMaximoNumeroHorasMatricula(ArrayList<Asignatura> asignaturasMatricula) {
        int horasMatricula = 0;
        for (Asignatura a : asignaturasMatricula) {
            if (a != null) {
                horasMatricula += a.getHorasAnuales();
            }
        }
        return horasMatricula > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    // Devuelve una representación textual de las asignaturas matriculadas.
    private String asignaturasMatricula() {
        String s = "";
        for (int i = 0; i < coleccionAsignaturas.size(); i++) {
            if (coleccionAsignaturas.get(i) != null) {
                s += coleccionAsignaturas.get(i).getNombre();
                if (i < coleccionAsignaturas.size() - 1) {
                    s += ", ";
                }
            }
        }
        return s;
    }

    // Compara si dos matrículas son iguales basándose en su identificador.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return this.idMatricula == matricula.idMatricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula, cursoAcademico, fechaMatriculacion, fechaAnulacion, alumno, coleccionAsignaturas);
    }

    // Muestra la información de la matrícula.
    public String imprimir() {
        return "idMatricula=" + getIdMatricula() + ", " + "curso académico=" + getCursoAcademico() + ", " + "fecha matriculación="
                + getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " + "alumno=" + "{" + getAlumno().imprimir() + "}";
    }

    // Devuelve una representación textual de la matrícula
    @Override
    public String toString() {
        if (fechaAnulacion == null) {
            return "idMatricula=" + getIdMatricula() + ", " + "curso académico=" + getCursoAcademico() + ", " + "fecha matriculación="
                    + getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " + "alumno=" + getAlumno().imprimir() + ", "
                    + "Asignaturas=" + "{ " + asignaturasMatricula() + "}";
        } else {
            return "idMatricula=" + getIdMatricula() + ", " + "curso académico=" + getCursoAcademico() + ", " + "fecha matriculación="
                    + getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " + "fecha anulación="
                    + getFechaAnulacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) + ", " + "alumno=" + getAlumno().imprimir() + ", "
                    + "Asignaturas=" + "{ " + asignaturasMatricula() + "}";
        }
    }
}
