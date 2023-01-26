import java.io.*;
import java.net.*;

class Cliente {
	
	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	private ObjectInputStream entradaRed;
    private ObjectOutputStream salidaRed;
    private Vista vista;
	private Socket conexion;
    
    void intentaJugada() {      
        try {
            if (salidaRed != null) { // En realidad esto ya hemos controlado que no pueda ocurrir
                salidaRed.writeObject("Hola que tal?");
                salidaRed.flush();   
            }
        } catch (IOException e){
            System.err.println("ERROR: No se escribe la información que debe enviarse al servidor.");
            System.exit(1);
        }
  	}
    
	public Cliente( ) {
		vista = new Vista();
		try{
			conexion = new Socket( HOST , PUERTO );
			InputStream aux = conexion.getInputStream();
			//DataInputStream flujo = new DataInputStream( aux );
			entradaRed = new ObjectInputStream(aux);
            salidaRed = new ObjectOutputStream(conexion.getOutputStream());
			System.out.println( entradaRed.readObject() );
            Object e = entradaRed.readObject();
            //System.out.println( "Cliente " +  e);
            //String enviar = vista.leerTeclado("Di algo ");
            
            //salidaRed.writeObject("Hola desde cliente "+ e);
            //salidaRed.flush();
			//skCliente.close();
		} catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	void cerrarConexion() {
        try {
            // Se cierran los recursos en el orden contrario al que los hemos abierto
            // Técnicamente la vista se podría cerrar antes de haber si quiera ejecutado el controlador, de ahí las comprobaciones de NULL
            if (salidaRed != null)
                salidaRed.close();
            if (entradaRed != null)
                entradaRed.close();
            if (conexion != null)
                conexion.close();                  
        } catch ( IOException ioException ) {
            System.out.println("ERROR: No se ha podido cerrar la conexión con el cliente.");
            System.exit(1);
        }  
    }
	
	public static void main( String[] arg ) {
		Cliente cliente = new Cliente();
		cliente.intentaJugada();
		cliente.cerrarConexion();
	}
}
