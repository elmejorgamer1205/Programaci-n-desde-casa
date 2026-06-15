package Examen_Herencia2023;

/**
 * Clase que representa un vehículo de juguete fabricado en plástico.
 * Extiende la clase base JuguetePlastico.
 */
public class VehiculoPlastico extends JuguetePlastico {
    // Atributo específico para contar las ruedas del vehículo
    private int numRuedas;

    /*
     * Constructor del vehículo de plástico.
     * Por requerimientos de la clase padre, invoca a super() y preasigna
     * automáticamente que el tipo de plástico será PVC.
     */
    public VehiculoPlastico(String nombre, String marca, int numRuedas) throws JugueteException {
        super(nombre, marca, TPlastico.PVC);
        this.setNumRuedas(numRuedas);
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    /**
     * Método privado para establecer el número de ruedas.
     * @param numRuedas La cantidad de ruedas a asignar.
     * @throws JugueteException Si se intenta crear un vehículo sin ruedas.
     */
    private void setNumRuedas(int numRuedas) throws JugueteException {
        // Validación lógica: un vehículo con ruedas debe tener al menos 1
        if (numRuedas < 1) {
            throw new JugueteException("Un juguete con ruedas debe tener alguna rueda");
        }
        this.numRuedas = numRuedas;
    }

    @Override
    public String toString() {
        return this.getNombre() + ". Con " + numRuedas + " ruedas";
    }

}