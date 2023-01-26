package es.ucm.fdi.lps.g08;

public final class Constantes {
	
	public static final int MOVIMIENTOS = 2;
	
    public static final int PUERTO_RED = 5000;  
    
    public static final String HOST = "localhost";
    
    public static final int NUMERO_JUGADORES = 3; 
    
    public static final int NUMERO_MISAS = 8;
    
    public static final int NUMERO_TURNOS = 4;

	//Nombres de Monjes
	public static final String MATTHEW = "Matthew";
	public static final String WILLIAM = "William";
	public static final String FORTUNE = "Fortune";
	public static final String HAROLD = "Harold";
	public static final String MALACHI = "Malachi";
	public static final String GERARD = "Gerard";
	public static final String BASIL = "Basil";
	public static final String THOMAS = "Thomas";
	public static final String GALBRAITH = "Galbraith";
	public static final String MICHAEL = "Michael";
	public static final String EMMANUEL = "Emmanuel";
	public static final String CUTHBERT = "Cuthbert";
	public static final String JACQUES = "Jacques";
	public static final String BARTHOLOMEW = "Bartholomew";
	public static final String ANDRE = "Andre";
	public static final String NICHOLAS = "Nicholas";
	public static final String BRUNO = "Bruno";
	public static final String SERGIO = "Sergio";
	public static final String BERENGAR = "Berengar";
	public static final String JULIAN = "Juli�n";
	public static final String CYRILLE = "Cyrille";
	public static final String CHARLES = "Charles";
	public static final String PHILIPPE = "Philippe";
	public static final String GUY = "Guy";
	
	//Nombres de Estancias
	public static final String CLAUSTRO_NORTE = "Claustro Norte";	
	public static final String CLAUSTRO_ESTE = "Claustro Este";
	public static final String CLAUSTRO_SUR = "Claustro Sur";
	public static final String CLAUSTRO_OESTE = "Claustro Oeste";
	public static final String PATIO = "Patio";
	public static final String CAPILLA = "Capilla";
	public static final String CONFESIONARIO_INTERIOR = "Confesionario Interior";
	public static final String CRIPTA = "Cripta";
	public static final String CONFESIONARIO_EXTERIOR = "Confesionario Exterior";
	public static final String PASILLO_AZUL = "Pasillo Azul";
	public static final String CELDA_AZUL = "Celda Azul";
	public static final String SCRIPTORIUM = "Scriptorium";
	public static final String BIBLIOTECA = "Biblioteca";
	public static final String PASILLO_ROJO = "Pasillo Rojo";
	public static final String CELDA_ROJA = "Celda Roja";
	public static final String PASILLO_NEGRO = "Pasillo Negro";
	public static final String CELDA_NEGRA = "Celda Negra";
	public static final String SALA_CAPITULAR = "Sala Capitular";
	public static final String PASILLO_AMARILLO = "Pasillo Amarillo";
	public static final String CELDA_AMARILLA = "Celda Amarilla";
	public static final String PASILLO_VERDE = "Pasillo Verde";
	public static final String CELDA_VERDE = "Celda Verde";
	public static final String LOCUTORIO = "Locutorio";
	public static final String PASILLO_BLANCO = "Pasillo Blanco";
	public static final String CELDA_BLANCA = "Celda Blanca";
	
	public static final String textoCartaCripta = "Guarda esta carta y úsala en el futuro para jugar un turno adicional"+
			  									  " inmediatamente después de terminar tu turno normal.";
	
