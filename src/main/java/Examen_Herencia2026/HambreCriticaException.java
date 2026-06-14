package Examen_Herencia2026;

/**
 * Excepción lanzada cuando un invitado alcanza el nivel máximo de hambre (100).
 */
public class HambreCriticaException extends FiestaException {
    public HambreCriticaException(String message) {
        super(message);
    }
}