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
package es.ucm.fdi.lps.g08;

import java.io.Serializable;
import static es.ucm.fdi.lps.g08.MensajeServidor.Tipo.*;

/**
 * Esta clase representa los posibles mensajes que el servidor manda al cliente del juego.
 * @author Federico Peinado
 */
//Se parece a un Java Bean, sólo que no tiene constructor sin argumentos, le faltan setters y los getters están escritos en español (dameX) en vez de en inglés (getX)
public final class MensajeServidor implements Serializable {

    /**
     * Este enumerado representa los posibles mensajes que el servidor puede mandar a sus clientes:
     * JUGADOR_ASIGNADO indica a un cliente específico cual es el jugador (la ficha de juego) asociado a él
     * TURNO_ASIGNADO indica a todos los clientes a qué jugador le corresponde el turno en este momento
     * JUGADA_REALIZADA indica a todos los clientes qué jugada (cambio en las fichas del tablero) se acaba de producir
     * JUGADA_VALIDA indica a un cliente específico que su última jugada fue válida
     * JUGADA_NO_VALIDA indica a un cliente específico que su última jugada no fue válida
     * JUGADOR_GANADOR indica a todos los clientes qué jugador acaba de ganar la partida
     * @author Federico Peinado
     */
    // El tipo de mensaje es un enumerado interno, porque es un elemento auxiliar a esta clase
    public static enum Tipo {ELEGIR_COLOR, JUGAR, TEXTO, MOVIMIENTO, OPCION, CAMBIA_CASILLA, REVELACION, ACUSACION, AFIRMACION, PREGUNTA, PREGUNTA2, MOSTRAR_CARTAS, JUGADOR_ASIGNADO, TURNO_ASIGNADO, JUGADA_REALIZADA, JUGADA_VALIDA, JUGADA_NO_VALIDA, JUGADOR_GANADOR}
    
    // Aconsejable para guardar el número de versión de los objetos serializados de esta clase
    private static final long serialVersionUID = 1L;
    
    private final Tipo tipo;
    private Jugador jugador;
    private Jugada jugada;
    private String cadena,texto;
    private int numJ;
    
        
    // Los constructores son privados para que no se puedan usar directamente desde fuera
    
    private MensajeServidor(final Tipo tipo){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");    
        this.tipo = tipo;
    }
    
    private MensajeServidor(final Tipo tipo, final Jugador jugador,final int numJ){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");    
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos
        this.tipo = tipo;
        this.jugador = jugador;
        this.numJ = numJ;
    }
    
