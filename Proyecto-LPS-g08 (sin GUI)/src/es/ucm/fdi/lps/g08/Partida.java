package es.ucm.fdi.lps.g08;

import java.util.*;
import es.ucm.fdi.lps.g08.Cartas.Carta;
import es.ucm.fdi.lps.g08.Cartas.Sospechoso;
import es.ucm.fdi.lps.g08.EventosEstancias.*;

public class Partida {
	
	private ArrayList<Jugador> _jugadores;
	private mazoSospechoso _sospechosos;
	private mazoSospechoso _monjes;
	private mazoBibliotheca _cartasBibliotheca;
	private mazoScriptorium _cartasScriptorium;
	private mazoEvento _cartasEvento;
	private mazoCripta _cartasCripta;
	private Stack<Sospechoso> _mazoTablero;
	private Stack<Carta> _mazoBibliotheca;
	private Stack<Carta> _mazoEvento;
	private Stack<Carta> _mazoScriptorium;
	private Stack<Carta> _mazoCripta;
	private Mapa _mapa;
	private Jugador _primero;
	private Sospechoso _asesino;
	private ArrayList<Color> _colores;
	private Vista _vista;
	private Preguntas _pregunta;
	private Color _dadoConfesionario[];
	private Hashtable<String,Jugador> _revelacion;
	
	/**
	 * Constructora de la clase partida
	 * @param num es el numero de jugadores que hay
	 */
	public Partida(int num){//num es el numero de jugadores que le pasamos al inicio
		_mapa = new Mapa();
		_jugadores = new ArrayList<Jugador>();
		for(int i=0;i<num;i++){
			_jugadores.add(new Jugador(Color.values()[i]));
			_jugadores.get(i).setEstancia(_mapa.dameInicial());
		}
		
		_primero = null;
		_sospechosos = new mazoSospechoso();
		_sospechosos.mazoInicial();
		_monjes = new mazoSospechoso();
		_monjes.mazoInicial();
		_mazoTablero = new Stack<Sospechoso>();
		_mazoBibliotheca = new Stack<Carta>();
		_cartasBibliotheca = new mazoBibliotheca();
		_mazoEvento = new Stack<Carta>();
		_cartasEvento = new mazoEvento();
		_cartasScriptorium = new mazoScriptorium();
		_mazoScriptorium = new Stack<Carta>();
		_cartasCripta = new mazoCripta();
		_mazoCripta = new Stack<Carta>();
		_asesino = new Sospechoso(" ",null,null,false,null,null);
		_colores = new ArrayList<Color>();
		_vista = new Vista();
		_pregunta = new Preguntas();
		_dadoConfesionario = new Color[2];
		_revelacion = new Hashtable<String,Jugador>();
		
		generarMazos();
	}
	
	public ArrayList<Jugador> dameJugadores(){
		return _jugadores;
	}
	
	public Mapa dameMapa(){
		return _mapa;
	}
	
	/**
	 * Metodo para generar el asesino
	 * @return devuelve el asesino
	 */
	public Sospechoso generaAsesino(){
		int tam = _sospechosos.longitud();
		Random rand = new Random();
		int posAleatoria = rand.nextInt(tam);
		Sospechoso s = _sospechosos.getSospechoso(posAleatoria);
		_sospechosos.elimina(s);
		_asesino = s;
		return _asesino;
	}
	
	/**
	 * Metodo que devuelve el asesino
	 * @return
	 */
	public Sospechoso dameAsesino(){
		return _asesino;
	}
	
	/**
	 * Metodo que muestra el asesino
	 */
	public void muestraAsesino(){
		_vista.normal(" El asesino es: "+dameAsesino().getNombre());
	}
	/** 
	*Genera el mazo de cartas Biblioteca
	*/
	public void generaMazoBibliotheca(){
		_cartasBibliotheca.iniciaMazo();
		while(!_cartasBibliotheca.getMazo().isEmpty()){
			int tam = _cartasBibliotheca.getMazo().size();
			Random rand = new Random();
			int posAleatoria = rand.nextInt(tam);
			Carta ob = _cartasBibliotheca.getCarta(posAleatoria);
			_mazoBibliotheca.push(ob);
			_cartasBibliotheca.getMazo().remove(ob);
		}
	}
	/** 
	*Genera el mazo de cartas Evento
	*/
	
	public void generaMazoEvento(){
		_cartasEvento.iniciaMazo();
		while(!_cartasEvento.getMazo().isEmpty()){
			int tam = _cartasEvento.getMazo().size();
			Random rand = new Random();
			int posAleatoria = rand.nextInt(tam);
			Carta ob = _cartasEvento.getCarta(posAleatoria);
			_mazoEvento.push(ob);
			_cartasEvento.getMazo().remove(ob);
		}
	}
	
