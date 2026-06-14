package Examen_Herencia2026;
import java.util.Random;

/**
 * Representa al vecino colado.
 * Tiene una lógica especial: no se va por aburrimiento ni por hambre crítica,
 * solo se marcha si consigue saciarse por completo robando comida.
 */
public class Gorron extends Invitado {

    public Gorron(String nombre) {
        super(nombre);
        Random r = new Random();
        this.hambre = 90; // Siempre entra canino
        this.aburrimiento = r.nextInt(51); // Entre 0 y 50
    }

    /*
     * 1. SOBRESCRITURA DE MÉTODOS BASE
     * El gorrón sobrescribe las reglas de salida SIN lanzar excepciones conflictivas.
     * En Java, un hijo puede omitir las excepciones "checked" del padre si no las necesita.
     */
    @Override
    protected void modificarHambre(int cantidad) {
        this.hambre += cantidad;
        if (this.hambre >= 100) {
            this.hambre = 100; // El gorrón nunca se va por hambre, siempre quiere más
        }
        if (this.hambre < 0) {
            this.hambre = 0;
        }
    }

    @Override
    protected void modificarAburrimiento(int cantidad) {
        this.aburrimiento += cantidad;
        if (this.aburrimiento < 0) this.aburrimiento = 0;
        if (this.aburrimiento >= 100) {
            this.aburrimiento = 100; // El gorrón no se va por aburrimiento
        }
    }

    /**
     * 2. Habilidad especial explícita pedida en el enunciado.
     * Reduce el hambre del Gorrón de forma clandestina.
     * @param cantidad Puntos de hambre que recupera robando.
     */
    public void robarComida(int cantidad) {
        modificarHambre(-cantidad);
    }

    @Override
    public void reaccionar(Evento evento) throws FiestaException {
        switch (evento) {
            case CORTE_TARTA:
                modificarHambre(-30); // Come explícitamente, más que los demás
                break;
            case MUSICA_ALTA:
            case BAILE:
                modificarAburrimiento(-5);
                robarComida(10); // No comen explícitamente -> Aprovecha y roba el doble
                break;
            case MUSICA_BAJA:
                modificarAburrimiento(30);
                robarComida(5);  // No comen explícitamente -> Roba normal
                break;
            case CHARLITA_COLOQUIAL:
                modificarAburrimiento(20);
                robarComida(5);  // No comen explícitamente -> Roba normal
                break;
            case PINATA:
                modificarAburrimiento(-20);
                modificarHambre(-20); // Come explícitamente el doble
                break;
            case APERTURA_REGALOS:
                modificarAburrimiento(-20);
                modificarHambre(10); // Cotillea, sube el hambre normal y no roba
                break;
        }

        /*
         * 3. CONDICIÓN DE SALIDA EXCLUSIVA
         * Como reaccionar() lanza FiestaException, lanzar SaciadoException (que hereda de ella)
         * aquí es perfectamente legal y cumple el polimorfismo.
         */
        if (this.hambre == 0) {
            this.enFiesta = false;
            throw new SaciadoException(this.nombre + " se va de la fiesta hasta arriba de comida. ¡Adiós pringaos!");
        }
    }
}