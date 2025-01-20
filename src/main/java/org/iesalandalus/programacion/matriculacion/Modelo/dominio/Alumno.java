package org.iesalandalus.programacion.matriculacion.Modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alumno {

    private static final String ER_TELEFONO = "^\\d{9}";
    private static final String ER_CORREO = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String ER_DNI = "[0-9]{8}[A-Z]";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String ER_NIA = "[a-zéáíóú]{4}[0-9]{3}";
    private static final int MIN_EDAD_ALUMNADO = 16;

    // Atributos.
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;


    // Constructor con parámetros.
    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();
    }

    // Constructor copia.
    public Alumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        this.nombre = alumno.nombre;
        this.dni = alumno.dni;
        this.correo = alumno.correo;
        this.telefono = alumno.telefono;
        this.fechaNacimiento = alumno.fechaNacimiento;
        this.nia = alumno.nia;
    }

    // Todos los métodos de acceso y modificación.

    // Nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        String nombreFormateado = nombre.trim().replaceAll("\\s+", " ");
        if (nombreFormateado.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }
        this.nombre = formatearNombre(nombreFormateado);
    }

    public String getIniciales() {
        String[] tokens = this.nombre.split(" " );
        String iniciales = "";
        for (int i = 0; i < tokens.length; i++) {
            iniciales = iniciales + tokens [i].substring(0, 1);
        }
        return iniciales;
    }

    private String formatearNombre(String nombre) {
        StringBuilder formateado = new StringBuilder();
        for (String palabra : nombre.split(" ")) {
            formateado.append(palabra.isEmpty() ? "" : palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase()).append(" ");
        }
        return formateado.toString().trim();
    }

    // DNI.
    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        }
        // Metodo para comprobar el formato del DNI.

        if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni;
    }

    private boolean comprobarLetraDni(String dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";  // Usamos un String en lugar de un array
        int numero = Integer.parseInt(dni.substring(0, 8));
        String letra =String.valueOf(letras.charAt(numero % 23));
        return letra.equals(dni.substring(8));
    }

    // Correo.
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        if (correo.isBlank() || correo.isEmpty()) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        this.correo = correo;
    }

    // Telefono.
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    // Fecha de nacimiento.
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }
        LocalDate fechaLimite = LocalDate.now().minusYears(MIN_EDAD_ALUMNADO);
        if (fechaNacimiento.compareTo(fechaLimite) > 0) {
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    // NIA.
    public String getNia() {
        return nia;
    }

    private void setNia() {
        String nombreBase = nombre.toLowerCase().substring(0, 4);
        String dniBase = dni.substring(5, 8);
        this.setNia(nombreBase + dniBase);
    }

    private void setNia(String nia) {
    this.nia = nia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Alumno a= (Alumno) o;
        return this.dni.toString()==a.getDni().toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ER_TELEFONO, ER_CORREO, ER_DNI, FORMATO_FECHA, ER_NIA, MIN_EDAD_ALUMNADO, nombre, telefono, correo, dni, fechaNacimiento, nia);
    }

    // Metodo imprimir los datos.
    public String imprimir() {
        return "Alumno=" +
                "nombre=" + getNombre() + " " + "(" + getIniciales() + ")" +
                ", DNI=" + getDni() +
                ", correo=" + getCorreo() +
                ", teléfono=" + getTelefono() +
                ", fechaNacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) +
                ", NIA=" + nia;
    }

    // Metodo toString.
    @Override
    public String toString() {
        return "Número de Identificación del Alumnado " +
                "(NIA)=" + getNia() + " nombre=" + getNombre() + " (" + getIniciales() + ")" +
                ", DNI=" + getDni() + ", correo=" + getCorreo() + ", teléfono=" + getTelefono() +
                ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
    }
}


