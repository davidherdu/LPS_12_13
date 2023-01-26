package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;
import es.ucm.fdi.lps.g08.*;

public class jugarDeNuevo{
	
	public jugarDeNuevo(){
		
	}
	
	public void eligiendoEstancia(int i,Partida p){
		eligeEstancia e = new eligeEstancia();
		Room r = e.evento(p);
		Jugador j = p.dameJugador();
		p.movimiento(j.getEstancia(),r,j);
	}
	
	public void sinElegir(int i,Partida p){
		ArrayList<Jugador> aux = p.dameJugadores();
		Jugador j = p.dameJugador();
		int num = aux.indexOf(j);
		p.faseMovimiento(num,3);
	}
}
