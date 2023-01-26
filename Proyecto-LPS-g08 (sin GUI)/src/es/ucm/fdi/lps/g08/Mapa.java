package es.ucm.fdi.lps.g08;

import java.util.*;
import es.ucm.fdi.lps.g08.Cartas.Sospechoso;
import es.ucm.fdi.lps.g08.servidor.*;

public class Mapa {
	
	private mazoSospechoso _sospechosos;
	private Vista _vista;
	private Set<Room> _estancias;
	private Room _inicial;

	public Mapa(){
		_sospechosos = new mazoSospechoso();
		_vista = new Vista();
		_estancias = new LinkedHashSet<Room>();
		createRooms();
	}
	
	public void ponInicial(Room r){
		_inicial = r;
	}
	
	public Room dameInicial(){
		return _inicial;
	}
	
	public void createRooms() {         
		// 0: Capilla
		Room capilla = new Room(Constantes.CAPILLA,"Lugar donde comienzan todos los jugadores");
		_estancias.add(capilla);		
		// 1: Claustro Norte
		Room claustro_norte = new Room(Constantes.CLAUSTRO_NORTE,"Estancia Vacia");
		_estancias.add(claustro_norte);		
		// 2: Claustro Este
		Room claustro_este = new Room(Constantes.CLAUSTRO_ESTE,"Estancia Vacia");
		_estancias.add(claustro_este);
		// 3: Claustro Sur
		Room claustro_sur = new Room(Constantes.CLAUSTRO_SUR,"Estancia Vacia");
		_estancias.add(claustro_sur);		
		// 4: Claustro Oeste 
		Room claustro_oeste = new Room(Constantes.CLAUSTRO_OESTE,"Estancia Vacia");
		_estancias.add(claustro_oeste);		
		// 5: Patio 
		Room patio = new Room(Constantes.PATIO,"Patio(aula)");
		_estancias.add(patio);		
		// 6: Confesionario Interior 
		Room confesionario_interior = new Room(Constantes.CONFESIONARIO_INTERIOR,"Lugar donde se revelan los pecados y los secretos(interior)");
		_estancias.add(confesionario_interior);		
		// 7: Cripta 
		Room cripta = new Room(Constantes.CRIPTA,"Lugar donde se reza frente a los sagrados restos de SantGalbert");
		_estancias.add(cripta);
		// 8: Confesionario Exterior
		Room confesionario_exterior = new Room(Constantes.CONFESIONARIO_EXTERIOR,"Lugar donde se revelan los pecados y los secretos(exterior)");
		_estancias.add(confesionario_exterior);	
		// 9: PasilloAzul
		Room pasillo_azul = new Room(Constantes.PASILLO_AZUL,"Pasillo adyacente a la celda del jugador azul");
		_estancias.add(pasillo_azul);	
		// 10: CeldaAzul
		Room celda_azul = new Room(Constantes.CELDA_AZUL,"Celda perteneciente al jugador azul");
		_estancias.add(celda_azul);	
		// 11: Scriptorium 
		Room scriptorium = new Room(Constantes.SCRIPTORIUM,"Lugar donde se coge una carta del tipo Scriptorium");
		_estancias.add(scriptorium);	
		// 12: Biblioteca 
		Room biblioteca = new Room(Constantes.BIBLIOTECA,"Lugar donde se coge una carta del tipo biblioteca.");
		_estancias.add(biblioteca);
		// 13: PasilloRojo 
		Room pasillo_rojo = new Room(Constantes.PASILLO_ROJO,"Pasillo adyacente a la celda del jugador rojo");
		_estancias.add(pasillo_rojo);				
		// 14: CeldaRoja
		Room celda_roja = new Room(Constantes.CELDA_ROJA,"Celda perteneciente al jugador rojo");
		_estancias.add(celda_roja);							
		// 15: PasilloNegro 
		Room pasillo_negro = new Room(Constantes.PASILLO_NEGRO,"Pasillo adyacente a la celda del jugador negro");
		_estancias.add(pasillo_negro);	
		// 16: CeldaNegra 
		Room celda_negra = new Room(Constantes.CELDA_NEGRA,"Celda perteneciente al jugador negro");
		_estancias.add(celda_negra);	
		// 17: SalaCapitular 
		Room sala_capitular = new Room(Constantes.SALA_CAPITULAR,"Lugar donde se puede realizar una Revelacion o Acusacion");
		_estancias.add(sala_capitular);				
		// 18: PasilloAmarillo
		Room pasillo_amarillo = new Room(Constantes.PASILLO_AMARILLO,"Pasillo adyacente a la celda del jugador amarillo");
		_estancias.add(pasillo_amarillo);					
		// 19: CeldaAmarilla 
		Room celda_amarilla = new Room(Constantes.CELDA_AMARILLA,"Celda perteneciente al jugador amarillo");
		_estancias.add(celda_amarilla);	
		// 20: PasilloVerde 
		Room pasillo_verde = new Room(Constantes.PASILLO_VERDE,"Pasillo adyacente a la celda del jugador verde");
		_estancias.add(pasillo_verde);					
		// 21: CeldaVerde 
		Room celda_verde = new Room(Constantes.CELDA_VERDE,"Celda perteneciente al jugador verde");
		_estancias.add(celda_verde);
		// 22: Locutorio
		Room locutorio = new Room(Constantes.LOCUTORIO,"Lugar donde llegan las noticias del mundo secular que rodea a la abadia");
		_estancias.add(locutorio);	
		// 23: PasilloBlanco 
		Room pasillo_blanco = new Room(Constantes.PASILLO_BLANCO,"Pasillo adyacente a la celda del jugador blanco");
		_estancias.add(pasillo_blanco);	
		// 24: CeldaBlanca 
		Room celda_blanca = new Room(Constantes.CELDA_BLANCA,"Celda perteneciente al jugador blanco");
		_estancias.add(celda_blanca);
		
		//Aqui pongo las vecinas de cada estancia
		capilla.ponVecinas(patio,confesionario_interior,cripta);
		claustro_norte.ponVecinas(claustro_este,claustro_oeste,patio);
		claustro_este.ponVecinas(claustro_norte,claustro_sur,pasillo_azul,scriptorium,pasillo_rojo);
		claustro_sur.ponVecinas(claustro_este,claustro_oeste,pasillo_negro,sala_capitular,pasillo_amarillo);
		claustro_oeste.ponVecinas(claustro_norte,claustro_sur,pasillo_verde,locutorio,pasillo_blanco);
		patio.ponVecinas(capilla,claustro_norte,confesionario_exterior);
		confesionario_interior.ponVecinas(capilla);
		cripta.ponVecinas(capilla);
		confesionario_exterior.ponVecinas(patio);
		pasillo_azul.ponVecinas(claustro_este,celda_azul);
		celda_azul.ponVecinas(pasillo_azul);
		scriptorium.ponVecinas(claustro_este,biblioteca);
		biblioteca.ponVecinas(scriptorium);
		pasillo_rojo.ponVecinas(claustro_este,celda_roja);
		celda_roja.ponVecinas(pasillo_rojo);
		pasillo_negro.ponVecinas(claustro_sur,celda_negra);
		celda_negra.ponVecinas(pasillo_negro);
		sala_capitular.ponVecinas(claustro_sur);
		pasillo_amarillo.ponVecinas(claustro_sur,celda_amarilla);
		celda_amarilla.ponVecinas(pasillo_amarillo);
		pasillo_verde.ponVecinas(claustro_oeste,celda_verde);
		celda_verde.ponVecinas(pasillo_verde);
		locutorio.ponVecinas(claustro_oeste);
		pasillo_blanco.ponVecinas(claustro_oeste,celda_blanca);
		celda_blanca.ponVecinas(pasillo_blanco);
		
		ponInicial(capilla);
	}
	
