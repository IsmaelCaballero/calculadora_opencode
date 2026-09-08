package progia.calculadora;

/**
 * <p>Renderiza el <b>menú de opciones</b> de la calculadora.
 * Contiene además la constante {@link #OPCION_SALIR} que
 * identifica la opción para terminar la aplicación.</p>
 *
 * <p>El menú siempre se imprime por {@link System#out salida estándar}
 * en este formato:</p>
 *
 * <pre>
 *
 * ====== CALCULADORA ======
 * 1 - Sumar
 * 2 - Restar
 * 3 - Multiplicar
 * 4 - Dividir
 * 5 - Salir
 * Elige una opcion (1-5):
 * </pre>
 *
 * <p>Es una clase <b>sin estado</b>; las instancias son
 * intercambiables. La salida es fija en español.</p>
 *
 * @author ismael
 * @since  1.0
 * @see    #imprimir()
 * @see    #OPCION_SALIR
 */
public class Menu {

    /**
     * Crea una nueva instancia del menú.
     *
     * <p>La clase no mantiene estado; el constructor simplemente
     * permite crear una instancia para llamar a
     * {@link #imprimir()}.</p>
     */
    public Menu() {
    }

    /**
     * Valor entero de la opción de menú que termina la
     * aplicación ({@code "5 - Salir"}).
     */
    public static final int OPCION_SALIR = 5;

    /**
     * Imprime el menú completo por la salida estándar y deja
     * el cursor en la línea del prompt, listo para recibir la
     * elección del usuario.
     *
     * <p>Este método <b>no lee</b> nada del teclado; solo
     * escribe el texto del menú y el prompt. La lectura de la
     * opción correspondiente corresponde al llamador
     * (por ejemplo, a través de
     * {@link InputReader#leerOpcion(int)}).</p>
     */
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
