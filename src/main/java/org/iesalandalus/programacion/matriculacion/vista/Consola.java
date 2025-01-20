package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.Modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    // Constructor.
    private Consola() {
        throw new IllegalStateException("No se puede instanciar la clase de utilidades Consola.");
    }

    // Metodo para mostrar el menu.
    public static void mostrarMenu() {
        System.out.println("-----Menú de opciones-----:");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    // Metodo para elegir una opcion.
    public static Opcion elegirOpcion() {
        int opcionElegida = -1;
        do {
            try {
                mostrarMenu();
                System.out.print("Introduce el número de la opción: ");
                opcionElegida = Entrada.entero();
                if (opcionElegida < 0 || opcionElegida >= Opcion.values().length) {
                    System.out.println("ERROR: Debes elegir un número válido entre 0 y " + (Opcion.values().length - 1) + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Debes introducir un número entero.");
            }
        } while (opcionElegida < 0 || opcionElegida >= Opcion.values().length);
        return Opcion.values()[opcionElegida];
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

    // Metodo para mostrar los ciclos formativos.
    public static void mostrarCiclosFormativos(CicloFormativo[] ciclosFormativos) {
        if (ciclosFormativos == null || ciclosFormativos.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            System.out.println("Lista de ciclos formativos registrados:");
            for (int i = 0; i < ciclosFormativos.length; i++) {
                System.out.println((i + 1) + ".- " + ciclosFormativos[i]);
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
        int horasDesdoble = 9;
        EspecialidadProfesorado especialidad = EspecialidadProfesorado.FOL;
        CicloFormativo ciclo = new CicloFormativo(101, "Familia Ficticia", Grado.GDCFGM, "Ciclo Ficticio", 2000);

        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidad, ciclo);
    }

    // Metodo para que se muestren las asignaturas.
    public static void mostrarAsignaturas(Asignatura[] asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }
        }
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
    public static Matricula leerMatricula(Alumno alumno, Asignatura[] asignaturas) throws OperationNotSupportedException{

        System.out.print("Introduce el identificador de la matrícula: ");
        int id = Entrada.entero();
        System.out.print("Introduce el curso académico: ");
        String cursoAcademico = Entrada.cadena();
        LocalDate fechaMatriculacion = leerFecha("Introduce la fecha de matriculación (dd/MM/yyyy): ");

        Asignatura[] asignaturasSeleccionadas = new Asignatura[5];
        int contador = 0;

        while (contador < 5) {
            mostrarAsignaturas(asignaturas);
            System.out.print("Selecciona el código de una asignatura o escribe 'terminar': ");
            String codigo = Entrada.cadena();

            if (codigo.equalsIgnoreCase("terminar")) break;

            Asignatura asignaturaSeleccionada = null;
            for (Asignatura asignatura : asignaturas) {
                if (asignatura != null && asignatura.getCodigo().equals(codigo)) {
                    asignaturaSeleccionada = asignatura;
                    break;
                }
            }

            if (asignaturaSeleccionada != null) {
                if (!asignaturaYaMatriculada(asignaturasSeleccionadas, asignaturaSeleccionada)) {
                    asignaturasSeleccionadas[contador++] = asignaturaSeleccionada;
                } else {
                    System.out.println("La asignatura ya está matriculada.");
                }
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        }

        if (contador == 0) {
            System.out.println("Debe seleccionar al menos una asignatura.");
            return null;
        }

        try {
            return new Matricula(id, cursoAcademico, fechaMatriculacion, alumno, asignaturasSeleccionadas);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear la matrícula: " + e.getMessage());
            return null;
        }
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