	public void generaMazoScriptorium(){
		_cartasScriptorium.iniciaMazo();
		while(!_cartasScriptorium.getMazo().isEmpty()){
			int tam = _cartasScriptorium.getMazo().size();
			Random rand = new Random();
			int posAleatoria = rand.nextInt(tam);
			Carta ob = _cartasScriptorium.getCarta(posAleatoria);
			_mazoScriptorium.push(ob);
			_cartasScriptorium.getMazo().remove(ob);
		}
	}
	
	public void generaMazoCripta(){
		_cartasCripta.iniciaMazo();
		while(!_cartasCripta.getMazo().isEmpty()){
			int tam = _cartasCripta.getMazo().size();
			Random rand = new Random();
			int posAleatoria = rand.nextInt(tam);
			Carta ob = _cartasCripta.getCarta(posAleatoria);
			_mazoCripta.push(ob);
			_cartasCripta.getMazo().remove(ob);
		}
	}
	
	public void generarMazos(){
		generaMazoBibliotheca();
		generaMazoEvento();
		generaMazoScriptorium();
		generaMazoCripta();
	}
	
	/**
	 * Metodo que pone cartas en el tablero dependiendo del numero 
	 * de jugadores
	 */
	public void repartoEsquina(){
		int cartas;
		int num = _jugadores.size();
		switch(num){
		case 3: cartas = 5;
				break;
		case 4: cartas = 3;
				break;
		case 5: cartas = 3;
				break;
		default: cartas = 5;
				 break;
		}

		int i = 0;
		while(i<cartas){
			Random rand = new Random();
			int posAleatoria = rand.nextInt(_sospechosos.longitud());
			Sospechoso s = _sospechosos.getSospechoso(posAleatoria);
			_mazoTablero.push(s);
			_sospechosos.elimina(s);
			i++;
		}
	}
	
	/**
	 * Metodo que muestra las cartas de sospechoso que hay 
	 * en el tablero
	 */
	public void muestraSospechososTablero(){
		_vista.normal(" Cartas de sospechoso del tablero ");
		_mapa.muestraSospechosos();
		for(int i = 0;i<_mazoTablero.size();i++){
			_vista.normal(" <---------------- Carta "+(i+1)+" ---------------->\n");
			_vista.mostrarSospechoso(_mazoTablero.get(i));
		}
	}
	
	/**
	 * Metodo que reparto a cada jugador un numero de cartas de 
	 * sospechoso alazar dependiendo del numero de jugadores
	 */
	public void repartoNumSospechosos(){
		int i = _jugadores.size();
		int num;
		switch(i){
		case 3: num = 6;
				break;
		case 4: num = 5;
				break;
		case 5: num = 4;
				break;
		default: num = 3;
				 break;
		}
		for(int j=0;j<_jugadores.size();j++)
			repartoSospechosos(num,_jugadores.get(j));
	}
	
	/**
	 * Metodo que hace la logica del reparto de sospechoso
	 * @param num es el numero de cartas que tiene que repartir
	 * @param j es eljugador al que le reparte
	 */
	public void repartoSospechosos(int num, Jugador j){
		j.getMazoSospechoso().iniciaMazoJuego(num);
		int i = 0;
		while(i<num){
			Random rand = new Random();
			int posAleatoria = rand.nextInt(_sospechosos.longitud());
			Sospechoso s = _sospechosos.getSospechoso(posAleatoria);
			if((j.getMazoSospechoso().longitud()>0)&&(!j.getMazoSospechoso().esta(s))){
				j.getMazoSospechoso().modificaSospechoso(i,s);
				_sospechosos.elimina(s);
				i++;
			}
		}	
	}
	
	/**
	 * Metodo que tacha los monjes que tiene cada jugador de su mazo de sospechosos 
	 */
	public void tacharMonjes(){
		for(int i=0;i<_jugadores.size();i++)
			tacharSospechososPrincipio(i);
	}

