package progia.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    private InputReader reader;

    private InputReader readerDe(InputStream in) {
        return new InputReader(new java.util.Scanner(in, "UTF-8"));
    }

    @Test
    @DisplayName("Opción válida dentro del rango")
    void leerOpcionValidaDeberiaDevolverElNumero() {
        reader = readerDe(stream("3\n"));
        assertEquals(Optional.of(3), reader.leerOpcion(5));
    }

    @Test
    @DisplayName("Opción fuera de rango devuelve empty")
    void opcionFueraDeRangoDebeDevolverEmpty() {
        assertEquals(Optional.empty(), readerDe(stream("0\n")).leerOpcion(5));
        assertEquals(Optional.empty(), readerDe(stream("9\n")).leerOpcion(5));
    }

    @Test
    @DisplayName("Opción no numérica devuelve empty")
    void opcionNoNumericaDebeDevolverEmpty() {
        assertEquals(Optional.empty(), readerDe(stream("abc\n")).leerOpcion(5));
    }

    @Test
    @DisplayName("Opción con línea vacía devuelve empty")
    void opcionLineaVaciaDebeDevolverEmpty() {
        assertEquals(Optional.empty(), readerDe(stream("\n")).leerOpcion(5));
    }

    @Test
    @DisplayName("Operando entero")
    void leerOperandoEnteroDeberiaDevolverElValor() {
        assertEquals(Optional.of(12.0), readerDe(stream("12\n")).leerOperando("a"));
    }

    @Test
    @DisplayName("Operando decimal (punto y coma)")
    void leerOperandoDecimalDeberiaDevolverElValor() {
        assertEquals(Optional.of(3.14), readerDe(stream("3.14\n")).leerOperando("a"));
        assertEquals(Optional.of(31.4), readerDe(stream("31,4\n")).leerOperando("a"));
    }

    @Test
    @DisplayName("Operando no numérico devuelve empty")
    void operandoNoNumericoDebeDevolverEmpty() {
        assertEquals(Optional.empty(), readerDe(stream("hola\n")).leerOperando("a"));
    }

    @Test
    @DisplayName("Fin de entrada devuelve empty sin excepción")
    void finDeEntradaDebeDevolverEmpty() {
        assertEquals(Optional.empty(), readerDe(stream("")).leerOpcion(5));
        assertEquals(Optional.empty(), readerDe(stream("")).leerOperando("a"));
    }

    private static InputStream stream(String contenido) {
        return new ByteArrayInputStream(contenido.getBytes(StandardCharsets.UTF_8));
    }
}
