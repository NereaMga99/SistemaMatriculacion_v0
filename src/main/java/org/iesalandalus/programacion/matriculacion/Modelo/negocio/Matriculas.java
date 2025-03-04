package org.iesalandalus.programacion.matriculacion.Modelo.negocio;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Objects;

public class Matriculas {
    // Atributos.
    private ArrayList<Matricula> coleccionMatriculas;

    // Constructor que inicializa la colección con la capacidad especificada.
    public Matriculas() {
        this.coleccionMatriculas = new ArrayList<>();
    }

    // Obtiene una copia profunda de la colección de matrículas.
    public ArrayList<Matricula> get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    // Realiza una copia profunda de las matrículas almacenadas en la colección.
    private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        for (Matricula m : coleccionMatriculas) {
            copiaMatriculas.add(new Matricula(m));
        }
        return copiaMatriculas;
    }

    // Obtiene el tamaño actual de la colección (número de matrículas almacenadas).
    public int getTamano() {
        return this.coleccionMatriculas.size();
    }

    // Inserta una matrícula en la colección.
    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede insertar una matrícula nula.");

        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            this.coleccionMatriculas.add(new Matricula(matricula));
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }

    // Busca una matrícula en la colección.
    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        Objects.requireNonNull(matricula, "ERROR: No se puede buscar una Matricula nula.");

        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            return null;
        } else {
            return new Matricula(coleccionMatriculas.get(indice));
        }
    }

    // Borra una matrícula de la colección.
    public void borrar(Matricula matricula) throws OperationNotSupportedException {
    if (matricula == null) {
        throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
    }

    int indice = this.coleccionMatriculas.indexOf(matricula);
    if (indice == -1) {
        throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
    }
    this.coleccionMatriculas.remove(indice);
    }

    // Obtiene las matrículas asociadas a un alumno específico.
    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
        ArrayList<Matricula> aux = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                aux.add(new Matricula(matricula));
            }
        }
        return aux;
    }

    // Obtiene las matrículas asociadas a un curso académico específico.
    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException {
        ArrayList<Matricula> aux = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                aux.add(new Matricula(matricula));
            }
        }
        return aux;
    }

    // Obtiene las matrículas asociadas a un ciclo formativo específico.
    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ArrayList<Matricula> aux = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null) {
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
                    if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        aux.add(new Matricula(matricula));
                        break;
                    }
                }
            }
        }
        return aux;
    }
}

