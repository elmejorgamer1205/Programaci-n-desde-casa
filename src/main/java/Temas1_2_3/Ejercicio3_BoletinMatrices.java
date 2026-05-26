package Temas1_2_3;

public class Ejercicio3_BoletinMatrices {

    public static void impresionMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
        }
    }

    static void main(String[] args) {
        int[][] matriz = {{1, 3, 5}, {0, 2, 7}};
        System.out.println("Salida: ");
        impresionMatriz(matriz);
    }
}
