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
import java.util.ArrayList;
import java.util.Arrays;

public class Consola {
    private Consola() {}

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
        String nombre;
        String telefono;
        String correo;
        String dni;
        LocalDate fechaNacimiento;

        System.out.println("Introduce los datos del alumno: ");
        do {
            System.out.println("\nIntroduce el nombre del alumno: ");
            nombre = Entrada.cadena();
        } while (nombre.isBlank());

        do {
            System.out.println("\nIntroduce el telefono del alumno: ");
            telefono = Entrada.cadena();
        } while (telefono.isBlank());

        do {
            System.out.println("\nIntroduce el correo del alumno: ");
            correo = Entrada.cadena();
        } while (correo.isBlank());

        do {
            System.out.println("\nIntroduce el DNI del alumno: ");
            dni = Entrada.cadena();
        } while (dni.isBlank());

        do {
            String mensaje = "Introduce la fecha de nacimiento del alumno:";
            fechaNacimiento = leerFecha(mensaje);
        } while (fechaNacimiento == null);

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);

    }

    // Metodo get para encontrar a un alumno por su dni.
    public static Alumno getAlumnoPorDni() {
        String nombre = "ficticio";
        String telefono = "111111111";
        String correo = "ficticio@inventado.es";
        String dni = "12345678Z";
        LocalDate fechaNacimiento = LocalDate.of(1991, 12, 12);

        do {
            System.out.println("\nIntroduce el DNI del alumno: ");
            dni = Entrada.cadena();
        } while (dni.isBlank());

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    // Metodo para leer una fecha.
    public static LocalDate leerFecha(String mensaje) {
        do {
            try {
                System.out.printf(mensaje, Alumno.FORMATO_FECHA);
                LocalDate fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                return fecha;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: El formato de la fecha no es correcto.");
            }
        } while (true);
    }

    // Metodo para leer los grados.
    public static Grado leerGrado() {
        int seleccion;
        do {
            System.out.println("\nIntroduce el codigo del grado: ");
            for (Grado grado : Grado.values()) {
                System.out.println(grado.imprimir());
            }
            seleccion = Entrada.entero();
        } while (seleccion < 0 || seleccion >= Grado.values().length);
        return Grado.values()[seleccion];
    }

    // Metodo para leer los ciclos formativos.
    public static CicloFormativo leerCicloFormativo() {
        int codigo;
        String familiaProfesional;
        Grado grado;
        String nombre;
        int horas;
        do {
            System.out.println("\nIntroduce el codigo del ciclo formativo: ");
            codigo = Entrada.entero();
        } while (codigo < 0);

        do {
            System.out.println("\nIntroduce la familia profesional del ciclo formativo: ");
            familiaProfesional = Entrada.cadena();
        } while (familiaProfesional.isBlank());

        grado = leerGrado();

        do {
            System.out.println("\nIntroduce el nombre del ciclo formativo: ");
            nombre = Entrada.cadena();
        } while (nombre.isBlank());

        do {
            System.out.println("\nIntroduce las horas del ciclo formativo: ");
            System.out.println("El maximo numero de horas es " + CicloFormativo.MAXIMO_NUMERO_HORAS);
            horas = Entrada.entero();
        } while (horas < 0);
        if (horas > CicloFormativo.MAXIMO_NUMERO_HORAS) {
            throw new IllegalArgumentException(
                    "ERROR: El numero de horas no puede ser mayor que " + CicloFormativo.MAXIMO_NUMERO_HORAS);
        }

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
        int codigo;
        String familiaProfesional = "ficticia";
        Grado grado = Grado.GDCFGB;
        String nombre = "ficticio";
        int horas = 1;
        do {
            System.out.println("\nIntroduce el codigo del ciclo formativo: ");
            codigo = Entrada.entero();
        } while (codigo < 0);
        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    // Metodo para leer el curso que se elija dentro de la lista de cursos existentes.
    public static Curso leerCurso() {
        int seleccion;
        do {
            System.out.println("\nIntroduce el curso de la asignatura: ");
            for (Curso curso : Curso.values()) {
                System.out.println(curso.imprimir());
            }
            seleccion = Entrada.entero();
        } while (seleccion < 0 || seleccion >= Curso.values().length);
        return Curso.values()[seleccion];
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
    public static Asignatura leerAsignatura(CicloFormativo cicloFormativo) {
        String codigo;
        String nombre;
        int horasAnuales;
        Curso curso;
        int horasDesdoble;
        EspecialidadProfesorado especialidadProfesorado;
        do {
            System.out.println("\nIntroduce el codigo de la asignatura: ");
            System.out.println("El codigo debe tener un formato de 4 digitos.");
            codigo = Entrada.cadena();
        } while (codigo.isBlank());

        do {
            System.out.println("\nIntroduce el nombre de la asignatura: ");
            nombre = Entrada.cadena();
        } while (nombre.isBlank());

        do {
            System.out.println("\nIntroduce las horas anuales de la asignatura: ");
            System.out.println("El numero de horas tiene que ser entre 0 y 300.");
            horasAnuales = Entrada.entero();
        } while (horasAnuales < 0);

        curso = leerCurso();

        do {
            System.out.println("\nIntroduce las horas desdoble de la asignatura: ");
            System.out.println("El numero de horas de desdoble tiene que ser entre 0 y 6");
            horasDesdoble = Entrada.entero();
        } while (horasDesdoble < 0);

        especialidadProfesorado = leerEspecialidadProfesorado();


        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado,
                cicloFormativo);
    }

    // Metodo get de asignaturas por código.
    public static Asignatura getAsignaturaPorCodigo() {
        String codigo;
        String nombre = "Asignaturaficticia";
        int horasAnuales = 13;
        Curso curso = Curso.PRIMERO;
        int horasDesdoble = 5;
        EspecialidadProfesorado especialidadProfesorado = EspecialidadProfesorado.SISTEMAS;
        CicloFormativo cicloFormativo = new CicloFormativo(9999, "ficticio", Grado.GDCFGB, "ficticio", 200);
        Asignatura asignatura;
        do {
            System.out.println("\nIntroduce el codigo de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo == null || codigo.isBlank());
        asignatura = new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado,
                cicloFormativo);

        return new Asignatura(asignatura);
    }

    // Muestra en consola el listado de asignaturas disponibles.
    public static void mostrarAsignaturas(ArrayList<Asignatura> asignaturas) {
        System.out.println("Listado de Asignaturas disponibles:");
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }
        }
    }

    // Permite al usuario elegir asignaturas para su matrícula y las retorna en un arreglo.
    public static ArrayList<Asignatura> elegirAsignaturasMatricula(ArrayList<Asignatura> asignaturas)
            throws OperationNotSupportedException {
        if (asignaturas == null || asignaturas.size() == 0) {
            throw new IllegalArgumentException("ERROR: No hay asignaturas disponibles para seleccionar.");
        }

        ArrayList<Asignatura> asignaturasMatricula = new ArrayList<>();
        int opcion = 0;

        do {
            mostrarAsignaturas(asignaturas);

            System.out.print("Introduzca el código de la asignatura: ");
            String codigo = Entrada.cadena();
            Asignatura asignatura = null;

            for (Asignatura a : asignaturas) {
                if (a != null && a.getCodigo().equals(codigo)) {
                    asignatura = a;
                    break;
                }
            }

            if (asignatura == null) {
                System.out.println("ERROR: La asignatura seleccionada no es válida.");
                continue;
            }

            if (asignaturaYaMatriculada(asignaturasMatricula, asignatura)) {
                System.out.println("La asignatura ya está seleccionada.");
            } else {
                // Añadir asignatura al array
                asignaturasMatricula.add(asignatura);
                System.out.println("Asignatura añadida correctamente.");
            }

            System.out.print("¿Desea añadir otra asignatura? (0 = No, 1 = Sí): ");
            opcion = Entrada.entero();
        } while (opcion == 1 && asignaturasMatricula.size() < asignaturas.size());

        return asignaturasMatricula;
    }

    // Metodo para comparar si la asignatura ya esta matriculada.
    static boolean asignaturaYaMatriculada(ArrayList<Asignatura> asignaturasMatricula, Asignatura asignatura) {
        if (asignaturasMatricula != null) {
            for (int i = 0; i < asignaturasMatricula.size(); i++) {
                if (asignaturasMatricula.get(i)!= null && asignaturasMatricula.get(i).equals(asignatura)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Metodo leer matricula donde nos pedira todos los datos de la matricula.
    public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> asignaturas)
            throws OperationNotSupportedException {

        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        if (asignaturas == null || asignaturas.size() == 0) {
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
    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico = "24-25";
        LocalDate fechaMatriculacion = LocalDate.now();
        Alumno alumno = new Alumno("ficticio apellidoficticio", "12345678Z", "ficticio@ficticio.es", "444444444",
                LocalDate.of(2000, 8, 21));

        System.out.println("Introduzca el id de la Matrícula.");
        idMatricula = Entrada.entero();

        Matricula matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, new Asignaturas().get());

        return matricula;
    }
}

