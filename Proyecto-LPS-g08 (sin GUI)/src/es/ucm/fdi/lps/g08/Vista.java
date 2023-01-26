package es.ucm.fdi.lps.g08;

import java.io.*;

import es.ucm.fdi.lps.g08.Cartas.Sospechoso;

public final class Vista implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void normal(String s){
		System.out.println();
		System.out.print(s);
	}
	
	public void normalAux(String s){
		System.out.println(s);
	}
	
	public void verColores(){
		
	}
	
	public int limites(int i, int j){
		int n = -1;
		while((n<i)||(n>j)){
			n = leerTecladoEntero(" Introduce el numero ");
			if((n<i)||(n>j))
				System.out.print(" Por favor introduce un numero entre "+i+" y "+j);
		}
		return n;
	}
	
	public String leerTeclado(String sms){
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String codigo = "";
		try{
			System.out.print(sms);
			codigo = buffer.readLine();
		}catch(Exception e){
			System.out.print("Error en la lectura de pantalla");
		}
		return codigo;
	}
	
	public int leerTecladoEntero(String sms){
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String codigo = "";
		int i = 0;
		try{
			System.out.print(sms);
			codigo = buffer.readLine();
			i = Integer.valueOf(codigo);
		}catch(Exception e){
			System.out.print(" Error, por favor introduzca un numero entero");
		}
		return i;
	}

	public String preguntaAfirmacion(){
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String pregunta = "";
		
		boolean correcto = false;
		
		while(!correcto){
			try {
				pregunta = buffer.readLine();
				if((!pregunta.equals("si"))&&(!pregunta.equals("Si"))&&(!pregunta.equals("SI"))&&(!pregunta.equals("sI"))
					&&(!pregunta.equals("no"))&&(!pregunta.equals("No"))&&(!pregunta.equals("NO"))&&(!pregunta.equals("nO")))
						System.out.print(" Por favor Introduzca Si o No ");
				else correcto = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pregunta;
	}
	
	public String preguntaJugar(){
		String codigo = " ";
		boolean correcto = false;
		while(!correcto){
			codigo = leerTeclado(" Para comenzar partida escriba jugar ");
			if((!codigo.equals("salir"))&&(!codigo.equals("jugar"))&&(!codigo.equals("Jugar"))&&(!codigo.equals("JUGAR")))
				System.out.println(" Teclee salir o jugar");
			else correcto = true;
		}
		return codigo;
	}
	
	public String eligePropiedad(){
		System.out.println();
		System.out.println(" Elige una de las siguientes opciones que quieres revelar: ");
		System.out.println(" 1. Orden");
		System.out.println(" 2. Titulo");
		System.out.println(" 3. Capucha");
		System.out.println(" 4. Facial");
		System.out.println(" 5. Complexion");
		int num = limites(1,5);
		int i = 0;
		switch(num){
		case 1: System.out.println();
				System.out.println(" Introduce la orden que desea revelar");
				System.out.println(" 1. Templario");
				System.out.println(" 2. Franciscano");
				System.out.println(" 3. Benedictino");
				i = limites(1,3);
				switch(i){
				case 1: /*Sospechoso s1 = new Sospechoso("","Templario","",-1,-1,"");
						j.setRevelacion(s1);
						break;*/
						return "es un templario";
				case 2: /*Sospechoso s2 = new Sospechoso("","Franciscano","",-1,-1,"");
						j.setRevelacion(s2);
						break;*/
						return "es un franciscano";
				default: /*Sospechoso s3 = new Sospechoso("","Benedictino","",-1,-1,"");
						j.setRevelacion(s3);*/
						return "es un benedictino";
				}
				//break;
		case 2: System.out.println();
				System.out.println(" Introduce el titulo que desea revelar");
				System.out.println(" 1. Padre");
				System.out.println(" 2. Hermano");
				System.out.println(" 3. Novicio");
				i = limites(1,3);
				switch(i){
				case 1: /*Sospechoso s1 = new Sospechoso("","","Padre",-1,-1,"");
						j.setRevelacion(s1);
						break;*/
						return "es un padre";
				case 2: /*Sospechoso s2 = new Sospechoso("","","Hermano",-1,-1,"");
						j.setRevelacion(s2);
						break;*/
						return "es un hermano";
				default: /*Sospechoso s3 = new Sospechoso("","","Novicio",-1,-1,"");
						j.setRevelacion(s3);*/
						return "es un novicio";
				}
				//break;
		case 3: System.out.println();
				System.out.println(" Introduce si lleva o no lleva capucha");
				System.out.println(" 1. Si");
				System.out.println(" 2. No");
				i = limites(1,2);
				if(i == 1){
					/*Sospechoso s1 = new Sospechoso("","","",1,-1,"");
					j.setRevelacion(s1);*/
					return "lleva capucha";
				}else if(i == 2){
					/*Sospechoso s2 = new Sospechoso("","","",0,-1,"");
					j.setRevelacion(s2);*/
					return "no lleva capucha";
				}
		case 4: System.out.println();
				System.out.println(" Introduce si lleva o no lleva barba");
				System.out.println(" 1. Si");
				System.out.println(" 2. No");
				i = limites(1,2);
				if(i == 1){
					/*Sospechoso s1 = new Sospechoso("","","",-1,1,"");
					j.setRevelacion(s1);*/
					return "lleva barba";
				}else if(i == 2){
					/*Sospechoso s2 = new Sospechoso("","","",-1,0,"");
					j.setRevelacion(s2);*/
					return "no lleva barba";
				}
				break;
		default: System.out.println(); 
				 System.out.println(" Introduce si es gordo o delgado");
				 System.out.println(" 1. Gordo");
				 System.out.println(" 2. Delgado");
				 i = limites(1,2);
				 if(i == 1){
					 /*Sospechoso s1 = new Sospechoso("","","",-1,-1,"gordo");
					 j.setRevelacion(s1);*/
					 return "es gordo";
				 }else if(i == 2){
					 /*Sospechoso s2 = new Sospechoso("","","",-1,-1,"delgado");
					 j.setRevelacion(s2);*/
					 return "es delgado";
				 }
		}
		return "";
	}
	
	public String titulo(){
		return "* El Misterio de la AbadÃ­a *";
	}
	
	public String bienvenida(){
		return "           *****************************************\n"+
			   "           * Bienvenido a EL MISTERIO DE LA ABADIA *\n"+
		   	   "           *****************************************";
	}
	
	public void mensajeBienvenida(){

		System.out.println();
		System.out.println();
		System.out.println("           *****************************************");
		System.out.println("           * Bienvenido a EL MISTERIO DE LA ABADIA *");
		System.out.println("           *****************************************");
		System.out.println();
		System.out.println(" La pacifica abadia de los templarios es un singular remanso de paz para los viajeros");
		System.out.println(" cansados del camino.Y eso fue lo que encontraste cuando llegaste ayer, a altas horas");
		System.out.println(" de la noche.Esa serenidad se vio rota esta mañana con el descubrimiento del cuerpo");
		System.out.println(" sin vida del hermano Adelmo a los pies de los barrancos de Monasterio.¿Se resbalo");
		System.out.println(" el hermano, de conocida agilidad,hasta su muerte?¿O le ayudo alguien en su caida?");
		System.out.println(" Todo apunta a que algo se esconde tras esta muerte...y el Abad te ha pedido a ti");
		System.out.println(" y a tus compañeros que investigueis y encontreis la respuestaa la siguiente pregunta:");
		System.out.println(" ¿Quien asesino al hermano Adelmo?");
		System.out.println();
		System.out.println("   Normas del juego");
		System.out.println(" ---------------------");
		System.out.println();
		System.out.println(" Se empieza por el primer jugador y en el sentido de las agujas del reloj.");
		System.out.println(" Cada turno tiene las siguientes fases:");
		System.out.println(" 1.Mover la campana de misa (solo el primer jugador del turno): ");
	    System.out.println("   Si eres el primer jugador mueve la campana un paso en la carta de misa superior del mazo. ");
	    System.out.println("   Si la campana ya está en 4 se quita de la carta, llama a Misa y aplica los efectos de la Carta de Misa.");
		System.out.println(" 2.Mueve tu ficha: Mueve tu ficha uno o dos pasos en cualquier dirección del tablero.");
		System.out.println("   Cada estancia de la abadía cuenta como un paso. El movimiento es opbligatorio y no se puede usar ");
		System.out.println("   un movimiento de dos pasos para salir y volver a entrar donde estabamos al principio. ");
		System.out.println(" 3.Encuentro: Si tu ficha termina su movimiento en una habitación ocupada por otra ficha debes hacerle una pregunta. ");
		System.out.println(" 4.Acción: Lleva a cabo cualquier acción relevante a la estancia en la que esta tu ficha. ");
		System.out.println();
		System.out.println("      Objetivo ");
		System.out.println(" --------------------");
		System.out.println();
		System.out.println(" Lo que tienen que hacer los investigadores es preguntar a los habitantes del monasterio y ");
		System.out.println(" buscar por la Abadí­a para descubrir las caracterí­sticas (orden, título, capucha, vello facial, complexión)");
		System.out.println(" y el nombre del culpable.");
		System.out.println(" Los jugadores ganan puntos cuando revelan las caracteristicas del culpable y su nombre. ");
		System.out.println(" Un jugador pierde puntos cuando se equivoca al revelar el culpable o al realizar una acusación.");
		System.out.println(" Las revelaciones y acusaciones se anotan a lo largo de la partida; ");
		System.out.println(" al final del juego, cuando se ha atrapado al culpable se conceden y se suman los puntos de victoria."); 
		System.out.println(" Gana el juego quien haya conseguido mas puntos de victoria al final de la partida; a menudo pero no siempre,");
		System.out.println(" será el jugador que haya descubierto al culpable. ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println();
	}
	
	public String mensajeAyuda(){

		String s=" ----------------------------------------------------------------------------------------------\n";
		s=s+"                                          Normas del juego       \n";
		s=s+" Se empieza por el primer jugador y en el sentido de las agujas del reloj.\n";
		s=s+" Cada turno tiene las siguientes fases:\n";
		s=s+" 1.Mover la campana de misa (solo el primer jugador del turno):\n";
		s=s+"   Si eres el primer jugador mueve la campana un paso en la carta de misa superior del mazo.\n";
		s=s+"   Si la campana ya está en 4 se quita de la carta, llama a Misa y aplica los efectos de la Carta de Misa.\n";
		s=s+" 2.Mueve tu ficha: Mueve tu ficha uno o dos pasos en cualquier dirección del tablero.\n";
		s=s+"   Cada estancia de la abadía cuenta como un paso. El movimiento es opbligatorio y no se puede usar\n";
		s=s+"   un movimiento de dos pasos para salir y volver a entrar donde estabamos al principio.\n ";
		s=s+" 3.Encuentro: Si tu ficha termina su movimiento en una habitación ocupada por otra ficha debes hacerle una pregunta.\n";
		s=s+" 4.Acción: Lleva a cabo cualquier acción relevante a la estancia en la que esta tu ficha. \n";
		s=s+"                                                                \n";
		s=s+"     	                                    Objetivo       \n";
		s=s+" ----------------------------------------------------------------------------------------------\n";
		s=s+" Lo que tienen que hacer los investigadores es preguntar a los habitantes del monasterio y\n";
		s=s+" buscar por la Abadí­a para descubrir las caracterí­sticas (orden, título, capucha, vello facial, complexión)\n";
		s=s+" y el nombre del culpable.\n";
		s=s+" Los jugadores ganan puntos cuando revelan las caracteristicas del culpable y su nombre.\n";
		s=s+" Un jugador pierde puntos cuando se equivoca al revelar el culpable o al realizar una acusación.\n";
		s=s+" Las revelaciones y acusaciones se anotan a lo largo de la partida;\n ";
		s=s+" al final del juego, cuando se ha atrapado al culpable se conceden y se suman los puntos de victoria.\n";
		s=s+" Gana el juego quien haya conseguido mas puntos de victoria al final de la partida; a menudo pero no siempre,\n";
		s=s+" será el jugador que haya descubierto al culpable.\n";
		return s;
	}
	
	public void Menu(){
		System.out.println();
		System.out.println(" ===== Menu de opciones =====");
		System.out.println(" 1. Ver tus cartas de sospechoso");
		System.out.println(" 2. Ver tu hoja de rasgos");
		System.out.println(" 3. Ver los monjes que tienes tachados");
		System.out.println(" 4. Ver revelaciones y acusaciones");
		System.out.println(" 5. Ver todos los monjes");
		System.out.println(" 6. Realizar un movimiento");
		System.out.println(" 7. Mostrar ultimos en confesarse");
	}
	
	
	public String InterfazInicio(){
		
		String s="*EL MISTERIO DE LA ABADIA *\n";
		s=s+" La pacifica abadia de los templarios es un singular remanso de paz para los viajeros cansados del camino.\n";
		s=s+" Y eso fue lo que encontraste cuando llegaste ayer, a altas horas de la noche.\n";
		s=s+" Esa serenidad se vio rota esta mañana con el descubrimiento del cuerpo sin vida del hermano Adelmo a los pies\n";
		s=s+" de los barrancos de Monasterio.¿Se resbalo el hermano, de conocida agilidad,hasta su muerte?\n";
		s=s+" ¿O le ayudo alguien en su caida?\n";
		s=s+" Todo apunta a que algo se esconde tras esta muerte...y el Abad te ha pedido a ti y a tus compañeros que investigueis\n";
		s=s+" y encontreis la respuestaa la siguiente pregunta:\n";
		s=s+" ¿Quien asesinó al hermano Adelmo?\n";
		
		return s;
	}
	
	/*public void mostrarSospechoso(Sospechoso s){
		String sms = "No";
		String sms1 = "No";
		System.out.println(" Nombre: "+s.getNombre());
		System.out.println(" Orden: "+s.getOrden());
		System.out.print(" Titulo: "+s.getTitulo());
		if(s.getCapucha()==true) sms = "Si";
		System.out.print(" |Capucha: "+sms);
		if(s.getFacial()==Barba.barba.toString()) sms1 = "Si";
		System.out.println(" |Barba: "+sms1);
		System.out.println(" Complexion: "+s.getComplexion());	
	}*/
	
	public String mostrarSospechoso(Sospechoso s){
		String tmp = "";
		String sms = "No";
		String sms1 = "No";
		tmp = tmp+" Nombre: "+s.getNombre()+"\n";
		tmp = tmp+" Orden: "+s.getOrden()+"\n";
		tmp = tmp+" Titulo: "+s.getTitulo()+"\n";
		if(s.getCapucha()==true) sms = "Si";
		tmp = tmp+" |Capucha: "+sms+"\n";
		if(s.getFacial()==Barba.barba.toString()) sms1 = "Si";
		tmp = tmp+" |Barba: "+sms1+"\n";
		tmp = tmp+" Complexion: "+s.getComplexion()+"\n";
		return tmp;
	}
	
	/*public void listaRasgos(){
		System.out.println(" 1. Titulo: ");
		System.out.println("    Padre, hermano o novicio");
		System.out.println(" 2. Orden: ");
		System.out.println("    Templario, francisacno o benedictino");
		System.out.println(" 3. Capucha: ");
		System.out.println("    Si o no");
		System.out.println(" 4. Vello facial: ");
		System.out.println("    Si o no");
		System.out.println(" 5. Complexion: ");
		System.out.println("    Gordo o delgado");
	}
	
	public void ubicacion(){
		System.out.println(" 1. Cartas de sospechoso");
		System.out.println(" 2. Lista de sospechosos descartados");
	}*/
	
	public String listaRasgos(){
		String s =" 1. Titulo: \n";
		s=s+"    Padre, hermano o novicio\n";
		s=s+" 2. Orden: \n";
		s=s+"    Templario, francisacno o benedictino\n";
		s=s+" 3. Capucha: \n";
		s=s+"    Si o no\n";
		s=s+" 4. Vello facial: \n";
		s=s+"    Si o no\n";
		s=s+" 5. Complexion: \n";
		s=s+"    Gordo o delgado\n";
		return s;
	}
	
	public String ubicacion(){
		return " 1. Cartas de sospechoso\n"+
		       " 2. Lista de sospechosos descartados\n";
	}
	
	/*public void tiposPregunta(){
		System.out.println(" 1. Â¿Cuantos monjes con rasgoX tienes en ubicacionY?");
		System.out.println(" 2. Â¿Tienes al monjeX en ubicacionY?");
	}*/
	
	public String tiposPregunta(){
		return " 1. ¿Cuantos monjes con rasgoX tienes en ubicacionY?\n"+
			   " 2. ¿Tienes al monjeX en ubicacionY?\n";
	}
	
	public void cantoGregoriano(){
		System.out.println(" Todos los jugadores deben hablar en canto gregoriano.Cantando");
		
	}
	
	public void campanasMatutinas(){
	    System.out.println("Todos los jugadores deben cantar inmediatamente Alabaré, alabaré, alabaré a mi Señor.");	
	}	
		
	
}
