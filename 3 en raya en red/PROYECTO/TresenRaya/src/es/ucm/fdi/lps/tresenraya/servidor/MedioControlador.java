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
package es.ucm.fdi.lps.tresenraya.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.ucm.fdi.lps.tresenraya.Jugada;
import es.ucm.fdi.lps.tresenraya.Jugador;

/**
 * Esta clase representa la mitad del controlador del juego, situado en el lado del servidor. 
 * Gestiona la conexión por red con los clientes mediante unas clases auxiliares, pero usando un único hilo de ejecución.
 * @author Federico Peinado
 */
class MedioControlador {

    private Modelo modelo;
    private ServerSocket servidor;    
    private List<MedioControladorCliente> controladoresClientes; 
 
    /**
     * El constructor de la mitad del controlador del lado del servidor. 
     * @param modelo El modelo a controlar
     */
    MedioControlador(Modelo modelo) {
        if (modelo == null)
            throw new IllegalArgumentException("No se puede crear un medio controlador de servidor con un modelo nulo.");                
        this.modelo = modelo;
    }

    /**
     * Este método pone en marcha el controlador, comenzando el juego desde el punto de vista del servidor. 
     * @param puerto El puerto al que se conectarán los clientes
     */
    void ejecutar(int puerto) {
        // Aquí lo suyo sería comprobar que el valor del puerto es razonables

        mostrarTexto("* Servidor de Tres en Raya *");  

        try{
            servidor = new ServerSocket(puerto, Jugador.values().length); // Así es más general, aunque en la práctica el backlog (la longitud máximo de la cola de conexiones entrantes -clientes-) sólo puede ser 2 porque este código no admite N jugadores
        } catch (IOException ioException){
            System.err.println("ERROR: El servidor no se puede poner a la escucha en este puerto");
            System.exit(1);
        }         
        mostrarTexto("Esperando la conexión de los clientes...");
        
        // Usamos una ArrayList, aunque no habría muchas diferencias con una LinkedList. Vector es una clase bastante obsoleta y por eso no conviene elegirla.
        controladoresClientes = new ArrayList<MedioControladorCliente>(Jugador.values().length); // Aparentemente genérico, aunque en la práctica sólo haya 2 jugadores
        for (Jugador jugador : Jugador.values()){
            try {
                // 1. Recibir petición de conexión de cada cliente 
                MedioControladorCliente controladorCliente = new MedioControladorCliente(servidor.accept(), jugador);
                mostrarTexto("Jugador " + jugador.toString() + " conectado." );
                controladoresClientes.add(controladorCliente);    
            } catch (IOException ioException){
                System.err.println("ERROR: Problemas de conexión con alguno de los clientes.");
                System.exit(1);
            } 
        } 
        
        // Se suceden las rondas de todos los jugadores hasta que alguien gane la partida o el tablero se llene
        Jugador jugador;
        Jugada jugada;  
        MedioControladorCliente controladorCliente;
        Iterator<MedioControladorCliente> iterador;
        boolean resultadoJugada;
        do {
            // Cogemos el jugador activo y buscamos su controlador (esto sería muy ineficiente, pero como en la práctica son sólo dos jugadores...)
            jugador = modelo.dameJugadorActivo();            
            // Asumo que en la práctica esto no me va a fallar... ejem...
            iterador = controladoresClientes.iterator();
            controladorCliente = iterador.next();
            while (!controladorCliente.dameJugador().equals(jugador))
                controladorCliente = iterador.next(); 
                
            // 3. Por defecto, no es el turno de ningún cliente, salvo los que iremos dando paso desde este bucle
            controladorCliente.notificaTurnoAsignado();
    
            // 4. Procesar peticiones del cliente (y devolver movimientos dados como válidos o inválidos, jugadas realizadas y fin de la partida)                                
            do {
                // Se espera a que el cliente con el turno envíe su jugada...
                jugada = controladorCliente.esperarJugada();
                
                resultadoJugada = modelo.realizaJugada(jugador, jugada); // Con esto ya cambia el jugador activo dentro del modelo, por ejemplo, si hay cambio de turno
                
                // Se informa de el resultado de la jugada al cliente que la realizó
                controladorCliente.notificaResultadoJugada(resultadoJugada);
                
                if (resultadoJugada) { 
                    mostrarTexto("Ficha del jugador " + jugador.toString() + " colocada en " + jugada.toString()); // Nos fiamos del toString de una jugada
    
                    // Se notifica de la jugada realizada a todos, incluido el que acaba de mover
                    for (MedioControladorCliente controladorClienteDestino : controladoresClientes)
                        controladorClienteDestino.notificaJugadaRealizada(jugador, jugada);            
                } 
            } while (!resultadoJugada);            
            
        } while(!modelo.juegoGanado() && !modelo.tableroCompleto()); // Repetir mientras que no haya ganado nadie ni se haya llenado el tablero

        if (modelo.juegoGanado())  // Sin importar si el tablero está completo o no
            // Notificar a todos quien es el ganador, incluido al que acaba de mover (que será precisamente el ganador, por lógica...)
            for (MedioControladorCliente controladorClienteDestino : controladoresClientes)
                controladorClienteDestino.notificaJugadorGanador(jugador);         
        else // Sabemos que entonces lo que ha pasado es que se ha llenado el tablero pero nadie ha ganado
                for (MedioControladorCliente controladorClienteDestino : controladoresClientes)
                    controladorClienteDestino.notificaJugadorGanador(null); // NULL significa que la partida ha quedado en tablas

        // IMPORTANTE: Aquí me quedo parado para que pulsen una tecla antes de cerrar el servidor y cerrar por ello a todos los clientes
        try {
            // Se asume que primero cerrarán los clientes y luego ya apagaremos manualmente el servidor
            mostrarTexto("La partida terminó. Pulsa una tecla para cerrar la conexión.");
            System.in.read();
            for (MedioControladorCliente controladorClienteDestino : controladoresClientes)
                controladorClienteDestino.cerrarConexion();
        } catch (IOException e) {
            System.err.println("ERROR: Problemas de lectura de teclado.");
            System.exit(1);
        }
    } 

    // Este método hace las veces de pequeña 'vista' del servidor, para mostrar texto informativo sobre lo que está ocurriendo
    // Podría ser algo más complejo, una clase aparte o incluso una GUI, pero en este ejemplo simplemente consiste en mostrar texto por consola
    private void mostrarTexto(String texto){
        if (texto == null)
            throw new IllegalArgumentException("No se puede mostrar un texto nulo.");        
        System.out.println(texto);
    }
} 