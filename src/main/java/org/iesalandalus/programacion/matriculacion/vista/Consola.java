package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.Modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.Modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Consola {
    private Consola() {}

    // Constructor.
    public static final CiclosFormativos ciclosFormativos = new CiclosFormativos(5);
    public static final Asignaturas asignaturas = new Asignaturas(5);
    public static final Alumnos alumnos = new Alumnos(5);
    public static final Matriculas matriculas = new Matriculas(5);

    // Muestra el menú de opciones en la consola.
    public static void mostrarMenu() {
        System.out.println("\n");
        System.out.println("GESTOR DE MATRICULAS IES AL-ÁNDALUS");
        System.out.println("---      Menú de opciones      ---");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    // Metodo para elegir una opcion.
    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.println("Elige opcion: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion >= Opcion.values().length);
        return Opcion.values()[opcion];
        }

    // Metodo leer alumno donde nos pedirá todos los datos del alumno.
    public static Alumno leerAlumno() {
        System.out.print("Nombre completo del alumno: ");
        String nombre = Entrada.cadena();
        System.out.print("DNI del alumno: ");
        String dni = Entrada.cadena();
        System.out.print("Correo del alumno: ");
        String correo = Entrada.cadena();
        System.out.print("Teléfono del alumno: ");
        String telefono = Entrada.cadena();
        LocalDate fechaNacimiento = leerFecha("Fecha de nacimiento (dd/MM/yyyy): ");
        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    // Metodo get para encontrar a un alumno por su dni.
    public static Alumno getAlumnoPorDni() {
        System.out.print("DNI del alumno: ");
        String dni = Entrada.cadena();
        try {
            return new Alumno("Alumno Ficticio", dni, "ficticio@gmail.com", "600123456", LocalDate.of(2000, 1, 1));
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se pudo crear el alumno. " + e.getMessage());
            return null;
        }
    }

    // Metodo para leer una fecha.
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.print(mensaje);
                String fechaTexto = Entrada.cadena();
                fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intenta nuevamente.");
            }
        }
        return fecha;
    }

    // Metodo para leer los grados.
    public static Grado leerGrado() {
        System.out.println("Grados que tiene disponibles:");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.ordinal() + " .- " + grado);
        }
        System.out.print("Introduce el número del grado interesado: ");
        int opcion = Entrada.entero();
        if (opcion < 0 || opcion >= Grado.values().length) {
            throw new IllegalArgumentException("ERROR: Opción no válida.");
        }
        return Grado.values()[opcion];
    }

    // Metodo para leer los ciclos formativos.
    public static CicloFormativo leerCicloFormativo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();

        System.out.print("Introduce el nombre del ciclo formativo: ");
        String nombre = Entrada.cadena();

        Grado grado = leerGrado();

        System.out.print("Introduce las horas del ciclo formativo: ");
        int horas = Entrada.entero();

        System.out.print("Introduce la familia profesional: ");
        String familiaProfesional = Entrada.cadena();

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    // Muestra una lista de ciclos formativos disponibles.
    public static void mostrarCiclosFormativos(CicloFormativo[] ciclosFormativos) {
        System.out.println("Lista de ciclos formativos disponibles:");
        if (ciclosFormativos.length == 0) {
            System.out.println("No hay ciclos formativos disponibles.");
        } else {
            for (CicloFormativo cicloFormativo : ciclosFormativos) {
                if (cicloFormativo != null) {
                    System.out.println(cicloFormativo);
                }
            }
        }
    }

    // Metodo get para encontrar un ciclo formativo por su codigo.
    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();

        if (codigo <= 0) {
            throw new IllegalArgumentException("ERROR: El código del ciclo formativo debe ser un número positivo.");
        }

        return new CicloFormativo(codigo, "Ficticia", Grado.GDCFGM, "Ficticio", 1000);
    }

    // Metodo para leer el curso que se elija dentro de la lista de cursos existentes.
    public static Curso leerCurso() {
        System.out.println("Cursos que hay disponibles:");

        for (Curso curso : Curso.values()) {
            System.out.println(curso);
        }

        Curso curso = null;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("Introduce el nombre del curso: ");
                String cursoTexto = Entrada.cadena().toUpperCase().trim();

                curso = Curso.valueOf(cursoTexto);
                opcionValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Curso no válido. Inténtalo de nuevo.");
            }
        }
        return curso;
    }

    // Metodo para leer la especialidad del profesorado.
    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Especialidades de profesorado disponibles:");

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad);
        }

        EspecialidadProfesorado especialidad = null;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("Introduce el nombre de la especialidad: ");
                String especialidadTexto = Entrada.cadena().toUpperCase().trim();

                especialidad = EspecialidadProfesorado.valueOf(especialidadTexto);
                opcionValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Especialidad no válida. Inténtalo de nuevo.");
            }
        } while (especialidad == null);
        return especialidad;
    }

    // Metodo leer asignatura donde nos pedira todos los datos de la asignatura.
    public static Asignatura leerAsignatura(CicloFormativo ciclo) {

        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        System.out.print("Introduce el nombre de la asignatura: ");
        String nombre = Entrada.cadena();

        System.out.print("Introduce las horas de la asignatura: ");
        int horas = Entrada.entero();

        Curso curso = leerCurso();

        System.out.print("Introduce las horas de desdoble: ");
        int horasDesdoble = Entrada.entero();

        EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();

        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidad, ciclo);
    }

    // Metodo get de asignaturas por código.
    public static Asignatura getAsignaturaPorCodigo() {
        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código de la asignatura no puede ser vacío.");
        }

        String nombre = "Asignatura Ficticia";
        int horas = 100;
        Curso curso = Curso.PRIMERO;
        int horasDesdoble = 6;
        EspecialidadProfesorado especialidad = EspecialidadProfesorado.FOL;
        CicloFormativo ciclo = new CicloFormativo(1011, "Familia Ficticia", Grado.GDCFGM, "Ciclo Ficticio", 2000);

        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidad, ciclo);
    }

    // Muestra en consola el listado de asignaturas disponibles.
    public static void mostrarAsignaturas(Asignatura[] asignaturas) {
        System.out.println("Listado de Asignaturas disponibles:");
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }
        }
    }

    // Permite al usuario elegir asignaturas para su matrícula y las retorna en un arreglo.
    public static Asignatura[] elegirAsignaturasMatricula(Asignatura[] asignaturas)
            throws OperationNotSupportedException {

        if (asignaturas == null || asignaturas.length == 0) {
            throw new IllegalArgumentException("ERROR: No hay asignaturas disponibles para seleccionar.");
        }

        Asignatura[] asignaturasMatricula = new Asignatura[asignaturas.length];
        int asignaturasSeleccionadas = 0; // Número de asignaturas añadidas al array
        int opcion = 0;

        do {
            mostrarAsignaturas(asignaturas); // Muestra las asignaturas disponibles

            // Obtener la asignatura por código
            System.out.print("Introduzca el código de la asignatura: ");
            String codigo = Entrada.cadena();
            Asignatura asignatura = null;

            // Validar si el código corresponde a una asignatura válida
            for (Asignatura a : asignaturas) {
                if (a != null && a.getCodigo().equals(codigo)) {
                    asignatura = a;
                    break;
                }
            }

            if (asignatura == null) {
                System.out.println("ERROR: La asignatura seleccionada no es válida.");
                continue; // Volver a solicitar la asignatura
            }

            // Verificar si ya está matriculada
            if (asignaturaYaMatriculada(asignaturasMatricula, asignatura)) {
                System.out.println("La asignatura ya está seleccionada.");
            } else {
                // Añadir asignatura al array
                asignaturasMatricula[asignaturasSeleccionadas++] = asignatura;
                System.out.println("Asignatura añadida correctamente.");
            }

            // Preguntar si desea añadir otra asignatura
            System.out.print("¿Desea añadir otra asignatura? (0 = No, 1 = Sí): ");
            opcion = Entrada.entero();
        } while (opcion == 1 && asignaturasSeleccionadas < asignaturas.length);

        // Crear un nuevo array con las asignaturas seleccionadas
        return Arrays.copyOf(asignaturasMatricula, asignaturasSeleccionadas);
    }

    // Metodo para comparar si la asignatura ya esta matriculada.
    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturas, Asignatura asignatura) {
        for (Asignatura a : asignaturas) {
            if (a != null && a.equals(asignatura)) {
                return true;
            }
        }
        return false;
    }

    // Metodo leer matricula donde nos pedira todos los datos de la matricula.
    public static Matricula leerMatricula(Alumno alumno, Asignatura[] asignaturas)
            throws OperationNotSupportedException {

        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        if (asignaturas == null || asignaturas.length == 0) {
            throw new NullPointerException("ERROR: Las asignaturas de una matrícula no pueden ser nulas.");
        }

        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Matricula matricula;

        System.out.println("Introduzca el Id de la Matrícula.");
        idMatricula = Entrada.entero();

        System.out.println("Introduzca el Curso Académico.");
        System.out.println("El curso academico tiene que tener el formato 24-25");
        cursoAcademico = Entrada.cadena();

        String mensaje = "Introduzca la Fecha de matriculación.";

        System.out.println("La fecha de matriculación como maximo puede ser de 15 días anterior al día actual");
        fechaMatriculacion = leerFecha(mensaje);

        matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas);

        return matricula;
    }

    // Metodo get del identificador de la matricula.
    public static Matricula getMatriculaPorIdentificador() {
        System.out.print("Introduce el identificador de la matrícula: ");
        int id = Entrada.entero();
        try {
            Alumno alumno = getAlumnoPorDni();
            return new Matricula(id, "24-25", LocalDate.now(), alumno, new Asignatura[0]);
        } catch (Exception e) {
            System.out.println("Error al obtener la matrícula: " + e.getMessage());
            return null;
        }
    }
}

