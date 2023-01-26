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

import static es.ucm.fdi.lps.g08.Constantes.*;
//import jargs.gnu.CmdLineParser;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import es.ucm.fdi.lps.g08.*;

/**
 * Esta clase representa la mitad del controlador del juego, situado en el lado del servidor. 
 * Gestiona la conexión por red con los clientes mediante unas clases auxiliares, pero usando un único hilo de ejecución.
 * @author Federico Peinado
 */
class MedioControlador {

    private Modelo modelo;
    private ServerSocket servidor;    
    private List<MedioControladorCliente> controladoresClientes; 
    private Vista vista;
    private ObjectInputStream entradaRed; 
    private ObjectOutputStream salidaRed;
    private int color;
 
    /**
     * El constructor de la mitad del controlador del lado del servidor. 
     * @param modelo El modelo a controlar
     */
    MedioControlador(Modelo modelo) {
        if (modelo == null)
            throw new IllegalArgumentException("No se puede crear un medio controlador de servidor con un modelo nulo.");
        this.modelo = modelo;
        vista = new Vista();
    }
    
    /**
     * Este método pone en marcha el controlador, comenzando el juego desde el punto de vista del servidor. 
     * @param puerto El puerto al que se conectarán los clientes
     * @throws IOException 
     */
    void ejecutar(int puerto){
        // Aquí lo suyo sería comprobar que el valor del puerto es razonable
    	
        mostrarTexto("* El misterio de la Abadia *");  

        try{
            servidor = new ServerSocket(puerto, NUMERO_JUGADORES); // Así es más general, aunque en la práctica el backlog (la longitud máximo de la cola de conexiones entrantes -clientes-) sólo puede ser 2 porque este código no admite N jugadores
        } catch (IOException ioException){
            System.err.println("ERROR: El servidor no se puede poner a la escucha en este puerto");
            System.exit(1);
        }         
        mostrarTexto("Esperando la conexión de los clientes...");
        
        // Usamos una ArrayList, aunque no habría muchas diferencias con una LinkedList. Vector es una clase bastante obsoleta y por eso no conviene elegirla.
        int numJ = 1;
        controladoresClientes = new ArrayList<MedioControladorCliente>(); // Aparentemente genérico, aunque en la práctica sólo haya 2 jugadores
        for (Jugador jugador : modelo.dameJugadores()){
            try {
                // 1. Recibir petición de conexión de cada cliente 
                MedioControladorCliente controladorCliente = new MedioControladorCliente(servidor.accept(), jugador,numJ);
               // modelo.eligeColor();
                mostrarTexto("Jugador " + numJ + " conectado." );
                controladoresClientes.add(controladorCliente);
                numJ++;
            } catch (IOException ioException){
                System.err.println("ERROR: Problemas de conexión con alguno de los clientes.");
                System.exit(1);
            } 
        } 
        
        
        Jugador jugador;
        Jugada jugada;  
        MedioControladorCliente controladorCliente;
        Iterator<MedioControladorCliente> iterador;
        boolean resultadoJugada;
        Vista vista = new Vista();
        
        //jugador = modelo.dameJugadores().get(0);
        iterador = controladoresClientes.iterator();
        controladorCliente = iterador.next();
        modelo.ponColores();
        int cont = 0;
        
        /*
         * primero eligen los colores cada jugador
         */
        do{
        	jugador = modelo.dameJugadores().get(cont);
	        while (!controladorCliente.dameJugador().equals(jugador))
	            controladorCliente = iterador.next();
	        
	        controladorCliente.notificaColorAsignado(modelo.enviarColores());
	        cont++;

			jugada = controladorCliente.esperarJugada();
			color = jugada.dameNumero();
			modelo.elegirColor(controladorCliente.dameJugador(), color);
        }while(cont<NUMERO_JUGADORES);
        
        /*
         * cada jugador comienza la partida
         */
        cont = 0;
        iterador = controladoresClientes.iterator();
        controladorCliente = iterador.next();
        do{
        	String cadena = "";
        	jugador = modelo.dameJugadores().get(cont);
	        while (!controladorCliente.dameJugador().equals(jugador))
	            controladorCliente = iterador.next();
	        
	        controladorCliente.notificaComenzarJugar();

			jugada = controladorCliente.esperarJugada();
			cadena = jugada.dameCadena();
			if((cadena.equals("jugar"))||(cadena.equals("Jugar"))||(cadena.equals("JUGAR")))
				cont++;
        }while(cont<NUMERO_JUGADORES);
        
		//String codigo = vista.preguntaJugar();
		
	//	if((codigo.equals("jugar"))||(codigo.equals("Jugar"))||(codigo.equals("JUGAR"))){
			modelo.generaAsesino();
			modelo.repartoEsquina();
			//modelo.muestraSospechososTablero();
			modelo.repartoNumSospechosos();
			//modelo.muestraCartasJugadores();
			modelo.tacharMonjes();
			
			/*
			 * muestra los resultados de los dados a todos los clientes
			 */
			cont = 0;
	        iterador = controladoresClientes.iterator();
	        controladorCliente = iterador.next();
	        String cadena = modelo.Empieza();
	        do{
	        	jugador = modelo.dameJugadores().get(cont);
		        while (!controladorCliente.dameJugador().equals(jugador))
		            controladorCliente = iterador.next();
		        cont++;
		        controladorCliente.notificaTexto(cadena);
	        }while(cont<NUMERO_JUGADORES);
			
			//modelo.Empieza();
			modelo.monjesConfesionario();
			int turno = modelo.dameTurno();
	 		numJ = modelo.numJugadores();
			int numMovimientos = NUMERO_MISAS*NUMERO_TURNOS;
			int i = 0;
			int j = 0;
			int ronda = 1;
			int misa = 1;
			vista.normal(" Empieza el jugador "+modelo.dameColor(turno)+"\n");
			
			//aqui empieza el bucle de toda la partida
			cont = 0;
	        iterador = controladoresClientes.iterator();
	        controladorCliente = iterador.next();
			do{
	           jugador = modelo.damePrimero();
			   i = 0;
			   do{
				   while (!controladorCliente.dameJugador().equals(jugador)){
			           if(iterador.hasNext())
			        	   controladorCliente = iterador.next();
			           else{
			        	   iterador = controladoresClientes.iterator();
			               controladorCliente = iterador.next();
			           }
				   }
				 
				   cadena = " ***********\n * Misa "+misa+"\n * Ronda "+ronda+"\n ***********\n";
				   cadena = cadena+" ------ Es el turno del jugador "+modelo.dameColor(turno)+" ------\n";
				   controladorCliente.notificaMovimiento(cadena);
				   jugada = controladorCliente.esperarJugada();
				   
				   int op = jugada.dameNumero();
				   jugada = modelo.menuOpciones(turno,MOVIMIENTOS,op,controladorCliente,controladoresClientes);
				   if(!jugada.dameTipo().equals("MOVIMIENTO")){
					   controladorCliente.notificaTexto(jugada.dameCadena());
					   while(!jugada.dameTipo().equals("MOVIMIENTO")){
						   controladorCliente.notificaMovimiento(cadena);
						   jugada = controladorCliente.esperarJugada();
						   
						   op = jugada.dameNumero();
						   jugada = modelo.menuOpciones(turno,MOVIMIENTOS,op,controladorCliente,controladoresClientes);
						   controladorCliente.notificaTexto(jugada.dameCadena());
					   }
				   }
				   if((jugada.dameTipo().equals("MOVIMIENTO"))&&(!jugada.dameCadena().equals("")))		   
					   mensajeTodos(jugada.dameCadena());	   		
				   
				   if(turno==numJ-1) turno = 0;
				   else turno++;
				   modelo.cambiaTurno(turno);
				   jugador = modelo.tieneTurno();
				   i++;
				   cadena = "";
			   }while(i<numJ);
			   j++;
			   ronda++;
			   if(j%4==0){
				  modelo.cambiaPrimero();
				  turno = modelo.dameTurno();
				  vista.normal(" ------ Se cambia la misa ------\n");
				  misa++;
				  ronda = 1;
			   }

			   
			   //controladorCliente.notificaTexto(cadena);
			   /*vista.normal(" ***********");
			   vista.normal(" * Misa "+misa+"\n * Ronda "+ronda);
			   vista.normal(" ***********\n");*/
			   /*while(i<numJ){
				   String texto = " ------ Es el turno del jugador "+modelo.dameColor(turno)+" ------\n";
				   controladorCliente.notificaMovimiento(cadena,texto);
				   jugada = controladorCliente.esperarJugada();
				   int op = jugada.dameNumero();
				   String jugadaAux = modelo.menuOpciones(turno,MOVIMIENTOS,op);
				   controladorCliente.notificaOpcion(jugadaAux);
				   //vista.normal(" ----------------------------------------------------------\n");
				   if(turno==numJ-1) turno = 0;
				   else turno++;
				   modelo.cambiaTurno(turno);
				   i++;
			   }
			   j++;
			   ronda++;
			   if(j%4==0){
				  modelo.cambiaPrimero();
				  turno = modelo.dameTurno();
				  vista.normal(" ------ Se cambia la misa ------\n");
				  misa++;
				  ronda = 1;
			   }*/
			}while((!modelo.getJuegoGanado())||(j<numMovimientos));
		//}else {
			vista.normal(" <><><><><><> Fin de Partida <><><><><><>\n");
			System.exit(0);
		//}
		//}
    } 
    
    public void mensajeTodos(String texto){
    	List<MedioControladorCliente> contClientes = controladoresClientes;
    	Jugador jugador;
    	MedioControladorCliente controladorCliente;
        Iterator<MedioControladorCliente> iterador;
        iterador = contClientes.iterator();
        controladorCliente = iterador.next();
        int cont = 0;
        do{
        	jugador = modelo.dameJugadores().get(cont);
	        while (!controladorCliente.dameJugador().equals(jugador))
	            controladorCliente = iterador.next();
	        cont++;
	        controladorCliente.notificaTexto(texto);
        }while(cont<NUMERO_JUGADORES);
    }
    
    public List<MedioControladorCliente> dameControladoresClientes(){
    	return controladoresClientes;
    }
   
    // Este método hace las veces de pequeña 'vista' del servidor, para mostrar texto informativo sobre lo que está ocurriendo
    // Podría ser algo más complejo, una clase aparte o incluso una GUI, pero en este ejemplo simplemente consiste en mostrar texto por consola
    private void mostrarTexto(String texto){
        if (texto == null)
            throw new IllegalArgumentException("No se puede mostrar un texto nulo.");        
        System.out.println(texto);
    }
} 