# STATE.md — Estado y contexto del proyecto

> Archivo vivo que registra en todo momento el estado del proyecto,
> el plan y el progreso. Se actualiza al final de cada iteración.

## Proyecto

Calculadora CLI en Java 17 gestionada con Maven. Menú de 5 opciones
(1 sumar, 2 restar, 3 multiplicar, 4 dividir, 5 salir), bucle principal
hasta elegir salir, lectura de 2 operandos por teclado e impresión del resultado.

## Entorno

- JDK: OpenJDK 25 (compila a target 17)
- Maven: 3.9.16 (instalado manualmente en `~/tools/apache-maven-3.9.16`, usar `~/tools/apache-maven-3.9.16/bin/mvn`)
- Git: identidad local `ismael <ismael@localhost>` (solo de este repo)

## Plan de iteraciones

| # | Alcance | Estado |
|---|---------|--------|
| 1 | Estructura Maven + dominio `Calculator` (4 operaciones) + tests JUnit | ✅ Completada |
| 2 | CLI: `Menu`, `InputReader`, `Main` con bucle de opciones | ✅ Completada |
| 3 | Robustez: opción inválida, operandos no numéricos, división por cero sin crash + tests | ✅ Completada |

## Estructura

```
calculadora/
├── pom.xml
├── STATE.md
└── src
    ├── main/java/progia/calculadora/
    │   ├── Main.java          (bucle principal + switch de opciones)
    │   ├── Menu.java          (render del menú, OPCION_SALIR)
    │   ├── InputReader.java   (lectura Scanner)
    │   └── Calculator.java    (dominio: sumar/restar/multiplicar/dividir)
    └── test/java/progia/calculadora/
        ├── CalculatorTest.java (5 tests)
        ├── InputReaderTest.java (8 tests)
        └── MainTest.java      (3 tests)
```

## Cómo usar

```
~/tools/apache-maven-3.9.16/bin/mvn test      # tests de unidad
~/tools/apache-maven-3.9.16/bin/mvn compile
java -cp target/classes progia.calculadora.Main
```

## Decisiones de diseño

- `Calculator` es puro y testable; no depende de I/O.
- `InputReader` devuelve `Optional` en vez de lanzar excepciones: el
  llamador decide cómo reaccionar ante entradas inválidas. Constructor
  de paquete acepta un `Scanner` inyectado para testear.
- Operandos `double` para soportar decimales (admite coma decimal).
- `formatear()` en Main evita el ".0" cuando el resultado es entero.
- `Scanner` con charset UTF-8.
- `Main` captura `IllegalArgumentException` de división por cero.

## Estado actual

✅ **Proyecto completo.** 3 iteraciones entregadas, 16 tests de unidad
verdes, flujo interactivo verificado (entradas inválidas, división por
cero y salida se gestionan sin crash).
