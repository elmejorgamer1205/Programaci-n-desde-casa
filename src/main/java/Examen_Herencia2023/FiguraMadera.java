package Examen_Herencia2023;

/**
 * Representa una figura de madera que hereda de JugueteMadera e implementa Apilable.
 */
public class FiguraMadera extends JugueteMadera implements Apilable {
    // Atributos específicos de la figura de madera
    private String color;
    private int numLados;

    public FiguraMadera(String nombre, String marca, String origen, int anoTala, String color, int numLados) throws JugueteException {
        super(nombre, marca, origen, anoTala);
        this.color = color;
        this.setNumLados(numLados);
    }

    public int getNumLados() {
        return numLados;
    }

    /*
     * Setter privado para validar que el número de lados
     * introducido sea correcto al construir el objeto.
     */
    private void setNumLados(int numLados) throws JugueteException {
        if (numLados < 1) {
            throw new JugueteException("No puedes tener una figura sin lados");
        }
        this.numLados = numLados;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void apilar(Juguete a) throws JugueteException {
        Apilable.super.apilar(a);
        System.out.println("Se ha apilado " + this.getNombre() + " sobre " + a.getNombre());

    }

    @Override
    public String toString() {
        return this.getNombre() + " " + color + " de " + numLados + " lados";
    }

}