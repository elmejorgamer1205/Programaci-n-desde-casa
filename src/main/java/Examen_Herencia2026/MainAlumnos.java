package Examen_Herencia2026;

import java.util.Random;

/**
 * Clase principal que orquesta el flujo de la fiesta de Lolo.
 * Gestiona las rondas, los eventos aleatorios y las condiciones de derrota globales.
 */
public class MainAlumnos {
    // Constante para definir el umbral de cancelación de la fiesta
    private static final int ROPA_MAX = 4;

    public static void main(String[] args) {
        System.out.println("--- ¡COMIENZA EL CUMPLE DE LOLO! ---");

        // TODO 1: Crea un Array de Invitados de tamaño 10.
        Invitado[] invitados = new Invitado[10];

        /*
         * TODO 2: Rellena las primeras posiciones.
         * Utilizamos una variable 'pos' como contador para ir insertando ordenadamente
         * en el array y evitar hardcodear los índices.
         */
        int pos = 0;
        invitados[pos++] = new Familiar("Mamá");
        invitados[pos++] = new Familiar("Su primo Juaqui");
        invitados[pos++] = new Familiar("La tita Antonia");

        invitados[pos++] = new Colega("Er kinki");
        invitados[pos++] = new Colega("Er cabesa");
        invitados[pos++] = new Colega("Er flequi");

        invitados[pos++] = new Gorron("Er melodeja");

        // Variables de control de flujo
        int ronda = 1;
        boolean fiestaSigue = true;
        boolean yaSeAbrieronRegalos = false;
        int ropaRecibida = 0;

        // Bucle principal de la fiesta (máximo 10 rondas según requisitos)
        while (fiestaSigue && ronda <= 10) {
            System.out.println("\n--- RONDA " + ronda + " ---");
            Evento eventoActual = obtenerEventoAleatorio();

            // Evitamos que se abran regalos dos veces repitiendo la tirada si procede
            while (eventoActual == Evento.APERTURA_REGALOS && yaSeAbrieronRegalos) {
                eventoActual = obtenerEventoAleatorio();
            }

            System.out.println("Evento: " + eventoActual);

            // Marcamos si ya se abrieron en esta ronda para bloquearlo en el futuro
            if (eventoActual == Evento.APERTURA_REGALOS) {
                yaSeAbrieronRegalos = true;
            }

            // Contador dinámico para saber cuánta gente sigue en pie
            int invitadosActivos = 0;

            // TODO 3: Recorre el array de invitados
            for (int i = 0; i < invitados.length; i++) {
                Invitado inv = invitados[i];

                // 1. Cuidado con las posiciones null del array (ya que el array es de 10 pero hay 7 invitados).
                if (inv != null && inv.isEnFiesta()) {
                    invitadosActivos++;

                    try {
                        // 3. Haz que reaccione al evento (gestiona las excepciones).
                        inv.reaccionar(eventoActual);

                        /*
                         * 4. Si es APERTURA_REGALOS y el invitado es regalador:
                         * Utilizamos 'instanceof' para comprobar con seguridad si el objeto
                         * implementa la interfaz antes de hacer el casteo explícito.
                         */
                        if (eventoActual == Evento.APERTURA_REGALOS && inv instanceof Regalador) {
                            TipoRegalo regalo = ((Regalador) inv).darRegalo();
                            System.out.println(inv.getNombre() + " te regala " + regalo);

                            // Sumamos la ropa recibida para las condiciones de derrota
                            if (regalo == TipoRegalo.ROPA) {
                                ropaRecibida++;
                            }
                        }
                    } catch (FiestaException e) {
                        // Se imprime el motivo por el cual el invitado se ha ido mediante el getMessage()
                        System.out.println(e.getMessage());
                        invitadosActivos--; // Restamos al que se acaba de ir de la cuenta activa
                    }
                }
            }

            /*
             * TODO 4: Chequeo de fin de fiesta global.
             * Estas son las condiciones que cancelan el bucle principal.
             */
            // - Si se han regalado 4 prendas de ropa -> Mensaje de decepción.
            if (ropaRecibida >= ROPA_MAX) {
                System.out.println("Esta fiesta es un rollo, todo el mundo regala ropa. Cada uno pa su casa");
                fiestaSigue = false;
            }
            // - Si no queda nadie en la fiesta -> Fin con mensaje.
            else if (invitadosActivos <= 0) {
                System.out.println("Se ha ido todo el mundo. Vaya mojón de fiesta, Lolo");
                fiestaSigue = false;
            }

            ronda++;
        }
        System.out.println("--- FIN DE LA FIESTA ---");
    }

    /**
     * TODO 5: Obtiene un evento aleatorio de la enumeración Evento.
     * @return Un objeto de tipo Evento seleccionado al azar.
     */
    private static Evento obtenerEventoAleatorio() {
        Evento[] eventos = Evento.values(); // Extrae todos los valores del Enum a un array
        Random r = new Random();
        return eventos[r.nextInt(eventos.length)]; // Retorna una posición aleatoria
    }
}