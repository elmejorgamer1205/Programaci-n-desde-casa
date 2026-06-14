package Examen_Herencia2026;

/**
 * Clase abstracta que representa a cualquier asistente a la fiesta de Lolo.
 * Define los atributos básicos y la lógica común de estado (hambre y aburrimiento).
 */
public abstract class Invitado {
    protected String nombre;
    protected int hambre;
    protected int aburrimiento;
    protected boolean enFiesta;

    /**
     * Constructor por defecto para inicializar un invitado.
     * @param nombre Nombre del invitado.
     */
    public Invitado(String nombre) {
        this.nombre = nombre;
        this.enFiesta = true; // Todos entran a la fiesta inicialmente
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEnFiesta() {
        return enFiesta;
    }

    public void setEnFiesta(boolean enFiesta) {
        this.enFiesta = enFiesta;
    }

    /*
     * MÉTODOS DE CONTROL DE ESTADÍSTICAS
     * Estos métodos centralizan la modificación del hambre y el aburrimiento.
     * Lanzan excepciones si los límites (0-100) superan el umbral crítico.
     */

    /**
     * Modifica el nivel de hambre del invitado.
     * @param cantidad Valor a sumar o restar al hambre actual.
     * @throws HambreCriticaException Si el hambre llega o supera 100.
     */
    protected void modificarHambre(int cantidad) throws HambreCriticaException {
        this.hambre += cantidad;

        // El hambre nunca puede ser negativa
        if (this.hambre < 0) this.hambre = 0;

        // Si llega a 100, el invitado se marcha de la fiesta
        if (this.hambre >= 100) {
            this.hambre = 100;
            this.enFiesta = false;
            throw new HambreCriticaException(this.nombre + " se ha ido hambriento");
        }
    }

    /**
     * Modifica el nivel de aburrimiento del invitado.
     * @param cantidad Valor a sumar o restar al aburrimiento actual.
     * @throws AburrimientoCriticoException Si el aburrimiento llega o supera 100.
     */
    protected void modificarAburrimiento(int cantidad) throws AburrimientoCriticoException {
        this.aburrimiento += cantidad;

        // El aburrimiento nunca puede ser negativo
        if (this.aburrimiento < 0) this.aburrimiento = 0;

        // Si llega a 100, la fiesta ha sido un fracaso para él
        if (this.aburrimiento >= 100) {
            this.aburrimiento = 100;
            this.enFiesta = false;
            throw new AburrimientoCriticoException(this.nombre + " se va de la fiesta aburrido");
        }
    }

    /**
     * Método abstracto que obliga a las subclases a definir cómo reaccionan a cada evento.
     * @param evento El evento que está ocurriendo en la ronda actual.
     * @throws FiestaException Si la reacción provoca que el invitado se vaya.
     */
    public abstract void reaccionar(Evento evento) throws FiestaException;
}