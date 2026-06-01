package Temas4_5;

import Exceptions.CuentaExceptions;
import io.MiEntradaSalida;

public class MainEjercicio2_Boletin4_1 {
    static void main(String[] args) {
        Cuenta c = new Cuenta();

        System.out.println("\nBienvenido a C.S.S Bank's, nos alegra verle por aquí\n");
        boolean opciones = true;

        while (opciones) {

            String comando = MiEntradaSalida.leerLinea("¿Qué desea realizar?: ");

            switch (comando.toLowerCase()) {

                case "reintegro de dinero":
                    try {
                        int cantidad1 = MiEntradaSalida.leerEntero("\n ¿Cuánto quiere reintegrar?");
                        c.retirarDinero(cantidad1);
                        System.out.println("¡¡¡ Reintegro exitoso ");
                    }catch (CuentaExceptions e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "ingresar dinero":
                    try {
                        int cantidad2 = MiEntradaSalida.leerEntero("\n ¿Cuánto quieres ingresar?");
                        c.ingresarDinero(cantidad2);
                        System.out.println("\n ¡¡¡Ingreso exitoso!!!");
                    }catch (CuentaExceptions e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "mirar cuenta":
                    System.out.println("\nSu cuenta actualmente tiene: " + c.getSaldoActual() + "€\n");
                    break;
                case "mirar número de reintegros":
                    System.out.println("Ha realizado "+ c.getNumReintegros() + " reintegro/s realizados ");
                    break;
                case "mirar número de ingresos":
                    System.out.println("Ha realizado "+ c.getNumIngresos() + " ingreso/s realizados");
                    break;
                case "ayuda":
                    ayuda();
                    break;
                default:
                    ayuda();
                    break;
                case "salir":
                    opciones = false;
                    System.out.println("\n!!!Vuelva pronto!!!");
                    break;
            }
        }
    }

    static void ayuda() {
        System.out.print("\n====================Opciones====================\n");
        System.out.print("> reintegro de dinero\n");
        System.out.print("> ingresar dinero\n");
        System.out.print("> mirar cuenta\n");
        System.out.print("> mirar número de reintegros\n");
        System.out.print("> mirar número de ingresos\n");
        System.out.print("> salir\n");
        System.out.print("=============================================\n \n");
    }
}
