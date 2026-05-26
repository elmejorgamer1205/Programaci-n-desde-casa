package Temas1_2_3;

import io.MiEntradaSalida;

public class Ejercicio5_BoletinArrays {

    public static void generarYMostrarArray(int tamaño){

        int[] array = new int[tamaño];

        for (int i = 0; i < array.length; i++) {
            array[i]= MiEntradaSalida.generaAleatorioEntre(0, 1000, true);
            System.out.println(array[i]);
        }
    }

    static void main(String[] args) {
        generarYMostrarArray(5);
    }
}
