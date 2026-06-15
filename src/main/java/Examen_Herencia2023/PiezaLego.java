package Examen_Herencia2023;

/**
 * Representa una pieza de Lego que hereda de JuguetePlastico y se puede apilar.
 */
public class PiezaLego extends JuguetePlastico implements Apilable {
    private int longitud;
    private String color;

    public PiezaLego(String nombre, String marca, int longitud, String color) throws JugueteException {
        super(nombre, marca, TPlastico.ABS);
        this.setLongitud(longitud);
        this.color = color;
    }

    public int getLongitud() {
        return longitud;
    }

    /*
     * Método de validación para asegurar que la pieza
     * tiene un tamaño lógico antes de ser creada.
     */
    private void setLongitud(int longitud) throws JugueteException {
        // La longitud mínima permitida es 1
        if (longitud < 1) {
            throw new JugueteException("No puedes tener una pieza con longitud menor que 1");
        }
        this.longitud = longitud;
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
        return this.getNombre() + " de color " + color + " de " + longitud + " unidades de longitud";
    }


}