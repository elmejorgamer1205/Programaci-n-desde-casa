package Examen_Herencia2026;

/**
 * Excepción exclusiva del Gorrón, lanzada cuando su nivel de hambre llega a 0.
 */
public class SaciadoException extends FiestaException {
    public SaciadoException(String message) {
        super(message);
    }
}