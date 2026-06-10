package Examen_Colecciones2026;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private List<Empleado> empleados;

    public Principal() {
        empleados = new ArrayList<>();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.inicializarEmpleados();

        // Este empleado obviamente existe
        principal.buscarEmpleado(principal.empleados.getFirst().getDni());

        // Este empleado no existe
        principal.buscarEmpleado("12345678A");
        System.out.println();

        System.out.println("#############");
        System.out.println("Número de empleados por departamento:");
        principal.mostrarNumeroEmpleadosPorDepartamento();
        System.out.println();

        System.out.println("#############");
        System.out.printf("Porcentaje de retención para los trabajadores que llevan menos de 1 año trabajando: %.2f%%%n",
                principal.porcentajeRetencionTrabajadoresNuevos());
        System.out.println();

        System.out.println("#############");
        principal.empleadosQueMasCobran();
        System.out.println();

        System.out.println("#############");
        System.out.println("Coste total en salarios");
        principal.costeTotalSalarios(YearMonth.of(2024, 12));
        System.out.println();

        System.out.println("#############");
        System.out.println("Todos los empleados ordenados por antigüedad");
        principal.mostrarTodosEmpleadosOrdenados();
        System.out.println();
    }

    // 1. Buscar empleado por DNI
    private void buscarEmpleado(String dni) {
        empleados.stream()
                // Filtramos para dejar pasar solo al empleado con el DNI exacto
                .filter(empleado -> empleado.getDni().equalsIgnoreCase(dni))
                // Nos quedamos con el primero que encuentre
                .findFirst()
                // Si lo encuentra (ifPresent), imprime sus datos. Si no (OrElse), imprime el error.
                .ifPresentOrElse(
                        e -> System.out.println(e.getNombre() + " " + e.getApellido() + " (" + e.getDni() + ") - Departamento: " + e.getDepartamento()),
                        () -> System.out.println("El empleado con dni " + dni + " no existe.")
                );
    }

    // 2. Recuento por departamentos
    private void mostrarNumeroEmpleadosPorDepartamento() {
        Map<Departamento, Long> recuento = empleados.stream()
                // Agrupamos usando como clave el Departamento, y como valor contamos cuántos hay
                .collect(Collectors.groupingBy(Empleado::getDepartamento, Collectors.counting()));

        // Recorremos el diccionario resultante para imprimirlo
        recuento.forEach((dept, cantidad) -> System.out.println(dept + ": " + cantidad));
    }

    // 3. Retención de los empleados nuevos
    private double porcentajeRetencionTrabajadoresNuevos() {
        YearMonth haceUnAno = YearMonth.now().minusYears(1);

        return empleados.stream()
                // Filtramos: la fecha de su primera nómina debe ser posterior a hace 1 año
                .filter(empleado -> empleado.devolverFecha().isAfter(haceUnAno))
                // Extraemos el valor del porcentaje de retención de su ÚLTIMA nómina
                .mapToDouble(empleado -> empleado.devolverUltimaNomina().getRetencion())
                // Calculamos la media matemática de todos esos porcentajes
                .average()
                // Si no hay empleados nuevos y la media da error, devolvemos 0.0 por defecto
                .orElse(0.0);
    }

    // 4. Salario máximo
    private void empleadosQueMasCobran() {
        // PASO 1: Descubrir cuál es el salario neto más alto de toda la empresa
        double maxSalarioNeto = empleados.stream()
                // Mapeamos a cada empleado a su salario neto usando el método auxiliar
                .mapToDouble(this::calcularSalarioNetoAuxiliar)
                // Nos quedamos con el valor máximo
                .max()
                .orElse(0.0);

        System.out.printf("Empleados que más cobran (%.2f€):%n", maxSalarioNeto);

        // PASO 2: Recorrer de nuevo e imprimir a los empleados que cobren exactamente ese máximo (por si hay empate)
        empleados.stream()
                .filter(empleado -> calcularSalarioNetoAuxiliar(empleado) == maxSalarioNeto)
                .forEach(e -> System.out.println(e.getNombre() + " " + e.getApellido() + " (" + e.getDni() + ") - Departamento: " + e.getDepartamento()));
    }

    // Método auxiliar sugerido por el examen para calcular el salario neto de la última nómina
    private double calcularSalarioNetoAuxiliar(Empleado empleado) {
        Nomina ultima = empleado.devolverUltimaNomina();
        return ultima.getSalarioBase() * (1 - (ultima.getRetencion() / 100));
    }

    // 5. Coste salarial total
    private void costeTotalSalarios(YearMonth fecha) {
        double costeTotal = empleados.stream()
                // flatMap coge las listas de nóminas de todos los empleados y las "aplana" en un único flujo gigante de nóminas
                .flatMap(empleado -> empleado.getNominas().stream())
                // Filtramos para dejar pasar solo las nóminas que coincidan con la fecha pedida
                .filter(nomina -> nomina.getMesNomina().equals(fecha))
                // Extraemos el salario bruto (salario base) de esas nóminas concretas
                .mapToDouble(Nomina::getSalarioBase)
                // Sumamos todo el dinero
                .sum();

        // Se usa %d/%d para extraer el mes y el año de la fecha dada en el formato que pide el ejemplo
        System.out.printf("El coste total para la fecha %d/%d es %.2f€%n", fecha.getMonthValue(), fecha.getYear(), costeTotal);
    }

    // 6. Listado por antigüedad
    private void mostrarTodosEmpleadosOrdenados() {
        empleados.stream()
                // Comparamos primero por la fecha de la primera nómina, y si empatan, desempatamos por el apellido
                .sorted(Comparator.comparing(Empleado::devolverFecha).thenComparing(Empleado::getApellido))
                // Imprimimos uno a uno con el formato exacto del examen
                .forEach(e -> System.out.println(
                        e.getNombre() + " " + e.getApellido() + " (" + e.getDni() +
                                ") - Departamento: " + e.getDepartamento() +
                                " - En la empresa desde " + e.devolverFecha().getMonthValue() + "/" + e.devolverFecha().getYear()
                ));
    }

    public void inicializarEmpleados() {
        Random random = new Random();

        // Configuración para generar 5 empleados por cada departamento
        for (Departamento dept : Departamento.values()) {
            // Los departamentos tendrán entre 3 y 7 empleados
            int numEmpleados = random.nextInt(5) + 3;
            for (int i = 1; i <= numEmpleados; i++) {
                String nombre = "Empleado " + i + "_" + dept.name().charAt(0);
                String apellido = "Apellido " + (random.nextInt(10) + i);
                String dni = (random.nextInt(90000000) + 10000000) + "X";

                Empleado emp = new Empleado(nombre, apellido, dni, dept);

                // Generamos entre 5 y 30 nóminas para cada uno
                // Algunos empezarán hace 30 meses, otros hace solo 5
                int numNominas = random.nextInt(26) + 5;
                YearMonth mesInicio = YearMonth.now().minusMonths(numNominas);

                for (int m = 0; m < numNominas; m++) {
                    YearMonth mesNomina = mesInicio.plusMonths(m);
                    // Salarios entre 1500 y 3500
                    double salarioBase = 1500 + (random.nextDouble() * 2000);
                    // Retenciones entre 10% y 22%
                    double retencion = 10 + (random.nextDouble() * 12);

                    emp.getNominas().add(new Nomina(mesNomina, salarioBase, retencion));
                }
                empleados.add(emp);
            }
        }
    }
}