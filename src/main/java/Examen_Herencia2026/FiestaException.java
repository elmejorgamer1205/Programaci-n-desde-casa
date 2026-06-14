package Examen_Herencia2026;

/**
 * Clase base para todas las excepciones controladas que pueden ocurrir durante la fiesta.
 * Hereda de Exception para obligar a su captura (checked exception).
 */
public class FiestaException extends Exception {
    public FiestaException(String message) {
        super(message);
    }
}