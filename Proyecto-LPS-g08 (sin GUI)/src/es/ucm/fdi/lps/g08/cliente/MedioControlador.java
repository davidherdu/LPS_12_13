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
package es.ucm.fdi.lps.g08.cliente;

import java.io.*;
import java.net.Socket;

import es.ucm.fdi.lps.g08.Jugada;
import es.ucm.fdi.lps.g08.MensajeCliente;
import es.ucm.fdi.lps.g08.MensajeServidor;
import es.ucm.fdi.lps.g08.Preguntas;
import es.ucm.fdi.lps.g08.TipoRasgo;
import es.ucm.fdi.lps.g08.TipoUbicacion;
import es.ucm.fdi.lps.g08.Vista;
import es.ucm.fdi.lps.g08.Cartas.Sospechoso;

import static es.ucm.fdi.lps.g08.MensajeServidor.Tipo.*;
import static es.ucm.fdi.lps.g08.Constantes.*;

/**
 * Esta clase representa la mitad del controlador del juego, situado en el lado del cliente. Gestiona la conexi�n por red con el servidor.
 * @author Federico Peinado
 */
class MedioControlador { 

    private Vista vista;
    
    private Socket conexion;
    private ObjectInputStream entradaRed;
    private ObjectOutputStream salidaRed;
    private MensajeServidor mensaje = null;
    private boolean jugar = true;
    private Jugada jugada;
    
    // La informaci�n del turno es algo as� como una 'copia' de parte del modelo que guardamos en el controlador para agilizar tr�mites (evitar env�o de falsas jugadas por red)
    // Ponemos volatile por precauci�n, para que ambos hilos (el principal que pasa por aqu� y el de Swing) siempre lean el valor actualizado del turno 
    private volatile boolean esMiTurno = false;

    /**
     * El constructor de la mitad del controlador del lado del cliente. 
     * @param vista La vista a controlar
     */
    MedioControlador(Vista vista) {
        if (vista == null)
            throw new IllegalArgumentException("No se puede crear un medio controlador de cliente con una vista nula.");        
        this.vista = vista;
       // this.vista.ponControlador(this); // Inmediatamente se asocia el controlador a la vista en ambos sentidos
    }
   
