package Temas4_5;

public class Rectangulo {

    private int longitud, ancho;

    public Rectangulo() {
        this.longitud = 1;
        this.ancho = 1;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        if (longitud > 0 && longitud < 20) {
            this.longitud = longitud;
        }
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        if (ancho > 0 && ancho < 20) {
            this.ancho = ancho;
        }
    }

    public int calculoArea(){
        return this.longitud * this.ancho;
    }

    public int calculoPerimetro(){
        return 2 * (this.longitud + this.ancho);
    }
}
