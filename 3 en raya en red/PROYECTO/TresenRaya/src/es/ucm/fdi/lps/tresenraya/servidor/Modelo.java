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
package es.ucm.fdi.lps.tresenraya.servidor;

import es.ucm.fdi.lps.tresenraya.Jugada;
import es.ucm.fdi.lps.tresenraya.Jugador;

import static es.ucm.fdi.lps.tresenraya.Parametros.*;

/**
 * Esta clase representa el modelo del juego Tres en Raya, con la informaci�n del tablero y las reglas b�sicas del juego (como el c�lculo para averiguar si se ha terminado).
 * @author Federico Peinado
 */
class Modelo {
        
    // El tablero l�gico ser� siempre cuadrado (N x N) y las coordenadas ir�n de 0 a N-1.
    // Al principio el tablero tiene todas sus casillas a NULL que significa que est�n vac�as
    private Jugador[][] tablero = new Jugador[DIMENSION_TABLERO][DIMENSION_TABLERO]; 
    private Jugador jugadorActivo = Jugador.dameInicial(); 
    // Esta variable booleana nos ayuda a comprobar si alguien ha ganado el juego S�LO tras una jugada realizada, no cada vez que nos pregunten
    private boolean juegoGanado = false;
    // Esta variable booleana nos ayuda a comprobar si el tablero est� completo S�LO tras una jugada realizada, no cada vez que nos pregunten
    private boolean tableroCompleto = false;
    
    // Estas variables se declaran aqu� por eficiencia, aunque en realidad s�lo se usen en el m�todo 'juegoTerminado'. Representan a las tres casillas causantes de que un jugador gane la partida.
    private Jugador casilla1, casilla2, casilla3;
    private int fila1, columna1, fila2, columna2, fila3, columna3;

    // Constructor por defecto, sin par�metros
    
    /**
     * Este m�todo devuelve el jugador que tiene actualmente el turno en la partida.
     * @return El jugador activo
     */
    Jugador dameJugadorActivo(){
        return jugadorActivo;
    }

    /**
     * Este m�todo verifica que es posible realizar una jugada de cierto jugador.
     * En caso de que sea posible, devuelve cierto y realiza el cambio correspondiente en el tablero. Si no, devuelve falso.
     * @param jugador El jugador que desea realizar la jugada
     * @param jugada La jugada a realizar
     * @return Cierto si se ha podido realizar la jugada, falso en caso contrario
     */
    boolean realizaJugada(final Jugador jugador, final Jugada jugada) {
        if (jugador == null)
            throw new IllegalArgumentException("No se puede realizar una jugada hecha por un jugador nulo.");        
        if (jugada == null)
            throw new IllegalArgumentException("No se puede realizar una jugada nula.");        

        // Por robustez comprobamos que el tablero no est� completo, que nadie haya ganado ya el juego y que sea el turno de este jugador
        if (!tableroCompleto && !juegoGanado && jugador.equals(jugadorActivo) && !casillaOcupada(jugada) ){
            tablero[jugada.dameFila() - 1][jugada.dameColumna() - 1] = jugador; 
            if (verificaJuegoGanado())
                juegoGanado = true;
            else 
                if (verificaTableroCompleto())
                    tableroCompleto = true;
                else
                    jugadorActivo = jugador.dameSiguiente(); // M�todo gen�rico para acceder al siguiente jugador, aunque en la pr�ctica s�lo haya 2 jugadores
            return true;
        }
        return false;
    }

    private boolean casillaOcupada(Jugada jugada) { 
        if (jugada == null)
            throw new IllegalArgumentException("No se puede ver si est� ocupada la casilla de una jugada nula.");        
        return (tablero[jugada.dameFila() - 1][jugada.dameColumna() - 1] != null ? true : false); // Si la casilla es distinta de NULL, entonces est� ocupada
    } 
    
    /**
     * Este m�todo informa de si el juego ya ha terminado.
     * @return Cierto si la partida ha terminado, falso en caso contrario
     */
    boolean juegoGanado() {   
        return juegoGanado;
    }

    // Esta implementaci�n podr�a optimizarse con un algoritmo (idealmente recibiendo informaci�n del �ltimo movimiento realizado), en vez de comprobar una por una todas las alternativas de tableros completos. 
    // De hecho ser�a interesante generalizar el algoritmo a tableros de N x N, por ejemplo como ejercicio de examen ;)
    // En cualquier caso es importante se�alar que no hace falta indicar el ganador o si hay empate: s�lo decir si la partida ha terminado
    // M�s informaci�n: http://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over-java
    private boolean verificaJuegoGanado() {   
        
        fila1 = 1; columna1 = 1;
        fila2 = 1; columna2 = 2;
        fila3 = 1; columna3 = 3;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;
        
        fila1 = 1; columna1 = 1;
        fila2 = 2; columna2 = 1;
        fila3 = 3; columna3 = 1;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;
        
        fila1 = 1; columna1 = 1;
        fila2 = 2; columna2 = 2;
        fila3 = 3; columna3 = 3;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;

        fila1 = 1; columna1 = 2;
        fila2 = 2; columna2 = 2;
        fila3 = 3; columna3 = 2;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;

        fila1 = 1; columna1 = 3;
        fila2 = 2; columna2 = 3;
        fila3 = 3; columna3 = 3;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;

        fila1 = 2; columna1 = 1;
        fila2 = 2; columna2 = 2;
        fila3 = 2; columna3 = 3;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;
        
        fila1 = 3; columna1 = 1;
        fila2 = 3; columna2 = 2;
        fila3 = 3; columna3 = 3;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;
        
        fila1 = 1; columna1 = 3;
        fila2 = 2; columna2 = 2;
        fila3 = 3; columna3 = 1;
        casilla1 = tablero[fila1 - 1][columna1 - 1];
        casilla2 = tablero[fila2 - 1][columna2 - 1];
        casilla3 = tablero[fila3 - 1][columna3 - 1];        
        if(casilla1!=null && casilla1.equals(casilla2) && casilla1.equals(casilla3))
            return true;

        // Si no se dan ninguno de los casos anteriores, el juego no ha terminado
        return false;
    } 
    
    /**
     * Este m�todo informa de si el tablero ya est� completo (no se pueden colocar m�s fichas).
     * @return Cierto si el tablero est� completo, falso en caso contrario
     */
    boolean tableroCompleto() {   
        return tableroCompleto;
    }
    
    // Comprobaci�n no gen�rica de si el tablero est� completo
    private boolean verificaTableroCompleto() {   
        
        // Aprovechamos las variables fila1 y columna1 para usarlas de contadores
        for (fila1 = 1; fila1 <= DIMENSION_TABLERO; fila1++)
            for (columna1 = 1; columna1 <= DIMENSION_TABLERO; columna1++)
                if (tablero[fila1 - 1][columna1 - 1] == null)
                    return false;
        
        // Si no hemos encontrado ning�n NULL es que el tablero est� completo
        return true;
    } 
}