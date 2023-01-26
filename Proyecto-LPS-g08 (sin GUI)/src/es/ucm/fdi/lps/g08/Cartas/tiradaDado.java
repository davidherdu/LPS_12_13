package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;
import java.util.Random;
import es.ucm.fdi.lps.g08.*;

public class tiradaDado{
	
	public tiradaDado(){

	}
	
	public Jugador evento(Partida p){
		Jugador j = p.dameJugador();
		ArrayList<Jugador> aux = new ArrayList<Jugador>();
		int i = 0;
		while(i<p.numJugadores()){
			aux.add(p.dameJugadores().get(i));
			i++;
		}
		aux.remove(j);
		
		int tam = aux.size();
		Random rand = new Random();
		int posAleatoria = rand.nextInt(tam);
		return dameJugador(posAleatoria,aux);
	}
	
	public Jugador dameJugador(int i,ArrayList<Jugador> aux){
		return aux.get(i);
	}

}
