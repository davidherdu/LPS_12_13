package es.ucm.fdi.lps.g08.Cartas;

import es.ucm.fdi.lps.g08.*;

public class realizaRevelacion{
	
	public realizaRevelacion(){
		
	}

	public void evento(Partida p){
		Jugador j = p.dameJugador();
		p.revelacion(j);
	}
}
