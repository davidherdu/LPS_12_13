package es.ucm.fdi.lps.g08;

import java.util.ArrayList;
//import java.util.*;
import es.ucm.fdi.lps.g08.Cartas.Carta;
import static es.ucm.fdi.lps.g08.Constantes.*;

public class mazoMisa {
	
	
	private ArrayList<Carta> _mazo = new ArrayList<Carta>();
	
	public mazoMisa(){
		_mazo = new ArrayList<Carta>();
	}
	
	public void iniciaMazo(){
		Carta cmisa1 = new Carta(MAITINES,textoMaitines,false);
		Carta cmisa2 = new Carta(LAUDES,textoLaudes,false);
		Carta cmisa3 = new Carta(PRIMA,textoPrima,false);
		Carta cmisa4 = new Carta(TERCIA,textoTercia,false);
		Carta cmisa5 = new Carta(SEXTA,textoSexta,false);
		Carta cmisa6 = new Carta(NONA,textoNona,false);
		Carta cmisa7 = new Carta(VISPERAS,textoVisperas,false);
		Carta cmisa8 = new Carta(COMPLETAS,textoCompletas,false);
		
		_mazo.add(cmisa1);
		_mazo.add(cmisa2);
		_mazo.add(cmisa3);
		_mazo.add(cmisa4);
		_mazo.add(cmisa5);
		_mazo.add(cmisa6);
		_mazo.add(cmisa7);
		_mazo.add(cmisa8);
	}
	
	public Carta getCarta(int i){
		return _mazo.get(i);
	}
	
	public ArrayList<Carta> getMazo(){
		return _mazo;
	}

}