	//Textos de las cartas
	public static final String textoDemonologia = "Coloca las fichas de todos los dem�s jugadores en el Scriptorium. Especifica"+ 
												  " una caracter�stica de monje (Templario, por ejemplo). Cada uno de los dem�s jugadores"+ 
                                                  " deben mostrarte una de sus cartas de sospechoso con esta caracter�stica, si tienen alguna.";
	public static final String textoDiarioSecreto = "Mira una carta de sospechoso, al azar, de la mano de cada uno"+
													" de los dem�s jugadores. Baraja despu�s esas cartas y dale una a cada uno de los dem�s jugadores.";
	public static final String textoEspejoMundo = "Mira todas las cartas de sospechoso de otro jugador, determinado por"+
												  " una tirada de dado.";
	public static final String textoEvangelioJudas = "Intercambia todas tus cartas de sospechoso con las de otro jugador.";
	public static final String textoHorusAploo = "Roba tres cartas de Scriptorium.";
	public static final String textoRisaAristoteles = "Juega inmediatamente otro turno, durante el cual puedes ir al lugar que elijas.";
	public static final String textoTratadoMagia = "Realiza inmediatamente una revelaci�n, que s�lo se tendr� en cuenta al final del juego si es correcta.";
	public static final String textoTratadoPlaceres = "Mira todas las cartas de sospechoso de otro jugador a tu"+
													  " elecci�n. Antes de que mires sus cartas, puede esconder una de ellas para que no puedas verla.";
	public static final String textoCronicaSanTeodulo = "Las fichas de todos los jugadores se colocan en la Capilla para la"+
														" misa. La misa se celebra inmediatamente. Coge la campana y las cartas de misa, y juega el"+
														" primero despu�s de la misa.";
	public static final String textoCantarCantares = "Juega otro turno inmediatamente, en el que puedes mover tres espacios.";
	public static final String textoCronicaMonasterio = "Coge una carta de sospechosos al azar de un jugador determinado por una tirada de dado.";
	public static final String textoLibroAltaMagia = "Coloca las fichas de los dem�s jugadores donde quieras. No puedes poner dos fichas en el mismo espacio.";
	public static final String textoProfeciasPresagios = "Mira las cuatro cartas de evento siguientes y vuelve a colocarlas en el orden que elijas.";
	public static final String textoReglaTemplaria = "Coge una carta de libro de otro jugador, sin mirarla antes, y guardatela.";
	public static final String textoTextoCriptografico = "Coloca tu ficha en la Cripta o en un confesionario a tu elecci�n. Aplica"+
														 " inmediatamente el efecto de la Cripta o el Confesionario.";
	public static final String textoTratadoAstrologicoArabe = "Mira dos Cartas de Sospechoso cogidas al azar de las manos"+
															  " de los dos jugadores que elijas. Intercambia las cartas antes de devolverlas.";
	public static final String textoTratadoNeoplatonico = "Mira dos cartas de sospechoso cogidas al azar de un jugador determinado por una tirada de dado";
	public static final String textoApocalipsisJuan = "Guarda esta carta y úsala cuando haya que tirar un dado para"+
				  									  " determinar un jugador. No se tira el dado, y eres tú quien elige al jugador.";
	public static final String textoBreviarioIluminado = "Guarda esta carta y úsala para mover un espacio adicional durante tu turno.";
	public static final String textoCartularioAbadia = "Guarda esta carta y úsala durante una misa para mirar las cartas"+
				   									   " que el jugador que elijas le está dando a otro jugador.";
	public static final String textoCiudadDios = "Guarda esta carta y úsala en el futuro para mover un espacio adicional durante tu turno.";
	public static final String textoDeAlquimia = "Guarda esta carta y úsala para ignorar la llamada a misa. Tu ficha no se"+
												 " mueve del sitio. No recibes ni das ninguna carta de sospechoso. El jugador sentado a tu"+
												 " derecha le da las cartas al jugador sentado a tu izquierda.";
	public static final String textoLibroHoras = "Guarda esta carta y úsala al final de una misa. Coge la campana y las"+
			 									 " cartas de misa y juega en primer lugar después de la misa.";
	public static final String textoLogicaAristotelica = "Guarda esta carta y úsala cuando estés en el Locutorio. Si el jugador al"+
			 											 " que le has pedido que te enseñe una carta no puede hacerlo, puedes pedirle que te enseñe"+
			 											 " otra, y así sucesivamente hasta que te enseñe una de sus cartas.";
	public static final String textoManualInquisidor = "Guarda esta carta y úsala durante tu turno para realizar una"+
			   										   " Revelación o una Acusación, desde cualquier lugar del interior de la Abadía.";
	public static final String textoMapaAbadia = "Guarda esta carta y úsala durante tu turno para ir directamente de la"+
			 									 " Cripta al Locutorio, el Scriptorium o la Sala Capitular, en vez de realizar un movimiento normal.";
	public static final String textoReglaBenedictina = "Guarda esta carta y úsala antes de hacerle una pregunta a otro jugador." +
			   										   " Tanto la pregunta como la respuesta se comunican en voz baja, al oído del otro jugador. Si"+
			   										   " ese jugador quiere preguntarte a su vez, la pregunta y la respuesta también se comunicarán en privado.";
	public static final String textoReglaFranciscana = "Guarda esta carta y úsala después de haberle hecho una pregunta a"+
			   										   " otro jugador y, posiblemente, haber sido preguntado a tu vez. Haya respondido o no, la ficha"+
			   										   " de ese jugador es enviada a la Capilla. No es una penitencia y no tiene que pasar en ella un turno.";
	public static final String textoSalmos = "Guarda esta carta y úsala para mover un espacio adicional durante tu turno.";
	public static final String textoSummaTheologica = "Guarda esta carta y úsala durante una misa para darle una carta"+
			  										  " menos de lo requerido normalmente a tu vecino.";
	public static final String textoTratadoPatristicaBizantino = "Guarda esta carta y úsala para entrar en la Biblioteca,"+
																 " incluso si ya has estado allí o tienes demasiadas cartas en la mano.";
	public static final String textoTratadoRetorica = "Guarda esta carta y úsala cuando un jugador tome un voto de silencio" +
			  										  " para no responder una de tus preguntas. Debe responder y no puede hacerte a su vez una pregunta.";

