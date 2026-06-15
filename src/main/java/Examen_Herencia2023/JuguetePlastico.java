package Examen_Herencia2023;

/**
 * Clase abstracta para los juguetes fabricados con plástico.
 */
public abstract class JuguetePlastico extends Juguete {
    // Tipo de plástico utilizado, basado en el enumerado TPlastico
    private TPlastico tipoPlastico;

    /*
     * Constructor de la clase abstracta JuguetePlastico.
     */
    public JuguetePlastico(String nombre, String marca, TPlastico tipoPlastico) {
        super(nombre, marca);
        this.tipoPlastico = tipoPlastico;
    }

    public TPlastico getTipoPlastico() {
        return tipoPlastico;
    }

}