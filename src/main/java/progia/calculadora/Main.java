package progia.calculadora;

import java.util.Optional;

/**
 * Punto de entrada de la aplicación de línea de comandos.
 * Ejecuta un bucle que muestra el menú, lee la opción,
 * los dos operandos y muestra el resultado, hasta que el
 * usuario elija salir. Todas las entradas inválidas se
 * avisan al usuario sin interrumpir la aplicación.
 */
public class Main {

    /**
     * El constructor está implícito (por defecto); la clase solo
     * contiene métodos estáticos, así que no necesita instancia.
     */
    public Main() {
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * <p>Flujo de ejecución:</p>
     * <ol>
     *   <li>Saluda al usuario.</li>
     *   <li>Entra en un bucle que, en cada iteración:
     *     <ol>
     *       <li>Imprime el {@link Menu menú de opciones}.</li>
     *       <li>Lee la opción elegida ({@link InputReader#leerOpcion(int)}).</li>
     *       <li>Si la opción es {@link Menu#OPCION_SALIR}, termina.</li>
     *       <li>Lee los dos operandos ({@link InputReader#leerOperando(String)}).</li>
     *       <li>Descarga la operación correspondiente a {@link Calculator}
     *           e imprime el resultado formateado.</li>
     *     </ol>
     *   </li>
     *   <li>Libera la {@link InputReader} y termina.</li>
     * </ol>
     *
     * <p>Todos los errores de entrada (opción inválida, operando no
     * numérico, división por cero) se comunican al usuario y el bucle
     * continúa; la aplicación no se interrumpe ante entradas inválidas.</p>
     *
     * @param args argumentos de línea de comandos. No se utilizan;
     *             el programa siempre se maneja por teclado interactivo.
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Menu menu = new Menu();
        InputReader reader = new InputReader();

        System.out.println("Bienvenido a la Calculadora CLI");

        boolean salir = false;
        while (!salir) {
            menu.imprimir();

            Optional<Integer> opcionOpt = reader.leerOpcion(Menu.OPCION_SALIR);

            if (opcionOpt.isEmpty()) {
                System.out.println("\nEntrada no válida. Elige un número entre 1 y 5.");
                continue;
            }

            int opcion = opcionOpt.get();

            if (opcion == Menu.OPCION_SALIR) {
                salir = true;
                System.out.println("Adiós, hasta la próxima.");
                continue;
            }

            Optional<Double> aOpt = reader.leerOperando("Introduce el primer operando");
            if (aOpt.isEmpty()) {
                System.out.println("\nEntrada no válida: se esperaba un número.");
                continue;
            }

            Optional<Double> bOpt = reader.leerOperando("Introduce el segundo operando");
            if (bOpt.isEmpty()) {
                System.out.println("\nEntrada no válida: se esperaba un número.");
                continue;
            }

            double a = aOpt.get();
            double b = bOpt.get();

            try {
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
                        System.out.println("\nOpción no válida. Elige un número entre 1 y 5.");
                        continue;
                    }
                }

                System.out.printf("El resultado de la %s es: %s%n%n", operacion, formatear(resultado));
            } catch (IllegalArgumentException e) {
                System.out.println("\nERROR: " + e.getMessage());
            }
        }

        reader.cerrar();
    }

    /**
     * Convierte un resultado numérico a una cadena apta para
     * mostrar al usuario, limpiando los decimales sobrantes.
     *
     * <ul>
     *   <li>{@code 5.0} &rarr; {@code "5"}</li>
     *   <li>{@code 1.5} &rarr; {@code "1.5"}</li>
     *   <li>{@code Infinity} &rarr; {@code "Infinito"}</li>
     *   <li>{@code -Infinity} &rarr; {@code "-Infinito"}</li>
     * </ul>
     *
     * @param valor resultado de la operación a mostrar.
     * @return la representación formateada, sin sufijo {@code .0}
     *         si el valor es un entero.
     */
    static String formatear(double valor) {
        if (Double.isInfinite(valor)) {
            return valor > 0 ? "Infinito" : "-Infinito";
        }
        if (valor == Math.rint(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }
}
