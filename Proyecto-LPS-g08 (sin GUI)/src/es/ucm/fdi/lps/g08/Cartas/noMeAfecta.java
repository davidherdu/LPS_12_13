package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;
import es.ucm.fdi.lps.g08.*;

public class noMeAfecta {
	
	private ArrayList<Jugador> aux;
	
	public noMeAfecta(Partida p){
		aux = new ArrayList<Jugador>();
		Jugador j = p.dameJugador();
		int i = 0;
		while(i<p.numJugadores()){
			aux.add(p.dameJugadores().get(i));
			i++;
		}
		aux.remove(j);
	}
	
	public ArrayList<Jugador> dameJugadores(){
		return aux;
	}

}
