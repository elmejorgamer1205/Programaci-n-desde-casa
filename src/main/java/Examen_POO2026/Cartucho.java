package Examen_POO2026;

/**
 * Representa la unidad de combustible que utilizarán los depósitos.
 * Contiene información sobre su tipo, energía generada y peso.
 */
public class Cartucho {

    /* * Atributos de la clase encapsulados como privados
     * para proteger el acceso directo a los datos.
     */
    private String id;
    private String tipo;
    private double potencia;
    private double peso;

    /**
     * Constructor para inicializar un nuevo cartucho de combustible.
     * * @param id Identificador único del cartucho (Ej. "C1").
     * @param tipo Tipo químico (Ej. "Antimateria", "Plasma Verde").
     * @param potencia Energía generada en TeraWatts (TW).
     * @param peso Peso total del cartucho en toneladas.
     */
    public Cartucho(String id, String tipo, double potencia, double peso) {
        this.id = id;
        this.tipo = tipo;
        this.potencia = potencia;
        this.peso = peso;
    }

    // --- MÉTODOS GETTERS ---
    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public double getPotencia() { return potencia; }
    public double getPeso() { return peso; }

    // --- MÉTODOS SETTERS ---
    public void setId(String id) { this.id = id; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setPotencia(double potencia) { this.potencia = potencia; }
    public void setPeso(double peso) { this.peso = peso; }
}