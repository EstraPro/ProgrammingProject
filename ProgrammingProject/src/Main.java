
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
	
	public static void main(String[] args) {
	   
		Network n1 = new Network();
		String fileName = "files/peopleG612056.txt";	//**Must be able to load several people files!!

		try {
	         //BufferedReader creation
	         BufferedReader br = new BufferedReader(new FileReader(fileName));
	         //Leer las 2 primeras línea, guardando en un String
	         String texto = br.readLine();
	         texto = br.readLine();
	         //Repetir mientras no se llegue al final del fichero
	        
	         String p1= new String(texto);
	         
	         //Leer la siguiente línea
             texto = br.readLine();
             
             String p2= new String(texto);
             
             //Leer la siguiente línea
             texto = br.readLine();
             
             String p3= new String(texto);
             
             //Leer la siguiente línea
             texto = br.readLine();
             
             String p4= new String(texto);
             
             System.out.println(p1);
             System.out.println(p2);
             n1.addPeople(p1);
             
	         while(texto != null)
	         {
	        	 
	         }
	         
		}
		catch (FileNotFoundException a) {
	          System.out.println("Error: Fichero no encontrado");
	          System.out.println(a.getMessage());
	          }
	    catch(Exception e) {
	          System.out.println("Error de lectura del fichero");
	          System.out.println(e.getMessage());
	    	  }
	}
}
