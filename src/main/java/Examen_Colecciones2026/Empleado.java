package Examen_Colecciones2026;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Empleado {

    private String nombre;
    private String apellido;
    private String dni;
    Departamento departamento;
    private List<Nomina> nominas;

    public Empleado(String nombre, String apellido, String dni, Departamento departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.departamento = departamento;
        this.nominas = new ArrayList<>();
    }

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

    public YearMonth devolverFecha() {
        return nominas.getFirst().getMesNomina();
    }

    public Nomina devolverUltimaNomina() {
        return  nominas.getLast();
    }
}
