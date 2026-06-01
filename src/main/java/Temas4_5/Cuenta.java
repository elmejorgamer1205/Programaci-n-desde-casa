package Temas4_5;

import Exceptions.CuentaExceptions;

public class Cuenta {

    public int numReintegros;
    public int numIngresos;
    public int saldoInical;
    public int saldoActual;
    public int retirarSaldo;

    public Cuenta() {
        this.numReintegros = numReintegros;
        this.numIngresos = numIngresos;
        this.saldoInical = saldoInical;
        this.saldoActual = saldoActual;
        this.retirarSaldo = retirarSaldo;
    }

    public int getNumReintegros() {
        return numReintegros;
    }

    public int getNumIngresos() {
        return numIngresos;
    }

    public int getSaldoActual() {
        return saldoActual;
    }

    public void ingresarDinero(int cantidad) throws CuentaExceptions {
        if (cantidad < 0 || cantidad > 1000) {
            throw new CuentaExceptions("Error, no puede ingresar dinero negativo o mayor a 2500€");
        }
        saldoActual += cantidad;
        numIngresos++;
    }

    public void retirarDinero(int cantidad) throws CuentaExceptions{
        if (cantidad > 0 || cantidad < 2500 || cantidad <= saldoActual) {
            throw new CuentaExceptions("Error, no puedes retirar dinero porque no tienes suficiente saldo");
        }
        saldoActual -= cantidad;
        retirarSaldo += cantidad;
        numReintegros++;
    }
}
