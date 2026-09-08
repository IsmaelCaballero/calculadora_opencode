package progia.calculadora;

/**
 * Punto de entrada de la aplicación de línea de comandos.
 * Ejecuta un bucle que muestra el menú, lee la opción,
 * los dos operandos y muestra el resultado, hasta que el
 * usuario elija salir.
 */
public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Menu menu = new Menu();
        InputReader reader = new InputReader();

        System.out.println("Bienvenido a la Calculadora CLI");

        boolean salir = false;
        while (!salir) {
            menu.imprimir();

            int opcion = reader.leerOpcion(Menu.OPCION_SALIR);

            if (opcion == Menu.OPCION_SALIR) {
                salir = true;
                System.out.println("Adiós, hasta la próxima.");
                continue;
            }

            double a = reader.leerOperando("Introduce el primer operando");
            double b = reader.leerOperando("Introduce el segundo operando");

            double resultado;
            String operacion;

            switch (opcion) {
                case 1 -> {
                    resultado = calc.sumar(a, b);
                    operacion = "suma";
                }
                case 2 -> {
                    resultado = calc.restar(a, b);
                    operacion = "resta";
                }
                case 3 -> {
                    resultado = calc.multiplicar(a, b);
                    operacion = "multiplicación";
                }
                case 4 -> {
                    resultado = calc.dividir(a, b);
                    operacion = "división";
                }
                default -> {
                    System.out.println("Opción no válida. Elige un número entre 1 y 5.");
                    continue;
                }
            }

            System.out.printf("El resultado de la %s es: %s%n%n", operacion, formatear(resultado));
        }

        reader.cerrar();
    }

    /**
     * Formatea un double para mostrarlo sin el trailing ".0" si es entero.
     */
    private static String formatear(double valor) {
        if (valor == Math.rint(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }
}
