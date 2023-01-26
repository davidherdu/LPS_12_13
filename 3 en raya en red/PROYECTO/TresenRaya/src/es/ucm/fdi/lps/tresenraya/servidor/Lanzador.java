/**
 * LABORATORIO DE PROGRAMACI�N DE SISTEMAS 2012/2013
 * Ingenier�a T�cnica en Inform�tica de Sistemas
 * Departamento de Ingenier�a del Software e Inteligencia Artificial
 * Facultad de Inform�tica, Universidad Complutense de Madrid
 *
 * TRES EN RAYA - VERSI�N CLIENTE/SERVIDOR
 * Por Federico Peinado
 * 
 * Adaptaci�n del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
 * http://www.deitel.com/Books/Java/JavaHowtoProgram9eEarlyObjectsVersion/tabid/3622/Default.aspx
 */
package es.ucm.fdi.lps.tresenraya.servidor;

import static es.ucm.fdi.lps.tresenraya.Parametros.*;

/**
 * La clase que se ocupa de preparar y lanzar el servidor del juego. 
 * Es una clase pensada para no tener ejemplares (objetos).
 * @author Federico Peinado
 */
final class Lanzador {
	
    // El constructor es privado para que no lo puedan usar otras clases.    
    private Lanzador() { 
        throw new AssertionError("No se permite crear ejemplares de esta clase.");
    }
	
    /**
     * Este m�todo crea el modelo del juego Tres en Raya en el servidor, 
     * crea la mitad del controlador correspondiente al lado del servidor (asoci�ndolo al modelo),
     * y finalmente ejecuta el controlador pas�ndole las indicaciones necesarias para que haga la gesti�n por red.
     * Desde el punto de vista del servidor da comienzo el juego.
     * @param args Los argumentos que pasa el usuario al ejecutar la aplicaci�n desde la consola (por ahora, simplemente los ignoramos)
     */
	public static void main(String args[]) {
		Modelo modelo = new Modelo();
	
	    // Usamos un controlador sencillo, que gestiona la comunicaci�n por red con varios clientes con un s�lo hilo (apoy�ndonos en que sabemos que los clientes son respetuosos y no mandan informaci�n cuando no les toca)
        MedioControlador controlador = new MedioControlador(modelo);
		
		// El servidor escucha en el puerto especificado
		controlador.ejecutar(PUERTO_RED);
	} 
} 