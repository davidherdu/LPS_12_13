package es.ucm.fdi.lps.g08;

import java.io.Serializable;

public final class TipoRasgo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Titulo titulo;
	private final Barba barba;
	private final Orden orden;
	private final Complexion comp;
	private final Capucha capucha;
	
	public TipoRasgo(Titulo titulo){
		this.titulo = titulo;
		barba = null;
		orden = null;
		comp = null;
		capucha = null;
	}
	
	public TipoRasgo(Barba barba){
		this.barba = barba;
		titulo = null;
		orden = null;
		comp = null;
		capucha = null;
		
	}
	
	public TipoRasgo(Orden orden){
		this.orden = orden;
		titulo = null;
		barba = null;
		comp = null;
		capucha = null;
	}
	
	public TipoRasgo(Complexion comp){
		this.comp = comp;
		titulo = null;
		barba = null;
		orden = null;
		capucha = null;
	}
	
	public TipoRasgo(Capucha capucha){
		this.capucha = capucha;
		titulo = null;
		barba = null;
		comp = null;
		orden = null;
	}
	
	public Titulo getTitulo(){
		return titulo;
	}
	
	public Barba getBarba(){
		return barba;
	}
	
	public Orden getOrden(){
		return orden;
	}
	
	public Complexion getComplexion(){
		return comp;
	}
	
	public Capucha getCapucha(){
		return capucha;
	}
}
