package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

public class Matriculas {
    // Atributos.
    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    // Constructor con parámetros.
    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    // Get que devuelve copia profunda.
    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    // Copia profunda de la colección.
    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copiaMatriculas = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatriculas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    // Insertar matriculas.
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        if (buscarIndice(matricula) >= 0) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
        coleccionMatriculas[tamano] = new Matricula(matricula);
        tamano++;
    }

    // Metodo para buscar las matriculas por el código.
    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        for (Matricula a : coleccionMatriculas) {
            if (a!= null && a.equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    // Metodo para borrar matrículas.
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice < 0) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }

        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new IllegalArgumentException("ERROR: No se puede buscar el índice de una matrícula nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null;
    }

    // Devuelve colección de matriculas de curso académico.
    public Matricula[] get(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }

        Matricula[] matriculasAlumno = new Matricula[tamano];
        int j = 0;

        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                matriculasAlumno[j] = new Matricula(coleccionMatriculas[i]);
                j++;
            }
        }

        Matricula[] matriculaAlumno = new Matricula[j];
        for (int i = 0; i < j; i++) {
            matriculaAlumno[i] = matriculasAlumno[i];
        }

        return matriculaAlumno;
    }

    //Devuelve colección de matriculas de curso académico.
    public Matricula[] get(String cursoAcademico) throws OperationNotSupportedException {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico no puede ser nulo ni vacío.");
        }
        if (cursoAcademico.isBlank()) {
            throw new IllegalArgumentException("ERROR: El curso académico no puede ser nulo ni vacío.");
        }
        if (cursoAcademico.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El curso académico no puede ser nulo ni vacío.");
        }
        Matricula[] matriculasCursoAcademico = new Matricula[tamano];
        int j = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                matriculasCursoAcademico[j] = new Matricula(coleccionMatriculas[i]);
                j++;
            }
        }
        Matricula[] matriculaCursoAcademico = new Matricula[j];
        for (int i = 0; i < j; i++) {
            matriculaCursoAcademico[i] = matriculasCursoAcademico[i];
        }
        return matriculaCursoAcademico;
    }

    //Devuelve colección de matriculas de ciclo formativo.
    public Matricula[] get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        Matricula[] matriculasCicloFormativo = new Matricula[tamano];
        int j = 0;
        for (int i = 0; i < tamano; i++) {
            for (Asignatura asignatura : coleccionMatriculas[i].getColeccionAsignaturas()) {
                if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    matriculasCicloFormativo[j] = new Matricula(coleccionMatriculas[i]);
                    j++;
                    break;
                }
            }
        }
        Matricula[] matriculaCicloFormativo = new Matricula[j];
        for (int i = 0; i < j; i++) {
            matriculaCicloFormativo[i] = matriculasCicloFormativo[i];
        }
        return matriculaCicloFormativo;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }














}
