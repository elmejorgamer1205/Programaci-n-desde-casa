package Examen_Ficheros2026;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * - Clase de utilidad para generar un entorno de prueba caótico con archivos de log.
 */
public class GeneradorLogsExamen {

    private static final Random random = new Random();
    private static final DateTimeFormatter FORMATO_APP1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter FORMATO_APP2 = DateTimeFormatter.ofPattern("dd-MM-yyyy|HH:mm:ss");

    private static final List<String> MENSAJES_INFO = Arrays.asList(
            "Iniciando servicio...", "Conexion a BD establecida.", "Usuario logueado correctamente.",
            "Sincronizacion completada.", "Limpiando cache temporal."
    );
    private static final List<String> MENSAJES_ERROR_APP1 = Arrays.asList(
            "Timeout en la conexion a BD.", "Fallo al autenticar usuario.", "Memoria insuficiente.",
            "No se encuentra el archivo de configuracion."
    );
    private static final List<String> MENSAJES_ERROR_APP2 = Arrays.asList(
            "NullPointerException en el servicio.", "Error 500 al llamar a la API externa.",
            "Disco lleno al intentar escribir.", "Token JWT expirado o invalido."
    );

    /**
     * - Genera la estructura de carpetas y los archivos de log falsos para simular el entorno.
     */
    public static void execute() {
        String baseDir = "entorno_examen_logs";

        crearDirectorio(baseDir);

        // Crear una estructura caótica de carpetas
        String[] subcarpetas = {"", "/var_logs", "/tmp/historico", "/backup_old"};
        for (String sub : subcarpetas) {
            crearDirectorio(baseDir + sub);
        }

        // Generar ficheros log para APP 1
        generarLog(baseDir + "/var_logs", "server1_app1.log", 1, 45);
        generarLog(baseDir, "server2_app1.log", 1, 30);

        // Generar ficheros log para APP 2
        generarLog(baseDir + "/tmp/historico", "server1_app2.log", 2, 50);
        generarLog(baseDir + "/backup_old", "server3_app2.log", 2, 25);

        // Generar ficheros "basura"
        generarFicheroBasura(baseDir, "notas_becario.txt");
        generarFicheroBasura(baseDir + "/var_logs", "server1_app1.bak");
        generarFicheroBasura(baseDir + "/tmp/historico", "config.ini");
        generarFicheroBasura(baseDir + "/backup_old", "app3_error.log");

        System.out.println("✅ ¡Entorno de examen generado con éxito en la carpeta: " + baseDir + "!");
    }

    /**
     * - Crea un directorio si no existe previamente en el sistema.
     *
     * @param ruta - Ruta del directorio a crear.
     */
    private static void crearDirectorio(String ruta) {
        File dir = new File(ruta);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * - Escribe un fichero de log simulado con entradas aleatorias de distintos niveles y formatos.
     *
     * @param ruta          - Directorio donde se guardará el archivo.
     * @param nombreFichero - Nombre del archivo de log.
     * @param tipoApp       - Identificador de la app (1 o 2) para aplicar un formato u otro.
     * @param lineas        - Cantidad de líneas que contendrá el fichero.
     */
    private static void generarLog(String ruta, String nombreFichero, int tipoApp, int lineas) {
        File archivo = new File(ruta, nombreFichero);
        LocalDateTime fechaActual = LocalDateTime.of(2024, 11, random.nextInt(28) + 1, 8, 0);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < lineas; i++) {
                fechaActual = fechaActual.plusSeconds(random.nextInt(120) + 1);

                String nivel = (random.nextInt(10) > 7) ? "ERROR" : (random.nextInt(10) > 5 ? "DEBUG" : "INFO");
                String mensaje;
                String lineaLog;

                if (tipoApp == 1) {
                    mensaje = nivel.equals("ERROR") ?
                            MENSAJES_ERROR_APP1.get(random.nextInt(MENSAJES_ERROR_APP1.size())) :
                            MENSAJES_INFO.get(random.nextInt(MENSAJES_INFO.size()));
                    lineaLog = String.format("%s - [%s] - %s\n", fechaActual.format(FORMATO_APP1), nivel, mensaje);
                } else {
                    mensaje = nivel.equals("ERROR") ?
                            MENSAJES_ERROR_APP2.get(random.nextInt(MENSAJES_ERROR_APP2.size())) :
                            MENSAJES_INFO.get(random.nextInt(MENSAJES_INFO.size()));
                    lineaLog = String.format("[%s] <%s> %s\n", fechaActual.format(FORMATO_APP2), nivel, mensaje);
                }

                bw.write(lineaLog);
            }
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo: " + archivo.getAbsolutePath());
        }
    }

    /**
     * - Genera archivos basura para probar la robustez de los filtros de lectura del alumno.
     *
     * @param ruta   - Directorio donde se creará el fichero basura.
     * @param nombre - Nombre del archivo.
     */
    private static void generarFicheroBasura(String ruta, String nombre) {
        File archivo = new File(ruta, nombre);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("Este es un fichero de prueba que el alumno debe ignorar.\n");
            bw.write("Si el programa procesa esto, fallará la expresión regular.\n");
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo basura: " + archivo.getAbsolutePath());
        }
    }
}