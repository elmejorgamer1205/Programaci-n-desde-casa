package Examen_Matrices2026;

public class principal {

    public static void main(String[] args) {

        System.out.println("Bienvenido al juego de las parejas. Destapa todas las parejas en menos de 6 intentos y gana!");

        int intentosJugador = 6;
        int N = 4;

        // Matriz de soluciones definida "a mano" al descartar la parte opcional
        int[][] tableroDescubierto = {
                {3, 1, 4, 2},
                {2, 4, 1, 3},
                {1, 4, 2, 3},
                {4, 3, 2, 1}
        };

        boolean[][] tableroOculto = new boolean[N][N]; // Por defecto se inicializa en "false" (tódo oculto)

        boolean juegoTerminado = false;

        // Bucle principal del juego
        while (intentosJugador > 0 && !juegoTerminado) {

            System.out.println("\nTe quedan " + intentosJugador + " intentos.");

            // --- Paso 1: Mostrar el tablero actual ---
            mostrarTablero(tableroDescubierto, tableroOculto);

            // --- Paso 2: Pide la primera coordenada y la valida ---
            int cordX1, cordY1;
            while (true) {
                cordX1 = MiEntradaSalida2.solicitarEnteroEnRango("Introduce la primera fila (del 1 al " + N + "): ", 1, N) - 1;
                cordY1 = MiEntradaSalida2.solicitarEnteroEnRango("Introduce la primera columna (del 1 al " + N + "): ", 1, N) - 1;

                if (tableroOculto[cordX1][cordY1]) {
                    System.out.println("Esa casilla ya está destapada. Por favor, elige otra.");
                } else {
                    break; // Coordenada válida
                }
            }

            // --- Paso 3: Revelar temporalmente y mostrar tablero ---
            tableroOculto[cordX1][cordY1] = true;
            mostrarTablero(tableroDescubierto, tableroOculto);

            // --- Paso 4: Pide la segunda coordenada y la valida ---
            int cordX2, cordY2;
            while (true) {
                cordX2 = MiEntradaSalida2.solicitarEnteroEnRango("Introduce la segunda fila (del 1 al " + N + "): ", 1, N) - 1;
                cordY2 = MiEntradaSalida2.solicitarEnteroEnRango("Introduce la segunda columna (del 1 al " + N + "): ", 1, N) - 1;

                if (cordX1 == cordX2 && cordY1 == cordY2) {
                    System.out.println("Has escogido las mismas casillas que antes, vuelve a introducir otra vez la coordenada.");
                } else if (tableroOculto[cordX2][cordY2]) {
                    System.out.println("Esa casilla ya está destapada. Por favor, elige otra.");
                } else {
                    break; // Coordenada válida
                }
            }

            // --- Paso 5: Revelar temporalmente la segunda y mostrar tablero ---
            tableroOculto[cordX2][cordY2] = true;
            mostrarTablero(tableroDescubierto, tableroOculto);

            // --- Paso 6: Comprueba si es acierto o fallo ---
            if (tableroDescubierto[cordX1][cordY1] == tableroDescubierto[cordX2][cordY2]) {
                System.out.println("¡Bien, has encontrado una pareja!");

                // Comprobamos si el jugador ha ganado
                if (comprobarVictoria(tableroOculto)) {
                    System.out.println("\n¡Felicidades! Has destapado todas las parejas y has ganado el juego.");
                    juegoTerminado = true;
                }
            } else {
                System.out.println("Vaya, no hubo suerte esta vez");
                // Es un fallo, volvemos a ocultar las casillas y restamos un intento
                tableroOculto[cordX1][cordY1] = false;
                tableroOculto[cordX2][cordY2] = false;
                intentosJugador--;
            }
        }

        // Condición de derrota
        if (intentosJugador == 0) {
            System.out.println("\nHas agotado todos tus intentos. ¡Fin del juego!");
            System.out.println("Aquí tienes la solución:");
            mostrarSolucion(tableroDescubierto);
        }
    }

    /**
     * Muestra el tablero por consola. Si la posición en la matriz visible es false,
     * imprime un '?', de lo contrario imprime el número oculto.
     */
    public static void mostrarTablero(int[][] matrizSolucion, boolean[][] matrizVisible) {
        for (int i = 0; i < matrizVisible.length; i++) {
            for (int j = 0; j < matrizVisible[i].length; j++) {
                if (!matrizVisible[i][j]) {
                    System.out.print("? ");
                } else {
                    System.out.print(matrizSolucion[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Muestra la matriz completa destapada (se utiliza al perder el juego).
     */
    public static void mostrarSolucion(int[][] matrizSolucion) {
        for (int i = 0; i < matrizSolucion.length; i++) {
            for (int j = 0; j < matrizSolucion[i].length; j++) {
                System.out.print(matrizSolucion[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Comprueba si todas las casillas de la matriz booleana están a true.
     * Retorna true si el jugador ha destapado todas las parejas.
     */
    public static boolean comprobarVictoria(boolean[][] matrizVisible) {
        for (int i = 0; i < matrizVisible.length; i++) {
            for (int j = 0; j < matrizVisible[i].length; j++) {
                if (!matrizVisible[i][j]) {
                    return false; // Si hay al menos una oculta, no ha ganado
                }
            }
        }
        return true;
    }
}