package Examen_Herencia2026;

/**
 * Excepción lanzada cuando un invitado alcanza el nivel máximo de aburrimiento (100).
 */
public class AburrimientoCriticoException extends FiestaException {
    public AburrimientoCriticoException(String message) {
        super(message);
    }
}