	/*public ArrayList<Room> damePosibles(Room e,int mov){
		_vista.normal(" Estas en la estancia "+e.getNombre());
		_vista.normal(" Puedes ir a una de las siguientes ");
		
		Set<Room> estancia = new LinkedHashSet<Room>();
		estancia.add(e);
		for(int j=1;j<=mov;j++){  
			Set<Room> estanciaAux = new LinkedHashSet<Room>();
			for (Room tmp: estancia)
				estanciaAux.addAll(tmp.dameVecinas()); 
			estancia.addAll(estanciaAux);
		}
		
		estancia.remove(e);	
		int i = 1;
		for(Room s: estancia){
			_vista.normal(" "+i+". "+s.getNombre());
			i++;
		}
		_vista.normal(" Elige el numero de la habitacion a la que quieres moverte\n");
		ArrayList<Room> aux = new ArrayList<Room>();
		for(Room r: estancia)
			aux.add(r);
		return aux;
	}*/
	
	public ArrayList<Room> damePosibles(Room e,int mov){
		/*_vista.normal(" Estas en la estancia "+e.getNombre());
		_vista.normal(" Puedes ir a una de las siguientes ");*/
		
		Set<Room> estancia = new LinkedHashSet<Room>();
		estancia.add(e);
		for(int j=1;j<=mov;j++){  
			Set<Room> estanciaAux = new LinkedHashSet<Room>();
			for (Room tmp: estancia)
				estanciaAux.addAll(tmp.dameVecinas()); 
			estancia.addAll(estanciaAux);
		}
		
		estancia.remove(e);	
		/*int i = 1;
		for(Room s: estancia){
			_vista.normal(" "+i+". "+s.getNombre());
			i++;
		}
		_vista.normal(" Elige el numero de la habitacion a la que quieres moverte\n");*/
		ArrayList<Room> aux = new ArrayList<Room>();
		for(Room r: estancia)
			aux.add(r);
		return aux;
	}
	
