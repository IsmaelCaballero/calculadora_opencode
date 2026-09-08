package progia.calculadora;

/**
 * Renderiza el menú de opciones de la calculadora.
 */
public class Menu {

    public static final int OPCION_SALIR = 5;

    public void imprimir() {
        System.out.println();
        System.out.println("====== CALCULADORA ======");
        System.out.println("1 - Sumar");
        System.out.println("2 - Restar");
        System.out.println("3 - Multiplicar");
        System.out.println("4 - Dividir");
        System.out.println("5 - Salir");
        System.out.print("Elige una opcion (1-5): ");
    }
}
