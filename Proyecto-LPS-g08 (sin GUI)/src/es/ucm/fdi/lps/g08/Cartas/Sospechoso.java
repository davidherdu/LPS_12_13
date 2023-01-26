package es.ucm.fdi.lps.g08.Cartas;

import java.io.Serializable;
import es.ucm.fdi.lps.g08.*;

public class Sospechoso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _nombre;
	private Orden _orden;
	private Titulo _titulo;
	private boolean _capucha;
	private Barba _facial;
	private Complexion _complexion;

	public Sospechoso(String nombre, Orden orden, Titulo titulo, boolean capucha,
			Barba facial, Complexion complexion){
		
		_nombre = nombre;
		_orden = orden;
		_titulo = titulo;
		_capucha = capucha;
		_facial = facial;
		_complexion = complexion;
	}

	public String getNombre(){
		return _nombre;
	}
	
	public String getOrden(){
		return _orden.toString();
	}
	
	public String getTitulo(){
		return _titulo.toString();
	}
	
	public boolean getCapucha(){
		return _capucha;
	}
	
	public String getFacial(){
		return _facial.toString();
	}
	
	public String getComplexion(){
		return _complexion.toString();
	}
}
