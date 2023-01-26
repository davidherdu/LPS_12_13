package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;
import java.util.Random;
import es.ucm.fdi.lps.g08.*;

public class Misa{

	public Misa(String nombre,String descripcion,Partida p,int num,Jugador j1,Jugador j2){
		ArrayList<Jugador> tmp = new ArrayList<Jugador>();
		int i = 0;
		while(i<p.numJugadores()){
			tmp.add(tmp.get(i));
			i++;
		}
		if(j2!=null) 
			tmp.remove(j2);
		
		evento(p,num,j1,tmp);
	}
	
	public void evento(Partida p, int num,Jugador jAux,ArrayList<Jugador> tmp){
		//Primero muevo todos a la capilla
		for(int i=0;i<tmp.size();i++){
			Room r = tmp.get(i).getEstancia();
			Jugador jug = tmp.get(i);
			p.movimiento(r,p.dameMapa().dameInicial(),jug);
		}
		
		Vista vista = new Vista();
		//Despues le dan todos un numero determinado de sus cartas de sospechoso
		//al jugador que tienen a la izaquierda
		for(int j=0;j<tmp.size();j++){
			Jugador jug = tmp.get(j);
			mazoSospechoso m = jug.getMazoSospechoso();
			int k = 0;
			while(k<=num){
				if(jug.getMazoSospechoso().longitud()==0){
					vista.normal(" A este jugador no le quedan mas cartas de sospechoso");
				}
				else{
					Random rand = new Random();
					int posAleatoria = rand.nextInt(m.dameMazo().size());
					Sospechoso s = m.getSospechoso(posAleatoria);
					jug.quitaCartaSospechoso(s);
					//esto lo hago x la carta CartularioAbadia en la que puedo ver las 
					//cartas que un jugador le pasa a otro
					if(jug==jAux){
						vista.mostrarSospechoso(s);
						jAux.setEnLista(s);
					}
					//cualquier jugador le da la carta al que esta a su izquierda
					//que es j-1, si es el primero, es decir j=0, se la da al ultimo
					if(j>0) 
						tmp.get(j-1).setSospechoso(s);
					else{
						int aux = tmp.size();
						tmp.get(aux).setSospechoso(s);;
					}
					k++;
				}
			}
		}
		//coger una carta de evento
	}
}
