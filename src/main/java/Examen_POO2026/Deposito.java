package Examen_POO2026;

/**
 * Representa un depósito físico dentro de la nave.
 * Es capaz de almacenar un máximo de 5 cartuchos de combustible.
 */
public class Deposito {

    // Estructura fija (Array) para almacenar hasta 5 cartuchos
    private Cartucho[] cartuchos;

    /**
     * Constructor por defecto.
     * Inicializa el array reservando espacio para 5 elementos.
     */
    public Deposito() {
        this.cartuchos = new Cartucho[5];
    }

    /**
     * Intenta añadir un cartucho al depósito en el primer hueco que encuentre vacío.
     * * @param c El cartucho a añadir.
     * @return true si se añadió correctamente, false si el depósito está lleno.
     */
    public boolean agregarCartucho(Cartucho c) {
        /*
         * Recorremos el array buscando una posición que sea null.
         * Si la encontramos, guardamos el cartucho y salimos del método.
         */
        for (int i = 0; i < cartuchos.length; i++) {
            if (cartuchos[i] == null) {
                cartuchos[i] = c;
                return true; // Éxito al insertar
            }
        }
        return false; // Depósito lleno
    }

    /**
     * @return La suma de la potencia (en TW) de todos los cartuchos almacenados.
     */
    public double calcularPotenciaTotal() {
        double potenciaTotal = 0;
        for (Cartucho c : cartuchos) {
            // Es vital verificar que no sea nulo antes de leer sus datos
            if (c != null) {
                potenciaTotal += c.getPotencia();
            }
        }
        return potenciaTotal;
    }

    /**
     * @return El peso total acumulado de los cartuchos en este depósito.
     */
    public double calcularPesoTotal() {
        double pesoTotal = 0;
        for (Cartucho c : cartuchos) {
            if (c != null) {
                pesoTotal += c.getPeso();
            }
        }
        return pesoTotal;
    }

    /**
     * Cuenta cuántos cartuchos de un tipo específico hay en el depósito.
     * * @param tipo El tipo químico a buscar (Ej. "antimateria").
     * @return La cantidad entera encontrada.
     */
    public int contarCartuchosDeTipo(String tipo) {
        int contador = 0;
        for (Cartucho c : cartuchos) {
            // equalsIgnoreCase evita errores si escriben "Antimateria" o "antimateria"
            if (c != null && c.getTipo().equalsIgnoreCase(tipo)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Calcula cuánto peso en total aporta un tipo específico de cartucho.
     * * @param tipo El tipo químico a buscar.
     * @return La suma de los pesos de esos cartuchos concretos.
     */
    public double calcularPesoCartuchoTipo(String tipo) {
        double pesoTotalTipo = 0;
        for (Cartucho c : cartuchos) {
            if (c != null && c.getTipo().equalsIgnoreCase(tipo)) {
                pesoTotalTipo += c.getPeso();
            }
        }
        return pesoTotalTipo;
    }
}