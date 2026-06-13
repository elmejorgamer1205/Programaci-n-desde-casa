package Examen_POO2026;

/**
 * Clase de control de la nave. Coordina los depósitos de combustible
 * y evalúa si se cumplen los requisitos de seguridad antes de viajar.
 */
public class Nave {

    private String nombre;
    // Array para contener exactamente los 3 depósitos reglamentarios
    private Deposito[] depositos;

    /**
     * Constructor de la Nave.
     * * @param nombre El nombre identificativo de la nave.
     */
    public Nave(String nombre) {
        this.nombre = nombre;

        /* * Inicializamos el array de tamaño 3 y posteriormente creamos
         * cada objeto Deposito para evitar errores de tipo NullPointerException.
         */
        this.depositos = new Deposito[3];
        for (int i = 0; i < depositos.length; i++) {
            depositos[i] = new Deposito();
        }
    }

    public String getNombre() {
        return nombre;
    }

    /* ====================================================================
     * MÉTODOS PRINCIPALES (OBLIGATORIOS)
     * ==================================================================== */

    /**
     * Carga un cartucho en un depósito determinado delegando la acción.
     * * @param indice El número del depósito destino (0, 1 o 2).
     * @param c El cartucho que se desea agregar.
     * @return true si el cartucho cupo en el depósito, false si estaba lleno.
     * @throws NaveException Si el índice indicado no corresponde a ningún depósito.
     */
    public boolean cargarDeposito(int indice, Cartucho c) throws NaveException {
        if (indice >= 0 && indice < 3) {
            return depositos[indice].agregarCartucho(c); // Delega al objeto Deposito
        } else {
            throw new NaveException("Índice de depósito inválido. Debe ser 0, 1 o 2.");
        }
    }

    /**
     * Evalúa las directrices de seguridad física y química. Si algo falla,
     * interrumpe el flujo lanzando una NaveException con el código de error.
     * * @throws NaveException si se produce una explosión, falta energía o es inestable.
     */
    public void saltarHiperespacio() throws NaveException {
        // Regla 1: Comprobación de peligro de explosión química
        if (checkAntimateria()) {
            throw new NaveException("ERROR CRÍTICO: ¡EXPLOSIÓN!");
        }

        // Regla 2: Comprobación de potencia mínima (> 1000 TW)
        if (calcularPotenciaTotal() <= 1000.0) {
            throw new NaveException("FALLO: Potencia insuficiente");
        }

        // Regla 3: Estabilidad molecular (Debe cumplirse al menos una de las dos)
        if (!hayUnaAntimateriaPorDeposito() && !puedeVolarConPlasmaVerde()) {
            throw new NaveException("FALLO: Configuración química inestable");
        }

        // Si supera de forma exitosa todos los filtros anteriores
        System.out.println("SALTO AL HIPERESPACIO INICIADO... BUEN VIAJE");
    }

    /* ====================================================================
     * MÉTODOS AUXILIARES
     * ==================================================================== */

    // Suma las potencias individuales de cada uno de los 3 depósitos
    private double calcularPotenciaTotal() {
        double potencia = 0;
        for (Deposito d : depositos) {
            potencia += d.calcularPotenciaTotal();
        }
        return potencia;
    }

    // Suma el peso de la totalidad de la carga de la nave
    private double calcularPesoTotal() {
        double peso = 0;
        for (Deposito d : depositos) {
            peso += d.calcularPesoTotal();
        }
        return peso;
    }

    /**
     * Comprueba si en ALGÚN depósito hay más de 1 antimateria (Peligro letal).
     */
    private boolean checkAntimateria() {
        for (Deposito d : depositos) {
            if (d.contarCartuchosDeTipo("antimateria") > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si en TODOS los depósitos hay exactamente 1 cartucho de antimateria.
     */
    private boolean hayUnaAntimateriaPorDeposito() {
        for (Deposito d : depositos) {
            if (d.contarCartuchosDeTipo("antimateria") != 1) {
                return false;
            }
        }
        return true; // Solo devuelve true si los 3 depósitos tienen justo 1
    }

    // Calcula el peso total exclusivo del Plasma Verde en la nave
    private double calcularPesoPlasmaVerde() {
        double pesoPlasma = 0;
        for (Deposito d : depositos) {
            pesoPlasma += d.calcularPesoCartuchoTipo("plasma verde");
        }
        return pesoPlasma;
    }

    /**
     * Determina si el peso del plasma verde supera el 50% de la masa total de combustible.
     */
    private boolean puedeVolarConPlasmaVerde() {
        double pesoTotalNave = calcularPesoTotal();

        // Control de seguridad para evitar divisiones aritméticas por cero
        if (pesoTotalNave == 0) return false;

        double pesoPlasma = calcularPesoPlasmaVerde();
        return pesoPlasma > (pesoTotalNave * 0.5);
    }
}