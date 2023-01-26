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
package es.ucm.fdi.lps.g08;

import java.io.Serializable;

import es.ucm.fdi.lps.g08.Cartas.Sospechoso;
//import static es.ucm.fdi.lps.g08.Constantes.*;

/**
 * Esta clase representa las posibles jugadas que se pueden hacer en el juego (coordenadas del tablero donde colocar una ficha).
 * @author Federico Peinado
 */
// Se parece a un Java Bean, sólo que no tiene constructor sin argumentos, le faltan setters y los getters están escritos en español (dameX) en vez de en inglés (getX)
// Tiene que ser serializable obligatoriamente porque, aunque no se manda directamente por red, si forma parte del MensajeCliente que se envía por red
public final class Jugada implements Serializable {

    // Aconsejable para guardar el número de versión de los objetos serializados de esta clase
    private static final long serialVersionUID = 1L;
    
    /*private final byte fila;
    private final byte columna;*/
    private final int room;
    private final int numero;
    private final String cadena,tipo;
    private final Room estancia;
    private final TipoRasgo tr;
    private final Sospechoso sospechoso;
    private final TipoUbicacion tu;
    
    /**
     * El constructor de una jugada (coordenadas del tablero donde colocar una ficha).
     * @param fila La fila donde se realiza la jugada [1..DIMENSION_TABLERO]
     * @param columna La columna donde se realiza la jugada [1..DIMENSION_TABLERO]
     */
    /*public Jugada(final byte fila, final byte columna){
        if (fila < 1 || fila > DIMENSION_TABLERO)
            throw new IllegalArgumentException("No se puede crear una jugada en la fila " + fila + ".");
        if (columna < 1 || columna > DIMENSION_TABLERO)
            throw new IllegalArgumentException("No se puede crear una jugada en la columna " + columna + ".");
        this.fila = fila;
        this.columna = columna;
    }*/
    
    public Jugada(final int room, final int numero, final String cadena){
    	this.room = room;
    	this.numero = numero;
    	this.cadena = cadena;
    	estancia = null;
    	tipo = "";
    	tr = null;
    	sospechoso = null;
    	tu = null;
    }
    
    public Jugada(final Room est, final int numero, final String cadena){
    	this.estancia = est;
    	this.numero = numero;
    	this.cadena = cadena;
    	room = 0;
    	tipo = "";
    	tr = null;
    	sospechoso = null;
    	tu = null;
    }
    
    public Jugada(final String tipo,final String cadena){
    	this.tipo = tipo;
    	this.cadena = cadena;
    	numero = 0;
    	room = 0;
    	estancia = null;
    	tr = null;
    	sospechoso = null;
    	tu = null;
    }
    
    public Jugada(final TipoRasgo t,final TipoUbicacion tu,final String cadena){
    	this.tr = t;
    	this.cadena = cadena;
    	this.tu = tu;
    	numero = 0;
    	room = 0;
    	estancia = null;
    	tipo = "";
    	sospechoso = null;
    }
    
    public Jugada(final Sospechoso sospechoso){
    	this.sospechoso = sospechoso;
    	tipo = "";
    	cadena = "";
    	numero = 0;
    	room = 0;
    	estancia = null;
    	tr = null;
    	tu = null;
    }
    
    public int dameEstancia(){
    	return room;
    }
    
    public int dameNumero(){
    	return numero;
    }
    
    public String dameCadena(){
    	return cadena;
    }
    
    public String dameTipo(){
    	return tipo;
    }
    
    public TipoRasgo dameTipoRasgo(){
    	return tr;
    }
    
    public Sospechoso dameSospechoso(){
    	return sospechoso;
    }
    
    public TipoUbicacion dameTipoUbicacion(){
    	return tu;
    }
    
    /**
     * Este método devuelve la fila donde se realiza la jugada.
     * @return La fila donde se realiza la jugada
     */
   /* public byte dameFila() {
        return fila;
    }*/
    
    /**
     * Este método devuelve la columna donde se realiza la jugada.
     * @return La columna se realiza la jugada
     */
   /* public byte dameColumna() {
        return columna;
    }*/
    
    /**
     * Este método devuelve la cadena de texto que sirve para representar una jugada en este juego (coordinadas de una casilla en el tablero).
     * @return La cadena de texto correspondiente
     */
    //@Override
    /*public String toString(){
        return "(" + fila + ", " + columna + ")";
    } */
    
    public String toString(){
    	return "Estancia: " + room;
    }
}
