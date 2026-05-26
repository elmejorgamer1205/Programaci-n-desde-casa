package Temas1_2_3;

import io.MiEntradaSalida;

import java.util.Arrays;

public class Ejercicio3_BoletinArrays {
    static void main(String[] args) {

        /**
         * Creamos una variable para guardar el número posiciones del array que quiere el usuario.
         * Ese número se le pide por consola
         **/
        int cantidaPosicionesArray = MiEntradaSalida.leerEntero("¿Cuántas posiciones quieres que tenga el array?");

        int[] array = new int[cantidaPosicionesArray];

        for (int i = 0; i < array.length; i++) {
            array[i] = MiEntradaSalida.leerEntero("Introduzca el número " + (i + 1) + ": ");
        }

        int numeroMenor = Integer.MAX_VALUE;
        int numeroMayor = Integer.MIN_VALUE;
        int sumaDeNumeros = 0;
        int cantidadDeVecesMayor = 0;
        int cantidadDeVecesMenor = 0;

        for (int i = 0; i < array.length; i++) {
            if(array[i] < numeroMenor){
                numeroMenor = array[i];
            }
            if(array[i] > numeroMayor){
                numeroMayor = array[i];
            }
            sumaDeNumeros += array[i];
        }

        for (int n: array){
            if(n == numeroMayor){
                cantidadDeVecesMayor++;
            }
            if (n == numeroMenor){
                cantidadDeVecesMenor++;
            }
        }

        System.out.println("El número más pequeño que has puesto es: " + numeroMenor + " y aparece: " + cantidadDeVecesMenor);
        System.out.println("El número más grande que has puesto es: " + numeroMayor + " y aparece: " + cantidadDeVecesMayor);
        System.out.println("La media de los números es: " + sumaDeNumeros/array.length);
    }
}
