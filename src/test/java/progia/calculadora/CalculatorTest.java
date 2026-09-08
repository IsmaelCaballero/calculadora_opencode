package progia.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test
    @DisplayName("Suma")
    void sumarDeberiaSumarDosNumeros() {
        assertEquals(5.0, calc.sumar(2.0, 3.0));
        assertEquals(0.0, calc.sumar(-1.0, 1.0));
    }

    @Test
    @DisplayName("Resta")
    void restarDeberiaRestarDosNumeros() {
        assertEquals(-1.0, calc.restar(2.0, 3.0));
        assertEquals(5.0, calc.restar(8.0, 3.0));
    }

    @Test
    @DisplayName("Multiplicación")
    void multiplicarDeberiaMultiplicarDosNumeros() {
        assertEquals(6.0, calc.multiplicar(2.0, 3.0));
        assertEquals(0.0, calc.multiplicar(0.0, 100.0));
    }

    @Test
    @DisplayName("División")
    void dividirDeberiaDividirDosNumeros() {
        assertEquals(1.5, calc.dividir(3.0, 2.0));
        assertEquals(-2.0, calc.dividir(6.0, -3.0));
    }

    @Test
    @DisplayName("División por cero lanza excepción")
    void dividirPorCeroDebeLanzarExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> calc.dividir(5.0, 0.0));
    }
}
