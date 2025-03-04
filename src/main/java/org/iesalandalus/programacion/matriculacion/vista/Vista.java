package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.Controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.Modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.Matricula;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.iesalandalus.programacion.matriculacion.MainApp.*;
import static org.iesalandalus.programacion.matriculacion.vista.Consola.mostrarAsignaturas;

public class Vista {
    // Controlador que gestiona las operaciones sobre los datos.
    private Controlador controlador;

    public Vista() {
        Opcion.setVista(this);
    }

    // Inicia el ciclo de interacción con el usuario, mostrando el menú y gestionando las opciones seleccionadas.
    public void comenzar(){
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    // Finaliza la ejecución de la aplicación, cerrando el controlador.
    public void terminar() {
        controlador.terminar();
    }

    //Establece el controlador de la vista.
    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo");
        }
        this.controlador = controlador;
    }

    // Ejecuta la opción seleccionada por el usuario.
    public void ejecutarOpcion(Opcion opcion){
        switch (opcion) {
            case SALIR:
                System.out.println("Hasta luego!!");
                System.exit(0);
            case INSERTAR_ALUMNO:
                System.out.println("Insertar alumno");
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                System.out.println("Buscar alumno");
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                System.out.println("Borrar alumno");
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                System.out.println("Mostrar alumnos");
                mostrarAlumnos();
                break;
            case INSERTAR_CICLO_FORMATIVO:
                System.out.println("Insertar ciclo formativo");
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                System.out.println("Buscar ciclo formativo");
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                System.out.println("Borrar ciclo formativo");
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                System.out.println("Mostrar ciclos formativos");
                mostrarCicloFormativos();
                break;
            case INSERTAR_ASIGNATURA:
                System.out.println("Insertar asignatura");
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                System.out.println("Buscar asignatura");
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                System.out.println("Borrar asignatura");
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                System.out.println("Mostrar asignaturas");
                mostrarAsignaturas();
                break;
            case INSERTAR_MATRICULA:
                System.out.println("Insertar matricula");
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                System.out.println("Buscar matricula");
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                System.out.println("Borrar matricula");
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                System.out.println("Mostrar matriculas");
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                System.out.println("Mostrar matriculas alumno");
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                System.out.println("Mostrar matriculas ciclo formativo");
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                System.out.println("Mostrar matriculas curso academico");
                mostrarMatriculasPorCursoAcademico();
                break;
        }
    }

    // Inserta un nuevo alumno en el sistema.
    public void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar un Alumno nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Busca un alumno por su DNI y muestra los datos si lo encuentra.
    public void buscarAlumno() {
        try {
            Alumno alumnoBuscado = controlador.buscar(Consola.getAlumnoPorDni());
            if (alumnoBuscado != null) {
                System.out.printf("Los datos del alumno solicitado son: %s", alumnoBuscado);
            } else {
                System.out.println("No existe ningun alumno con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: 3.No se puede buscar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Borra un alumno del sistema.
    public void borrarAlumno() {
        try {
            controlador.borrar(Consola.getAlumnoPorDni());
            System.out.println("Alumno borrado correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un Alumno nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Muestra todos los alumnos registrados en el sistema.
    public void mostrarAlumnos() {
        ArrayList<Alumno> arrayAlumnos = controlador.getAlumnos();
        if (arrayAlumnos.size() == 0) {
            System.out.println("No existen alumnos.");
        } else {
            arrayAlumnos.sort(Comparator.comparing(Alumno::getNombre));
            for (Alumno alumno : arrayAlumnos) {
                System.out.println(alumno);
            }
        }
    }

    // Inserta una nueva asignatura en el sistema.
    public void insertarAsignatura() {
        try {
            mostrarCicloFormativos();
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CicloFormativo ciclo = controlador.buscar(cicloFormativo);
            if (ciclo == null) {
                System.out.println("No existe el ciclo formativo indicado.");
                return;
            }
            Asignatura asignatura = Consola.leerAsignatura(ciclo);
            controlador.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Asignatura nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    //Busca una asignatura por su código y muestra los datos si la encuentra.
    public void buscarAsignatura() {
        try {
            Asignatura asignaturaBuscar = controlador.buscar(Consola.getAsignaturaPorCodigo());
            Asignatura encontrada = controlador.buscar(asignaturaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos de la asignatura solicitada son: %s", asignaturaBuscar);
            } else {
                System.out.println("No existe ninguna asignatura con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar una asignatura nula.");
        }
    }

    // Borra una asignatura del sistema.
    public void borrarAsignatura() {
        try {
            Asignatura asignaturaBorrar = Consola.getAsignaturaPorCodigo();
            controlador.borrar(asignaturaBorrar);
            System.out.println("Asignatura borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar una asignatura nula.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Muestra todas las asignaturas registradas en el sistema.
    public void mostrarAsignaturas() {
        ArrayList<Asignatura> arrayAsignatura = controlador.getAsignaturas();
        if (arrayAsignatura.size() == 0) {
            System.out.println("No existen asignaturas.");
        } else {
            arrayAsignatura.sort(new Comparator<Asignatura>() {
                @Override
                public int compare(Asignatura a1, Asignatura a2) {
                    return a1.getNombre().compareTo(a2.getNombre());
                }
            });
            for (Asignatura asignatura : arrayAsignatura) {
                System.out.println(asignatura);
            }
        }
    }

    // Inserta un nuevo ciclo formativo en el sistema.
    public void insertarCicloFormativo() {
        try {
            CicloFormativo ciclosFormativo = Consola.leerCicloFormativo();
            controlador.insertar(ciclosFormativo);
            System.out.println("Ciclo formativo insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar Ciclo Formativo nulo.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Busca un ciclo formativo por su código y muestra los datos si lo encuentra.
    public void buscarCicloFormativo() {
        try {
            CicloFormativo cicloFormativoBuscar = controlador.buscar(Consola.getCicloFormativoPorCodigo());
            CicloFormativo encontrada = controlador.buscar(cicloFormativoBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos del ciclo formativo solicitado son: %s", cicloFormativoBuscar);
            } else {
                System.out.println("No existe ningun ciclo formativo con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Borra un ciclo formativo del sistema.
    public void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativoBorrar = Consola.getCicloFormativoPorCodigo();
            controlador.borrar(cicloFormativoBorrar);
            System.out.println("Ciclo formativo borrada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede borrar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Muestra todos los ciclos formativos registrados en el sistema.
    public void mostrarCicloFormativos() {
        ArrayList<CicloFormativo> arrayCicloFormativo = controlador.getCicloFormativos();
        if (arrayCicloFormativo.size() == 0) {
            System.out.println("No existen ciclos formativos.");
        } else {
            arrayCicloFormativo.sort(new Comparator<CicloFormativo>() {

                @Override
                public int compare(CicloFormativo c1, CicloFormativo c2) {
                    return c1.getNombre().compareTo(c2.getNombre());
                }
            });

            for (CicloFormativo cicloFormativo : arrayCicloFormativo) {
                System.out.println(cicloFormativo);
            }
        }
    }

    // Inserta una nueva matrícula en el sistema.
    public void insertarMatricula() {
        try {
            System.out.println("Datos del alumno:");
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno a = controlador.buscar(alumno);
            if (a == null) {
                System.out.println("No existe el alumno indicado.");
                return;
            }
            System.out.println("Asignaturas de la matricula:");
            ArrayList<Asignatura> matriculaAsignaturas = Consola.elegirAsignaturasMatricula(controlador.getAsignaturas());
            System.out.println("Datos de la matricula:");
            Matricula matricula = Consola.leerMatricula(a, matriculaAsignaturas);
            controlador.insertar(matricula);
            System.out.println("Matricula insertada correctamente.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede insertar una Matricula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    //Busca una matrícula por el DNI del alumno y muestra los detalles si la encuentra.
    public void buscarMatricula() {
        try {
            Matricula matriculaBuscar = controlador.buscar(Consola.getMatriculaPorIdentificador());
            Matricula encontrada = controlador.buscar(matriculaBuscar);
            if (encontrada != null) {
                System.out.printf("Los datos del ciclo formativo solicitado son: %s", matriculaBuscar);
            } else {
                System.out.println("No existe ningun ciclo formativo con tales datos.");
            }
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede buscar un ciclo formativo nulo.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Anula una matrícula.
    public void anularMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Matricula matriculaAnular = controlador.buscar(Consola.getMatriculaPorIdentificador());
            if (matriculaAnular != null && matriculaAnular.getAlumno().equals(alumno)) {
                controlador.borrar(matriculaAnular);
                System.out.println("Indique la fecha de anulación " + Matricula.FORMATO_FECHA + ":");
                String fechaAnulacion = Entrada.cadena();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA);
                LocalDate fechaAnular = LocalDate.parse(fechaAnulacion, formatter);

                matriculaAnular.setFechaAnulacion(fechaAnular);
                System.out.println("Matrícula anulada correctamente.");
            } else {
                System.out.println("No se ha encontrado la matrícula o no corresponde al alumno indicado.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: Formato de fecha incorrecto. Use dd-MM-yyyy.");
        } catch (NullPointerException e) {
            System.out.println("ERROR: No se puede anular una matrícula nula.");
        } catch (OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Muestra todas las matrículas registradas en el sistema.
    public void mostrarMatriculas() {
        try {
            ArrayList<Matricula> arrayMatriculas = controlador.getMatriculas();
            if (arrayMatriculas.size() == 0) {
                System.out.println("No existen matriculas.");
            } else {
                arrayMatriculas.sort(
                        Comparator.comparing(Matricula::getFechaMatriculacion).reversed()
                                .thenComparing(m -> m.getAlumno().getNombre())
                );
            }
            //Mostrar las matriculas ordenadas
            for (Matricula matricula : arrayMatriculas) {
                System.out.println(matricula);
            }
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar matrículas.");
        }
    }


    // Muestra las matrículas de un alumno.
    public void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            ArrayList<Matricula> arrayMatricula = controlador.getMatriculas(alumno);
            if(arrayMatricula.size() == 0) {
                System.out.println("No existen matriculas para el alumno indicado.");
            }else {
                arrayMatricula.sort(new Comparator<Matricula>() {

                    @Override
                    public int compare(Matricula m1, Matricula m2) {
                        int fechaComparison = m2.getFechaMatriculacion().compareTo(m1.getFechaMatriculacion());
                        if (fechaComparison != 0) {
                            return fechaComparison;
                        }
                        // Si las fechas son iguales, ordenar por el nombre del alumno en orden alfabético
                        return m1.getAlumno().getNombre().compareToIgnoreCase(m2.getAlumno().getNombre());
                    }
                });

                // Mostrar las matrículas ordenadas
                for (Matricula matricula : arrayMatricula) {
                    System.out.println(matricula);
                }
            }
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pueden mostrar matrículas por alumno.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Muestra las matrículas de un ciclo formativo.
    public void mostrarMatriculasPorCicloFormativo() {
        try {
            // Obtener el ciclo formativo
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            cicloFormativo = controlador.buscar(cicloFormativo);

            if (cicloFormativo == null) {
                System.out.println("No existe ningún ciclo formativo con tales datos.");
                return;
            }

            // Obtener las matrículas del ciclo formativo
            ArrayList<Matricula> matriculaCiclo = controlador.getMatriculas(cicloFormativo);

            // Verificar si no hay matrículas
            if (matriculaCiclo.size() == 0) {
                System.out.println("No existen matrículas para el ciclo formativo indicado.");
                return;
            }

            // Ordenar las matrículas por fecha de matriculación y luego por nombre del alumno
            matriculaCiclo.sort(new Comparator<Matricula>() {
                @Override
                public int compare(Matricula m1, Matricula m2) {
                    // Ordenar por fecha de matriculación en orden descendente
                    int fechaComparison = m2.getFechaMatriculacion().compareTo(m1.getFechaMatriculacion());
                    if (fechaComparison != 0) {
                        return fechaComparison;
                    }
                    // Si las fechas son iguales, ordenar por nombre del alumno en orden alfabético
                    return m1.getAlumno().getNombre().compareToIgnoreCase(m2.getAlumno().getNombre());
                }
            });

            // Mostrar las matrículas ordenadas
            System.out.println("Matrículas del ciclo formativo " + cicloFormativo.getCodigo() + ":");
            for (Matricula matricula : matriculaCiclo) {
                System.out.println(matricula);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Muestra las matrículas de un curso académico.
    public void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("Indique el curso académico:");
            System.out.println("El formato del curso es YY-YY");
            String cursoAcademico = Entrada.cadena();
            ArrayList<Matricula> arrayMatriculas = controlador.getMatriculas(cursoAcademico);

            if (arrayMatriculas.size() == 0) {
                System.out.println("No existen matrículas para el curso académico indicado.");
                return;
            }

            // Ordenar las matrículas por fecha de matriculación y luego por nombre del alumno
            arrayMatriculas.sort(new Comparator<Matricula>() {
                @Override
                public int compare(Matricula m1, Matricula m2) {

                    int fechaComparison = m2.getFechaMatriculacion().compareTo(m1.getFechaMatriculacion());
                    if (fechaComparison != 0) {
                        return fechaComparison;
                    }

                    return m1.getAlumno().getNombre().compareToIgnoreCase(m2.getAlumno().getNombre());
                }
            });

            // Mostrar las matrículas ordenadas
            System.out.println("Matrículas del curso académico " + cursoAcademico + ":");
            for (Matricula matricula : arrayMatriculas) {
                System.out.println(matricula);
            }
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar matrículas por curso académico.");
        }
    }
}