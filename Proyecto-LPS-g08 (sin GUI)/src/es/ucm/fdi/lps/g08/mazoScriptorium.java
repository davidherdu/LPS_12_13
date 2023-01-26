package es.ucm.fdi.lps.g08;

import java.util.ArrayList;
import es.ucm.fdi.lps.g08.Cartas.Carta;
import static es.ucm.fdi.lps.g08.Constantes.*;

public class mazoScriptorium {
	
	private ArrayList<Carta> _mazo = new ArrayList<Carta>();
	
	public mazoScriptorium(){
		_mazo = new ArrayList<Carta>();
	}
	
	public void iniciaMazo(){
		Carta apoj = new Carta(APOCALIPSISJUAN,textoApocalipsisJuan,true);
		Carta brev = new Carta(BREVIARIOILUMINADO,textoBreviarioIluminado,true);
		Carta cant = new Carta(CANTARCANTARES,textoCantarCantares,false);
		Carta cart = new Carta(CARTULARIOABADIA,textoCartularioAbadia,true);
		Carta ciu = new Carta(CIUDADDIOS,textoCiudadDios,true);
		Carta crom = new Carta(CRONICAMONASTERIO,textoCronicaMonasterio,false);
		Carta cros = new Carta(CRONICASANTEODULO,textoCronicaSanTeodulo,false);
		Carta dea = new Carta(DEALQUIMIA,textoDeAlquimia,true);
		Carta liba = new Carta(LIBROALTAMAGIA,textoLibroAltaMagia,false);
		Carta libh =new Carta(LIBROHORAS,textoLibroHoras,true);
		Carta log = new Carta(LOGICAARISTOTELICA,textoLogicaAristotelica,true);
		Carta man = new Carta(MANUALINQUISIDOR,textoManualInquisidor,true);
		Carta map = new Carta(MAPAABADIA,textoMapaAbadia,true);
		Carta pro = new Carta(PROFECIASPRESAGIOS,textoProfeciasPresagios,false);
		Carta regb = new Carta(REGLABENEDICTINA,textoReglaBenedictina,true);
		Carta regf = new Carta(REGLAFRANCISCANA,textoReglaFranciscana,true);
		Carta regt = new Carta(REGLATEMPLARIA,textoReglaTemplaria,false);
		Carta salm = new Carta(SALMOS,textoSalmos,true);
		Carta summ = new Carta(SUMMATHEOLOGICA,textoSummaTheologica,true);
		Carta textc = new Carta(TEXTOCRIPTOGRAFICO,textoTextoCriptografico,false);
		Carta trata= new Carta(TRATADOASTROLOGICOARABE,textoTratadoAstrologicoArabe,false);
		Carta tratn = new Carta(TRATADONEOPLATONICO,textoTratadoNeoplatonico,false);
		Carta trab = new Carta(TRATADOPATRISTICABIZANTINO,textoTratadoPatristicaBizantino,true);
		Carta trar = new Carta(TRATADORETORICA,textoTratadoRetorica,true);
		
		_mazo.add(apoj);
		_mazo.add(brev);
		_mazo.add(cant);
		_mazo.add(cart);
		_mazo.add(ciu);
		_mazo.add(crom);
		_mazo.add(cros);
		_mazo.add(dea);
		_mazo.add(liba);
		_mazo.add(libh);
		_mazo.add(log);
		_mazo.add(man);
		_mazo.add(map);
		_mazo.add(pro);
		_mazo.add(regb);
		_mazo.add(regf);
		_mazo.add(regt);
		_mazo.add(salm);
		_mazo.add(summ);
		_mazo.add(textc);
		_mazo.add(trata);
		_mazo.add(tratn);
		_mazo.add(trab);
		_mazo.add(trar);	
	}
	
	public Carta getCarta(int i){
		return _mazo.get(i);
	}
	
	public ArrayList<Carta> getMazo(){
		return _mazo;
	}
	

}