	/**
	 * Metodo que muestra las opciones que puede hacer cada jugador en su turno
	 * @param turno es la posicion para saber a que jugador le toca
	 */
	public void menuOpciones(int turno,int mov){
		Jugador j = _jugadores.get(turno);
		int n = 0;
		boolean pasar = false;
		while(!pasar){
			_vista.Menu();
			n = _vista.limites(1,7);
			switch(n){
			case 1: System.out.println();
					j.getMazoSospechoso().muestraCartas();
					break;
			case 2: if(!j.tieneCaracteristicas())
						j.listarCaracteristicas();
					else _vista.normal(" No tienes aun ninguna caracteristica\n ");
					break;
			case 3: System.out.println();
					mostrarMonjesTachados(turno);
					break; 
			case 4: if(_revelacion.isEmpty())
				 		_vista.normal(" No se ha realizado aun ninguna revelacion\n ");
					else
						mostrarRevelaciones();
					break;
			case 5: mostrarMonjes();
					break;
			case 6: faseMovimiento(turno,mov);
					pasar = true;
					break;
			default: mostrarUltimosConfesarse();
					 break;
			}
		}
	}
	
	/**
	 * Metodo que muestra las cartas de sospechoso de cada jugador
	 */
	public void muestraCartasJugadores(){
		for(int i=0;i<_jugadores.size();i++){
			_vista.normal(" +++++ Cartas de sospechoso del jugador "+_jugadores.get(i).getColor().toString()+" +++++\n");
			System.out.println();
			_jugadores.get(i).Sospechosos();
		}
	}
	
	/**
	 * Metodo que determina llama a eligeColor() para que cada jugador elija un color
	 * y que determina quien empieza la partida depiendo de lo que haya sacado
	 * cada jugador en la tirada de dados
	 */
	public void Empieza(){
		eligeColor();
		int i = 0;
		while(i<_jugadores.size()){
			Random rand = new Random();
			int puntos = rand.nextInt(6)+1;
			_jugadores.get(i).setDado(puntos);
			i++;
		}
		
		int j = 0;
		int max = _jugadores.get(j).getDado();
		i = 0;
		while(i<_jugadores.size()){
			if(_jugadores.get(i).getDado()>max){
				max = _jugadores.get(i).getDado();
				j = i;
			}
			i++;
		}
		_primero = _jugadores.get(j);
		System.out.println();
		for(i=0;i<_jugadores.size();i++){
			_vista.normal(" El jugador "+_jugadores.get(i).getColor().toString()+" ha sacado "+_jugadores.get(i).getDado()+"\n");
		}
	}
	
	/**
	 * Metodo que hace la logica de la eleccion de color de cada jugador
	 */
	public void eligeColor(){
		int col = 0;
		while(col<Color.values().length){
			_colores.add(Color.values()[col]);
			col++;
		}
		
		for(int i=0;i<_jugadores.size();i++){
			_vista.normal(" Elige uno de los siguientes colores \n");
			for(int j=0;j<_colores.size();j++){
				System.out.println(" "+(j+1)+". "+_colores.get(j));
			}
			int n = _vista.limites(1,_colores.size());
			_jugadores.get(i).setColor(_colores.get(n-1));
			_colores.remove(_colores.get(n-1));
		}
	}
	
	public void monjesConfesionario(){
		int i = 0;
		Color c,cAux;
		cAux = null;
		while(i<2){
			Random rand = new Random();
			int posAleatoria = rand.nextInt(_jugadores.size());
			Jugador j = _jugadores.get(posAleatoria);
			c = j.getColor();
			
			if(cAux != c){
				_dadoConfesionario[i] = c;
				i++;
			}
			cAux = c;
		}	
	}
	
	public void mostrarUltimosConfesarse(){
		_vista.normal(" En el confesionario interior se confeso el jugador "+_dadoConfesionario[0].toString());
		_vista.normal(" En el confesionario exterior se confeso el jugador "+_dadoConfesionario[1].toString()+"\n");
	}
	
	/**
	 * Metodo que devuelve quien es el primer jugador
	 * @return 
	 */
	public Jugador damePrimero(){
		return _primero;
	}
	
	/**
	 * Metodo que cambia el orden del primer jugador ya que al cambiar la misa el
	 * primer jugador pasa a ser el que esta a las izquierda del actual
	 */
	public void cambiaPrimero(){
		int i = _primero.getColor().ordinal();
		if(i==_jugadores.size()-1) 
			_primero = _jugadores.get(0);
		else _primero = _jugadores.get(i+1);
	}
	
	public void ponersePrimero(Jugador j) {
		_primero = j;
	}
	
	/**
	 * Metodo que devuelve el numero de jugadores que hay
	 * @return
	 */
	public int numJugadores(){
		return _jugadores.size();
	}
	
	/**
	 * Metodo que devuelve que jugador tiene el turno
	 * @return devuelveun entero que es el identificador del primer jugador en el
	 * ArrayLista de _jugadores
	 */
	public int dameTurno(){
		return _jugadores.indexOf(_primero);
	}
	