	//Cartas Evento
	
	public static final String textoCampanasMatutinas =  "Todos los jugadores deben cantar inmediatamente ,Alabaré,alabaré, alabaré a mi Señor.";
	public static final String textoCoartada1 = "Cada jugador elige una carta de sospechoso de su mano y la muestra. Todas las cartas mostradas son retiradas" +
												"del juego y colocadas boca arriba a un lado del tablero";
	public static final String textoCoartada2 = "Cada jugador coge al azar una carta de sospechoso del jugador sentado a su izquierda" +
												"y la muestra. Todas las cartas mostradas son retiradas del juego y colocadas boca arriba aun lado del tablero";
	
	public static final String textoFervorGregoriano = "Hasta la siguiente misa, todos los jugadores deben hablar en cantos gregorianos (cantando).";
	public static final String textoInquisidor = "Las fichas de todos los jugadores se colocan en la Sala Capitular, y cada" +
												"jugador realiza una revelación. Cada jugador anota en secreto en un papel su revelación y" +
												"después se muestran todas simultáneamente. Es posible que varios jugadores realicen la misma revelación.";
	public static final String textoLaboresDiarias = "Tira el dado tres veces para escoger tres de las fichas de los jugadores. La" +
													"primera es enviada a limpiar la Sala Capitular, la segunda es enviada a fregar el suelo del" +
													"Locutorio y la tercera es enviada a ordenar el Scriptorium.";
	public static final String textoLibrosProhibidos = "Se descartan todas las cartas de libro de las manos de los jugadores.";
	public static final String textoLlavePerdida = "Tira un dado para determinar qué habitación estará cerrada para las fichas" +
													"de los jugadores hasta la siguiente misa. Si sale rojo o blanco: Scriptorium. Si sale azul o " +
													"verde: Locutorio. Si sale amarillo o negro: Sala Capitular.";
	public static final String textoPenitencia1 = "Hasta la siguiente misa, los jugadores sólo pueden realizar preguntas de ‘sí’ o ‘no’";
	public static final String textoPenitencia2 = "Tira dos veces el dado y coloca las fichas correspondientes en sus celdas";
	public static final String textoPlegariasPrivadas= "Todas las fichas de los jugadores se colocan en sus respectivas celdas";
	public static final String textoProcesion = "El juego pasa inmediatamente a la siguiente misa.";
	public static final String textoSermon =    "Hasta la siguiente misa, las fichas de todos los jugadores se mueven un espacio adicional cada turno.";
	public static final String textoSospecha =  "Todos los jugadores deben mostrar una carta de sospechoso con el rasgo ‘Hermano’";
	public static final String textoSospecha1 = "Todos los jugadores deben mostrar una carta de sospechoso con el rasgo ‘Novicio’";
	public static final String textoSospecha2 = "Todos los jugadores deben mostrar una carta de sospechoso con el rasgo ‘Templario’";
	public static final String textoSospecha3 = "Todos los jugadores deben mostrar una carta de sospechoso con el rasgo ‘Franciscano’";
	public static final String textoSospecha4 = "Todos los jugadores deben mostrar una carta de sospechoso con el rasgo ‘Benedictino’";

	//Textos Cartas Misa
	
