package Temas1_2_3;

public class Ejercicio1_BoletinMatrices {

    public static boolean comprobarNegativo(){
        int[][] matriz = {{2,3},
                         {-2,3}};

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] < 0){
                    return true;
                }
            }
        }
        return false;
    }

    static void main(String[] args) {
        System.out.println(comprobarNegativo());
    }
}
