package progia.calculadora;

import java.util.Optional;
import java.util.Scanner;

/**
 * Gestiona la interacción con el teclado: lectura de opciones de menú
 * y de operandos numéricos. Devuelve always {@link Optional} para que
 * el llamador decida cómo reaccionar ante entradas inválidas, sin
 * provocar excepciones no capturadas.
 */
public class InputReader {

    private final Scanner scanner;

    public InputReader() {
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Lee una opción de menú (entero entre 1 y max, ambos inclusive).
     * Devuelve {@link Optional#empty()} si la línea está vacía, no es
     * numérica o está fuera de rango.
     */
    public Optional<Integer> leerOpcion(int max) {
        String linea = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        if (linea.isEmpty()) {
            return Optional.empty();
        }
        try {
            int valor = Integer.parseInt(linea);
            if (valor < 1 || valor > max) {
                return Optional.empty();
            }
            return Optional.of(valor);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Lee un operando numérico (doble).
     * Devuelve {@link Optional#empty()} si la entrada no es numérica válida.
     */
    public Optional<Double> leerOperando(String etiqueta) {
        System.out.print(etiqueta + ": ");
        String linea = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        try {
            return Optional.of(Double.parseDouble(linea.replace(",", ".")));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public void cerrar() {
        scanner.close();
    }
}
