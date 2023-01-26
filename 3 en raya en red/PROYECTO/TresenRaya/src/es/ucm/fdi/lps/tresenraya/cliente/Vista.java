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
package es.ucm.fdi.lps.tresenraya.cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import es.ucm.fdi.lps.tresenraya.Jugada;
import es.ucm.fdi.lps.tresenraya.Jugador;
import static es.ucm.fdi.lps.tresenraya.Parametros.*;

/**
 * Esta clase representa la interfaz gráfica de usuario del cliente del juego. Utiliza de forma auxiliar a 'VistaCasilla'.
 * @author Federico Peinado
 * @see es.ucm.fdi.lps.tresenraya.cliente.VistaCasilla
 */
final class Vista extends JFrame { 

    private static final String JUGADOR_VACIO = " ";
    private static final short ANCHO_VENTANA = 300;
    private static final short ALTO_VENTANA = 225;
    
    // Aconsejable porque los JFrames son serializables
    private static final long serialVersionUID = 1L;
    
    private JTextField idJugador; 
    private JTextArea areaTexto;
    private JPanel panelTablero; 
    private JPanel panel; 

    // El tablero visual será siempre cuadrado (N x N) y las coordenadas irán de 0 a N-1.
    private VistaCasilla tablero[][] = new VistaCasilla[DIMENSION_TABLERO][DIMENSION_TABLERO];
    // Por defecto, la vista comienza sin controlador asociado
    private MedioControlador controlador = null;

    /**
     * El constructor de la interfaz gráfica de usuario del cliente.  
     */
    Vista() {
        idJugador = new JTextField();
        idJugador.setEditable(false); 
        add(idJugador, BorderLayout.NORTH); 
        
        areaTexto = new JTextArea(4, 30);
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.SOUTH);
        
        panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(DIMENSION_TABLERO, DIMENSION_TABLERO, 0, 0));
        // Aquí se crean las vistas de cada casilla, inicialmente vacías, que conformarán el panel central 
        for (byte fila = 1; fila <= tablero.length; fila++){
            for (byte columna = 1; columna <= tablero[fila-1].length; columna++){
                tablero[fila - 1][columna - 1] = new VistaCasilla(this, JUGADOR_VACIO, new Jugada(fila, columna));
                panelTablero.add(tablero[fila - 1][columna - 1]); 
            } 
        } 

        panel = new JPanel();  
        panel.add(panelTablero, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER); 
        setSize(ANCHO_VENTANA, ALTO_VENTANA); 
         
        // Código necesario para asegurarnos de cerrar el medio controlador del cliente, si tenemos uno asociado a esta vista
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (controlador != null)
                    controlador.cerrarConexion();
                dispose();
            }
        });
                
        setVisible(true); // Esta operación típicamente se realiza desde fuera del constructor, en realidad
    } 

    /**
     * Este método establece el controlador asociado a esta vista de la interfaz gráfica del usuario.
     * @param controlador El controlador asociado
     */
    void ponControlador(MedioControlador controlador){
        if (controlador == null)
            throw new IllegalArgumentException("No se puede poner un medio controlador de cliente nulo a la vista.");        
        this.controlador = controlador;
    }

    /**
     * Este método establece en la intefaz el identificador del jugador asociado a este cliente.
     * @param jugador El jugador asociado
     */
    void ponIDJugador(final Jugador jugador){
        if (jugador == null)
            throw new IllegalArgumentException("No se puede poner una ID de un jugador nulo en la vista.");        

        // Cada vez que se vaya a cambiar el estado de un componente Swing desde otro hilo, como ocurre aquí, hay que usar esta técnica para incorporar el cambio como una tarea más dentro del hilo de Swing.
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        idJugador.setText("Bienvenido, Jugador \"" + jugador.toString() + "\""); // Esta vista decide usar directamente la conversión de enumerado a cadena de texto
                    } 
                }); 
    }

    // Este método muestra mensajes de texto en el área destinada para ello en la interfaz.
    private void muestraTexto(final String texto){
        if (texto == null)
            throw new IllegalArgumentException("No se puede mostrar un texto nulo en la vista.");        

        // Cada vez que se vaya a cambiar el estado de un componente Swing desde otro hilo, como ocurre aquí, hay que usar esta técnica para incorporar el cambio como una tarea más dentro del hilo de Swing.
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        areaTexto.append(texto + "\n"); 
                    } 
                }); 
    } 
    
    /**
     * Este método anuncia que según el servidor tienes el turno para realizar una jugada. 
     */
    void anunciaTurno(){
        muestraTexto("Es tu turno...");
    }
    
    /**
     * Este método anuncia que según el servidor has realizado una jugada válida.
     */
    void anunciaJugadaValida(){
        muestraTexto("¡Jugada válida!");
    }
    
    /**
     * Este método anuncia que según el servidor has realizado una jugadaba no válida.
     */
    void anunciaJugadaNoValida(){
        muestraTexto("Jugada no válida, inténtalo de nuevo.");
    }
    
    /**
     * Este método anuncia que según el servidor otro jugador ha realizado una jugada.
     * @param jugador El jugador que ha realizado la jugada
     */
    void anunciaJugada(Jugador jugador){
        if (jugador == null)
            throw new IllegalArgumentException("No se puede anunciar la jugada de un jugador nulo.");        

        muestraTexto("El Jugador \"" + jugador.toString() + "\" ha realizado una jugada."); // Esta vista decide usar directamente la conversión de enumerado a cadena de texto
    }
    
    /**
     * Este método anuncia que según el servidor hay un jugador que ha ganado el juego.
     * @param jugador El jugador ganador (o NULL si queremos indicar que han quedado en tablas)
     */
    void anunciaJugadorGanador(Jugador jugador){
        // Se admite un jugador NULL, significando tablas
        if (jugador == null)
            muestraTexto("¡Fin del juego! Los jugadores han quedado empatados"); // Tablas
        else
            muestraTexto("¡Fin del juego! Ha ganado el Jugador \"" + jugador.toString() + "\"."); // Esta vista decide usar directamente la conversión de enumerado a cadena de texto
    }

    /**
     * Este método realiza una jugada de un determinada jugador, poniendo la ficha correspondiente.
     * @param jugador El jugador representado por la ficha
     * @param jugada La jugada
     */
    void ponFicha(final Jugador jugador, final Jugada jugada){
        if (jugador == null)
            throw new IllegalArgumentException("No se puede poner la ficha de un jugador nulo.");        
        if (jugada == null)
            throw new IllegalArgumentException("No se puede poner la ficha de una jugada nula.");        

        // Cada vez que se vaya a cambiar el estado de un componente Swing desde otro hilo, como ocurre aquí, hay que usar esta técnica para incorporar el cambio como una tarea más dentro del hilo de Swing.
        SwingUtilities.invokeLater(
                new Runnable(){
                    public void run(){
                        tablero[jugada.dameFila() - 1][jugada.dameColumna() - 1].ponFicha(jugador.toString()); // Esta vista decide usar directamente la conversión de enumerado a cadena de texto
                    } 
                });
    } 
    
    /**
     * Este método informa al controlador de que el jugador ha intentado realizar una determinada jugada.
     * @param jugada La jugada
     */
    void intentaJugada(final Jugada jugada){
        if (jugada == null)
            throw new IllegalArgumentException("No se puede intentar una jugada nula.");        

        if (controlador != null) // Por si acaso la vista todavía no tiene un controlador asociado
            controlador.intentaJugada(jugada); 
    }
} 
