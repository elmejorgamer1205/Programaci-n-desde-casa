package Temas1_2_3;

public class Ejercicio2_BoletinArrays {

    static void main() {

        int[] array = {-2, 3, -1, 1, 4, -5};
        int totalNegativos = 0;

        for (int i = 0; i < array.length; i++) {
            if(array[i] < 0){
                totalNegativos++;
            }
        }
        System.out.println(totalNegativos);
    }
}
