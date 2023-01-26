package es.ucm.fdi.lps.g08;

import java.util.ArrayList;
import es.ucm.fdi.lps.g08.Cartas.Carta;
import static es.ucm.fdi.lps.g08.Constantes.*;

public class mazoCripta {
	
	private ArrayList<Carta> _mazo = new ArrayList<Carta>();
	
	public mazoCripta(){
		_mazo = new ArrayList<Carta>();
	}
	
	public void iniciaMazo(){
		Carta ccripta1 = new Carta(CARTACRIPTA,textoCartaCripta,true);
		Carta ccripta2 = new Carta(CARTACRIPTA,textoCartaCripta,true);
		Carta ccripta3 = new Carta(CARTACRIPTA,textoCartaCripta,true);
		Carta ccripta4 = new Carta(CARTACRIPTA,textoCartaCripta,true);
		Carta ccripta5 = new Carta(CARTACRIPTA,textoCartaCripta,true);
		Carta ccripta6 = new Carta(CARTACRIPTA,textoCartaCripta,true);
		
		_mazo.add(ccripta1);
		_mazo.add(ccripta2);
		_mazo.add(ccripta3);
		_mazo.add(ccripta4);
		_mazo.add(ccripta5);
		_mazo.add(ccripta6);
	}
	
	public Carta getCarta(int i){
		return _mazo.get(i);
	}
	
	public ArrayList<Carta> getMazo(){
		return _mazo;
	}

}
