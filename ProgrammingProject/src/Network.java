
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Network {
	
	public ArrayList<People> s1 = new ArrayList<>();
	public ArrayList<String> relations = new ArrayList<String>();
	
	
	public void addPeople(String n1) {
		People p1 = new People();
		String[] atributes= n1.split(",");	
		
		p1.setId(atributes[0]);
		p1.setName(atributes[1]);
		p1.setLastName(atributes[2]);
		p1.setBirthDate(atributes[3]);
		p1.setGender(atributes[4]);
		p1.setBirthPlace(atributes[5]);
		p1.setHome(atributes[6]);
		p1.setStudiedAt(atributes[7]);
		p1.setWorkPlaces(atributes[8]);
		p1.setFilms(atributes[9]);
		p1.setGroupCode(atributes[10]);
		
		s1.add(p1);
	}
	
	public void printNetworkToFile() {
		
		try {
			FileWriter wr = new FileWriter("newPeople.txt");
			
			for (People p : s1) {
				
				wr.write(p.toString());	//Look if writes new line
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addRelation() {
		
		File fr = new File("friends.txt");
		try {
			Scanner sc = new Scanner(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}