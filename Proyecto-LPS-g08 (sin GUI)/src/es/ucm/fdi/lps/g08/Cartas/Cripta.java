package es.ucm.fdi.lps.g08.Cartas;

import es.ucm.fdi.lps.g08.*;

public class Cripta{

	public Cripta(Partida p){
		
	}
	
	public void evento(Partida p){
		int j = p.dameTurno();
		p.faseMovimiento(j,1);
	}
}