    /**
     * Este m�todo pone en marcha el controlador, comenzando el juego desde el punto de vista del cliente. 
     * @param direccion La direcci�n IP del servidor al que conectarse
     * @param puerto El puerto al que conectarse
     */
    void ejecutar(String direccion, int puerto) { 
    	// Aqu� lo suyo ser�a comprobar que los valores de direcci�n y puerto son razonables
    	if (direccion == null)
            throw new IllegalArgumentException("No se puede crear un medio controlador de cliente conectado a una direcci�n IP nula.");        

        try {
            // 1. Enviar petici�n de conexi�n al servidor   
            conexion = new Socket(direccion, puerto);
            // IMPORTANTE: Como en el servidor se crea primero el ObjectOutputStream, aqu� se hace justo al rev�s: se crea primero el ObjectInputStream. �Si no se hace as�, se produce un bloqueo!
            entradaRed = new ObjectInputStream(conexion.getInputStream());
            salidaRed = new ObjectOutputStream(conexion.getOutputStream()); 
            
            // 2. Recibir jugador asignado a este cliente 
            do {
                try {
                    mensaje = (MensajeServidor)entradaRed.readObject(); // Si falla el casting, la liamos (comprobar que el objeto leido no es null, eso lo primero)
                	System.out.println("Jugador "+mensaje.dameNumJ()+" conectado");
                } catch (IOException | ClassNotFoundException e){
                    System.err.println("ERROR: No se lee o no se descifra la informaci�n que debe enviar el servidor.");
                    System.exit(1);
                }
            } while (mensaje.dameTipo() != JUGADOR_ASIGNADO); 
            //vista.ponIDJugador(mensaje.dameJugador());
            
            /* 3. Por defecto, nunca es el turno de ning�n cliente (todos comienzan con esMiTurno a false), aunque de forma concurrente permiten interactuar con la interfaz gr�fica de usuario
                    
             4. Procesar respuestas del servidor (asignar turno, jugadas dadas como v�lidas o no v�lidas, jugadas ajenas y declarar ganador de la partida)*/    
            do { 
                try {
                    mensaje = (MensajeServidor)entradaRed.readObject();
                    String texto = "";
                    switch(mensaje.dameTipo()){
                        // Los clientes deben esperar siempre a que el servidor les diga que es su turno para actuar
                    	case ELEGIR_COLOR:                   		
                    		vista.normal(" Elige uno de los siguientes colores \n");
                        	texto = mensaje.dameCadena();
                        	System.out.println(texto);
                        	int i = texto.length();
                        	int cad = texto.charAt(i-1);
                        	int n = vista.limites(1,cad);
                        	jugada = new Jugada(0,n,"");
                        	intentaJugada(jugada);
                        	jugar = false;                    	
                    		break;
                    	case JUGAR:
                    		texto = vista.preguntaJugar();
                    		jugada = new Jugada(0,0,texto);
                    		intentaJugada(jugada);
                    		jugar = false;
                    		break;
                    	case TEXTO:
                    		texto = mensaje.dameCadena();
                    		System.out.println(texto);
                    		jugar = false;
                    		break;
                    	case MOVIMIENTO: 
                            //vista.anunciaTurno();
                    		texto = mensaje.dameCadena();
                    		//String t = mensaje.dameTexto();
                    		System.out.println(texto);
                    		//System.out.println(t);
                    		int op = 0;
                    		vista.Menu();
                			op = vista.limites(1,7);
                			jugada = new Jugada(0,op,"");
                			intentaJugada(jugada);
                            //esMiTurno = true;
                    		jugar = false;
                            break;
                    	case OPCION: 
                    		Jugada jAux = mensaje.dameJugada();
                    		texto = jAux.dameCadena();
                            System.out.println(texto);
                            op = vista.limites(1,jAux.dameNumero());
                            jugada = new Jugada(0,op,"");
                            intentaJugada(jugada);
                            jugar = false;
                            break;
                    	case CAMBIA_CASILLA:
                    		texto = mensaje.dameJugada().dameCadena();
                    		int lim = mensaje.dameJugada().dameNumero();
                    		System.out.println(texto); 
                    		op = vista.limites(1,lim);
                    		jugada = new Jugada(0,op,"");
                    		intentaJugada(jugada);
                    		break;
                    	case TURNO_ASIGNADO: 
                            //vista.anunciaTurno();
                            esMiTurno = true;
                            break;  
                    	case AFIRMACION:
                    		texto = mensaje.dameCadena();
                    		System.out.println(texto);
                    		String afirmacion = vista.preguntaAfirmacion();
                    		jugada = new Jugada(0,0,afirmacion);
                    		intentaJugada(jugada);
                    		break;
                    	case REVELACION:
                			String sms = vista.eligePropiedad();
                			jugada = new Jugada(0,0,sms);
                    		intentaJugada(jugada);                  			     
                    		break;
                    	case PREGUNTA:
                    		Preguntas p = new Preguntas();
                    		String rasgo = p.pregunta1();
                    		TipoRasgo t = p.getTipoRasgo();
                    		TipoUbicacion tu = p.getTipoUbicacion();
                    		jugada = new Jugada(t,tu,rasgo);
                    		intentaJugada(jugada);
                    		break;
                    	case PREGUNTA2:
                    		jugada = mensaje.dameJugada();
                    		System.out.println(jugada.dameCadena());
                    		Sospechoso s = jugada.dameSospechoso();
                    		Preguntas p1 = new Preguntas();
                    		texto = p1.pregunta2(s);
                    		TipoUbicacion tuAux = p1.getTipoUbicacion();
                            jugada = new Jugada(null,tuAux,texto);
                            intentaJugada(jugada);
                    		break;
                    	case MOSTRAR_CARTAS:
                    		jugada = mensaje.dameJugada();
                    		texto = jugada.dameCadena();
                            System.out.println(texto);
                            op = vista.limites(0,jugada.dameNumero());
                            jugada = new Jugada(0,op,"");
                            intentaJugada(jugada);
                            jugar = false;
                    		break;
                        case JUGADA_VALIDA:    
                            /*vista.anunciaJugadaValida();
                            esMiTurno = false;*/
                            break;
                        case JUGADA_NO_VALIDA:  
                          //  vista.anunciaJugadaNoValida();
                            break;
                        case JUGADA_REALIZADA:    
                            /*vista.ponFicha(mensaje.dameJugador(), mensaje.dameJugada()); 
                            vista.anunciaJugada(mensaje.dameJugador());*/
                            break;
                        case JUGADOR_GANADOR:   
                            //vista.anunciaJugadorGanador(mensaje.dameJugador()); // Si nos han enviado un NULL significar� que hay tablas
                            // Poco importa de quien sea el turno, por defecto lo dejamos en quien lo tuviera
                        default: // Si no es ninguno de estos mensajes, lo ignoramos */
                    }
                            
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("ERROR: No se lee o no se descifra la informaci�n que debe enviar el servidor.");
                    System.exit(1);
                }
            }while(!jugar) ; // Este bucle s�lo se termina cuando se cierra la aplicaci�n entera desde el hilo de Swing
           // }
        } catch (IOException e) { 
            System.err.println("ERROR: No se puede establecer conexi�n con el servidor.");
            System.exit(1);
        } finally {
            try {
                // Se cierran los recursos en el orden contrario al que los hemos abierto
                salidaRed.close();
                entradaRed.close();
                conexion.close();  
            } catch (IOException ex) {
                System.err.println("ERROR: No se ha podido cerrar la conexi�n con el servidor.");
                System.exit(1);
            }             
        }
    } 

    /**
     * Este m�todo informa al modelo de que el jugador ha intentado realizar una determinada jugada.
     * @param jugada La jugada
     */
    // No me hace falta protegerlo para sincronizaci�n porque s�lo es llamado desde el hilo de Swing
    void intentaJugada(final Jugada jugada) {  
        if (jugada == null)
            throw new IllegalArgumentException("No se puede intentar una jugada nula.");        

        // S�lo podemos enviar nuestra intenci�n de realizar una jugada cuando es nuestro turno
        //if (esMiTurno) { 
            try {
                if (salidaRed != null) { // En realidad esto ya hemos controlado que no pueda ocurrir
                    salidaRed.writeObject(new MensajeCliente(jugada));
                    salidaRed.flush();   
                }
            } catch (IOException e){
                System.err.println("ERROR: No se escribe la informaci�n que debe enviarse al servidor.");
                System.exit(1);
            }
       // }  
    } 
    
    /**
     * Este m�todo cierra la conexi�n por red con el servidor. 
     */
    // No me hace falta protegerlo para sincronizaci�n porque s�lo es llamado desde el hilo de Swing
    void cerrarConexion() {
        try {
            // Se cierran los recursos en el orden contrario al que los hemos abierto
            // T�cnicamente la vista se podr�a cerrar antes de haber si quiera ejecutado el controlador, de ah� las comprobaciones de NULL
            if (salidaRed != null)
                salidaRed.close();
            if (entradaRed != null)
                entradaRed.close();
            if (conexion != null)
                conexion.close();                  
        } catch ( IOException ioException ) {
            System.out.println("ERROR: No se ha podido cerrar la conexi�n con el cliente.");
            System.exit(1);
        }  
    }
}
