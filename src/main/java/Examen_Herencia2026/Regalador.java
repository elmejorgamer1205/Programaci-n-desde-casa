package Examen_Herencia2026;

/**
 * Interfaz que define el comportamiento de los invitados que traen regalos.
 * Cualquier clase que la implemente está obligada a definir cómo dar un regalo.
 */
public interface Regalador {
    /**
     * Devuelve el regalo que el invitado ha traído a la fiesta.
     * @return TipoRegalo (DINERO, JUGUETE o ROPA).
     */
    TipoRegalo darRegalo();
}