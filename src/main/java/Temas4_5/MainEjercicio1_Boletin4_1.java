package Temas4_5;

import Exceptions.MiEntradaSalidaException;
import io.MiEntradaSalida;

public class MainEjercicio1_Boletin4_1 {
    static void main(String[] args) throws MiEntradaSalidaException {
        Rectangulo r = new Rectangulo();

        int longitud = MiEntradaSalida.leerEnteroRango("Di una longitud que le quieras dar al rectángulo: ", 0 , 20);
        int ancho = MiEntradaSalida.leerEnteroRango("Di un ancho que le quieras dar al rectángulo: ", 0, 20);

        r.setAncho(ancho);
        r.setLongitud(longitud);
        System.out.println("\nEl área del rectángulo sería: " + r.calculoArea());
        System.out.println("\nEl perímetro del rectángulo sería: " + r.calculoPerimetro());
    }
}
