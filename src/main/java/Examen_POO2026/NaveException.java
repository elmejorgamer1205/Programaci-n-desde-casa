package Examen_POO2026;

/**
 * Clase de excepción personalizada para capturar y controlar los errores
 * específicos de la lógica de vuelo o carga de los depósitos.
 */
public class NaveException extends Exception {

    /**
     * Constructor que asocia un mensaje descriptivo al error.
     * * @param mensaje El texto que explica el motivo de la excepción.
     */
    public NaveException(String mensaje) {
        super(mensaje); // Pasa el mensaje a la clase padre Exception
    }
}