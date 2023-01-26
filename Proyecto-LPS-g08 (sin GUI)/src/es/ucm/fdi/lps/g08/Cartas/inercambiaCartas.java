package es.ucm.fdi.lps.g08.Cartas;

import es.ucm.fdi.lps.g08.*;

public class inercambiaCartas{
	
	public inercambiaCartas(){
		
	}
	
	public void evento(Partida p){
		tiradaDado t = new tiradaDado();
		Jugador j = p.dameJugador();
		Jugador jAux = t.evento(p);
		jAux.getMazoSospechoso().muestraCartas();
		intercambiar(j,jAux);
	}
	
	public void intercambiar(Jugador j,Jugador jAux){
		mazoSospechoso mazoAux = new mazoSospechoso();
		mazoSospechoso mazoTmp = new mazoSospechoso();
		
		//Las quito del primer jugador y las pongo en mazoAux
		int i = 0;
		while(j.getMazoSospechoso().longitud()>0){
			mazoAux.setSospechoso(j.getMazoSospechoso().getSospechoso(i));
			j.quitaCartaSospechoso(j.getMazoSospechoso().getSospechoso(i));
			i++;
		}
		
		//Las quito del segundo jugador y las pongo en mazoTmp
		i = 0;
		while(jAux.getMazoSospechoso().longitud()>0){
			mazoTmp.setSospechoso(jAux.getMazoSospechoso().getSospechoso(i));
			jAux.quitaCartaSospechoso(jAux.getMazoSospechoso().getSospechoso(i));
			i++;
		}
		
		//Las cartas de mazoTmp se las pongo al primer jugador
		i = 0;
		while(mazoTmp.longitud()>0){
			j.setSospechoso(mazoTmp.getSospechoso(i));
			mazoTmp.elimina(mazoTmp.getSospechoso(i));
			i++;
		}
		
		//Las cartas de mazoAux se las pongo al segundo jugador
		i = 0;
		while(mazoAux.longitud()>0){
			jAux.setSospechoso(mazoAux.getSospechoso(i));
			mazoAux.elimina(mazoAux.getSospechoso(i));
			i++;
		}
	}

}
