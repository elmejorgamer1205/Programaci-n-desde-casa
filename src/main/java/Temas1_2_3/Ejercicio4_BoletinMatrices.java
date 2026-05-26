package Temas1_2_3;

public class Ejercicio4_BoletinMatrices {
    public static void imprimirInverso(int[][] matriz) {
        for (int i = matriz.length - 1; i >= 0; i--) {
            for (int j = matriz[i].length - 1; j >= 0; j--) {
                System.out.print(matriz[i][j] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matriz = {
                {1, 3, 5},
                {0, 2, 7}
        };

        System.out.print("La salida es: ");
        imprimirInverso(matriz);
    }
}
