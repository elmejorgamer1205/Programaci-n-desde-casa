package Examen_Herencia2026;

import java.util.Random;

/**
 * Representa a un amigo de Lolo.
 * Son el alma de la fiesta, les encanta la música alta y regalan juguetes o ropa.
 */
public class Colega extends Invitado implements Regalador {
    private TipoRegalo regalo;

    public Colega(String nombre) {
        super(nombre);
        Random r = new Random();
        this.hambre = r.nextInt(21) + 50; // Entre 50 y 70
        this.aburrimiento = r.nextInt(51); // Entre 0 y 50
        this.regalo = r.nextBoolean() ? TipoRegalo.JUGUETE : TipoRegalo.ROPA;
    }

    @Override
    public TipoRegalo darRegalo() {
        return this.regalo;
    }

    @Override
    public void reaccionar(Evento evento) throws FiestaException {
        switch (evento) {
            case CORTE_TARTA:
                modificarHambre(-20);
                break;
            case MUSICA_ALTA:
            case BAILE:
                modificarAburrimiento(-30); // Desfasan y se lo pasan genial
                modificarHambre(10);
                break;
            case MUSICA_BAJA:
                modificarAburrimiento(30); // Se aburren si no hay caña
                modificarHambre(10);
                break;
            case CHARLITA_COLOQUIAL:
            case APERTURA_REGALOS:
                modificarAburrimiento(-20);
                modificarHambre(10);
                break;
            case PINATA:
                modificarAburrimiento(-20);
                modificarHambre(-10);
                break;
        }
    }
}