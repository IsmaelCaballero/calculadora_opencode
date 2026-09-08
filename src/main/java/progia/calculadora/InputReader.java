package progia.calculadora;

/**
 * Gestiona la interacción con el teclado: lectura de opciones de menú
 * y de operandos numéricos.
 */
public class InputReader {

    private final java.util.Scanner scanner;

    public InputReader() {
        this.scanner = new java.util.Scanner(System.in, "UTF-8");
    }

    /**
     * Lee una opción de menú (entero del 1 al max).
     */
    public int leerOpcion(int max) {
        String linea = scanner.nextLine().trim();
        return Integer.parseInt(linea);
    }

    /**
     * Lee un operando numérico (doble).
     */
    public double leerOperando(String etiqueta) {
        System.out.print(etiqueta + ": ");
        String linea = scanner.nextLine().trim();
        return Double.parseDouble(linea);
    }

    public void cerrar() {
        scanner.close();
    }
}
