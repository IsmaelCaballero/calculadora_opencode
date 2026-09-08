package progia.calculadora;

/**
 * Clase de dominio que implementa las cuatro operaciones básicas.
 */
public class Calculator {

    public double sumar(double a, double b) {
        return a + b;
    }

    public double restar(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("División por cero no permitida");
        }
        return a / b;
    }
}
