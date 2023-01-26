import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Vista{
	
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
}
