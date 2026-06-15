package Examen_POO2023; // Indica en qué carpeta/paquete está guardado este archivo

public class Jugador {

    // --- ATRIBUTOS ---
    // Usamos 'private' para que nadie pueda modificar estos datos directamente desde fuera de la clase.
    private String nombre; // Guarda el nombre del jugador (ej. "Paco")
    private int edad;      // Guarda la edad del jugador (ej. 19)
    private int golesMarcados; // Guarda los goles que lleva. No es 'static' porque cada jugador tiene los suyos propios.

    // --- CONSTRUCTOR ---
    // Este metodo se llama automáticamente cuando hacemos 'new Jugador(...)'
    public Jugador(String nombre, int edad) {
        this.nombre = nombre; // 'this.nombre' es el atributo de arriba. Le asignamos el valor que llega entre paréntesis.
        this.edad = edad;     // Guardamos la edad recibida.
        this.golesMarcados = 0; // Al crear un jugador nuevo, lógicamente empieza con 0 goles.
    }

    // --- MÉTODOS ---

    // Metodo para registrar un gol nuevo
    public void addGol() {
        this.golesMarcados++; // El '++' le suma exactamente 1 a la variable golesMarcados de este jugador.
    }

    // Metodo que devuelve cuántos goles lleva el jugador
    public int getGolesTotales() {
        return this.golesMarcados; // Devuelve el número entero guardado en el atributo
    }

    // Getter para que otras clases puedan consultar el nombre del jugador
    public String getNombre() {
        return nombre;
    }

    // Getter para que otras clases puedan consultar la edad del jugador
    public int getEdad() {
        return edad;
    }

    // El metodo toString() se usa para imprimir el objeto fácilmente por pantalla
    @Override
    public String toString() {
        // Concatena textos fijos con las variables para mostrar un resumen del jugador
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", golesMarcados=" + golesMarcados +
                '}';
    }
}