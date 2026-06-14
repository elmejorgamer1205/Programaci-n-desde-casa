package Examen_Ficheros2026;

/**
 * - Excepción personalizada para manejar los errores específicos durante la lectura o escritura de logs.
 */
public class LogException extends Exception {

    /**
     * - Constructor de la excepción con un mensaje personalizado.
     *
     * @param message - Mensaje que detalla la causa de la excepción.
     */
    public LogException(String message) {
        super(message);
    }
}