	public Jugador dameJugador(){
		return _jugadores.get(dameTurno());
	}
	
	/**
	 * Metodo que devuelve el color del jugador i-esimo
	 * @param i es la posicion del jugador que hay en el ArryList de _jugadores
	 * @return el color del jugador
	 */
	public String dameColor(int i){
		return _jugadores.get(i).getColor().toString();
	}
	
	/**
	 * Metodo que tacha los monjes de un jugador 
	 * @param i es el jugador que los tacha
	 */
	public void tacharSospechososPrincipio(int i){
		Jugador j = _jugadores.get(i);
		j.listaSospechosos();
	}
	
	/**
	 * Metodo que tacha un monje si descubre un sospechoso
	 * @param i es el jugador que lo tacha
	 * @param s es el sospechoso que tacha
	 */
	public void tacharSospechoso(int i, Sospechoso s){
		Jugador j = _jugadores.get(i);
		_vista.normal(" Elige un monje para tachar\n");
		mostrarNombreMonjes();
		int n = _vista.limites(1, _monjes.longitud());
		j.setEnLista(_monjes.getSospechoso(n-1));	
	}
	
	/**
	 * Metodo que muestra los monjes tachados de un jugador
	 * @param i
	 */
	public void mostrarMonjesTachados(int i){
		Jugador j = _jugadores.get(i);
		for(int k=0; k<j.mostrarSospechososTachados().size(); k++){
			System.out.println(" <---------------- Carta "+(k+1)+" ---------------->");
			_vista.mostrarSospechoso(j.mostrarSospechososTachados().get(k));
		}
		_vista.normal(" ===========================================\n");
	}
	
	/**
	 * Metodo que muestra todas las cartas de monjes que hay
	 */
	public void mostrarMonjes(){
		_vista.normal(" === Lista de monjes ===\n");
		_monjes.muestraCartas();
		_vista.normal(" =======================\n");
	}
	
	/**
	 * Metodo que hace la logica de mover un jugador de una estancia a otra
	 * @param estoy es la estancia en la que estoy
	 * @param proxima la estancia a la que me muevo
	 * @param j es el jugador que realiza el movimiento
	 */
	public void movimiento(Room estoy, Room proxima, Jugador j){
		j.setEstancia(proxima);
		_mapa.eliminaJugador(estoy,j);
		_mapa.addJugador(proxima,j);
	}
	
	/**
	 * Metodo que realiza toda la fase de movimiento
	 * @param turno es el jugador al que le toca mover 
	 */
	public void faseMovimiento(int turno,int movimiento){
		Jugador j = _jugadores.get(turno);
		Room prim = j.getEstancia();
		Room tmp = null;
		ArrayList<Room> aux = _mapa.damePosibles(prim,movimiento);
		
		Room confInt = _mapa.dameEstanciaPorNombre(Constantes.CONFESIONARIO_INTERIOR);
		Room confExt = _mapa.dameEstanciaPorNombre(Constantes.CONFESIONARIO_EXTERIOR);
		
		int mov = _vista.limites(1,aux.size());
		tmp = aux.get(mov-1);
		
		if(((tmp==confInt)||(tmp==confExt))&&(tmp.numJugadores()>=1)){
			while(((tmp==confInt)||(tmp==confExt))&&(tmp.numJugadores()>=1)){
				_vista.normal(" No puedes ir a ese confesionario porque hay ya una ficha\n");
				mov = _vista.limites(1,aux.size());
				tmp = aux.get(mov-1);
			}
		}
		
		movimiento(prim,tmp,j);
		
		_vista.normal(" Al final de tu turno estas en la estancia "+j.getEstancia().getNombre()+"\n");
		
		if(_mapa.numJugadores(j.getEstancia())>1){
			fasePregunta(j,j.getEstancia());
		}
		else _vista.normal(" En esa estancia no hay jugadores \n");
		
		/*if(j.getEstancia().getNombre().equals(Constantes.CONFESIONARIO_INTERIOR)){
			Confesionario c = new Confesionario(j,_dadoConfesionario[0],this);
			_dadoConfesionario[0] = j.getColor();
		}
		
		if(j.getEstancia().getNombre().equals(Constantes.CONFESIONARIO_EXTERIOR)){
			Confesionario c = new Confesionario(j,_dadoConfesionario[1],this);
			_dadoConfesionario[1] = j.getColor();
		}*/
		
		if(j.getEstancia().equals(Constantes.SALA_CAPITULAR)){
			_vista.normal(" ¿Desea hacer una revelacion? ");
			String pregunta = _vista.preguntaAfirmacion();
			if((pregunta.equals("Si"))||(pregunta.equals("si"))||(pregunta.equals("SI"))){
				revelacion(j);
			}
			
			_vista.normal(" ¿Desea hacer una acusacion? ");
			pregunta = _vista.preguntaAfirmacion();
			if((pregunta.equals("Si"))||(pregunta.equals("si"))||(pregunta.equals("SI"))){
				Sospechoso monje = acusacion(j);
				_vista.normal( " El jugador "+j.getColor().toString()+" ha dicho que el asesino es "+monje.getNombre());
			}
		}
	}
	
