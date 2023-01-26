package es.ucm.fdi.lps.g08.Cartas;

import es.ucm.fdi.lps.g08.*;

public class robarCartas{
	
	public robarCartas(){
		
	}
	
	public void evento(Partida p/*,tipoCarta t*/){
		Jugador j = p.dameJugador();
		//Robar tres cartas de Scriptorium
		//El codigo de esta carta seria mas o menos asi
		/*
		 * 	if(p.mazoScriptorium().longitud()>=3){
		 *    int cont = 0;
		 *    while(cont<3){
		 *      j.setScriptorium(p.mazoScruiptorium().getCima());
		 *      p.mazoScruiptorium().quitaCima();
		 *      cont++;
		 *    }
		 *  }else{
		 *    while(p.mazoScriptorium().longitud()>0){
		 *      j.setScriptorium(p.mazoScruiptorium().getCima());
		 *      p.mazoScruiptorium().quitaCima();
		 *    }
		 *  }
		 */
	}

}
