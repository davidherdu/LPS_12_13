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
package es.ucm.fdi.lps.g08.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import es.ucm.fdi.lps.g08.*;

/**
 * Esta clase representa la gestión específica para cada cliente, que es una clase auxiliar de una parte de la mitad del controlador del juego, situado en el lado del servidor. 
 * @author Federico Peinado
 */
// En este caso usamos un hilo para gestionar todos los clientes, aunque un argumento a favor de usar un hilo por cliente sería que así no dejamos que uno esté jugando mientras el otro ya ha cortado la comunicación...
public class MedioControladorCliente {

    private Socket conexion;
    private Jugador jugador; 
    private ObjectInputStream entradaRed; 
    private ObjectOutputStream salidaRed;
    private MensajeServidor mensaje = null;
    private int numJ;

    /**
     * El constructor de la clase auxiliar que gestiona cada cliente en la mitad del controlador del lado del servidor.  
     * @param conexion La conexión con uno de los clientes
     * @param jugador El jugador asociado a dicho cliente
     */
    MedioControladorCliente(final Socket conexion, final Jugador jugador, final int numJ) { 
        if (conexion == null)
            throw new IllegalArgumentException("No se puede crear un gestor de cliente con una conexión nula.");        
        if (jugador == null)
            throw new IllegalArgumentException("No se puede crear un gestor de cliente con un jugador nulo.");        

        this.conexion = conexion; 
        this.jugador = jugador;
        this.numJ = numJ;
        try {
            // IMPORTANTE: Se crea primero el ObjectOutputStream, y en el cliente se hará justo al revés: se creará primero el ObjectInputStream. ¡Si no se hace así, se produce un bloqueo!
            salidaRed = new ObjectOutputStream(conexion.getOutputStream());
            entradaRed = new ObjectInputStream(conexion.getInputStream());   
            
            mensaje = MensajeServidor.creaMensajeServidor_JugadorAsignado(this.jugador,numJ);
            // 2. Enviar jugadores asignados a cada cliente 
            salidaRed.writeObject(mensaje);
            salidaRed.flush();    
        } 
        catch (IOException ex) { 
            System.err.println("ERROR: Problemas de conexión con un cliente.");
            System.exit(1);
        } 
    }
    
    /**
     * Este método devuelve el jugador asociado a este gestor de cliente.
     * @return jugador El jugador asociado
     */
    Jugador dameJugador(){
        return jugador;
    }
    
