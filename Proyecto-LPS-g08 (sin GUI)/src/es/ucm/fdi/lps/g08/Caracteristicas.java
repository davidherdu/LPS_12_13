package es.ucm.fdi.lps.g08;

public class Caracteristicas {
	
	private String _nombre;
	private int _cantidad;
	
	public Caracteristicas(String _nombre, int _cantidad){
		this._nombre = _nombre;
		this._cantidad = _cantidad;
	}
	
	public void setNombre(String s){
		_nombre = s;
	}
	
	public void setCantidad(int i){
		_cantidad = i;
	}
	
	public String getNombre(){
		return _nombre;
	}
	
	public int getCantidad(){
		return _cantidad;
	}
	
	public void sumaCantidad(int i){
		_cantidad = _cantidad + i;
	}
	
	public String toString(){
		return " Rasgo "+getNombre()+", numero: "+getCantidad();
	}
}
