/**
 * LABORATORIO DE PROGRAMACIÓN DE SISTEMAS 2012/2013
 * Ingeniería Técnica en Informática de Sistemas
 * Departamento de Ingeniería del Software e Inteligencia Artificial
 * Facultad de Informática, Universidad Complutense de Madrid
 *
 * TRES EN RAYA - VERSIÓN CLIENTE/SERVIDOR
 * Por Federico Peinado
 * 
 * Adaptación del conocido tutorial del libro 'Java How to program' de Deitel & Deitel
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
     * Este método crea el modelo del juego Tres en Raya en el servidor, 
     * crea la mitad del controlador correspondiente al lado del servidor (asociándolo al modelo),
     * y finalmente ejecuta el controlador pasándole las indicaciones necesarias para que haga la gestión por red.
     * Desde el punto de vista del servidor da comienzo el juego.
     * @param args Los argumentos que pasa el usuario al ejecutar la aplicación desde la consola (por ahora, simplemente los ignoramos)
     */
	public static void main(String args[]) {
		Modelo modelo = new Modelo();
	
	    // Usamos un controlador sencillo, que gestiona la comunicación por red con varios clientes con un sólo hilo (apoyándonos en que sabemos que los clientes son respetuosos y no mandan información cuando no les toca)
        MedioControlador controlador = new MedioControlador(modelo);
		
		// El servidor escucha en el puerto especificado
		controlador.ejecutar(PUERTO_RED);
	} 
} 