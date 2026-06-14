package Examen_POO2024;

/**
 * Representa un mensaje publicado por un usuario dentro de la plataforma.
 * Gestiona el contenido del texto, el nivel de privacidad y la cantidad de interacciones (likes).
 */
public class Mensaje {

    /* * Bloque de atributos de la clase.
     * Se declaran como privados para garantizar el encapsulamiento y
     * evitar modificaciones directas desde fuera de la clase.
     */
    private String texto;
    private boolean esPrivado;
    private int likes;

    /**
     * Constructor para instanciar un nuevo Mensaje.
     * @param texto El contenido del mensaje (se asume que ya viene validado).
     * @param esPrivado Define si el mensaje es solo para amigos (true) o público (false).
     */
    public Mensaje(String texto, boolean esPrivado) {
        this.texto = texto;
        this.esPrivado = esPrivado;
        this.likes = 0; // Inicia siempre con 0 likes por defecto al publicarse
    }

    /**
     * @return El contenido en texto del mensaje.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @return true si el mensaje es privado, false si es público.
     */
    public boolean isPrivado() {
        return esPrivado;
    }

    /**
     * @return El número actual de likes que tiene el mensaje.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Incrementa el contador de likes del mensaje en una unidad.
     */
    public void incrementarLikes() {
        this.likes++; // Suma 1 al valor actual
    }
}