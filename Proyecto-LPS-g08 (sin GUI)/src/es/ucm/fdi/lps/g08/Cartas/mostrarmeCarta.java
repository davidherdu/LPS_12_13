package es.ucm.fdi.lps.g08.Cartas;

import java.util.ArrayList;
import es.ucm.fdi.lps.g08.*;

public class mostrarmeCarta {
	
	public mostrarmeCarta(Partida p, String propiedad,ArrayList<Jugador> aux){
		Jugador j = p.dameJugador();
		mazoSospechoso mazoAux = new mazoSospechoso();
		switch(propiedad){
			case "es un templario": for(int i=0;i<aux.size();i++){
										Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
										s = dameOrden(Orden.templario,aux.get(i));
										if(s.getOrden()==Orden.templario.toString())
											mazoAux.setSospechoso(s);
									}
									break;
			case "es un franciscano": for(int i=0;i<aux.size();i++){
										Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
										s = dameOrden(Orden.franciscano,aux.get(i));
										if(s.getOrden()==Orden.franciscano.toString())
											mazoAux.setSospechoso(s);
									}
									break;
			case "es un benedictino": for(int i=0;i<aux.size();i++){
										Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
										s = dameOrden(Orden.benedictino,aux.get(i));
										if(s.getOrden()==Orden.benedictino.toString())
											mazoAux.setSospechoso(s);
									}
									break;
			case "es un padre": for(int i=0;i<aux.size();i++){
									Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
									s = dameTitulo(Titulo.padre,aux.get(i));
									if(s.getTitulo()==Titulo.padre.toString())
										mazoAux.setSospechoso(s);
								}
								break;
			case "es un hermano": for(int i=0;i<aux.size();i++){
									Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
									s = dameTitulo(Titulo.hermano,aux.get(i));
									if(s.getTitulo()==Titulo.hermano.toString())
										mazoAux.setSospechoso(s);
								}
								break;
			case "es un novicio": for(int i=0;i<aux.size();i++){
									Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
									s = dameTitulo(Titulo.novicio,aux.get(i));
									if(s.getTitulo()==Titulo.novicio.toString())
										mazoAux.setSospechoso(s);
								}
								break;
			case "lleva capucha": for(int i=0;i<aux.size();i++){
									Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
									s = dameCapucha(true,aux.get(i));
									if(s.getCapucha())
										mazoAux.setSospechoso(s);
								}
								break;
			case "no lleva capucha": for(int i=0;i<aux.size();i++){
										Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
										s = dameCapucha(false,aux.get(i));
										if(!s.getCapucha())
											mazoAux.setSospechoso(s);
									}
									break;
			case "lleva barba": for(int i=0;i<aux.size();i++){
									Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
									s = dameBarba(Barba.barba,aux.get(i));
									if(s.getFacial()==Barba.barba.toString())
										mazoAux.setSospechoso(s);
								}
								break;
			case "no lleva barba": for(int i=0;i<aux.size();i++){
								Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
								s = dameBarba(Barba.afeitado,aux.get(i));
								if(s.getFacial()==Barba.afeitado.toString())
									mazoAux.setSospechoso(s);
							}
							break;
			case "es gordo": for(int i=0;i<aux.size();i++){
								Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
								s = dameComplexion(Complexion.gordo,aux.get(i));
								if(s.getComplexion()==Complexion.gordo.toString())
									mazoAux.setSospechoso(s);
							}
							break;
			default: for(int i=0;i<aux.size();i++){
						Sospechoso s = new Sospechoso(propiedad,null,null,false,null,null);
						s = dameComplexion(Complexion.delgado,aux.get(i));
						if(s.getComplexion()==Complexion.delgado.toString())
							mazoAux.setSospechoso(s);
					}
					break;
		}
		
		mazoAux.muestraCartas();
	    int	i = 0;
		while(i<mazoAux.longitud()){
			j.setEnLista(mazoAux.dameMazo().get(i));
		}
		
	}
	
	public Sospechoso dameOrden(Orden orden,Jugador j){
		int i = 0;
		switch(orden){
			case templario:
				while(i<j.getMazoSospechoso().longitud()){
					if(j.getMazoSospechoso().getSospechoso(i).getOrden()==Orden.templario.toString()){
						return j.getMazoSospechoso().getSospechoso(i);
					}
					else i++;
				}
				break;
			case benedictino:
				i = 0;
				while(i<j.getMazoSospechoso().longitud()){
					if(j.getMazoSospechoso().getSospechoso(i).getOrden()==Orden.benedictino.toString()){
						return j.getMazoSospechoso().getSospechoso(i);
					}
					else i++;
				}
				break;
			default: 
				i = 0;
				while(i<j.getMazoSospechoso().longitud()){
					if(j.getMazoSospechoso().getSospechoso(i).getOrden()==Orden.franciscano.toString()){
						return j.getMazoSospechoso().getSospechoso(i);
					}
					else i++;
				}
				break;
		}
		return null;
	}
	
	public Sospechoso dameTitulo(Titulo titulo,Jugador j){
		int i = 0;
		switch(titulo){
		case padre:
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getTitulo()==Titulo.padre.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
			break;
		case hermano:
			i = 0;
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getTitulo()==Titulo.hermano.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
			break;
		default:
			i = 0;
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getTitulo()==Titulo.novicio.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
			break;
		}
		return null;
	}
	
	public Sospechoso dameBarba(Barba barba,Jugador j){
		int i = 0;
		if(barba==Barba.barba){
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getFacial()==Barba.barba.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
		}
		else{
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getFacial()==Barba.afeitado.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
		}
		return null;
	}
	
	public Sospechoso dameCapucha(boolean capucha,Jugador j){
		int i = 0;
		if(capucha){
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getCapucha()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
		}
		else{
			while(i<j.getMazoSospechoso().longitud()){
				if(!j.getMazoSospechoso().getSospechoso(i).getCapucha()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
		}
		return null;
	}
	
	public Sospechoso dameComplexion(Complexion complexion,Jugador j){
		int i = 0;
		if(complexion==Complexion.gordo){
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getComplexion()==Complexion.gordo.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
		}
		else{
			while(i<j.getMazoSospechoso().longitud()){
				if(j.getMazoSospechoso().getSospechoso(i).getComplexion()==Complexion.delgado.toString()){
					return j.getMazoSospechoso().getSospechoso(i);
				}
				else i++;
			}
		}
		return null;
	}

}
