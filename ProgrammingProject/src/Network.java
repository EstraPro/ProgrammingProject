import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Class that represents a network
 * @author ZSJ
 */
public class Network {

	public ArrayList<People> peopleList = new ArrayList<>();	//ArrayList for people
	public ArrayList<String> relationList = new ArrayList<>();	//ArrayList for relationships

	/**
	 * Method that adds a person given his line of info 
	 * @param info The string containing all the info
	 */
	public void addPeople(String info) {
		
		String[] atributes = info.split(",");	//Separate all the attributes
		People p = new People();	//Create a new person that will be added to the list

		p.setId(atributes[0]);			//Set each attribute to the corresponding place
		p.setName(atributes[1]);		//...
		p.setLastName(atributes[2]);	//...
		p.setBirthDate(atributes[3]);
		p.setGender(atributes[4]);
		p.setBirthPlace(atributes[5]);
		p.setHome(atributes[6]);
		p.setStudiedAt(atributes[7]);
		p.setWorkPlaces(atributes[8]);
		p.setFilms(atributes[9]);
		p.setGroupCode(atributes[10]);

		peopleList.add(p);	//Finally add the person
	}

	/**
	 * Method that print all people and
	 * relationships in the network
	 * @throws FileNotFoundException 
	 */
	public void printNetwork(String s1) throws FileNotFoundException {

		System.out.println("\nThe users: \n");
		String pathh = s1;
		File path1 = new File(pathh);
		PrintWriter O1= new PrintWriter (path1);
		
		for (People p : peopleList) {	//Iterate all the people list
			
			System.out.println(p.toString());	//Print out each person
			
			O1.println(p.toString());
		}
		
		
		
		System.out.println("\nThe relationships: \n");
		
		for (String r : relationList) {	// Iterate all the relationships list
			
			System.out.println(r);	//Print out each relationship
			
			O1.println(r);
		}
		
		O1.close();
	}

	/**
	 * Method that adds a relation to the network
	 * @param r the relation
	 */
	public void addRelation(String r) {
		
		relationList.add(r);	//Add the relation
	}
	
	
	public String getSurnfromId(String S1E) {
		
		String mySurn ="";
		
		for(People p1: peopleList) {
			
			if(S1E.equals(p1.getId())) {
			
				mySurn=p1.getLastName();
			}
		}
		
		return mySurn;
	}
	
	
	@SuppressWarnings("null")
	public void returnPeoplefromDatas(String d1, String d2) {
		String Date="";
		String day,month,year="";
		Vector<String> vector = new Vector<String>();
		String nameandLast="";

		int i = 0;
		
		for(People p1: peopleList) {
			
			Date=p1.getBirthDate();
			
			String[] b1= Date.split("-");
			day= b1[0]; 
			month= b1[1]; 
			year= b1[2];
			System.out.println(day+" "+month+" "+year);
			int i0 = Integer. parseInt(year);
			int i1 = Integer. parseInt(d1);
			int i2 = Integer. parseInt(d2);
				
			System.out.println(i0+ " " + i1 + " " + i2);
			
			if (i1<=i0 && i0>i2) { //this if dosn't work 
				
				nameandLast=p1.getLastName()+""+p1.getName();
				
				vector.add(nameandLast);
				
				System.out.println(nameandLast);
				System.out.println("a");
			}
			
			i++;
		}
		
		Collections.sort(vector);
		System.out.println("");
		System.out.println("people between these tow dates sorted by lexicographic order:");
	    
		for(int y=0; y < vector.size(); y++){
	        System.out.println("");
			System.out.println("-" + y + " " + vector.get(y));
		}
	}
	
	
	public void returnRelationsFromSurname(String sur) {
		String S1E1=null;
		String S1E2=null;
		String LastName = null;
		String AiD = null;
		
		String a1="";
		String a2="";
		
				for (String s1: relationList) {
			
					String[] b1= s1.split(",");
					S1E1= b1[0]; // The Id of the first person in the Relationslist
					S1E2= b1[1]; // his friend
					
					a1=getSurnfromId(S1E1);
					//System.out.println(S1E1);
					//System.out.println(a1);
			
					a2=getSurnfromId(S1E2);
					
					//System.out.println(a2);
				
					
					if(a1.equals(sur)) {
						System.out.println("");
						System.out.println("("+ S1E1 +")");
						System.out.println("");
						System.out.println("	friend's ID:" + " " + S1E2 );
						System.out.println("	friend's Lastname:" + " " + a2);
					}
			
		}
	}
	
	
	
	public void returnIdaAndLastNameFromBirthplace(String r) {
		String r1 = "female";
		for (People p1: peopleList) {
			
			if(p1.getBirthPlace().equals(r)) {
				
				System.out.println("");
				
				if (p1.getGender().equals(r1)) {
				System.out.println("this is her ID:" + " " + p1.getId());
				System.out.println("this is her LastName:" + " " +p1.getLastName());
				System.out.println("");
				}else
				System.out.println("this is his ID:" + " " + p1.getId());
				System.out.println("this is his LastName:" + " " +p1.getLastName());
				System.out.println("");
			}
		}
	}

}