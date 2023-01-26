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
     * Este método crea la interfaz gráfica de usuario del juego en el cliente, 
     * crea la mitad del controlador correspondiente al lado del cliente (asociándolo a la vista),
     * y finalmente ejecuta el controlador pasándole las indicaciones necesarias para que haga la gestión por red.
     * Desde el punto de vista del cliente da comienzo el juego.
     * @param args Los argumentos que pasa el usuario al ejecutar la aplicación desde la consola (por ahora, simplemente los ignoramos)
     */
    public static void main(String args[]) {  
        Vista vista = new Vista(); 
        MedioControlador controlador = new MedioControlador(vista);
        // El cliente busca conectar con el servidor en la propia máquina, al puerto especificado
        controlador.ejecutar("localhost", PUERTO_RED); 
        //controlador.Cliente();
    } 
} 

