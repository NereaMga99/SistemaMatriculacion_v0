package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Matriculas {
    // Atributos.
    private int capacidad;
    private int tamano = 0;
    private Matricula[] coleccionMatriculas;

    // Constructor que inicializa la colección con la capacidad especificada.
    public Matriculas(int capacidad) {
        if (!(capacidad > 0)) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionMatriculas = new Matricula[capacidad];
    }

    // Obtiene una copia profunda de la colección de matrículas.
    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    // Realiza una copia profunda de las matrículas almacenadas en la colección.
    private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {
        Matricula[] copiaMatriculas = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatriculas;
    }

    // Obtiene el tamaño actual de la colección (número de matrículas almacenadas).
    public int getTamano() {
        return tamano;
    }

    // Obtiene la capacidad máxima de la colección.
    public int getCapacidad() {
        return capacidad;
    }

    // Inserta una matrícula en la colección.
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        int indice = buscarIndice(matricula);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }

        if (tamanoSuperado(indice)) {
            coleccionMatriculas[indice] = new Matricula(matricula);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }

    // Busca el índice de una matrícula en la colección.
    private int buscarIndice(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede buscar una Matricula nula.");

        int indice = 0;
        boolean matriculaEncontrado = false;
        while (!tamanoSuperado(indice) && !matriculaEncontrado) {
            if (get()[indice].equals(matricula)) {
                matriculaEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    // Comprueba si se ha superado la capacidad de la colección.
    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    // Comprueba si se ha superado el tamaño de la colección.
    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    // Busca una matrícula en la colección.
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede buscar una Matricula nula.");

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Matricula(get()[indice]);
        }
    }

    // Borra una matrícula de la colección.
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede borrar una matrícula nula.");

        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    // Desplaza los elementos de la colección una posición hacia la izquierda, para eliminar la matrícula en la posición indicada.
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        coleccionMatriculas[indice] = null;
        for (int i = indice; !tamanoSuperado(i); i++) {
            if (i < getCapacidad() - 1) {
                coleccionMatriculas[i] = coleccionMatriculas[i + 1];
            }
        }
        tamano--;
    }

    // Obtiene las matrículas asociadas a un alumno específico.
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

    // Obtiene las matrículas asociadas a un curso académico específico.
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

    // Obtiene las matrículas asociadas a un ciclo formativo específico.
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
    }
