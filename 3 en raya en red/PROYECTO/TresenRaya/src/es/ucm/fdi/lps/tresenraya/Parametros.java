/**
 * LABORATORIO DE PROGRAMACI�N DE SISTEMAS 2012/2013
 * Ingenier�a T�cnica en Inform�tica de Sistemas
 * Departamento de Ingenier�a del Software e Inteligencia Artificial
 * Facultad de Inform�tica, Universidad Complutense de Madrid
 *
 * TRES EN RAYA - VERSI�N CLIENTE/SERVIDOR
 * Por Federico Peinado
 * 
 * Adaptaci�n del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
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

    /** N�mero de puerto para la conexi�n por red */
    public static final int PUERTO_RED = 55555;    
    
    /** Dimensi�n del tablero de juego (siempre es cuadrado, DIMENSION_TABLERO x DIMENSION_TABLERO). En realidad este valor todav�a no se puede modificar, el programa s�lo funciona con tableros de 3x3. */
    public static final int DIMENSION_TABLERO = 3; 
    
    // El n�mero de jugadores (2) va impl�cito en el n�mero de valores que tiene el enumerado Jugador.
    // De hecho esta implementaci�n no es gen�rica ni en n�mero de jugador ni en tama�o de tablero :-( 
}
