package es.ucm.fdi.lps.g08;

import java.io.Serializable;
import java.util.*;

public final class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _descripcion;
	private String _nombre;
	private ArrayList<Jugador> _jugador;
	private Set<Room> _vecinas;
	
	public Room(String nombre,String desc) {
		_nombre = nombre;
		_descripcion = desc;		
		_jugador = new ArrayList<Jugador>();
		_vecinas  = new LinkedHashSet<Room>();
	}
	
	public void ponVecinas(Room...vecinas){
		for(Room v: vecinas)
			_vecinas.add(v);
	}
	
	public void ponVecina(Room vecina){
		_vecinas.add(vecina);
	}
	
	public Set<Room> dameVecinas(){
		return _vecinas;
	}

	public String getNombre(){
		return _nombre;
	}
	
	public String getDescripcion(){
		return _descripcion;
	}
	
	public void eliminaJugador(Jugador j){
		_jugador.remove(j);
	}
	
	public void addJugador(Jugador j){
		_jugador.add(j);
	}
	
	public boolean hayJugadores(){
		return _jugador.isEmpty();
	}
	
	public int numJugadores(){
		return _jugador.size();
	}
	
	/*public void mostrarJugadores(){
		for(int i=0;i<_jugador.size();i++){
			 System.out.println(" "+(i+1)+". "+_jugador.get(i).toString());
		}
	}*/
	
	public String mostrarJugadores(){
		String s = "";
		for(int i=0;i<_jugador.size();i++){
			 s = s+" "+(i+1)+". "+_jugador.get(i).toString()+"\n";
		}
		return s;
	}
	
	public ArrayList<Jugador> getJugadores(){
		return _jugador;
	}
	
	public void quitaJugadores(){
		int i = 0;
		while(!_jugador.isEmpty()){
			_jugador.remove(i);
		}
	}
}
