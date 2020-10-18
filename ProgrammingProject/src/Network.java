import java.util.ArrayList;

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
	 */
	public void printNetwork() {

		System.out.println("\nThe users: \n");
		
		for (People p : peopleList) {	//Iterate all the people list
			
			System.out.println(p.toString());	//Print out each person
		}
		
		System.out.println("\nThe relationships: \n");
		
		for (String r : relationList) {	// Iterate all the relationships list
			
			System.out.println(r);	//Print out each relationship
		}
	}

	/**
	 * Method that adds a relation to the network
	 * @param r the relation
	 */
	public void addRelation(String r) {
		
		relationList.add(r);	//Add the relation
	}

}