	/**
	 * Metodo que realiza la revelacion
	 * @param j es el jugador que la realiza
	 */
	public void revelacion(Jugador j){
		String s = _vista.eligePropiedad();
		while(_revelacion.containsKey(s)){
			_vista.normal(" No puede hacer una revelacion que ya este hecha\n");
			s = _vista.eligePropiedad();
		}
		_revelacion.put(s,j);
	}
	
	public void mostrarRevelaciones(){
		_vista.normal(" Lista de revelaciones realizadas\n");	
		String clave;
		Jugador valor;
		Enumeration<String> e = _revelacion.keys();
		while(e.hasMoreElements()){
	      clave = (String)e.nextElement();
	      valor = (Jugador)_revelacion.get(clave);
	      _vista.normal(" El jugador "+valor.getColor().toString()+" dice que el culpable "+clave);
		}	
		_vista.normal(" ================================\n");
	}
	
	public Sospechoso acusacion(Jugador j){
		mostrarNombreMonjes();
		int i = _vista.limites(1,_monjes.longitud());
		return _monjes.getSospechoso(i-1);
	}
	
	/**
	 * Metodo que muestra los nombres de todos los monjes que hay
	 */
	public void mostrarNombreMonjes(){
		int i = 1;
		_vista.normal(" == Lista de monjes ==\n");
		for(Sospechoso s: _monjes.dameMazo()){
			_vista.normal(" "+i+". "+s.getNombre());
			i++;
		}
		_vista.normal(" =================\n");
	}
	
	/**
	 * Metodo que añade un rasgo a su lista de rasgos
	 * @param i es la cantidad de ere rasgo que hay
	 * @param s es el rasgo que añade
	 * @param j es el jugador al que le toca
	 */
	public void ponerRasgos(int i,String s,Jugador j){
		if(j.estaCaracterstica(s))
			j.sumaCaracteristica(s,i);
		else j.ponerCaracteristica(s,i);
	}
	
	/**
	 * Metodo que hace la logica de las preguntas de un jugador a otro durante la partida
	 * @param j es el jugador al que le toca preguntar
	 * @param est es la estancia en la que esta el jugador
	 */
	public void fasePregunta(Jugador j, Room est){
		_vista.normal(" En esa estancia hay los siguientes jugadores: \n");
		_mapa.eliminaJugador(est,j);
		_mapa.mostrarJugadores(est);
		_vista.normal(" ¿ A quién quieres preguntarle algo ? ");
		String s = _vista.preguntaAfirmacion();
		
		if((s.equals("si"))||(s.equals("Si"))||(s.equals("SI"))||(s.equals("sI"))){
			int i = _vista.limites(1,_mapa.numJugadores(est));
			Jugador jAux = _mapa.dameJugador(est,i-1);
			_vista.normal(" ¿Que tipo de pregunta quieres hacer?\n");
			_vista.tiposPregunta();
			int pregunta = _vista.limites(1,2);
			
			_vista.normal(" == Sospechosos tachados del jugador "+jAux.getColor().toString()+" ==\n");
			int id = _jugadores.indexOf(jAux);
			mostrarMonjesTachados(id);
			
			if(pregunta==1){
				String rasgo = _pregunta.pregunta1();
				int numero = _vista.leerTecladoEntero("\n Introduce un numero ");
				ponerRasgos(numero,rasgo,j);
			}
			else{
				mostrarNombreMonjes();
				int num = _vista.limites(1,_monjes.dameMazo().size());
				String sAux = _vista.leerTeclado(_pregunta.pregunta2(_monjes.dameMazo().get(num-1)));
				if((sAux.equals("si"))||(sAux.equals("Si"))||(sAux.equals("SI"))||(sAux.equals("sI"))){
					int iAux = _jugadores.indexOf(j);
					tacharSospechoso(iAux,_monjes.dameMazo().get(num-1));
				}
			}
		}
		_mapa.addJugador(est,j);
	}

}
