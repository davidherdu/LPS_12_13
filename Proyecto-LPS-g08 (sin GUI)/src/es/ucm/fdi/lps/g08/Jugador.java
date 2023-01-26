package es.ucm.fdi.lps.g08;

import java.io.Serializable;
import java.util.ArrayList;
import es.ucm.fdi.lps.g08.Cartas.Sospechoso;

public final class Jugador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color _color;
	private int _puntos;
	private mazoSospechoso _mazo;
	private ArrayList<Sospechoso> _sospechosos; //variable para la lista de los sospechosos 
												//que no son el culpable, los que voy tachando
	private boolean _turno;
	private Room _esta;
	private int _dado;
	private ArrayList<Caracteristicas> _caracteristicas;
	private boolean ganador;
	
	public Jugador(Color c){
		_color = c;
		_puntos = 0;
		_mazo = new mazoSospechoso();
		_sospechosos = new ArrayList<Sospechoso>();
		_turno = false;
		_esta = null;
		_dado = 0;
		_caracteristicas = new ArrayList<Caracteristicas>();
		ganador = false;
	}
	
	public Color getColor(){
		return _color;
	}
	
	public int getPuntos(){
		return _puntos;
	}
	
	public boolean getTurno(){
		return _turno;
	}
	
	public Room getEstancia(){
		return _esta;
	}
	
	public int getDado(){
		return _dado;
	}
	
	public void setTurno(boolean t){
		_turno = t;
	}
	
	public void setGanador(boolean g){
		ganador = g;
	}
	
	public boolean getGanador(){
		return ganador;
	}
	
	public mazoSospechoso getMazoSospechoso(){
		return _mazo;
	}
	
	public void puntos(int i){
		_puntos = _puntos + i;
	}
	
	/**
	 * Este metodo añade una carta de sospechoso al mazo de cartas de sospechoso del 
	 * jugador y tambien tacha un sospechoso de la lista de sospechosos
	 * @param s es el sospechoso
	 */
	public void setSospechoso(Sospechoso s){
		_mazo.setSospechoso(s);
		_sospechosos.add(s);
	}
	
	public void quitaCartaSospechoso(Sospechoso s){
		_mazo.elimina(s);
	}
	
	public void setEnLista(Sospechoso s){
		_sospechosos.add(s);
	}
	
	public void setColor(Color c){
		_color = c;
	}
	
	public void setEstancia(Room e){
		_esta = e;
	}
	
	public void setDado(int d){
		_dado = d;
	}
	
	public void listaSospechosos(){
		int i = 0;
		while(i<_mazo.longitud()){
			_sospechosos.add(_mazo.getSospechoso(i));
			i++;
		}
	}
	
	public String toString(){
		return _color.toString();
	}
	
	public void Sospechosos(){
		_mazo.muestraCartas();
	}
	
	public ArrayList<Sospechoso> mostrarSospechososTachados(){
		return _sospechosos;
	}
	
	public boolean estaCaracterstica(String s){
		if(_caracteristicas.isEmpty())
			return false;
		int i = 0;
		while((i<_caracteristicas.size())&&(!_caracteristicas.get(i).getNombre().equals(s))){
			i++;
		}
		if(i==_caracteristicas.size())
			return false;
		else return true;
	}
	
	public void ponerCaracteristica(String s, int i){
		_caracteristicas.add(new Caracteristicas(s,i));
	}
	
	public void sumaCaracteristica(String s,int num){
		if(!_caracteristicas.isEmpty()){
			int i = 0;
			while((i<_caracteristicas.size())&&(!_caracteristicas.get(i).getNombre().equals(s))){
				i++;
			}
			if(i<_caracteristicas.size()){
				_caracteristicas.get(i).sumaCantidad(num);
			}
		}
	}
	
	public boolean tieneCaracteristicas(){
		return _caracteristicas.isEmpty();
	}
	
	/*public void listarCaracteristicas(){
		Vista v = new Vista();
		v.normal("===== Lista de rasgos =====");
		for(Caracteristicas c: _caracteristicas){
			v.normal(c.toString());
		}
		v.normal("===========================\n");
	}*/
	
	public String listarCaracteristicas(){
		String s = "";
		s = s+"===== Lista de rasgos =====\n";
		for(Caracteristicas c: _caracteristicas){
			s = s+c.toString()+"\n";
		}
		s = s+"===========================\n";
		return s;
	}
}

