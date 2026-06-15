package Examen_POO2023;

import Exceptions.MiEntradaSalidaException; // Importa tu excepción personalizada
import io.MiEntradaSalida; // Importa tu clase de utilidades de lectura

public class Mundial {

    // --- ATRIBUTOS ---
    private String nombre; // El nombre del torneo
    private Equipo[] equipos; // Array estático para guardar los equipos participantes
    private int contadorEquipos; // Lleva la cuenta de cuántos equipos se han registrado

    // --- CONSTRUCTOR ---
    public Mundial(String nombre) {
        this.nombre = nombre;
        this.equipos = new Equipo[4]; // El enunciado especifica 4 equipos como máximo para esta fase
        this.contadorEquipos = 0;
    }

    // --- MÉTODOS DE LÓGICA DE NEGOCIO ---
    // (Pongo estos métodos primero para que los entiendas antes de ver cómo se usan en el menú)

    public void addEquipo(Equipo equipo) {
        // Comprueba si quedan huecos en el array (menos de 4 equipos)
        if (contadorEquipos < equipos.length) {
            this.equipos[contadorEquipos] = equipo; // Guarda el equipo en la primera posición libre
            contadorEquipos++; // Suma 1 a la cuenta de equipos
        }
    }

    public Equipo buscarEquipo(String pais) {
        // Recorre los equipos guardados
        for (int i = 0; i < contadorEquipos; i++) {
            // Si la casilla no está vacía y el país coincide...
            if (equipos[i] != null && equipos[i].getPaisRepresentado().equalsIgnoreCase(pais)) {
                return equipos[i]; // ... devuelve el objeto Equipo
            }
        }
        return null; // Si no lo encuentra, devuelve nulo
    }

    public Jugador buscarJugador(String nombreJugador) {
        // Bucle externo: recorre todos los equipos del mundial
        for (int i = 0; i < contadorEquipos; i++) {
            if (equipos[i] != null) {
                // Le pide al equipo que busque al jugador entre sus propias filas usando el metodo que creamos en la clase Equipo
                Jugador j = equipos[i].buscarJugadorPorNombre(nombreJugador);
                // Si el equipo le devuelve un jugador (es decir, no es nulo)...
                if (j != null) {
                    return j; // ...lo hemos encontrado, lo devolvemos y cortamos la búsqueda global
                }
            }
        }
        return null; // Si revisa todos los equipos y ninguno lo tiene, devuelve nulo
    }

    public Equipo obtenerEquipoMasGoleador() {
        if (contadorEquipos == 0) return null; // Protección por si no hay equipos

        Equipo masGoleador = equipos[0]; // Asume que el primero es el que más goles lleva

        // Recorre desde el segundo equipo en adelante
        for (int i = 1; i < contadorEquipos; i++) {
            // Compara los goles totales. Si el equipo actual tiene más que nuestro "masGoleador" guardado...
            if (equipos[i] != null && equipos[i].getGolesTotales() > masGoleador.getGolesTotales()) {
                masGoleador = equipos[i]; // ...lo actualiza
            }
        }
        return masGoleador;
    }

    public Jugador obtenerJugadorMasGoleador() {
        if (contadorEquipos == 0) return null;

        Jugador masGoleadorMundial = null; // Variable para guardar al pichichi absoluto

        // Recorre todos los equipos
        for (int i = 0; i < contadorEquipos; i++) {
            if (equipos[i] != null) {
                // Le pide a cada equipo que le pase a su mejor jugador
                Jugador mejorDelEquipo = equipos[i].obtenerJugadorMasGoleador();

                if (mejorDelEquipo != null) {
                    // Si aún no teníamos pichichi mundial (es nulo) O si este jugador del equipo tiene más goles que nuestro pichichi actual...
                    if (masGoleadorMundial == null || mejorDelEquipo.getGolesTotales() > masGoleadorMundial.getGolesTotales()) {
                        masGoleadorMundial = mejorDelEquipo; // ...se convierte en el nuevo pichichi absoluto
                    }
                }
            }
        }
        return masGoleadorMundial; // Al final del bucle, devuelve al ganador
    }


