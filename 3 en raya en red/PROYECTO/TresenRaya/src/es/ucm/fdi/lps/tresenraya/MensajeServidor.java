/**
 * LABORATORIO DE PROGRAMACI?N DE SISTEMAS 2012/2013
 * Ingenier?a T?cnica en Inform?tica de Sistemas
 * Departamento de Ingenier?a del Software e Inteligencia Artificial
 * Facultad de Inform?tica, Universidad Complutense de Madrid
 *
 * TRES EN RAYA - VERSI?N CLIENTE/SERVIDOR
 * Por Federico Peinado
 * 
 * Adaptaci?n del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
 * http://www.deitel.com/Books/Java/JavaHowtoProgram9eEarlyObjectsVersion/tabid/3622/Default.aspx
 */
package es.ucm.fdi.lps.tresenraya;

import java.io.Serializable;
import static es.ucm.fdi.lps.tresenraya.MensajeServidor.Tipo.*;

/**
 * Esta clase representa los posibles mensajes que el servidor manda al cliente del juego.
 * @author Federico Peinado
 */
//Se parece a un Java Bean, s?lo que no tiene constructor sin argumentos, le faltan setters y los getters est?n escritos en espa?ol (dameX) en vez de en ingl?s (getX)
public final class MensajeServidor implements Serializable {

    /**
     * Este enumerado representa los posibles mensajes que el servidor puede mandar a sus clientes:
     * JUGADOR_ASIGNADO indica a un cliente espec?fico cual es el jugador (la ficha de juego) asociado a ?l
     * TURNO_ASIGNADO indica a todos los clientes a qu? jugador le corresponde el turno en este momento
     * JUGADA_REALIZADA indica a todos los clientes qu? jugada (cambio en las fichas del tablero) se acaba de producir
     * JUGADA_VALIDA indica a un cliente espec?fico que su ?ltima jugada fue v?lida
     * JUGADA_NO_VALIDA indica a un cliente espec?fico que su ?ltima jugada no fue v?lida
     * JUGADOR_GANADOR indica a todos los clientes qu? jugador acaba de ganar la partida
     * @author Federico Peinado
     */
    // El tipo de mensaje es un enumerado interno, porque es un elemento auxiliar a esta clase
    public static enum Tipo {JUGADOR_ASIGNADO, TURNO_ASIGNADO, JUGADA_REALIZADA, JUGADA_VALIDA, JUGADA_NO_VALIDA, JUGADOR_GANADOR}
    
    // Aconsejable para guardar el n?mero de versi?n de los objetos serializados de esta clase
    private static final long serialVersionUID = 1L;
    
    private final Tipo tipo;
    private Jugador jugador;
    private Jugada jugada;
        
    // Los constructores son privados para que no se puedan usar directamente desde fuera
    
    private MensajeServidor(final Tipo tipo){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");    
        this.tipo = tipo;
    }
    
    private MensajeServidor(final Tipo tipo, final Jugador jugador){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");    
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos
        this.tipo = tipo;
        this.jugador = jugador;
    }
    
    private MensajeServidor(final Tipo tipo, final Jugada jugada){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");    
        if (jugada == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con una jugada nula.");    
        this.tipo = tipo;
        this.jugada = jugada;
    }
    
    private MensajeServidor(final Tipo tipo, final Jugador jugador, final Jugada jugada){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");     
        if (jugador == null)
            throw new IllegalArgumentException("No se puede crear este tipo de mensaje de servidor con un jugador nulo.");            
        if (jugada == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con una jugada nula.");    
        this.tipo = tipo;
        this.jugador = jugador;
        this.jugada = jugada;
    }
    
    /**
     * Este m?todo crea un mensaje del servidor indicando el jugador asignado a un cliente, como si se tratase de una 'factor?a' (en realidad para evitar usos incorrectos de los constructores).
     * @param jugador El jugador asignado
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadorAsignado(final Jugador jugador){
        if (jugador == null)
            throw new IllegalArgumentException("No se puede crear este tipo de mensaje de servidor con un jugador nulo.");                
        return new MensajeServidor(JUGADOR_ASIGNADO, jugador);  
    }
    
    /**
     * Este m?todo crea un mensaje del servidor indicando que le toca el turno a un cliente, como si se tratase de una 'factor?a' (en realidad para evitar usos incorrectos de los constructores).
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_TurnoAsignado(){
        return new MensajeServidor(TURNO_ASIGNADO);  
    }
    
    /**
     * Este m?todo crea un mensaje del servidor indicando que la jugada solicitada por el cliente es v?lida, como si se tratase de una 'factor?a' (en realidad para evitar usos incorrectos de los constructores).
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadaValida(){
        return new MensajeServidor(JUGADA_VALIDA);  
    }
    
    /**
     * Este m?todo crea un mensaje del servidor indicando que la jugada solicitada por el cliente no es v?lida, como si se tratase de una 'factor?a' (en realidad para evitar usos incorrectos de los constructores).
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadaNoValida(){
        return new MensajeServidor(JUGADA_NO_VALIDA);  
    }
    
    /**
     * Este m?todo crea un mensaje del servidor indicando que se ha realizado una jugada, como si se tratase de una 'factor?a' (en realidad para evitar usos incorrectos de los constructores).
     * @param jugador El jugador que ha realizado la jugada
     * @param jugada La jugada a realizar
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadaRealizada(final Jugador jugador, final Jugada jugada){
        if (jugador == null)
            throw new IllegalArgumentException("No se puede crear este tipo de mensaje de servidor con un jugador nulo.");            
        if (jugada == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con una jugada nula.");    
        return new MensajeServidor(JUGADA_REALIZADA, jugador, jugada);  
    }
    
    /**
     * Este m?todo crea un mensaje del servidor indicando el jugador que ha ganado la partida, como si se tratase de una 'factor?a' (en realidad para evitar usos incorrectos de los constructores).
     * @param jugador El jugador que ha ganado la partida
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadorGanador(final Jugador jugador){
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos        
        return new MensajeServidor(JUGADOR_GANADOR, jugador);  
    }
    
    /**
     * Este m?todo devuelve el tipo de mensaje asociado a este mensaje del servidor.
     * @return El tipo de mensaje
     */
    public Tipo dameTipo() {
        return tipo;
    }
    
    /**
     * Este m?todo devuelve el jugador asociado a este mensaje del servidor.
     * Se admite el valor NULL, para el caso de querer indicar que no hay un ganador, sino que la partida acab? en tablas.
     * @return El jugador
     */
    public Jugador dameJugador() {
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos                    
        return jugador;
    }
    
    /**
     * Este m?todo devuelve la jugada asociada a este mensaje del servidor.
     * @return La jugada
     */
    public Jugada dameJugada() {
        if (jugada == null)
            throw new RuntimeException("No se puede acceder a la informaci?n de la jugada debido al tipo de mensaje.");
        return jugada;
    }
}