	public void setSospechoso(Sospechoso s){
		_sospechosos.setSospechoso(s);
	}
	
	public void muestraSospechosos(){
		_sospechosos.muestraCartas();
	}
	
	public mazoSospechoso getMazoSospechoso(){
		return _sospechosos;
	}
	
	public Room dameEstanciaPorNombre(String s){
		Iterator<Room> iter = _estancias.iterator();
		Room r = null;
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room aux = (Room)e;
		  if(aux.getNombre().equals(s))
			  r = aux;
		}
		return r;
	}

	public void eliminaJugador(Room i, Jugador j){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			  s.eliminaJugador(j);
		}
	}
	
	public void addJugador(Room i,Jugador j){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			  s.addJugador(j);
		}
	}

	/*public void mostrarJugadores(Room i){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			  s.mostrarJugadores();
		}
	}*/
	
	public String mostrarJugadores(Room i){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			  return s.mostrarJugadores();
		}
		return "";
	}

	public boolean hayJugadores(Room i){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			  return s.hayJugadores();
		  else return false;
		}
		return false;
	}
	
	public int numJugadores(Room i){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			 return s.numJugadores();
		}
		return 0;
	}
	
	public Jugador dameJugador(Room i,int j){
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  if(s.equals(i))
			 return s.getJugadores().get(j);
		}
		return null;
	}
	
	public ArrayList<Room> mostrarEstancias(){
		ArrayList<Room> aux = new ArrayList<Room>();
		Iterator<Room> iter = _estancias.iterator();
		int i = 1;
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  aux.add(s);
		  _vista.normal(i+". "+s.getNombre());
		  i++;
		}
		return aux;
	}
	
	public Room dameHabitacion(int i){
		ArrayList<Room> aux = new ArrayList<Room>();
		Iterator<Room> iter = _estancias.iterator();
		while(iter.hasNext()){
		  Object e = iter.next(); 
		  Room s = (Room)e;
		  aux.add(s);
		}
		return aux.get(i);
	}
}

