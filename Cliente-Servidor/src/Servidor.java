import java.io.* ;
import java.net.* ;

class Servidor {
	
	static final int PUERTO = 5000;
	private ObjectInputStream entradaRed; 
    private ObjectOutputStream salidaRed;
    private Socket conexion;
	
    public void sacaTexto() throws ClassNotFoundException{
        try {
            System.out.println(entradaRed.readObject()); 
        } 
        catch (IOException ex) {
            System.err.println("ERROR: No se puede notificar el turno de un jugador.");
            System.exit(1);
        } 
    }
    
	public Servidor( ) {
		try {
			ServerSocket skServidor = new ServerSocket(PUERTO);
			System.out.println("Escucho el puerto " + PUERTO );
			for ( int numCli = 0; numCli < 3; numCli++ ) {
				conexion = skServidor.accept(); // Crea objeto
				System.out.println("Sirvo al cliente " + numCli);
				OutputStream aux = conexion.getOutputStream();
				//DataOutputStream flujo= new DataOutputStream( aux );
				//flujo.writeUTF( "Hola cliente " + numCli );
				salidaRed = new ObjectOutputStream(aux);
	            entradaRed = new ObjectInputStream(conexion.getInputStream());   
	            
	            salidaRed.writeObject("Cliente "+numCli); 
	            //System.out.println(entradaRed.readObject());
	            // 2. Enviar jugadores asignados a cada cliente 
	           // salidaRed.writeObject(numCli); 
	            salidaRed.flush();   
				//conexion.close();
	           // System.out.println(entradaRed.readObject());
			}
			System.out.println("Demasiados clientes por hoy");
		} catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
	}
	
	public void cerrarConexion() {
        try {
            // Se cierran los recursos en el orden contrario al que los hemos abierto
            entradaRed.close();
            salidaRed.close();
            conexion.close();                  
        } catch ( IOException ioException ) {
            System.out.println("ERROR: No se ha podido cerrar la conexión con el cliente.");
            System.exit(1);
        }  
    }
	
	public static void main( String[] arg ) throws ClassNotFoundException {
		Servidor servidor = new Servidor();
		for (int i=0;i<3;i++)
			servidor.sacaTexto();
		servidor.cerrarConexion();
	}
}