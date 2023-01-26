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
package es.ucm.fdi.lps.tresenraya;

/**
 * Esta clase representa los posibles jugadores que admite el juego, con la respectiva cadena de texto que representa la ficha que se muestra en sus casillas.
 * @author Federico Peinado
 */
// El n�mero de jugadores (2) va impl�cito en el n�mero de valores que tiene el enumerado Jugador.
// Aunque la cuesti�n de la cadena de texto que representa a la ficha es s�lo relevante para el cliente, est� aqu� por el hecho de aprovechar el enumerado
public enum Jugador {
    JUGADOR_X("X"), JUGADOR_O("O");
    
    private String ficha; 
    
    // El constructor es privado para que no lo puedan usar otras clases.
    private Jugador(final String ficha){
        if (ficha == null)
            throw new IllegalArgumentException("No se puede crear un jugador de marca nula.");
        this.ficha = ficha;
    }
    
    /**
     * Este m�todo devuelve el jugador inicial. 
     * @return El jugador inicial
     */
    // Aunque los valores del enumerado est�n ordenados, es preferible no fiarse �nicamente de sus ordinales para trabajar con ellos.
    public static Jugador dameInicial(){
        return JUGADOR_X;
    }
    
    /**
     * Este m�todo devuelve el jugador siguiente al que recibe esta llamada. 
     * @return El siguiente jugador
     */
    // Aunque los valores del enumerado est�n ordenados, es preferible no fiarse �nicamente de sus ordinales para trabajar con ellos.
    // Internamente s� usamos el ordinal, pero queda encapsulado de cara al cliente.
    public Jugador dameSiguiente(){
        int ordinal = this.ordinal();
        if (ordinal == Jugador.values().length - 1)
            return dameInicial();
        else
            return Jugador.values()[this.ordinal() + 1];
    }

    /**
     * Este m�todo devuelve la cadena de texto que sirve para representar la ficha de cada jugador que se muestra en sus casillas.
     * @return La cadena de texto correspondiente
     */
    @Override
    public String toString(){
        return ficha;
    }  
}