    private MensajeServidor(final Tipo tipo, final String cadena){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");
        if (cadena == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con una cadena nula.");  
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos
        this.tipo = tipo;
        this.cadena = cadena;
    }
    
    private MensajeServidor(final Tipo tipo, final String cadena, final String texto){
        if (tipo == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con un tipo nulo.");
        if (cadena == null)
            throw new IllegalArgumentException("No se puede crear un mensaje de servidor con una cadena nula.");  
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos
        this.tipo = tipo;
        this.cadena = cadena;
        this.texto = texto;
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
     * Este método crea un mensaje del servidor indicando el jugador asignado a un cliente, como si se tratase de una 'factoría' (en realidad para evitar usos incorrectos de los constructores).
     * @param jugador El jugador asignado
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadorAsignado(final Jugador jugador,final int numJ){
        if (jugador == null)
            throw new IllegalArgumentException("No se puede crear este tipo de mensaje de servidor con un jugador nulo.");                
        return new MensajeServidor(JUGADOR_ASIGNADO, jugador,numJ);  
    }
    
    /**
     * Este método crea un mensaje del servidor indicando que le toca el turno a un cliente, como si se tratase de una 'factoría' (en realidad para evitar usos incorrectos de los constructores).
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_TurnoAsignado(){
        return new MensajeServidor(TURNO_ASIGNADO);  
    }
    
    public static MensajeServidor creaMensajeServidor_ColorAsignado(final String cadena){
        return new MensajeServidor(ELEGIR_COLOR,cadena);  
    }
    
    public static MensajeServidor creaMensajeServidor_ComenzarJugar(){
        return new MensajeServidor(JUGAR);  
    }
    
    public static MensajeServidor creaMensajeServidor_Texto(final String s){
        return new MensajeServidor(TEXTO, s);  
    }
    
    public static MensajeServidor creaMensajeServidor_Movimiento(final String s){
        return new MensajeServidor(MOVIMIENTO, s);  
    }
    
    public static MensajeServidor creaMensajeServidor_Opcion(final Jugada j){
        return new MensajeServidor(OPCION,j);  
    }
    
    public static MensajeServidor creaMensajeServidor_CambiaCasilla(final Jugada j){
        return new MensajeServidor(CAMBIA_CASILLA, j);  
    }
    
    /**
     * Este método crea un mensaje del servidor indicando que la jugada solicitada por el cliente es válida, como si se tratase de una 'factoría' (en realidad para evitar usos incorrectos de los constructores).
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadaValida(){
        return new MensajeServidor(JUGADA_VALIDA);  
    }
    
    /**
     * Este método crea un mensaje del servidor indicando que la jugada solicitada por el cliente no es válida, como si se tratase de una 'factoría' (en realidad para evitar usos incorrectos de los constructores).
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadaNoValida(){
        return new MensajeServidor(JUGADA_NO_VALIDA);  
    }
    
    /**
     * Este método crea un mensaje del servidor indicando que se ha realizado una jugada, como si se tratase de una 'factoría' (en realidad para evitar usos incorrectos de los constructores).
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
     * Este método crea un mensaje del servidor indicando el jugador que ha ganado la partida, como si se tratase de una 'factoría' (en realidad para evitar usos incorrectos de los constructores).
     * @param jugador El jugador que ha ganado la partida
     * @return El mensaje del servidor creado
     */
    public static MensajeServidor creaMensajeServidor_JugadorGanador(final Jugador jugador){
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos        
        return new MensajeServidor(JUGADOR_GANADOR, jugador,0);  
    }
    
    public static MensajeServidor creaMensajeServidor_Revelacion(final String texto){
    	return new MensajeServidor(REVELACION,texto);
    }
    
    public static MensajeServidor creaMensajeServidor_Afirmacion(final String texto){
    	return new MensajeServidor(AFIRMACION,texto);
    }
    
    public static MensajeServidor creaMensajeServidor_Acusacion(final String texto){
    	return new MensajeServidor(ACUSACION,texto);
    }
    
    public static MensajeServidor creaMensajeServidor_Pregunta(){
    	return new MensajeServidor(PREGUNTA);
    }
    
    public static MensajeServidor creaMensajeServidor_Pregunta2(Jugada j){
    	return new MensajeServidor(PREGUNTA2,j);
    }
    
    public static MensajeServidor creaMensajeServidor_MostrarCartas(final Jugada j){
    	return new MensajeServidor(MOSTRAR_CARTAS,j);
    }
    
    /**
     * Este método devuelve el tipo de mensaje asociado a este mensaje del servidor.
     * @return El tipo de mensaje
     */
    public Tipo dameTipo() {
        return tipo;
    }
    
    /**
     * Este método devuelve el jugador asociado a este mensaje del servidor.
     * Se admite el valor NULL, para el caso de querer indicar que no hay un ganador, sino que la partida acabó en tablas.
     * @return El jugador
     */
    public Jugador dameJugador() {
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos                    
        return jugador;
    }
    
    public int dameNumJ(){
    	return numJ;
    }
    
    /**
     * Este método devuelve la jugada asociada a este mensaje del servidor.
     * @return La jugada
     */
    public Jugada dameJugada() {
        if (jugada == null)
            throw new RuntimeException("No se puede acceder a la información de la jugada debido al tipo de mensaje.");
        return jugada;
    }
    
    public String dameCadena() {
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos                    
        return cadena;
    }
    
    public String dameTexto() {
        // Se admite NULL como posible valor de respuesta (cuando gana un jugador nulo se consideran tablas) y por eso no lo comprobamos                    
        return texto;
    }
}
