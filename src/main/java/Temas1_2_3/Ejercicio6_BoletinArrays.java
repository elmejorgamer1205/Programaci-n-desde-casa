package Temas1_2_3;

import io.MiEntradaSalida;

import java.util.Arrays;

public class Ejercicio6_BoletinArrays {
    static void main(String[] args) {

        int tamanoArray = MiEntradaSalida.leerEntero("¿Qué tamaño tenga de longitud el array: ");
        int[] array = new int[tamanoArray];

        for (int i = 0; i < array.length; i++) {
            int numArray = MiEntradaSalida.leerEntero("Diga su/s número/s: ");
            array[i] = numArray;
        }
        System.out.println(Arrays.toString(array));

        int numABuscar = MiEntradaSalida.leerEntero("¿Qué numero quieres buscar?: ");
        boolean encontrado = false;

        for (int i = 0; i < array.length; i++) {
            if (numABuscar == array[i]) {
                encontrado = true;
            }
        }if (encontrado) {
            System.out.println("¡El número " + numABuscar + " está en el array!");
        } else {
            System.out.println("El número " + numABuscar + " no se encuentra en el array.");
        }
    }
}
