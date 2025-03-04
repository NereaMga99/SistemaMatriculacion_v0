package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {
    SALIR("Salir") {
        @Override
        public void ejecutar() {
            System.out.println("Hasta luego!!");
            vista.terminar();
        }
    },
    INSERTAR_ALUMNO("Insertar alumno") {
        @Override
        public void ejecutar() {
            System.out.println("Insertar alumno");
            vista.insertarAlumno();
        }
    },
    BUSCAR_ALUMNO("Buscar alumno") {
        @Override
        public void ejecutar() {
            System.out.println("Buscar alumno");
            vista.buscarAlumno();
        }
    },
    BORRAR_ALUMNO("Borrar alumno") {
        @Override
        public void ejecutar() {
            System.out.println("Borrar alumno");
            vista.borrarAlumno();
        }
    },
    MOSTRAR_ALUMNOS("Mostrar alumnos") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar alumnos");
            vista.mostrarAlumnos();
        }
    },
    INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo") {
        @Override
        public void ejecutar() {
            System.out.println("Insertar ciclo formativo");
            vista.insertarCicloFormativo();
        }
    },
    BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo") {
        @Override
        public void ejecutar() {
            System.out.println("Buscar ciclo formativo");
            vista.buscarCicloFormativo();
        }
    },
    BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo") {
        @Override
        public void ejecutar() {
            System.out.println("Borrar ciclo formativo");
            vista.borrarCicloFormativo();
        }
    },
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar ciclos formativos");
            vista.mostrarCicloFormativos();
        }
    },
    INSERTAR_ASIGNATURA("Insertar asignatura") {
        @Override
        public void ejecutar() {
            System.out.println("Insertar asignatura");
            vista.insertarAsignatura();
        }
    },
    BUSCAR_ASIGNATURA("Buscar asignatura") {
        @Override
        public void ejecutar() {
            System.out.println("Buscar asignatura");
            vista.buscarAsignatura();
        }
    },
    BORRAR_ASIGNATURA("Borrar asignatura") {
        @Override
        public void ejecutar() {
            System.out.println("Borrar asignatura");
            vista.borrarAsignatura();
        }
    },
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar asignaturas");
            vista.mostrarAsignaturas();
        }
    },
    INSERTAR_MATRICULA("Insertar matrícula") {
        @Override
        public void ejecutar() {
            System.out.println("Insertar matricula");
            vista.insertarMatricula();
        }
    },
    BUSCAR_MATRICULA("Buscar matrícula") {
        @Override
        public void ejecutar() {
            System.out.println("Buscar matricula");
            vista.buscarMatricula();
        }
    },
    ANULAR_MATRICULA("Anular matrícula") {
        @Override
        public void ejecutar() {
            System.out.println("Borrar matricula");
            vista.anularMatricula();
        }
    },
    MOSTRAR_MATRICULAS("Mostrar matrículas") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar matriculas");
            vista.mostrarMatriculas();
        }
    },
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matrículas de un Alumno") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar matriculas alumno");
            vista.mostrarMatriculasPorAlumno();
        }
    },
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matrículas de un Ciclo Formativo") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar matriculas ciclo formativo");
            vista.mostrarMatriculasPorCicloFormativo();
        }
    },
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matrículas de este Curso Académico") {
        @Override
        public void ejecutar() {
            System.out.println("Mostrar matriculas curso academico");
            vista.mostrarMatriculasPorCursoAcademico();
        }
    };

    // Atributo que contiene la cadena a mostrar asociada a cada opción.
    private final String cadenaAMostrar;

    private static Vista vista;

    // Constructor de la enumeración Opcion que asocia una cadena a mostrar a cada opción.
    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public abstract void ejecutar();

    public static void setVista(Vista vista) {
        Opcion.vista = vista;
    }

    // Devuelve la representación en cadena de la opción, que incluye su índice en la enumeración
    @Override
    public String toString() {
        return ordinal() + ".-" + cadenaAMostrar;
    }
}
