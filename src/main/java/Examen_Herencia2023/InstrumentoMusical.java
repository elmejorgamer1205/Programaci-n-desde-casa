package Examen_Herencia2023;

/**
 * Clase que representa un instrumento musical fabricado en madera.
 */
public class InstrumentoMusical extends JugueteMadera {

    private int edadMinima;

    public InstrumentoMusical(String nombre, String marca, String origen, int anoTala, int edadMinima) throws JugueteException {
        super(nombre, marca, origen, anoTala);
        this.setEdadMinima(edadMinima);
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    /*
     * Establece la edad mínima requerida para usar el instrumento.
     * Lanza una excepción si se intenta poner un valor negativo.
     */
    private void setEdadMinima(int edadMinima) throws JugueteException {
        // La edad nunca puede ser menor que 0
        if (edadMinima < 0) {
            throw new JugueteException("La edad mínima no puede ser inferior a 0");
        }
        this.edadMinima = edadMinima;
    }

    @Override
    public String toString() {
        return this.getNombre() + ". Edad mínima " + edadMinima + " años";
    }

}