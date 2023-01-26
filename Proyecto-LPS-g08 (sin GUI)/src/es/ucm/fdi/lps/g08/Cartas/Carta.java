package es.ucm.fdi.lps.g08.Cartas;

public class Carta {
	
	private String _nombre;
	private String _descripcion;
	private boolean _seguarda;
	
	public Carta(String _nombre,String _descripcion,boolean _seguarda){
		this._nombre = _nombre;
		this._descripcion = _descripcion;
		this._seguarda = _seguarda;
	}

	public String getNombre(){
		return _nombre;
	}
	
	public String getDescripcion(){
		return _descripcion;
	}
	
	public boolean getSeguarda(){
		return _seguarda;
	}

}