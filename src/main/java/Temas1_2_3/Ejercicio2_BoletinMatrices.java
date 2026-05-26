package Temas1_2_3;

public class Ejercicio2_BoletinMatrices {

    public static int sumaPosicionesMatriz() {
        int[][] matriz = {{1, 2, 3}, {4, 5, 6}};
        int suma = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                suma += (i + j);
            }
        }
        return suma;
    }

    public static void main(String[] args) {
        System.out.println(sumaPosicionesMatriz());
    }
}
