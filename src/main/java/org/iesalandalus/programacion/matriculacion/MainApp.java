package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.Modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import javax.naming.OperationNotSupportedException;

import static org.iesalandalus.programacion.matriculacion.vista.Opcion.INSERTAR_ALUMNO;
import static org.iesalandalus.programacion.matriculacion.vista.Opcion.SALIR;

public class MainApp {
    public static final int CAPACIDAD= 10;

    // Atributos.
    private static final Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static final Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static final CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    private static final Matriculas matriculas = new Matriculas(CAPACIDAD);


    public static void main(String[] args) throws OperationNotSupportedException {
        Modelo modelo = new Modelo();

        Vista vista = new Vista();

        Controlador controlador = new Controlador(modelo, vista);

        Controlador.comenzar();

        Controlador.terminar();
    }
    private static void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {
        switch (opcion) {
            case SALIR:
                System.out.println("Nos vemos pronto!!");
                System.exit(0);
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCiclosFormativos();
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                break;
        }
    }

    // Metodo insertar alumno.
    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            if (alumno == null) {
                System.out.println("ERROR: El alumno no puede ser nulo.");
                return;
            }

            // Intentamos insertar el alumno
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al insertar el alumno: " + e.getMessage());
        }
    }

    // Metodo buscar alumno.
    private static void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno encontrado = alumnos.buscar(alumno);
            System.out.println((encontrado != null) ? encontrado : "No se encontró el alumno.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo borrar alumno.
    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar alumnos.
    private static void mostrarAlumnos() {
        Alumno[] coleccion = alumnos.get();
        if (coleccion.length == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : coleccion) {
                if (alumno != null) System.out.println(alumno);
            }
        }
    }

    // Metodo insertar ciclo formativo.
    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo buscar ciclo formativo.
    private static void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            if (ciclo == null) {
                System.out.println("El ciclo formativo no es válido.");
                return;
            }
            CicloFormativo encontrado = ciclosFormativos.buscar(ciclo);
            System.out.println((encontrado != null) ? encontrado : "No se encontró el ciclo formativo.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo borrar ciclo formativo.
    private static void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            ciclosFormativos.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar ciclos formativos.
    private static void mostrarCiclosFormativos() {
        CicloFormativo[] coleccion = ciclosFormativos.get();
        if (coleccion.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo ciclo : coleccion) {
                if (ciclo != null) System.out.println(ciclo);
            }
        }
    }

    // Metodo insertar asignatura.
    private static void insertarAsignatura() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            Asignatura asignatura = Consola.leerAsignatura(ciclo);
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo buscar asignatura.
    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura encontrada = asignaturas.buscar(asignatura);
            System.out.println((encontrada != null) ? encontrada : "No se encontró la asignatura");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo borrar asignatura.
    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar asignaturas.
    private static void mostrarAsignaturas() {
        Asignatura[] coleccion = asignaturas.get();
        if (coleccion.length == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for (Asignatura asignatura : coleccion) {
                if (asignatura != null) System.out.println(asignatura);
            }
        }
    }

    // Metodo insertar matricula.
    private static void insertarMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Matricula matricula = Consola.leerMatricula(alumno, asignaturas.get());
            matriculas.insertar(matricula);
            System.out.println("Matricula insertada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo buscar matricula.
    private static void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            System.out.println((encontrada != null) ? encontrada : "No se encontró la matricula.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo borrar matricula.
    private static void anularMatricula() {
        try {
            mostrarMatriculas();
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matriculas.borrar(matricula);
            System.out.println("Matrícula anulada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar matriculas.
    private static void mostrarMatriculas() {
        try {
        Matricula[] coleccion = matriculas.get();
        if (coleccion.length == 0) {
            System.out.println("No hay matriculas registradas.");
        } else {
            for (Matricula matricula : coleccion) {
                if (matricula != null) System.out.println(matricula);
            }
        }
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar matriculas por alumno.
    private static void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Matricula[] coleccion = matriculas.get(alumno);
            if (coleccion.length == 0) {
                System.out.println("El alumno no tiene matrículas registradas.");
            } else {
                for (Matricula matricula : coleccion) {
                    if (matricula != null) System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar matriculas por ciclo formativo.
    private static void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloFormativoPorCodigo();
            Matricula[] coleccion = matriculas.get(ciclo);
            if (coleccion.length == 0) {
                System.out.println("El ciclo formativo no tiene matrículas registradas.");
            } else {
                for (Matricula matricula : coleccion) {
                    if (matricula != null) System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metodo mostrar matriculas por curso academico.
    private static void mostrarMatriculasPorCursoAcademico() throws OperationNotSupportedException {
        System.out.print("Introduce el curso académico (e.g., 24-25): ");
        String cursoAcademico = Consola.leerCurso().toString();
        Matricula[] matriculasCurso = matriculas.get(cursoAcademico);
        if (matriculasCurso.length == 0) {
            System.out.println("No hay matrículas para el curso académico indicado.");
        } else {
            for (Matricula matricula : matriculasCurso) {
                if (matricula != null) System.out.println(matricula);
            }
        }
    }
}