    // --- MÉTODO MAIN (EL PROGRAMA EN SÍ) ---
    public static void main(String[] args) {
        int opcion = 0; // Variable para controlar la opción elegida por el usuario en el menú

        // Usa tu clase de utilidades para pedir un texto por pantalla
        String nombreMundial = MiEntradaSalida.leerLinea("Introduzca un nombre para el Mundial: ");
        System.out.println("\n¡Bienvenido al Mundial " + nombreMundial + "!\n");

        // Crea el objeto Mundial real en memoria pasándole el nombre que el usuario acaba de escribir
        Mundial mundial = new Mundial(nombreMundial);

        // Bucle do-while: ejecuta el código interno y repite mientras la condición del final se cumpla
        do {
            // Imprime las opciones del menú por pantalla
            System.out.println("\n--- MENÚ PRINCIPAL FICCPA ---");
            System.out.println("1. Crear un nuevo equipo");
            // ... (resto de opciones omitidas en comentarios para abreviar, son simples print) ...
            System.out.println("7. Salir del sistema");

            try {
                // Llama a tu método blindado que pide un número y asegura que esté entre 1 y 7
                opcion = MiEntradaSalida.leerEnteroRango("\nSelecciona una opción (1-7): ", 1, 7);

                // Estructura switch: redirige el flujo del programa según el número elegido
                switch (opcion) {
                    case 1:
                        System.out.println("\n[--- CREAR NUEVO EQUIPO ---]");
                        if (mundial.contadorEquipos < 4) { // Comprueba si aún caben equipos antes de pedir datos
                            String pais = MiEntradaSalida.leerLinea("Introduce el país que representa el equipo: ");
                            String entrenador = MiEntradaSalida.leerLinea("Introduce el nombre del entrenador: ");

                            Equipo nuevoEquipo = new Equipo(pais, entrenador); // Crea el objeto Equipo
                            mundial.addEquipo(nuevoEquipo); // Lo mete en el array usando el método que explicamos arriba
                            System.out.println("-> Equipo de " + pais + " registrado con éxito.");
                        } else {
                            System.out.println("-> Error: El cupo de 4 equipos ya está completo.");
                        }
                        break; // Termina el caso 1 y vuelve a mostrar el menú

                    case 2:
                        System.out.println("\n[--- AÑADIR JUGADOR A EQUIPO ---]");
                        String paisEquipo = MiEntradaSalida.leerLinea("Introduce el país del equipo: ");

                        Equipo equipoDestino = mundial.buscarEquipo(paisEquipo); // Busca el equipo usando el método
                        if (equipoDestino != null) { // Si el equipo existe (no es nulo)...
                            String nombreJugador = MiEntradaSalida.leerLinea("Introduce el nombre del jugador: ");
                            int edad = MiEntradaSalida.leerEnteroPositivo("Introduce la edad: ", false);

                            Jugador nuevoJugador = new Jugador(nombreJugador, edad); // Crea el objeto Jugador
                            // Intenta meterlo en el equipo. El método addJugador devuelve true si pudo, false si estaba lleno
                            boolean anadido = equipoDestino.addJugador(nuevoJugador);

                            if (anadido) { // Equivale a if (anadido == true)
                                System.out.println("-> Jugador " + nombreJugador + " añadido al equipo.");
                            } else {
                                System.out.println("-> Error: El equipo ya tiene sus 2 jugadores completos.");
                            }
                        } else {
                            System.out.println("-> Error: No se ha encontrado ningún equipo de " + paisEquipo);
                        }
                        break;

                    // ... Los casos 3 al 6 siguen la misma lógica: piden datos (si es necesario),
                    // llaman al método correspondiente del objeto 'mundial', comprueban si el resultado es nulo,
                    // y hacen un System.out.println con la respuesta.

                    case 7:
                        System.out.println("\nCerrando el sistema... ¡Nos vemos en las casapuertas de Isla Cristina!");
                        break;
                }

            } catch (MiEntradaSalidaException e) {
                // Si tu clase utilidades detecta un error de rango, lanza una excepción que se atrapa aquí
                System.out.println("\n[!] Error de validación: " + e.getMessage());
            } catch (Exception e) {
                // Un salvavidas genérico por si ocurre cualquier otro error inesperado (como un NullPointerException raro)
                System.out.println("\n[!] Ha ocurrido un error inesperado: " + e.getMessage());
            }

        } while (opcion != 7); // Repite el do-while eternamente hasta que el usuario meta un 7
    }
}