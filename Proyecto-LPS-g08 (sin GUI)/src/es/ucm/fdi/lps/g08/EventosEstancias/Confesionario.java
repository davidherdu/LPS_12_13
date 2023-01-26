package es.ucm.fdi.lps.g08.EventosEstancias;

import java.util.Random;
import es.ucm.fdi.lps.g08.*;
import es.ucm.fdi.lps.g08.Cartas.*;
import es.ucm.fdi.lps.g08.servidor.Modelo;

public class Confesionario {
	
	public Confesionario(Jugador j1,Color c,Modelo modelo){
		int i = 0;
		while(c!=modelo.dameJugadores().get(i).getColor()){
			i++;
		}
		
		if(c==modelo.dameJugadores().get(i).getColor()){
			Jugador j = modelo.dameJugadores().get(i);
			Random rand = new Random();
			int posAleatoria = rand.nextInt(j.getMazoSospechoso().longitud());
			Sospechoso s = j.getMazoSospechoso().getSospechoso(posAleatoria);
			j.quitaCartaSospechoso(s);
			j1.setSospechoso(s);
			
		}
	}
}
