package progia.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    @DisplayName("Formatea enteros sin el sufijo .0")
    void formatearEntero() {
        assertEquals("5", Main.formatear(5.0));
        assertEquals("-3", Main.formatear(-3.0));
        assertEquals("0", Main.formatear(0.0));
    }

    @Test
    @DisplayName("Formatea decimales con su valor")
    void formatearDecimal() {
        assertEquals("1.5", Main.formatear(1.5));
    }

    @Test
    @DisplayName("Formatea infinito")
    void formatearInfinito() {
        assertEquals("Infinito", Main.formatear(Double.POSITIVE_INFINITY));
        assertEquals("-Infinito", Main.formatear(Double.NEGATIVE_INFINITY));
    }
}
