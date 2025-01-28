package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_ALUMNO("Insertar alumno"),
    BUSCAR_ALUMNO("Buscar alumno"),
    BORRAR_ALUMNO("Borrar alumno"),
    MOSTRAR_ALUMNOS("Mostrar alumnos"),
    INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo"),
    BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo"),
    BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo"),
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos"),
    INSERTAR_ASIGNATURA("Insertar asignatura"),
    BUSCAR_ASIGNATURA("Buscar asignatura"),
    BORRAR_ASIGNATURA("Borrar asignatura"),
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas"),
    INSERTAR_MATRICULA("Insertar matrícula"),
    BUSCAR_MATRICULA("Buscar matrícula"),
    ANULAR_MATRICULA("Anular matrícula"),
    MOSTRAR_MATRICULAS("Mostrar matrículas"),
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matrículas de un Alumno"),
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matrículas de un Ciclo Formativo"),
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matrículas de este Curso Académico");

    // Atributo que contiene la cadena a mostrar asociada a cada opción.
    private String cadenaAMostrar;

    // Constructor de la enumeración Opcion que asocia una cadena a mostrar a cada opción.
    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Metodo para obtener el mensaje.
    public String getMensaje() {
        return cadenaAMostrar;
    }

    // Devuelve la representación en cadena de la opción, que incluye su índice en la enumeración
    @Override
    public String toString() {
        return ordinal() + ".-" + cadenaAMostrar;
    }
}
