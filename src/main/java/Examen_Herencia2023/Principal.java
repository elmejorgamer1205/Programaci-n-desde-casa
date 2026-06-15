package Examen_Herencia2023;

/**
 * Clase principal que contiene el punto de entrada (main) del programa
 * y gestiona el menú de usuario.
 */
public class Principal {

    public static final int MAX_JUGUETES = 10;
    public static Juguete[] juguetes = new Juguete[MAX_JUGUETES];
    public static int numJuguetes = 0;

    public static void main(String[] args) {
        boolean salir = false;

        /*
         * Bloque de carga inicial.
         * Generamos unos juguetes de prueba para tener datos en el array.
         */
        try {
            FiguraMadera j1 = new FiguraMadera("Cubo", "Cubemax", "España", 2020, "Rojo", 6);
            FiguraMadera j2 = new FiguraMadera("Dodecaedro", "Cubemax", "Camerún", 2022, "Amarillo", 12);
            InstrumentoMusical j3 = new InstrumentoMusical("Xilófono", "Matel", "Brasil", 1999, 10);
            VehiculoPlastico j4 = new VehiculoPlastico("Ferrari F11", "Ferrari", 4);
            VehiculoPlastico j5 = new VehiculoPlastico("Camión Pegaso", "Juguematic", 8);
            PiezaLego j6 = new PiezaLego("Bloque de lego", "Lego", 4, "Azul");
            PiezaLego j7 = new PiezaLego("Bloque de lego", "Lego", 2, "Verde");
            addJuguete(j1);
            addJuguete(j2);
            addJuguete(j3);
            addJuguete(j4);
            addJuguete(j5);
            addJuguete(j6);
            addJuguete(j7);

        } catch (JugueteException e1) {
            //Esto no va a pasar
        }


        do {
            System.out.println(menu());
            int opcion = UserDataCollector.getEnteroMinMax("Selecciona una opción", 1, 7);
            switch (opcion) {
                case 1:
                    String nombre = UserDataCollector.getString("Introduce el nombre");
                    String marca = UserDataCollector.getString("Introduce la marca");
                    String origen = UserDataCollector.getString("Introduce el origen de la madera");
                    int anoTala = UserDataCollector.getEntero("Introduce el año de tala de la madera");
                    int numLados = UserDataCollector.getEntero("Introduce el número de lados de la figura");
                    String color = UserDataCollector.getString("Introduce el color de la figura");
                    FiguraMadera f;
                    try {
                        f = new FiguraMadera(nombre, marca, origen, anoTala, color, numLados);
                        addJuguete(f);
                    }
                    catch (JugueteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    String nombre_c2 = UserDataCollector.getString("Introduce el nombre");
                    String marca_c2 = UserDataCollector.getString("Introduce la marca");
                    String origen_c2 = UserDataCollector.getString("Introduce el origen de la madera");
                    int anoTala_c2 = UserDataCollector.getEntero("Introduce el año de tala de la madera");
                    int edadMin_c2 = UserDataCollector.getEntero("Introduce la edad mínima");
                    InstrumentoMusical im;
                    try {
                        im = new InstrumentoMusical(nombre_c2, marca_c2, origen_c2, anoTala_c2, edadMin_c2);
                        addJuguete(im);
                    }
                    catch (JugueteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    String nombre_c3 = UserDataCollector.getString("Introduce el nombre");
                    String marca_c3 = UserDataCollector.getString("Introduce la marca");
                    int numRuedas_c3 = UserDataCollector.getEntero("Introduce el número de ruedas");

                    VehiculoPlastico vp;
                    try {
                        vp = new VehiculoPlastico(nombre_c3, marca_c3, numRuedas_c3);
                        addJuguete(vp);
                    }
                    catch (JugueteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    String nombre_c4 = UserDataCollector.getString("Introduce el nombre");
                    String marca_c4 = UserDataCollector.getString("Introduce la marca");
                    String color_c4 = UserDataCollector.getString("Introduce el color");
                    int unidades_c4 = UserDataCollector.getEntero("Introduce la medida, en unidades");

                    PiezaLego pl;
                    try {
                        pl = new PiezaLego(nombre_c4, marca_c4, unidades_c4, color_c4);
                        addJuguete(pl);
                    }
                    catch (JugueteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    mostrarJuguetes();
                    int opcion1_c5 = UserDataCollector.getEnteroMinMax("Selecciona un juguete", 1, numJuguetes);
                    Juguete j1_c5 = juguetes[opcion1_c5 - 1];

                    if (!(j1_c5 instanceof Apilable)) {
                        System.out.println("El juguete " + j1_c5.getNombre() + " no es apilable");
                        continue;
                    }

                    System.out.println("Selecciona ahora el juguete que quieres apilar");
                    mostrarJuguetes();
                    int opcion2_c5 = UserDataCollector.getEnteroMinMax("Selecciona un juguete", 1, numJuguetes);
                    Juguete j2_c5 = juguetes[opcion2_c5 - 1];

                    try {
                        // Casteo a Apilable para ejecutar el método
                        ((Apilable)j1_c5).apilar(j2_c5);
                    }
                    catch (JugueteException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    for (Juguete j: juguetes) {
                        if (j != null) {
                            System.out.println(j);
                        }
                    }
                    break;

                case 7:
                    // Rompe el bucle do-while
                    salir = true;
            }
        } while (!salir);

        System.out.println("Adiós");

    }

    /*
     * Método que construye el texto del menú principal.
     */
    private static String menu() {
        StringBuilder sb = new StringBuilder("Menú\n");
        sb.append("1. Crear una figura de madera\n");
        sb.append("2. Crear un instrumento musical\n");
        sb.append("3. Crear un vehículo de plástico\n");
        sb.append("4. Crear una pieza de lego\n");
        sb.append("5. Apilar un juguete\n");
        sb.append("6. Mostrar todos los juguetes\n");
        sb.append("7. Salir\n");

        return sb.toString();
    }

    private static void addJuguete(Juguete j) throws JugueteException {
        if (numJuguetes < juguetes.length) {
            juguetes[numJuguetes++] = j;
        }
        else {
            throw new JugueteException("Ya no caben más juguetes");
        }
    }

    private static void mostrarJuguetes() {
        for (int i = 0; i < juguetes.length; i++) {
            if (juguetes[i] != null) {
                System.out.println((i+1) + ": " + juguetes[i]);
            }
        }
    }

}