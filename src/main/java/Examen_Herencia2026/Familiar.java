package Examen_Herencia2026;

import java.util.Random;

/**
 * Representa a un familiar en la fiesta.
 * Son sensibles al ruido y siempre regalan dinero o ropa.
 */
public class Familiar extends Invitado implements Regalador {
    private TipoRegalo regalo; // Almacena el regalo que dará al abrirse los regalos

    public Familiar(String nombre) {
        super(nombre);
        Random r = new Random();
        this.hambre = r.nextInt(31) + 30; // Entre 30 y 60
        this.aburrimiento = r.nextInt(51); // Entre 0 y 50

        /* * Asignación aleatoria del regalo en el constructor,
         * tal y como permite el enunciado.
         */
        this.regalo = r.nextBoolean() ? TipoRegalo.DINERO : TipoRegalo.ROPA;
    }

    @Override
    public TipoRegalo darRegalo() {
        return this.regalo;
    }

    @Override
    public void reaccionar(Evento evento) throws FiestaException {
        // Lógica de reacción específica del Familiar
        switch (evento) {
            case CORTE_TARTA:
                modificarHambre(-20);
                break;
            case MUSICA_ALTA:
                modificarAburrimiento(20); // Les molesta mucho el ruido
                modificarHambre(10);
                break;
            case MUSICA_BAJA:
            case CHARLITA_COLOQUIAL:
            case APERTURA_REGALOS:
                modificarAburrimiento(-20);
                modificarHambre(10);
                break;
            case BAILE:
                modificarAburrimiento(-15);
                modificarHambre(10);
                break;
            case PINATA:
                modificarAburrimiento(-20);
                modificarHambre(-10);
                break;
        }
    }
}