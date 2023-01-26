/**
 * LABORATORIO DE PROGRAMACIÓN DE SISTEMAS 2012/2013
 * Ingeniería Técnica en Informática de Sistemas
 * Departamento de Ingeniería del Software e Inteligencia Artificial
 * Facultad de Informática, Universidad Complutense de Madrid
 *
 * TRES EN RAYA - VERSIÓN CLIENTE/SERVIDOR
 * Por Federico Peinado
 * 
 * Adaptación del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
 * http://www.deitel.com/Books/Java/JavaHowtoProgram9eEarlyObjectsVersion/tabid/3622/Default.aspx
 */
package es.ucm.fdi.lps.tresenraya;

/**
 * Esta clase representa todas las constantes relevantes tanto para el cliente como para el servidor del juego.
 * @author Federico Peinado
 */
public final class Parametros {
    
    // El constructor es privado para que no lo puedan usar otras clases.
    private Parametros() { 
        throw new AssertionError("No se permite crear ejemplares de esta clase.");
    }

    /** Número de puerto para la conexión por red */
    public static final int PUERTO_RED = 55555;    
    
    /** Dimensión del tablero de juego (siempre es cuadrado, DIMENSION_TABLERO x DIMENSION_TABLERO). En realidad este valor todavía no se puede modificar, el programa sólo funciona con tableros de 3x3. */
    public static final int DIMENSION_TABLERO = 3; 
    
    // El número de jugadores (2) va implícito en el número de valores que tiene el enumerado Jugador.
    // De hecho esta implementación no es genérica ni en número de jugador ni en tamaño de tablero :-( 
}
