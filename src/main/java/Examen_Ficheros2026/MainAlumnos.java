package Examen_Ficheros2026;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * - Clase principal que audita y procesa los logs generados por los servidores de MiravenTech.
 */
public class MainAlumnos {

    // Ruta base donde se organizarán todos los logs procesados
    public final static Path RUTA_DESTINO = Path.of("./logs_procesados");

    // Patrón para identificar ficheros de log: server{N}_app{N}.log
    private static final Pattern patron = Pattern.compile("^server(?<server>\\d)_app(?<app>\\d)\\.log$");

    // Patrón para parsear líneas de log de la app1
    // Formato esperado: yyyy/MM/dd HH:mm:ss - [NIVEL] - Mensaje
    private final static Pattern patronLogApp1 = Pattern.compile("^(?<fecha>\\d{4}/\\d{2}/\\d{2}) (?<hora>\\d{2}:\\d{2}:\\d{2}) - \\[(?<nivel>\\w+)\\] - (?<mensaje>.*)$");

    // Patrón para parsear líneas de log de la app2
    // Formato esperado: [dd-MM-yyyy|HH:mm:ss] <NIVEL> Mensaje
    private final static Pattern patronLogApp2 = Pattern.compile("^\\[(?<fecha>\\d{2}-\\d{2}-\\d{4})\\|(?<hora>\\d{2}:\\d{2}:\\d{2})\\] <(?<nivel>\\w+)> (?<mensaje>.*)$");

    /**
     * - Método de entrada principal de la aplicación.
     * * @param args - Argumentos de consola (no se utilizan).
     */
    public static void main(String[] args) {

        // Generamos el entorno de prueba con los logs desordenados
        GeneradorLogsExamen.execute();

        // Limpiamos la carpeta de destino antes de empezar para evitar
        // mezclar resultados de ejecuciones anteriores
        eliminarDirectorioRecursivo(RUTA_DESTINO);

        Path carpetaRaiz = Path.of("./entorno_examen_logs");
        organizaCaosLogs(carpetaRaiz);
    }