	public static final String textoMaitines ="Las fichas de todos los jugadores se colocan en la Capilla. Todos los jugadores le" +
			"dan simultáneamente una carta de sospechoso al jugador situado a su izquierda. Se coge una carta de evento";
	public static final String textoLaudes ="Las fichas de todos los jugadores se colocan en la Capilla. Todos los jugadores le " +
			"dan simultáneamente dos cartas de sospechoso al jugador situado a su izquierda. Se coge una carta de evento";
	public static final String textoPrima ="Las fichas de todos los jugadores se colocan en la Capilla. Todos los jugadores le " +
			"dan simultáneamente tres cartas de sospechoso al jugador situado a su izquierda. Se coge una carta de evento";
	public static final String textoTercia ="Las fichas de todos los jugadores se colocan en la Capilla (Ecclesia). Todos los " +
			"jugadores le dan simultáneamente cuatro Cartas de Sospechoso al jugador situado a su izquierda. Se coge una carta de evento.";
	public static final String textoSexta ="Las fichas de todos los jugadores se colocan en la Capilla. Todos los jugadores le " +
			"dan simultáneamente cinco cartas de sospechoso al jugador situado a su izquierda. Se coge una carta de evento.";
	public static final String textoNona ="Las fichas de todos los jugadores se colocan en la Capilla (Ecclesia). Todos los " +
			"jugadores le dan simultáneamente seis Cartas de Sospechoso al jugador situado a su izquierda. Se coge una Carta de Evento.";
	public static final String textoVisperas ="Las fichas de todos los jugadores se colocan en la Capilla (Ecclesia). Todos " +
			"los jugadores le dan simultáneamente seis Cartas de Sospechoso al jugador situado a su izquierda. Se coge una Carta de Evento.";
	public static final String textoCompletas ="Las fichas de todos los jugadores se colocan en la Capilla (Ecclesia). Todos " +
			"los jugadores le dan simultáneamente seis Cartas de Sospechoso al jugador situado a su izquierda. Se coge una Carta de Evento.";
	
	//nombres de las cartas
	
	//Biblioteca
	public static final String DEMONOLOGIA = "Demonologia";
	public static final String DIARIOSECRETO = "DiarioSecreto";
	public static final String ESPEJOMUNDO = "EspejoMundo";
	public static final String EVANGELIOJUDAS = "EvangelioJudas";
	public static final String HORUSAPLOO = "HorusAploo";
	public static final String RISAARISTOTELES = "RisaAristoteles";
	public static final String TRATADOMAGIA = "TratadoMagia";
	public static final String TRATADOPLACERES = "TratadoPlaceres";
	
	//Scriptorium
	public static final String APOCALIPSISJUAN = "ApocalipsisJuan";
	public static final String BREVIARIOILUMINADO = "BrevarioIluminado";
	public static final String CANTARCANTARES = "CantarCantares";
	public static final String CARTULARIOABADIA = "CartularioAbadia";
	public static final String CIUDADDIOS = "CiudadDios";
	public static final String CRONICAMONASTERIO = "CronicaMonasterio";
	public static final String CRONICASANTEODULO = "CronicaSanTeodulo";
	public static final String DEALQUIMIA = "DeAlquimia";
	public static final String LIBROALTAMAGIA = "LibroAltaMagia";
	public static final String LIBROHORAS = "LibroHoras";
	public static final String LOGICAARISTOTELICA = "LogicaAristotelica";
	public static final String MANUALINQUISIDOR = "ManualInquisidor";
	public static final String MAPAABADIA = "MapaAbadia";
	public static final String PROFECIASPRESAGIOS = "ProfeciasPresagios";
	public static final String REGLABENEDICTINA = "ReglaBenedictina";
	public static final String REGLAFRANCISCANA = "ReglaFranciscana";
	public static final String REGLATEMPLARIA = "ReglaTemplaria";
	public static final String SALMOS = "Salmos";
	public static final String SUMMATHEOLOGICA = "SummaTheologica";
	public static final String TEXTOCRIPTOGRAFICO = "TextoCriptografico";
	public static final String TRATADOASTROLOGICOARABE = "TratadoAstrologicoArabe";
	public static final String TRATADONEOPLATONICO = "TratadoNeoplatonico";
	public static final String TRATADOPATRISTICABIZANTINO = "TratadoPatristicaBizantino";
	public static final String TRATADORETORICA = "TratadoRetorica";
	
	//Evento
	
	public static final String CAMPANASMATUTINAS = "CampanasMatutinas";
	public static final String COARTADA = "Coartada";
	public static final String FERVORGREGORIANO = "FervorGregoriano";
	public static final String INQUISIDOR = "Inquisidor";
	public static final String LABORESDIARIAS = "LaboresDiarias";
	public static final String LIBROSPROHIBIDOS = "LibrosProhibidos";
	public static final String LLAVEPERDIDA = "LlavePerdida";
	public static final String PENITENCIA = "Penitencia";
	public static final String PLEGARIASPRIVADAS = "PlegariasPrivadas";
	public static final String PROCESION = "Procesion";
	public static final String SERMON = "Sermon";
	public static final String SOSPECHA = "Sospecha";
	
	//Cripta
	
	public static final String CARTACRIPTA = "Cripta";
	
	//Misa
	
	public static final String MAITINES = "Maitines";
	public static final String LAUDES = "Laudes";
	public static final String PRIMA = "Prima";
	public static final String TERCIA = "Tercia";
	public static final String SEXTA = "Sexta";
	public static final String NONA = "Nona";
	public static final String VISPERAS = "Visperas";
	public static final String COMPLETAS = "Completas";	
}
