package Examen_Ficheros2026;

import java.util.List;

/**
 * - Registro (record) utilizado para serializar el reporte final a formato JSON.
 *
 * @param total_errores_encontrados - Cantidad total de errores recopilados en el proceso.
 * @param detalles                  - Lista de los objetos DetalleError extraídos de los logs.
 */
public record ReporteErrores(int total_errores_encontrados, List<DetalleError> detalles) {}