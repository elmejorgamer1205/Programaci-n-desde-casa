package Temas1_2_3;

public class Ejercicio1_BoletinArrays {
    static void main() {

        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int total = 0;

        for (int i = 0; i < array.length; i++) {
            if (i%2 == 0){
              total += array[i];
            }
        }
        System.out.println(total);
    }
}
