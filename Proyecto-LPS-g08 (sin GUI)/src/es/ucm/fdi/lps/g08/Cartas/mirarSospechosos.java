package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;

import es.ucm.fdi.lps.g08.*;

public class mirarSospechosos{

	public mirarSospechosos(){
		
	}
	
	public void alAzar(Partida p){
		tiradaDado t = new tiradaDado();
		Jugador j = p.dameJugador();
		Jugador jAux = t.evento(p);
		mirarCartas(j,jAux);
	}
	
	public void elegidoPorMi(Partida p){
		Jugador j = p.dameJugador();
		ArrayList<Jugador> aux = new ArrayList<Jugador>();
		int i = 0;
		while(i<p.numJugadores()){
			aux.add(p.dameJugadores().get(i));
			i++;
		}
		aux.remove(j);
		
		Vista v = new Vista();
		v.normal(" Elige un jugador");
		int num = v.limites(1,aux.size()-1);
		Jugador jAux = aux.get(num);
		mirarCartas(j,jAux);
	}
	
	public void mirarCartas(Jugador j,Jugador jAux){
		jAux.getMazoSospechoso().muestraCartas();
		int i = 0;
		while(i<jAux.getMazoSospechoso().longitud()){
			j.setEnLista(jAux.getMazoSospechoso().getSospechoso(i));
			i++;
		}
	}
}
