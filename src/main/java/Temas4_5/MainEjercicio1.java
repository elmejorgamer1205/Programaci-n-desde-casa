package Temas4_5;

import Exceptions.MiEntradaSalidaException;
import io.MiEntradaSalida;

public class MainEjercicio1 {
    static void main(String[] args) throws MiEntradaSalidaException {
        Rectangulo r = new Rectangulo();

        int longitud = MiEntradaSalida.leerEnteroRango("Di una longitud que le quieras dar al rectángulo: ", 0 , 20);
        int ancho = MiEntradaSalida.leerEnteroRango("Di un ancho que le quieras dar al rectángulo: ", 0, 20);
    }
}
