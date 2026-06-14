package Examen_POO2024;

/**
 * Excepción personalizada para gestionar los errores de validación
 * al publicar mensajes en la red social.
 */
public class MensajeExcepcion extends Exception {

    /**
     * Constructor de la excepción.
     * @param mensaje El texto descriptivo del error que se mostrará.
     */
    public MensajeExcepcion(String mensaje) {
        // Llama al constructor de la clase padre (Exception)
        super(mensaje);
    }
}