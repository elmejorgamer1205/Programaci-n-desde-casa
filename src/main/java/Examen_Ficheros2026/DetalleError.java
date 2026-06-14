package Examen_Ficheros2026;

/**
 * - Registro (record) que almacena los detalles de un error encontrado en los logs.
 *
 * @param server      - Identificador del servidor donde se produjo el error.
 * @param aplicacion  - Identificador de la aplicación que generó el error.
 * @param fecha       - Fecha del error formateada unificadamente a dd/MM/yyyy.
 * @param hora        - Hora exacta en la que se registró el error.
 * @param descripcion - Mensaje descriptivo del error capturado.
 */
public record DetalleError(String server, String aplicacion, String fecha, String hora, String descripcion) {}