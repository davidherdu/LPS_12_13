package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;
import es.ucm.fdi.lps.g08.*;

public class moverEstancia{

	public moverEstancia(){
		
	}
	
	public void evento(Partida p,String s,ArrayList<Jugador> aux){
		Room estancia = p.dameMapa().dameEstanciaPorNombre(s);
		for(int i=0;i<aux.size();i++){
			Room r = aux.get(i).getEstancia();
			Jugador jug = aux.get(i);
			p.movimiento(r,estancia,jug);
		}
	}
	
	public void moverTodos(Partida p,String s){
		evento(p,s,p.dameJugadores());
	}
	
	public void moverTodosDiferentes(Partida p,String s){
		Jugador j = p.dameJugador();
		ArrayList<Jugador> aux = new ArrayList<Jugador>();
		int i = 0;
		while(i<p.numJugadores()){
			aux.add(p.dameJugadores().get(i));
			i++;
		}
		aux.remove(j);
		
		i = 0;
		eligeEstancia e = new eligeEstancia();
		Room r = null;
		while(i<aux.size()){
			r = e.evento(p);
			if(r.numJugadores()>0)
				while(r.numJugadores()>0){
					r = e.evento(p);
				}
			p.movimiento(aux.get(i).getEstancia(),r,aux.get(i));
			i++;
		}
	}
}
