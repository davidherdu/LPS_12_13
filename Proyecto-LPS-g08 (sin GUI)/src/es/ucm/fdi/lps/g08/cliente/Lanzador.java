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
package es.ucm.fdi.lps.g08.cliente;

import static es.ucm.fdi.lps.g08.Constantes.*; 
import es.ucm.fdi.lps.g08.Vista;

/**
 * La clase que se ocupa de preparar y lanzar el cliente del juego. 
 * Es una clase pensada para no tener ejemplares (objetos).
 * @author Federico Peinado
 */
final class Lanzador { 

    // El constructor es privado para que no lo puedan usar otras clases.    
    private Lanzador() { 
        throw new AssertionError("No se permite crear ejemplares de esta clase.");
    }

    /**
     * Este m�todo crea la interfaz gr�fica de usuario del juego en el cliente, 
     * crea la mitad del controlador correspondiente al lado del cliente (asoci�ndolo a la vista),
     * y finalmente ejecuta el controlador pas�ndole las indicaciones necesarias para que haga la gesti�n por red.
     * Desde el punto de vista del cliente da comienzo el juego.
     * @param args Los argumentos que pasa el usuario al ejecutar la aplicaci�n desde la consola (por ahora, simplemente los ignoramos)
     */
    public static void main(String args[]) {  
        Vista vista = new Vista(); 
        MedioControlador controlador = new MedioControlador(vista);
        // El cliente busca conectar con el servidor en la propia m�quina, al puerto especificado
        controlador.ejecutar("localhost", PUERTO_RED); 
        //controlador.Cliente();
    } 
} 