    /**
     * Este método notifica al cliente que es su turno. 
     */
    void notificaTurnoAsignado(){
        try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_TurnoAsignado());
            salidaRed.flush(); 
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el turno de un jugador.");
            System.exit(1);
        } 
    }
    
    void notificaColorAsignado(String s){
    	 try {
             salidaRed.writeObject(MensajeServidor.creaMensajeServidor_ColorAsignado(s));
             salidaRed.flush(); 
         } 
         catch (IOException ex) {
             System.err.println("ERROR: No se puede notificar el color del jugador.");
             System.exit(1);
         } 
    }
    
    void notificaComenzarJugar(){
   	 try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_ComenzarJugar());
            salidaRed.flush(); 
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el color del jugador.");
            System.exit(1);
        } 
    }
    
    void notificaTexto(String s){
      	 try {
               salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Texto(s));
               salidaRed.flush(); 
           } 
           catch (IOException ex) {
               System.err.println("ERROR: No se puede notificar el color del jugador.");
               System.exit(1);
           } 
       }
    
    void notificaMovimiento(String s){
     	 try {
              salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Movimiento(s));
              salidaRed.flush(); 
          } 
          catch (IOException ex) {
              System.err.println("ERROR: No se puede notificar el color del jugador.");
              System.exit(1);
          } 
      }
    
    void notificaOpcion(Jugada j){
    	 try {
             salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Opcion(j));
             salidaRed.flush(); 
         } 
         catch (IOException ex) {
             System.err.println("ERROR: No se puede notificar el color del jugador.");
             System.exit(1);
         } 
     }
    
    void notificaCambiaCasilla(Jugada jugada){
   	 try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_CambiaCasilla(jugada));
            salidaRed.flush(); 
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el color del jugador.");
            System.exit(1);
        } 
    }   
    
    /**
     * Este método notifica al cliente el resultado de la jugada (si ha sido válida o no). 
     * @param valida Cierto si la jugada ha sido válida y se ha realizado, falso en caso contrario
     */
    void notificaResultadoJugada(final boolean valida){
        try {
            if (valida) 
                salidaRed.writeObject(MensajeServidor.creaMensajeServidor_JugadaValida()); 
            else 
                salidaRed.writeObject(MensajeServidor.creaMensajeServidor_JugadaNoValida()); 
            salidaRed.flush ();  
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el resultado de una jugada.");
            System.exit(1);
        } 
    }

    /**
     * Este método espera a recibir, por parte del cliente, la jugada que este desea realizar. 
     * @return jugada La jugada que se desea realizar
     */
    Jugada esperarJugada() {
        
        Jugada jugada = null; // Aunque nunca vamos a devolver NULL        
        try {       
            MensajeCliente mensaje = (MensajeCliente) entradaRed.readObject(); 
            return mensaje.dameJugada(); // No nos puede dar NULL            
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("ERROR: No se lee o no se descifra la información que debe enviar el cliente."); 
            System.exit(1);
        }
        return jugada;             
    }
    
    void notificaRevelacion(final String texto){
    	try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Revelacion(texto)); 
            salidaRed.flush ();  
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el resultado de una jugada.");
            System.exit(1);
        } 
    }
    
    void notificaAfirmacion(final String texto){
    	try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Afirmacion(texto)); 
            salidaRed.flush ();  
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el resultado de una jugada.");
            System.exit(1);
        } 
    }
    
    void notificaPregunta(){
    	try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Pregunta()); 
            salidaRed.flush ();  
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el resultado de una jugada.");
            System.exit(1);
        } 
    }
    
    void notificaPregunta2(Jugada j){
    	try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_Pregunta2(j)); 
            salidaRed.flush ();  
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el resultado de una jugada.");
            System.exit(1);
        } 
    }
    
    void notificaMostrarCartas(Jugada j){
    	try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_MostrarCartas(j)); 
            salidaRed.flush ();  
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el resultado de una jugada.");
            System.exit(1);
        } 
    }

    /**
     * Este método notifica al cliente que alguien ha realizado un jugada en el juego. 
     * @param jugador El jugador que ha realizado la jugada
     * @param jugada La jugada que se ha realizado
     */
    void notificaJugadaRealizada(final Jugador jugador, final Jugada jugada) { 
        if (jugador == null)
            throw new IllegalArgumentException("No se puede notificar una jugada realizada por un jugador nulo.");        
        if (jugada == null)
            throw new IllegalArgumentException("No se puede notificar una jugada realizada nula.");        

        try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_JugadaRealizada(jugador, jugada));  
            salidaRed.flush();    
        } 
        catch (IOException ex) { 
            System.err.println("ERROR: No se puede notificar una jugada realizada.");
            System.exit(1);
        } 
    } 
    
    /**
     * Este método notifica al cliente que un jugador ha ganado la partida (o que ha acabado en tablas). 
     * @param jugador El jugador que ha ganado la partida (NULL significa que ha quedado en tablas)
     */
    void notificaJugadorGanador(Jugador jugador){
        // Se permite jugador NULL (tablas)
        try {
            salidaRed.writeObject(MensajeServidor.creaMensajeServidor_JugadorGanador(jugador));  
            salidaRed.flush();    
            // Implícitamente estamos informando al cliente de que la partida se acabó, de una forma u otra... aunque todavía no cerramos la conexión
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar al jugador ganador."); // O las tablas
            System.exit(1);
        } 
    }
    
    /**
     * Este método cierra la conexión por red con los clientes. 
     */
    void cerrarConexion() {
        try {
            // Se cierran los recursos en el orden contrario al que los hemos abierto
            entradaRed.close();
            salidaRed.close();
            conexion.close();                  
        } catch ( IOException ioException ) {
            System.out.println("ERROR: No se ha podido cerrar la conexión con el cliente.");
            System.exit(1);
        }  
    }
}
