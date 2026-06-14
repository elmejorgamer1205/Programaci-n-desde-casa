package Examen_Colecciones2026;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un empleado dentro de la empresa.
 * Esta clase almacena los datos personales del trabajador, el departamento al que pertenece
 * y el histórico de sus nóminas mensuales.
 * * @author [Tu Nombre/Alumno]
 * @version 1.0
 */
public class Empleado {

    // --- Atributos básicos del empleado ---
    private String nombre;
    private String apellido;
    private String dni;

    /* * El departamento se deja con visibilidad 'package-private' (sin modificador)
     * intencionadamente o por omisión, lo que permite que clases del mismo
     * paquete accedan a él directamente si fuera necesario.
     */
    Departamento departamento;
    private List<Nomina> nominas;

    /**
     * Constructor para inicializar un nuevo Empleado.
     * La lista de nóminas se inicializa vacía por defecto.
     *
     * @param nombre Nombre del empleado.
     * @param apellido Apellido del empleado.
     * @param dni Documento Nacional de Identidad.
     * @param departamento Departamento asignado.
     */
    public Empleado(String nombre, String apellido, String dni, Departamento departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.departamento = departamento;
        this.nominas = new ArrayList<>(); // Inicialización segura para evitar NullPointerException
    }

    // Getters y Setters estándar
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Nomina> getNominas() {
        return nominas;
    }

    /**
     * Recupera el mes y año en el que el empleado empezó a trabajar en la empresa,
     * basándose en su primera nómina.
     * * @return YearMonth correspondiente a la primera nómina.
     */
    public YearMonth devolverFecha() {
        // Se asume que la primera nómina insertada corresponde a su inicio de contrato
        return nominas.getFirst().getMesNomina();
    }

    /**
     * Devuelve la nómina más reciente (la última generada) del empleado.
     * * @return Objeto Nomina más actual.
     */
    public Nomina devolverUltimaNomina() {
        return nominas.getLast();
    }
}