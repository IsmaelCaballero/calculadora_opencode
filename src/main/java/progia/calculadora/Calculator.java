package progia.calculadora;

/**
 * <p>Clase de dominio que implementa las cuatro operaciones
 * aritméticas básicas sobre números de punto flotante
 * ({@code double}).</p>
 *
 * <p>Esta clase es <b>pura</b>: no depende de entrada ni salida,
 * por lo que es fácilmente testeable. Los cuatro métodos
 * {@code sumar}, {@code restar}, {@code multiplicar} y
 * {@code dividir} reciben dos operandos y devuelven el resultado.
 * El único caso no numérico soportado es la división por cero,
 * que se señala con una {@link IllegalArgumentException}.</p>
 *
 * <h2>Uso</h2>
 * <pre>{@code
 * Calculator calc = new Calculator();
 * double suma = calc.sumar(2.0, 3.0);      // 5.0
 * double cociente = calc.dividir(10.0, 4.0); // 2.5
 * }</pre>
 *
 * @author ismael
 * @since  1.0
 * @see    #sumar(double, double)
 * @see    #restar(double, double)
 * @see    #multiplicar(double, double)
 * @see    #dividir(double, double)
 */
public class Calculator {

    /**
     * Crea una nueva instancia de la calculadora.
     *
     * <p>La clase no mantiene estado; el constructor simplemente
     * existe para que la instancia pueda crearse.</p>
     */
    public Calculator() {
    }

    /**
     * Calcula la <b>suma</b> de dos números.
     *
     * @param a primer operando sumando.
     * @param b segundo operando sumando.
     * @return la suma {@code a + b}.
     */
    public double sumar(double a, double b) {
        return a + b;
    }

    /**
     * Calcula la <b>resta</b> de dos números.
     *
     * @param a operando minuendo (el que se resta <i>de</i>).
     * @param b operando sustraendo (el que se resta <i>de</i> {@code a}).
     * @return la diferencia {@code a - b}.
     */
    public double restar(double a, double b) {
        return a - b;
    }

    /**
     * Calcula el <b>producto</b> de dos números.
     *
     * @param a primer factor.
     * @param b segundo factor.
     * @return el producto {@code a * b}.
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Calcula el <b>cuociente</b> de dos números.
     *
     * @param a dividendo (el número que se divide).
     * @param b divisor (el número entre el que se divide {@code a}).
     * @return el cociente {@code a / b}.
     * @throws IllegalArgumentException si {@code b == 0}, ya que la
     *         división por cero no está definida.
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("División por cero no permitida");
        }
        return a / b;
    }
}
