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
     * Formatea un double para mostrarlo sin el trailing ".0" si es entero.
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
