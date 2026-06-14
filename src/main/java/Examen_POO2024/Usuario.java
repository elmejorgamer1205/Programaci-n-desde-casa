package Examen_POO2024;

import java.util.ArrayList;

/**
 * Representa a un usuario de la futura red social.
 * Contiene la lógica para la gestión de amigos, publicación de mensajes
 * y visualización del feed respetando las reglas de privacidad.
 */
public class Usuario {

    private String nombre;
    private Mensaje[] mensajes;
    private int contadorMensajes;

    /* * Se utiliza un ArrayList para la colección de amigos.
     * Esto cumple con el requisito del enunciado que especifica que
     * un usuario debe poder agregar un número infinito de amigos,
     * a diferencia del array estático usado para los mensajes.
     */
    private ArrayList<Usuario> amigos;

    /**
     * Crea un nuevo usuario con los límites establecidos por el sistema.
     * @param nombre El nombre identificativo del usuario.
     */
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.mensajes = new Mensaje[10]; // Límite estricto de 10 mensajes por usuario
        this.contadorMensajes = 0;
        this.amigos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Añade a otro usuario a la lista de amigos si no está ya presente.
     * @param amigo El objeto Usuario que se desea añadir.
     */
    public void agregarAmigo(Usuario amigo) {
        // Evitamos duplicados en la lista de amigos
        if (!this.amigos.contains(amigo)) {
            this.amigos.add(amigo);
        }
    }

    /**
     * Verifica si existe una relación de amistad bidireccional.
     * @param otro El usuario con el que se quiere comprobar la amistad.
     * @return true si ambos usuarios se tienen agregados mutuamente.
     */
    private boolean esAmigoMutuo(Usuario otro) {
        // Comprueba que la amistad exista en ambas direcciones exactas
        return this.amigos.contains(otro) && otro.amigos.contains(this);
    }

    /**
     * Publica un nuevo mensaje validando las restricciones del sistema.
     * @param texto El contenido a publicar.
     * @param esPrivado La visibilidad del mensaje.
     * @return El objeto Mensaje si se publicó con éxito.
     * @throws MensajeExcepcion Si se incumple el límite de caracteres o de cantidad de mensajes.
     */
    public Mensaje publicarMensaje(String texto, boolean esPrivado) throws MensajeExcepcion {

        /*
         * VALIDACIONES PREVIAS
         * Lanzamos nuestra excepción personalizada si se incumple
         * alguna de las reglas marcadas en el examen.
         */

        // Restricción 2: Límite de 150 caracteres
        if (texto.length() > 150) {
            throw new MensajeExcepcion("El mensaje supera la longitud máxima");
        }

        // Restricción 1: Límite de 10 mensajes por usuario
        if (contadorMensajes >= 10) {
            throw new MensajeExcepcion("Has alcanzado el límite máximo de 10 mensajes.");
        }

        // Si pasa las validaciones, creamos e insertamos el mensaje
        Mensaje nuevoMensaje = new Mensaje(texto, esPrivado);
        this.mensajes[contadorMensajes] = nuevoMensaje;
        this.contadorMensajes++;

        return nuevoMensaje;
    }

    /**
     * Da un "like" a un mensaje específico.
     * @param mensaje El mensaje objetivo.
     */
    public void darLike(Mensaje mensaje) {
        // Validación de seguridad para evitar NullPointerException
        if (mensaje != null) {
            mensaje.incrementarLikes();
        }
    }

    /**
     * Muestra por consola los mensajes de otro usuario a los que este tiene acceso.
     * @param otroUsuario El usuario cuyo feed se desea visualizar.
     */
    public void verMensajes(Usuario otroUsuario) {
        System.out.println(this.nombre + " ve los mensajes de " + otroUsuario.getNombre() + ":");

        for (int i = 0; i < otroUsuario.contadorMensajes; i++) {
            Mensaje m = otroUsuario.mensajes[i];

            /*
             * REGLA DE VISIBILIDAD:
             * Se muestra el mensaje si se cumple una de las dos condiciones:
             * A) El mensaje NO es privado (es público).
             * B) Es privado, PERO existe una amistad mutua confirmada.
             */
            if (!m.isPrivado() || this.esAmigoMutuo(otroUsuario)) {
                System.out.println(m.getTexto() + " (" + m.getLikes() + " likes)");
            }
        }
    }
}