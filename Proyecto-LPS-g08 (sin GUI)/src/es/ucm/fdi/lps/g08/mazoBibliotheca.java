package es.ucm.fdi.lps.g08;

import java.util.*;
import es.ucm.fdi.lps.g08.Cartas.Carta;
import static es.ucm.fdi.lps.g08.Constantes.*;

public class mazoBibliotheca {
	
	private ArrayList<Carta> _mazo = new ArrayList<Carta>();
	
	public mazoBibliotheca(){
		_mazo = new ArrayList<Carta>();
	}
	
	public void iniciaMazo(){
		Carta cbiblio1 = new Carta(DEMONOLOGIA,textoDemonologia,false);
		Carta cbiblio2 = new Carta(DIARIOSECRETO,textoDiarioSecreto,false);
		Carta cbiblio3 = new Carta(ESPEJOMUNDO,textoEspejoMundo,false);
		Carta cbiblio4 = new Carta(EVANGELIOJUDAS,textoEvangelioJudas,false);
		Carta cbiblio5 = new Carta(HORUSAPLOO,textoHorusAploo,false);
		Carta cbiblio6 = new Carta(RISAARISTOTELES,textoRisaAristoteles,false);
		Carta cbiblio7 = new Carta(TRATADOMAGIA,textoTratadoMagia,false);
		Carta cbiblio8 = new Carta(TRATADOPLACERES,textoTratadoPlaceres,false);
		
		_mazo.add(cbiblio1);
		_mazo.add(cbiblio2);
		_mazo.add(cbiblio3);
		_mazo.add(cbiblio4);
		_mazo.add(cbiblio5);
		_mazo.add(cbiblio6);
		_mazo.add(cbiblio7);
		_mazo.add(cbiblio8);
	}
	
	public Carta getCarta(int i){
		return _mazo.get(i);
	}
	
	public ArrayList<Carta> getMazo(){
		return _mazo;
	}

}
