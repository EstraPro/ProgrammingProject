
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
	    network n1= new network();
		
		int i=0;
		  String nombreFichero = "C:\\Users\\aritz\\Desktop\\UNI\\2\\DSA\\peopleG612056.txt";
	      //Declarar una variable BufferedReader
		  BufferedReader br = null; 
	      try {
	         //Crear un objeto BufferedReader al que se le pasa 
	         //   un objeto FileReader con el nombre del fichero
	         br = new BufferedReader(new FileReader(nombreFichero));
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
	      finally {
	          try {
	              if(br != null)
	                  br.close();
	          }
	          catch (Exception e) {
	              System.out.println("Error al cerrar el fichero");
	              System.out.println(e.getMessage());
	          }
	      }

	}
}
