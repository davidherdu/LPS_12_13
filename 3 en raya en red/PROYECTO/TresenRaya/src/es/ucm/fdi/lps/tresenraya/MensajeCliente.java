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

import java.io.Serializable;

/**
 * Esta clase representa los posibles mensajes que el cliente manda al servidor del juego.
 * @author Federico Peinado
 */
// Se parece a un Java Bean, sólo que no tiene constructor sin argumentos, le faltan setters y los getters están escritos en español (dameX) en vez de en inglés (getX)
public final class MensajeCliente implements Serializable {

    // Aconsejable para guardar el número de versión de los objetos serializados de esta clase
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
     * Este método devuelve la jugada que se desea realizar.
     * @return La jugada que se desea realizar
     */
    public Jugada dameJugada() {
        return jugada;
    }
}
