package Examen_POO2024;

/**
 * Clase principal (Main) diseñada para testear el comportamiento del sistema.
 * Recrea el entorno de pruebas especificado en el examen para validar
 * que la salida por consola es exactamente la esperada.
 */
public class Principal {

    public static void main(String[] args) {
        // Inicialización de los tres usuarios de prueba
        Usuario juan = new Usuario("Juan");
        Usuario pedro = new Usuario("Pedro");
        Usuario maria = new Usuario("Maria");

        /*
         * FASE 1: CONFIGURACIÓN DE AMISTADES
         * Establecemos las relaciones entre los usuarios.
         */

        // Juan y Pedro: Amistad completa (mutua)
        juan.agregarAmigo(pedro);
        pedro.agregarAmigo(juan);

        // Juan y Maria: Amistad completa (mutua)
        juan.agregarAmigo(maria);
        maria.agregarAmigo(juan);

        // Pedro y Maria: Amistad incompleta (Pedro agrega a Maria, pero Maria a Pedro no)
        pedro.agregarAmigo(maria);


        /*
         * FASE 2: PUBLICACIÓN DE MENSAJES
         * Al usar excepciones, estamos obligados a utilizar try-catch.
         */

        try {
            // Mensaje que supera los 150 caracteres para forzar el mensaje de error inicial
            String textoLargo = "a".repeat(151);
            juan.publicarMensaje(textoLargo, false);
        } catch (MensajeExcepcion e) {
            // Imprimirá exactamente: "El mensaje supera la longitud máxima"
            System.out.println(e.getMessage());
        }

        // Declaramos las variables fuera del try para poder darles "like" más adelante
        Mensaje p1 = null, p2 = null;
        Mensaje m1 = null, m2 = null;
        Mensaje j1 = null, j2 = null, j3 = null;

        try {
            // Mensajes de Pedro (1 Privado, 1 Público)
            p1 = pedro.publicarMensaje("Hola amigos", true);
            p2 = pedro.publicarMensaje("Estoy jugando al LOL, ¿alguien para una partidita?", false);

            // Mensajes de Maria (1 Público, 1 Privado)
            m1 = maria.publicarMensaje("¡Buenos días!", false);
            m2 = maria.publicarMensaje("¡¡Mañana me voy de vacaciones!!", true);

            // Mensajes de Juan (2 Públicos, 1 Privado)
            j1 = juan.publicarMensaje("Hola a todos", false);
            j2 = juan.publicarMensaje("Pasando el finde en casa", false);
            j3 = juan.publicarMensaje("Estudiando mucho", true);
        } catch (MensajeExcepcion e) {
            System.out.println(e.getMessage());
        }


        /*
         * FASE 3: INTERACCIONES (LIKES)
         */
        juan.darLike(p1); // 1 like para Pedro
        juan.darLike(m1); // 1 like para Maria
        pedro.darLike(m1); // 2º like para Maria
        maria.darLike(j1); // 1 like para Juan


        /*
         * FASE 4: VISUALIZACIÓN DE MENSAJES (TESTEO DE REGLAS)
         * Comprueba qué puede ver cada usuario basándose en su amistad mutua.
         */

        // Juan debería ver el mensaje privado de Pedro y de María
        juan.verMensajes(pedro);
        juan.verMensajes(maria);

        // Pedro debería ver el privado de Juan, pero SOLO el público de María
        pedro.verMensajes(juan);
        pedro.verMensajes(maria);

        // María debería ver el privado de Juan, pero SOLO el público de Pedro
        maria.verMensajes(juan);
        maria.verMensajes(pedro);
    }
}