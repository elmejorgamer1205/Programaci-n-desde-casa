package Examen_Colecciones2026;

import java.time.YearMonth;

/**
 * Entidad que modela la nómina mensual de un empleado.
 * Contiene la información financiera bruta y la retención aplicada para
 * poder calcular el salario neto posteriormente.
 */
public class Nomina {

    // Mes y año al que corresponde el pago de esta nómina
    private YearMonth mesNomina;

    /*
     * El salario base se considera el salario bruto antes de impuestos.
     * La retención se almacena como un valor porcentual (por ejemplo, 15.5 significa 15.5%).
     * Para calcular el salario neto, se aplicará: salarioBase - (salarioBase * retencion / 100)
     */
    private double salarioBase;
    private double retencion;

    /**
     * Constructor principal de la nómina.
     * * @param mesNomina Fecha (mes y año) de la nómina.
     * @param salarioBase Salario bruto en euros.
     * @param retencion Porcentaje de retención a aplicar (IRPF, etc.).
     */
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