    /**
     * - Recorre recursivamente la carpeta raíz en busca de ficheros de log,
     * los mueve a su ubicación organizada y extrae los errores de cada uno.
     *
     * @param carpetaRaiz - Ruta del directorio origen donde están los logs mezclados.
     */
    private static void organizaCaosLogs(Path carpetaRaiz) {

        // Lista acumuladora de todos los errores encontrados en todos los ficheros
        List<DetalleError> todosLosErrores = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(carpetaRaiz)) {

            // Solo procesamos ficheros regulares que terminen en .log
            paths.filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".log"))
                    .forEach(p -> {
                        Matcher m = patron.matcher(p.getFileName().toString());

                        // Si el nombre coincide con el patrón esperado
                        if (m.matches()) {
                            String server = m.group("server");
                            String app = m.group("app");

                            // Construimos la ruta destino organizada por servidor y aplicación
                            Path destDir = RUTA_DESTINO.resolve("server" + server).resolve("app" + app);

                            try {
                                // Creamos los directorios intermedios si no existen
                                Files.createDirectories(destDir);

                                // Construimos el nuevo nombre del fichero con el prefijo "procesado_"
                                Path destFile = destDir.resolve("procesado_app" + app + "_server" + server + ".log");

                                // Movemos el fichero a su nueva ubicación organizada
                                Files.move(p, destFile);

                                // Inspeccionamos el fichero ya movido en busca de líneas de nivel ERROR,
                                // usando el patrón correspondiente según la aplicación
                                Pattern logPattern = app.equals("1") ? patronLogApp1 : patronLogApp2;
                                List<DetalleError> erroresFichero = buscarErrores(destFile, logPattern, server, app);

                                // Añadimos los errores encontrados a la lista global
                                todosLosErrores.addAll(erroresFichero);

                            } catch (IOException | LogException e) {
                                System.err.println("Error procesando fichero " + p + ": " + e.getMessage());
                            }
                        }
                    });

        } catch (IOException e) {
            System.out.println("Error general recorriendo directorios: " + e.getMessage());
        }

        // Una vez procesados todos los ficheros, escribimos el reporte global de errores
        escribirErrorAFichero(todosLosErrores);
    }

    /**
     * - Lee un fichero de log línea a línea y extrae aquellas cuyo nivel sea ERROR.
     *
     * @param p          - Ruta del fichero a analizar.
     * @param logPattern - Patrón regex correspondiente al formato de la aplicación.
     * @param server     - ID del servidor de origen.
     * @param app        - ID de la aplicación de origen.
     * @return           - Lista de errores encontrados en el fichero.
     * @throws LogException - Si ocurre un error al leer el fichero.
     */
    private static List<DetalleError> buscarErrores(Path p, Pattern logPattern, String server, String app) throws LogException {
        List<DetalleError> errores = new ArrayList<>();

        try (Stream<String> lineas = Files.lines(p)) {
            lineas.forEach(linea -> {
                // Aplicamos el patrón a cada línea para obtener un Matcher
                Matcher m = logPattern.matcher(linea);

                // Descartamos las líneas que no coinciden y nos quedamos solo con las de nivel ERROR
                if (m.matches() && m.group("nivel").equals("ERROR")) {
                    String fecha = m.group("fecha");
                    String hora = m.group("hora");
                    String desc = m.group("mensaje");

                    // Unificamos el formato de la fecha a dd/MM/yyyy
                    if (app.equals("1")) {
                        // app 1 viene en formato yyyy/MM/dd
                        String[] partes = fecha.split("/");
                        fecha = partes[2] + "/" + partes[1] + "/" + partes[0];
                    } else {
                        // app 2 viene en formato dd-MM-yyyy
                        fecha = fecha.replace("-", "/");
                    }

                    // Construimos el objeto DetalleError con los datos extraídos
                    errores.add(new DetalleError(server, app, fecha, hora, desc));
                }
            });
        } catch (IOException e) {
            throw new LogException("Fallo de E/S al leer el fichero de log: " + p.toString());
        }

        return errores;
    }

    /**
     * - Serializa la lista de errores a JSON con formato legible
     * y la escribe en un fichero de reporte dentro de la carpeta de destino.
     *
     * @param errores - Lista de errores recolectados a escribir.
     */
    private static void escribirErrorAFichero(List<DetalleError> errores) {
        ReporteErrores reporte = new ReporteErrores(errores.size(), errores);

        // Configuramos Gson para que el JSON resultante tenga indentaciones (Pretty Printing)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path file = RUTA_DESTINO.resolve("reporte_errores.json");

        try {
            // Aseguramos que el directorio raíz de destino exista
            if (!Files.exists(RUTA_DESTINO)) {
                Files.createDirectories(RUTA_DESTINO);
            }
            Files.writeString(file, gson.toJson(reporte));
            System.out.println("Reporte final generado en: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir el JSON: " + e.getMessage());
        }
    }

    /**
     * - Borra un directorio y todo su contenido de forma recursiva.
     * Los ficheros y subdirectorios se eliminan antes que sus padres
     * para evitar errores al borrar directorios no vacíos.
     *
     * @param ruta - Ruta del directorio a eliminar.
     */
    private static void eliminarDirectorioRecursivo(Path ruta) {
        if (Files.exists(ruta)) {
            try (Stream<Path> walk = Files.walk(ruta)) {
                walk.sorted(java.util.Comparator.reverseOrder()) // Borra hijos antes que padres
                        .forEach(p -> {
                            try {
                                Files.delete(p);
                            } catch (IOException e) {
                                System.err.println("No se pudo borrar: " + p + " -> " + e.getMessage());
                            }
                        });
                System.out.println("Carpeta de destino limpia.");
            } catch (IOException e) {
                System.err.println("Error al intentar limpiar el directorio: " + e.getMessage());
            }
        }
    }
}