package Examen_Herencia2023;

/**
 * Clase abstracta que especializa un Juguete, añadiendo propiedades de madera.
 */
public abstract class JugueteMadera extends Juguete {
    private String origen;
    private int anoTala;

    /*
     * Constructor que llama al padre (Juguete) e
     * inicializa los datos propios de la madera.
     */
    public JugueteMadera(String nombre, String marca, String origen, int anoTala) {
        super(nombre, marca); // Llamada al constructor de Juguete
        this.origen = origen;
        this.anoTala = anoTala;
    }

    public String getOrigen() {
        return origen;
    }

    public int getAnoTala() {
        return anoTala;
    }
}