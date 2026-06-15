package Examen_Herencia2023;

/**
 * Interfaz que define el comportamiento para los juguetes que se pueden apilar.
 */
public interface Apilable {
    //public void apilar(Juguete a);

    /*
     * Metodo por defecto que implementa la lógica base para apilar.
     * Realiza comprobaciones de seguridad antes de permitir la acción.
     */
    public default void apilar(Juguete a) throws JugueteException {
        // Comprueba que no se apile sobre sí mismo
        if (this == a) {
            throw new JugueteException("No puedes apilar un juguete consigo mismo");
        }
        if (!(a instanceof Apilable)) {
            throw new JugueteException("El juguete " + a.getNombre() + " no es apilable");
        }

        //Comprobamos ahora que sea del mismo tipo
        if (this.getClass() != a.getClass()) {
            throw new JugueteException("No puedo apilar un juguete de tipo " + this.getClass().getCanonicalName()
                    + " con uno de tipo " + a.getClass().getCanonicalName());
        }
    }
}