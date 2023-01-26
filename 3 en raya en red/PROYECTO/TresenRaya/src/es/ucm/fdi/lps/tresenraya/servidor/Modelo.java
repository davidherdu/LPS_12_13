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

import es.ucm.fdi.lps.tresenraya.Jugada;
import es.ucm.fdi.lps.tresenraya.Jugador;

import static es.ucm.fdi.lps.tresenraya.Parametros.*;

/**
 * Esta clase representa el modelo del juego Tres en Raya, con la información del tablero y las reglas básicas del juego (como el cálculo para averiguar si se ha terminado).
 * @author Federico Peinado
 */
class Modelo {
        
    // El tablero lógico será siempre cuadrado (N x N) y las coordenadas irán de 0 a N-1.
    // Al principio el tablero tiene todas sus casillas a NULL que significa que están vacías
    private Jugador[][] tablero = new Jugador[DIMENSION_TABLERO][DIMENSION_TABLERO]; 
    private Jugador jugadorActivo = Jugador.dameInicial(); 
    // Esta variable booleana nos ayuda a comprobar si alguien ha ganado el juego SÓLO tras una jugada realizada, no cada vez que nos pregunten
    private boolean juegoGanado = false;
    // Esta variable booleana nos ayuda a comprobar si el tablero está completo SÓLO tras una jugada realizada, no cada vez que nos pregunten
    private boolean tableroCompleto = false;
    
    // Estas variables se declaran aquí por eficiencia, aunque en realidad sólo se usen en el método 'juegoTerminado'. Representan a las tres casillas causantes de que un jugador gane la partida.
    private Jugador casilla1, casilla2, casilla3;
    private int fila1, columna1, fila2, columna2, fila3, columna3;

    // Constructor por defecto, sin parámetros
    
    /**
     * Este método devuelve el jugador que tiene actualmente el turno en la partida.
     * @return El jugador activo
     */
    Jugador dameJugadorActivo(){
        return jugadorActivo;
    }

    /**
     * Este método verifica que es posible realizar una jugada de cierto jugador.
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

        // Por robustez comprobamos que el tablero no esté completo, que nadie haya ganado ya el juego y que sea el turno de este jugador
        if (!tableroCompleto && !juegoGanado && jugador.equals(jugadorActivo) && !casillaOcupada(jugada) ){
            tablero[jugada.dameFila() - 1][jugada.dameColumna() - 1] = jugador; 
            if (verificaJuegoGanado())
                juegoGanado = true;
            else 
                if (verificaTableroCompleto())
                    tableroCompleto = true;
                else
                    jugadorActivo = jugador.dameSiguiente(); // Método genérico para acceder al siguiente jugador, aunque en la práctica sólo haya 2 jugadores
            return true;
        }
        return false;
    }

    private boolean casillaOcupada(Jugada jugada) { 
        if (jugada == null)
            throw new IllegalArgumentException("No se puede ver si está ocupada la casilla de una jugada nula.");        
        return (tablero[jugada.dameFila() - 1][jugada.dameColumna() - 1] != null ? true : false); // Si la casilla es distinta de NULL, entonces está ocupada
    } 
    
    /**
     * Este método informa de si el juego ya ha terminado.
     * @return Cierto si la partida ha terminado, falso en caso contrario
     */
    boolean juegoGanado() {   
        return juegoGanado;
    }

    // Esta implementación podría optimizarse con un algoritmo (idealmente recibiendo información del último movimiento realizado), en vez de comprobar una por una todas las alternativas de tableros completos. 
    // De hecho sería interesante generalizar el algoritmo a tableros de N x N, por ejemplo como ejercicio de examen ;)
    // En cualquier caso es importante señalar que no hace falta indicar el ganador o si hay empate: sólo decir si la partida ha terminado
    // Más información: http://stackoverflow.com/questions/1056316/algorithm-for-determining-tic-tac-toe-game-over-java
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
     * Este método informa de si el tablero ya está completo (no se pueden colocar más fichas).
     * @return Cierto si el tablero está completo, falso en caso contrario
     */
    boolean tableroCompleto() {   
        return tableroCompleto;
    }
    
    // Comprobación no genérica de si el tablero está completo
    private boolean verificaTableroCompleto() {   
        
        // Aprovechamos las variables fila1 y columna1 para usarlas de contadores
        for (fila1 = 1; fila1 <= DIMENSION_TABLERO; fila1++)
            for (columna1 = 1; columna1 <= DIMENSION_TABLERO; columna1++)
                if (tablero[fila1 - 1][columna1 - 1] == null)
                    return false;
        
        // Si no hemos encontrado ningún NULL es que el tablero está completo
        return true;
    } 
}