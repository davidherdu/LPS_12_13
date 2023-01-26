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
package es.ucm.fdi.lps.tresenraya.cliente;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import es.ucm.fdi.lps.tresenraya.Jugada;

/**
 * Esta clase representa una parte de la interfaz gr�fica de usuario del cliente del juego, concretamente a cada una de las casillas del tablero de juego.
 * @author Federico Peinado 
 */
final class VistaCasilla extends JPanel { 

    /** Tama�o del tablero de juego (siempre cuadrado, N x N) */
    public static final int TABLERO = 3; 
    
    private static final short ANCHO_CASILLA = 30;
    private static final short ALTO_CASILLA = 30;
    private static final short FICHA_MARGEN_X = 11;
    private static final short FICHA_MARGEN_Y = 20;
    
    // Aconsejable porque los JPanel son serializables
    private static final long serialVersionUID = 1L;

    private String ficha; 

    /**
     * El constructor de cada una de las casillas del tablero en la interfaz gr�fica de usuario del cliente. 
     * @param vista La vista principal ('padre' de las casillas)
     * @param ficha La ficha (imagen) inicial que ocupa esta casilla
     * @param jugada La jugada que supone intentar colocar ficha en esta casilla
     */
    public VistaCasilla(final Vista vista, String ficha, final Jugada jugada) { 
        if (vista == null)
            throw new IllegalArgumentException("No se puede crear una vista de casilla con una vista principal nula.");        
        if (ficha == null)
            throw new IllegalArgumentException("No se puede crear una vista de casilla con una ficha nula.");        
        if (jugada == null)
            throw new IllegalArgumentException("No se puede crear una vista de casilla con una jugada nula.");        

        this.ficha = ficha;  
        addMouseListener( 
                new MouseAdapter() { 
                    public void mouseReleased(MouseEvent e) {
                        // No compruebo que el evento del rat�n sea nulo... me fio
                        // Se establece una cadena de mando: la vista de esta casilla informa a la vista general, y esta a su vez al controlador
                        vista.intentaJugada(jugada); 
                    } 
                });  
    } 

    /**
     * Este m�todo devuelve la dimensi�n preferida para el tama�o de una casilla en el tablero de juego.
     * @return La dimensi�n deseada
     */
    @Override
    public Dimension getPreferredSize() { 
        return new Dimension(ANCHO_CASILLA, ALTO_CASILLA);  
    } 

    /**
     * Este m�todo devuelve la dimensi�n m�nima requerida para el tama�o de una casilla en el tablero de juego.
     * @return La dimensi�n deseada
     */
    @Override
    public Dimension getMinimumSize() { 
        return getPreferredSize(); 
    }  

    /**
     * Este m�todo pinta la ficha del jugador (o el vac�o, si no hay ficha puesta) en el centro de esta casilla del tablero de juego.
     * @param g El contexto gr�fico donde pintar
     */
    @Override
    public void paintComponent(Graphics g) { 
        // No compruebo que el contexto gr�fico que me pasan sea nulo... me fio
        super.paintComponent(g); 
        g.drawRect(0, 0, ANCHO_CASILLA - 1, ALTO_CASILLA - 1); 
        g.drawString(ficha, FICHA_MARGEN_X, FICHA_MARGEN_Y);  
    } 

    /**
     * Este m�todo pone una ficha de un determinada jugador en esta casilla del tablero de juego.
     * @param ficha La ficha
     */
    void ponFicha(String ficha) { 
        if (ficha == null)
            throw new IllegalArgumentException("No se puede poner una ficha nula.");                
        this.ficha = ficha;
        repaint(); // Es necesario repintar para ver el cambio
    }
}