package progia.calculadora;

import java.util.Optional;
import java.util.Scanner;

/**
 * <p>Capa de <b>entrada por teclado</b> de la calculadora.
 * Encapsula la lectura de opciones de menú y de operandos
 * numéricos a través de un {@link Scanner} sobre
 * {@link System#in} con codificación UTF-8.</p>
 *
 * <p>Filosofía de diseño: <b>no lanza excepciones</b> para
 * entradas inválidas. En su lugar, los métodos {@code leer*}
 * devuelven un {@link Optional} vacío ({@link Optional#empty()})
 * y es el llamador quien decide cómo reaccionar (reintentar,
 * avisar al usuario, etc.). Esto separa la detección del error
 * de la decisión sobre él.</p>
 *
 * <h2>Uso</h2>
 * <pre>{@code
 * InputReader reader = new InputReader();
 * reader.leerOpcion(5).ifPresent(n -> System.out.println("Opción: " + n));
 * reader.leerOperando("Primer número").ifPresentOrElse(
 *         d -> System.out.println("Valor: " + d),
 *         () -> System.out.println("No es un número"));
 * reader.cerrar();
 * }</pre>
 *
 * <p>La variante que admite inyectar un {@code Scanner}
 * (constructor de visibilidad paquete) existe para facilitar
 * pruebas unitarias sin depender de {@code System.in}.</p>
 *
 * @author ismael
 * @since  1.0
 * @see    Scanner
 * @see    Optional
 */
public class InputReader {

    /**
     * Fuente de líneas de texto usada para todos los métodos
     * de lectura. La clase no expone este {@code Scanner};
     * el llamador se comunica siempre a través de los métodos
     * de alto nivel.
     */
    private final Scanner scanner;

    /**
     * Construye un {@code InputReader} que lee de la
     * {@link System#in entrada estándar} usando UTF-8.
     */
    public InputReader() {
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     * Constructor solo para pruebas: permite inyectar un
     * {@code Scanner} sobre cualquier fuente de caracteres.
     *
     * @param scanner {@code Scanner} no {@code null} de donde
     *               se leerán las líneas.
     */
    InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Lee una <b>opción de menú</b>, es decir, un entero no
     * negativo entre {@code 1} y {@code max} (ambos inclusive).
     *
     * <p>Se trata la línea leída así:</p>
     * <ul>
     *   <li>Se recorta (espacios en blanco)</li>
     *   <li>Se exige que sea un entero válido</li>
     *   <li>Se exige que caiga en el rango {@code [1, max]}</li>
     * </ul>
     *
     * <p>En cualquiera de los tres fallos se devuelve
     * {@link Optional#empty()}, sin lanzar excepción.</p>
     *
     * @param max valor máximo permitido para la opción.
     *            Debe ser un entero positivo.
     * @return un {@code Optional} que contiene la opción válida
     *         leída, o {@code empty} si la entrada está vacía,
     *         no es numérica o está fuera de rango.
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
     * Solicita al usuario un <b>operando numérico</b> mediante
     * una etiqueta descriptiva que se muestra por pantalla.
     *
     * <p>Se acepta tanto la notación decimal con punto
     * ({@code 3.14}) como con coma ({@code 3,14}); esta última
     * se normaliza a punto antes de parsear. El valor
     * resultante es un {@code double}.</p>
     *
     * @param etiqueta texto impreso al usuario antes de leer
     *                 la entrada (p. ej.
     *                 {@code "Introduce el primer operando"}).
     *                 Se escribe exactamente como se proporciona.
     * @return un {@code Optional} que contiene el valor
     *         numérico leído, o {@code empty} si la entrada no
     *         es un número válido o se agotó la entrada de
     *         teclado.
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

    /**
     * Cierra el {@link Scanner} subyacente. Después de esta
     * llamada el objeto no debe reutilizarse (los métodos de
     * lectura dejarán de funcionar).
     */
    public void cerrar() {
        scanner.close();
    }
}
