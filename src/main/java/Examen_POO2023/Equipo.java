package Examen_POO2023;

import java.util.Arrays; // Importamos esta librería por si usamos Arrays.toString() luego

public class Equipo {

    // --- ATRIBUTOS ---
    private String paisRepresentado; // Guarda el país (ej. "España")
    private String nombreEntrenador; // Guarda el nombre del míster
    private Jugador[] listaJugadores; // Declara un array que va a guardar OBJETOS de tipo Jugador
    private int contadorJugadores; // Una variable vital para saber cuántos jugadores hemos metido en el array y en qué posición toca meter el siguiente

    // --- CONSTRUCTOR ---
    public Equipo(String paisRepresentado, String nombreEntrenador) {
        this.paisRepresentado = paisRepresentado; // Asigna el país
        this.nombreEntrenador = nombreEntrenador; // Asigna el entrenador
        this.listaJugadores = new Jugador[2]; // Inicializa el array reservando memoria para exactamente 2 jugadores
        this.contadorJugadores = 0; // Al crear el equipo, aún no hay jugadores, así que el contador es 0
    }

    // --- MÉTODOS ---

    public boolean addJugador(Jugador jugador) {
        // Comprueba si el contador es menor que el tamaño máximo del array (2)
        if (contadorJugadores < listaJugadores.length) {
            // Si hay hueco, mete al jugador en la posición indicada por el contador (0 la primera vez, 1 la segunda)
            listaJugadores[contadorJugadores] = jugador;
            contadorJugadores++; // Suma 1 al contador porque ahora hay un jugador más
            return true; // Devuelve 'true' para avisar al menú de que ha habido éxito
        }
        return false; // Si el contador es 2 o más, el array está lleno y devuelve 'false'
    }

    public void eliminarJugador(String nombreJugador) {
        // Un bucle 'for' clásico que da tantas vueltas como jugadores haya registrados
        for (int i = 0; i < contadorJugadores; i++) {
            // Comprueba dos cosas: 1. Que la posición no esté vacía (!= null) y 2. Que el nombre coincida (ignorando mayúsculas)
            if (listaJugadores[i] != null && listaJugadores[i].getNombre().equalsIgnoreCase(nombreJugador)) {
                listaJugadores[i] = null; // Borra al jugador poniendo esa "casilla" a null

                // Este bucle anidado sirve para tapar el hueco. Mueve a los jugadores que estaban a la derecha, un paso a la izquierda
                for (int j = i; j < contadorJugadores - 1; j++) {
                    listaJugadores[j] = listaJugadores[j + 1];
                }
                listaJugadores[contadorJugadores - 1] = null; // Vacía la última casilla porque todos se han movido a la izquierda
                contadorJugadores--; // Resta 1 al total de jugadores
                System.out.println("Jugador eliminado con éxito.");
                return; // Corta la ejecución del metodo porque ya hemos terminado
            }
        }
        System.out.println("No se encontró al jugador en este equipo."); // Si el bucle termina y no encontró a nadie, muestra esto
    }

    public int getGolesTotales() {
        int golesEquipo = 0; // Variable temporal para ir acumulando la suma
        // Recorre los jugadores guardados
        for (int i = 0; i < contadorJugadores; i++) {
            if (listaJugadores[i] != null) { // Por seguridad, asegura que haya un jugador en esa posición
                golesEquipo += listaJugadores[i].getGolesTotales(); // Le pregunta al jugador cuántos goles lleva y se los suma al total
            }
        }
        return golesEquipo; // Devuelve la suma final
    }

    public Jugador obtenerJugadorMasGoleador() {
        if (contadorJugadores == 0) return null; // Si el equipo está vacío, no hay goleador, devuelve null

        Jugador mejor = listaJugadores[0]; // Asume temporalmente que el primer jugador (posición 0) es el mejor

        // Empieza a comparar desde el segundo jugador (posición 1)
        for (int i = 1; i < contadorJugadores; i++) {
            // Si el jugador actual tiene más goles que el que teníamos guardado en 'mejor'...
            if (listaJugadores[i] != null && listaJugadores[i].getGolesTotales() > mejor.getGolesTotales()) {
                mejor = listaJugadores[i]; // ...entonces este nuevo jugador pasa a ser el 'mejor'
            }
        }
        return mejor; // Devuelve el objeto Jugador que haya ganado la comparación
    }

    public Jugador buscarJugadorPorNombre(String nombre) {
        // Recorre todos los jugadores
        for (int i = 0; i < contadorJugadores; i++) {
            // Si la posición no es nula y el nombre coincide con el buscado...
            if (listaJugadores[i] != null && listaJugadores[i].getNombre().equalsIgnoreCase(nombre)) {
                return listaJugadores[i]; // ... devuelve ese jugador directamente, terminando el método
            }
        }
        return null; // Si acaba el bucle y no lo encuentra, devuelve nulo
    }

    // Getters simples
    public String getPaisRepresentado() {
        return paisRepresentado;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "pais='" + paisRepresentado + '\'' +
                ", entrenador='" + nombreEntrenador + '\'' +
                ", jugadores=" + Arrays.toString(listaJugadores) + // Arrays.toString() formatea el array para que se vea bonito al imprimirlo
                '}';
    }
}