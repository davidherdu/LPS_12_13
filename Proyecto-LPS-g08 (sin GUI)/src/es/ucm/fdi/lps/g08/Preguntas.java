package es.ucm.fdi.lps.g08;

import es.ucm.fdi.lps.g08.Cartas.Sospechoso;

public class Preguntas {
	
	private Vista _vista;
	private mazoSospechoso _monjes;
	private TipoRasgo tr;
	private TipoUbicacion tu;
	
	
	public Preguntas(){
		_vista = new Vista();
		_monjes = new mazoSospechoso();
		_monjes.mazoInicial();
	}
	
	/*public String pregunta1(){
		String s = elegirRasgo();
		_vista.normal(" ¿Cuantos monjes "+s+" tienes en "+elegirUbicacion()+"? ");
		return s;
	}*/
	
	public String pregunta1(){
		String s = elegirRasgo();
		String p = " ¿Cuantos monjes "+s+" tienes en "+elegirUbicacion()+"? ";
		return p;
	}
	
	public String pregunta2(Sospechoso s){
		return " ¿Tienes a "+s.getNombre()+" en "+elegirUbicacion()+"?";
	}
	
	/*public String elegirRasgo(){
		_vista.listaRasgos();
		int n = _vista.limites(1,5);
		int i;
		switch(n){
		case 1: System.out.println(" Elige un titulo");
				System.out.println(" 1. Padre");
				System.out.println(" 2. Hermano");
				System.out.println(" 3. Novicio");
				i = _vista.limites(1,3);
				switch(i){
					case 1: return "Padres";
					case 2: return "Hermanos";
					default: return "Novicios";
				}
		case 2: System.out.println(" Elige una orden");
				System.out.println(" 1. Templario");
				System.out.println(" 2. Franciscano");
				System.out.println(" 3. Benedictino");
				i = _vista.limites(1,3);
				switch(i){
					case 1: return "Templarios";
					case 2: return "Franciscanos";
					default: return "Benedictinos";
				}
		case 3: System.out.println(" Elige con o sin capucha");
				System.out.println(" 1. Si");
				System.out.println(" 2. No");
				i = _vista.limites(1,2);
				if(i==1) return "Con capucha";
				else return "Sin capucha";
		case 4: System.out.println(" Elige si tiene vello facial o no");
				System.out.println(" 1. Si");
				System.out.println(" 2. No");
				i = _vista.limites(1,2);
				if(i==1) return "Con barba";
				else return "Sin barba";
		default: System.out.println(" Elige la complexion");
				 System.out.println(" 1. Gordo");
				 System.out.println(" 2. Delgado");
				 i = _vista.limites(1,2);
				 if(i==1) return "Gordos";
				 else return "Delgados";
		}
	}
	
	public String elegirUbicacion(){
		_vista.ubicacion();
		int n = _vista.limites(1,2);
		if(n==1) return "las cartas de sospechoso";
		else return "la lista de sospechosos descartados";
	}*/
	
	public String elegirRasgo(){
		String s = _vista.listaRasgos();
		System.out.println(s);
		int n = _vista.limites(1,5);
		int i;
		switch(n){
		case 1: System.out.println(" Elige un titulo");
				System.out.println(" 1. Padre");
				System.out.println(" 2. Hermano");
				System.out.println(" 3. Novicio");
				i = _vista.limites(1,3);
				switch(i){
					case 1: tr = new TipoRasgo(Titulo.padre);
							return "Padres";
					case 2: tr = new TipoRasgo(Titulo.hermano);
							return "Hermanos";
					default:tr = new TipoRasgo(Titulo.novicio); 
							return "Novicios";
				}
		case 2: System.out.println(" Elige una orden");
				System.out.println(" 1. Templario");
				System.out.println(" 2. Franciscano");
				System.out.println(" 3. Benedictino");
				i = _vista.limites(1,3);
				switch(i){
					case 1: tr = new TipoRasgo(Orden.templario);
							return "Templarios";
					case 2: tr = new TipoRasgo(Orden.franciscano);
							return "Franciscanos";
					default:tr = new TipoRasgo(Orden.benedictino); 
							return "Benedictinos";
				}
		case 3: System.out.println(" Elige con o sin capucha");
				System.out.println(" 1. Si");
				System.out.println(" 2. No");
				i = _vista.limites(1,2);
				if(i==1){
					tr = new TipoRasgo(Capucha.con_capucha);
					return "Con capucha";
				}
				else{
					tr = new TipoRasgo(Capucha.sin_capucha);
					return "Sin capucha";
				}
		case 4: System.out.println(" Elige si tiene vello facial o no");
				System.out.println(" 1. Si");
				System.out.println(" 2. No");
				i = _vista.limites(1,2);
				if(i==1){
					tr = new TipoRasgo(Barba.barba);
					return "Con barba";
				}
				else{
					tr = new TipoRasgo(Barba.afeitado);
					return "Sin barba";
				}
		default: System.out.println(" Elige la complexion");
				 System.out.println(" 1. Gordo");
				 System.out.println(" 2. Delgado");
				 i = _vista.limites(1,2);
				 if(i==1){
					 tr = new TipoRasgo(Complexion.gordo);
					 return "Gordos";
				 }
				 else{
					 tr = new TipoRasgo(Complexion.delgado);
					 return "Delgados";
				 }
		}
	}
	
	public TipoRasgo getTipoRasgo(){
		return tr;
	}
	
	public TipoUbicacion getTipoUbicacion(){
		return tu;
	}
	
	public String elegirUbicacion(){
		System.out.println(_vista.ubicacion());
		int n = _vista.limites(1,2);
		if(n==1){
			tu = TipoUbicacion.cartas_de_sospechoso;
			return "las cartas de sospechoso";
		}
		else {
			tu = TipoUbicacion.monjes_tachados;
			return "la lista de sospechosos descartados";
		}
	}
	
	/*public String getRasgo(){
		
	}*/

}
