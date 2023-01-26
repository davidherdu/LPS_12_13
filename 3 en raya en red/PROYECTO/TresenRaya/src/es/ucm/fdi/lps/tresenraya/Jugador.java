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
package es.ucm.fdi.lps.tresenraya;

/**
 * Esta clase representa los posibles jugadores que admite el juego, con la respectiva cadena de texto que representa la ficha que se muestra en sus casillas.
 * @author Federico Peinado
 */
// El número de jugadores (2) va implícito en el número de valores que tiene el enumerado Jugador.
// Aunque la cuestión de la cadena de texto que representa a la ficha es sólo relevante para el cliente, está aquí por el hecho de aprovechar el enumerado
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
     * Este método devuelve el jugador inicial. 
     * @return El jugador inicial
     */
    // Aunque los valores del enumerado están ordenados, es preferible no fiarse únicamente de sus ordinales para trabajar con ellos.
    public static Jugador dameInicial(){
        return JUGADOR_X;
    }
    
    /**
     * Este método devuelve el jugador siguiente al que recibe esta llamada. 
     * @return El siguiente jugador
     */
    // Aunque los valores del enumerado están ordenados, es preferible no fiarse únicamente de sus ordinales para trabajar con ellos.
    // Internamente sí usamos el ordinal, pero queda encapsulado de cara al cliente.
    public Jugador dameSiguiente(){
        int ordinal = this.ordinal();
        if (ordinal == Jugador.values().length - 1)
            return dameInicial();
        else
            return Jugador.values()[this.ordinal() + 1];
    }

    /**
     * Este método devuelve la cadena de texto que sirve para representar la ficha de cada jugador que se muestra en sus casillas.
     * @return La cadena de texto correspondiente
     */
    @Override
    public String toString(){
        return ficha;
    }  
}