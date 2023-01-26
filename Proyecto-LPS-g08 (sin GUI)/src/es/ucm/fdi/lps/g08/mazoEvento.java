package es.ucm.fdi.lps.g08;

import java.util.*;
import es.ucm.fdi.lps.g08.Cartas.Carta;
import static es.ucm.fdi.lps.g08.Constantes.*;

public class mazoEvento {
	
	private ArrayList<Carta> _mazo = new ArrayList<Carta>();
	
	public mazoEvento(){
		_mazo = new ArrayList<Carta>();
	}
	
	public void iniciaMazo(){
		Carta cevento1 = new Carta(CAMPANASMATUTINAS,textoCampanasMatutinas,false);
		Carta cevento2 = new Carta(COARTADA,textoCoartada1,false);
		Carta cevento3 = new Carta(COARTADA,textoCoartada2,false);
		Carta cevento4 = new Carta(FERVORGREGORIANO,textoFervorGregoriano,false);
		Carta cevento5 = new Carta(INQUISIDOR,textoInquisidor,false);
		Carta cevento6 = new Carta(LABORESDIARIAS,textoLaboresDiarias,false);
		Carta cevento7 = new Carta(LIBROSPROHIBIDOS,textoLibrosProhibidos,false);
		Carta cevento8 = new Carta(LLAVEPERDIDA,textoLlavePerdida,false);
		Carta cevento9 = new Carta(PENITENCIA,textoPenitencia1,false);
		Carta cevento10 = new Carta(PENITENCIA,textoPenitencia2,false);
		Carta cevento11 = new Carta(PLEGARIASPRIVADAS,textoPlegariasPrivadas,false);
		Carta cevento12 = new Carta(PROCESION,textoProcesion,false);
		Carta cevento13 = new Carta(SERMON,textoSermon,false);
		Carta cevento14 = new Carta(SOSPECHA,textoSospecha,false);
		Carta cevento15 = new Carta(SOSPECHA,textoSospecha1,false);
		Carta cevento16 = new Carta(SOSPECHA,textoSospecha2,false);
		Carta cevento17 = new Carta(SOSPECHA,textoSospecha3,false);
		Carta cevento18 = new Carta(SOSPECHA,textoSospecha4,false);
		
		_mazo.add(cevento1);
		_mazo.add(cevento2);
		_mazo.add(cevento3);
		_mazo.add(cevento4);
		_mazo.add(cevento5);
		_mazo.add(cevento6);
		_mazo.add(cevento7);
		_mazo.add(cevento8);
		_mazo.add(cevento9);
		_mazo.add(cevento10);
		_mazo.add(cevento11);
		_mazo.add(cevento12);
		_mazo.add(cevento13);
		_mazo.add(cevento14);
		_mazo.add(cevento15);
		_mazo.add(cevento16);
		_mazo.add(cevento17);
		_mazo.add(cevento18);
		
	}
	
	public Carta getCarta(int i){
		return _mazo.get(i);
	}
	
	public ArrayList<Carta> getMazo(){
		return _mazo;
	}

}
