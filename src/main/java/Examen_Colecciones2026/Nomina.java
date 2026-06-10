package Examen_Colecciones2026;

import java.time.YearMonth;

public class Nomina {
    private YearMonth mesNomina;
    private double salarioBase;
    private double retencion;

    public Nomina(YearMonth mesNomina, double salarioBase, double retencion) {
        this.mesNomina = mesNomina;
        this.salarioBase = salarioBase;
        this.retencion = retencion;
    }

    public YearMonth getMesNomina() {
        return mesNomina;
    }

    public void setMesNomina(YearMonth mesNomina) {
        this.mesNomina = mesNomina;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getRetencion() {
        return retencion;
    }

    public void setRetencion(double retencion) {
        this.retencion = retencion;
    }
}
