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

import java.io.Serializable;

/**
 * Esta clase representa los posibles mensajes que el cliente manda al servidor del juego.
 * @author Federico Peinado
 */
// Se parece a un Java Bean, s�lo que no tiene constructor sin argumentos, le faltan setters y los getters est�n escritos en espa�ol (dameX) en vez de en ingl�s (getX)
public final class MensajeCliente implements Serializable {

    // Aconsejable para guardar el n�mero de versi�n de los objetos serializados de esta clase
    private static final long serialVersionUID = 1L;
    
    private final Jugada jugada;
    
    /**
     * El constructor de un mensaje del cliente. Todos son iguales, informan de la jugada que se desea realizar.
     * @param jugada La jugada a realizar
     */
    public MensajeCliente(final Jugada jugada){
        if (jugada == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de cliente con una jugada nula.");        
        this.jugada = jugada;
    }
    
    /**
     * Este m�todo devuelve la jugada que se desea realizar.
     * @return La jugada que se desea realizar
     */
    public Jugada dameJugada() {
        return jugada;
    }
}
