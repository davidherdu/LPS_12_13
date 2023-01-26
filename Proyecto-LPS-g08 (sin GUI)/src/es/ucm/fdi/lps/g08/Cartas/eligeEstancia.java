package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;

import es.ucm.fdi.lps.g08.*;

public class eligeEstancia{

	public eligeEstancia(){
		
	}
	
	public Room evento(Partida p){
		ArrayList<Room> tmp = p.dameMapa().mostrarEstancias();
		Vista v = new Vista();
		int num = v.limites(1,tmp.size());
		Room room = p.dameMapa().dameHabitacion(num-1);
		while((room.getNombre().equals(Constantes.CONFESIONARIO_EXTERIOR)||
			    room.getNombre().equals(Constantes.CONFESIONARIO_INTERIOR))&&
			    !room.getJugadores().isEmpty()){
			v.normal(" No se puede ir a un confesionario mientras esté ocupado");
			v.normal(" Vuelve a elegir una estancia");
			tmp = p.dameMapa().mostrarEstancias();
			v = new Vista();
			num = v.limites(1,tmp.size());
			room = p.dameMapa().dameHabitacion(num-1);
		}
		return room;
	}
}
