
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
	   
		Network n1 = new Network();
		String fileName = "files/peopleG612056.txt";	//**Must be able to load several people files!!

		try {
	         //Scanner creation
	         Scanner scan = new Scanner(new FileReader(fileName));
	         //Leer las 2 primeras línea, guardando en un String
	         scan.readLine();
	         texto = br.readLine();
	         //Repetir mientras no se llegue al final del fichero
	        
	         while(scan.hasNext()){
	        	 
	        	 String p= new String(texto);
	        	 n1.addPeople(p);
	        	 System.out.println(p.toString());
	         }      
	         
		}
		/*catch (FileNotFoundException a) {
	          System.out.println("Error: Fichero no encontrado");
	          System.out.println(a.getMessage());
	          }
	    catch(Exception e) {
	          System.out.println("Error de lectura del fichero");
	          System.out.println(e.getMessage());
	    	  }*/
	}
}
