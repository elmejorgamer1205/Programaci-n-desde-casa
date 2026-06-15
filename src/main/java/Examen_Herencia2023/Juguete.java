package Examen_Herencia2023;

/**
 * Clase abstracta principal de la que heredan todos los juguetes del sistema.
 */
public abstract class Juguete {
    // Variables base comunes a cualquier tipo de juguete
    private String nombre, marca;

    /*
     * Constructor base de la clase Juguete.
     * Inicializa el nombre y la marca proporcionados.
     */
    public Juguete(String nombre, String marca) {
        super();
        this.nombre = nombre;
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }


}