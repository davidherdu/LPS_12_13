package es.ucm.fdi.lps.g08;

import java.io.Serializable;
import java.util.*;
//import es.ucm.fdi.lps.g08.Cartas.Carta;
import es.ucm.fdi.lps.g08.Cartas.Sospechoso;
import static es.ucm.fdi.lps.g08.Constantes.*;

public final class mazoSospechoso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Sospechoso> mazo;
	private Vista _vista;

	public mazoSospechoso(){
		mazo = new ArrayList<Sospechoso>();
		_vista = new Vista();
	}
	
	public void mazoInicial(){		
		mazo.add(new Sospechoso(MATTHEW,Orden.templario,Titulo.padre,true,Barba.afeitado,Complexion.gordo));
		mazo.add(new Sospechoso(WILLIAM,Orden.templario,Titulo.padre,true,Barba.barba,Complexion.delgado));
		mazo.add(new Sospechoso(FORTUNE,Orden.templario,Titulo.hermano,true,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(HAROLD,Orden.templario,Titulo.hermano,true,Barba.afeitado,Complexion.delgado));
		mazo.add(new Sospechoso(MALACHI,Orden.templario,Titulo.hermano,false,Barba.barba,Complexion.delgado));
		mazo.add(new Sospechoso(GERARD,Orden.templario,Titulo.novicio,false,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(BASIL,Orden.templario,Titulo.novicio,false,Barba.afeitado,Complexion.gordo));
		mazo.add(new Sospechoso(THOMAS,Orden.templario,Titulo.novicio,true,Barba.afeitado,Complexion.delgado));
		
		mazo.add(new Sospechoso(GALBRAITH,Orden.franciscano,Titulo.padre,false,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(MICHAEL,Orden.franciscano,Titulo.padre,true,Barba.afeitado,Complexion.delgado));
		mazo.add(new Sospechoso(EMMANUEL,Orden.franciscano,Titulo.hermano,true,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(CUTHBERT,Orden.franciscano,Titulo.hermano,true,Barba.afeitado,Complexion.gordo));
		mazo.add(new Sospechoso(JACQUES,Orden.franciscano,Titulo.hermano,false,Barba.afeitado,Complexion.delgado));
		mazo.add(new Sospechoso(BARTHOLOMEW,Orden.franciscano,Titulo.novicio,true,Barba.afeitado,Complexion.gordo));
		mazo.add(new Sospechoso(ANDRE,Orden.franciscano,Titulo.novicio,false,Barba.barba,Complexion.delgado));
		mazo.add(new Sospechoso(NICHOLAS,Orden.franciscano,Titulo.novicio,false,Barba.afeitado,Complexion.delgado));
		
		mazo.add(new Sospechoso(BRUNO,Orden.benedictino,Titulo.padre,false,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(SERGIO,Orden.benedictino,Titulo.padre,false,Barba.afeitado,Complexion.delgado));
		mazo.add(new Sospechoso(BERENGAR,Orden.benedictino,Titulo.hermano,false,Barba.afeitado,Complexion.gordo));
		mazo.add(new Sospechoso(JULIAN,Orden.benedictino,Titulo.hermano,true,Barba.barba,Complexion.delgado));
		mazo.add(new Sospechoso(CYRILLE,Orden.benedictino,Titulo.hermano,false,Barba.barba,Complexion.delgado));
		mazo.add(new Sospechoso(CHARLES,Orden.benedictino,Titulo.novicio,true,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(PHILIPPE,Orden.benedictino,Titulo.novicio,false,Barba.barba,Complexion.gordo));
		mazo.add(new Sospechoso(GUY,Orden.benedictino,Titulo.novicio,true,Barba.afeitado,Complexion.delgado));
	}
	
	public void setSospechoso(Sospechoso s){
		if(!mazo.contains(s)) 
			mazo.add(s);
	}
	
	public void modificaSospechoso(int i, Sospechoso s){
		if(!mazo.contains(s)) 
			mazo.set(i,s);
	}
	
	public Sospechoso getSospechoso(int i){
		return mazo.get(i);
	}
	
	public boolean esta(Sospechoso s){
		return mazo.contains(s);
	}
	
	public void iniciaMazoJuego(int num){
		for(int i=0; i<num;i++){
			mazo.add(new Sospechoso(" ",null,null,false,null,null));
		}
	}
	
	public int longitud(){
		return mazo.size();
	}
	
	/*public Carta dameCarta(int i){
		return mazo.get(i);
	}*/
	
	/*public void muestraCartas(){
		for(int i=0; i<mazo.size(); i++){
			System.out.println(" <---------------- Carta "+(i+1)+" ---------------->");
			_vista.mostrarSospechoso(mazo.get(i));
		}
	}*/
	
	public String muestraCartas(){
		String s = "";
		for(int i=0; i<mazo.size(); i++){
			s = s+" <---------------- Carta "+(i+1)+" ---------------->\n"+_vista.mostrarSospechoso(mazo.get(i));
		}
		return s;
	}
	
	public void elimina(Sospechoso s){
		for(int i=0; i<mazo.size();i++){
			if(mazo.get(i).getNombre().equals(s.getNombre()))  
				mazo.remove(i);
		}
	}
	
	public ArrayList<Sospechoso> dameMazo(){
		return mazo;
	}
}
