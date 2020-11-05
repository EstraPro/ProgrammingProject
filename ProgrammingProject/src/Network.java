import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Class that represents a network
 * 
 * @author ZSJ
 */
public class Network {

	public ArrayList<People> peopleList = new ArrayList<>(); // ArrayList for people
	public ArrayList<String> relationList = new ArrayList<>(); // ArrayList for relationships
	public ArrayList<ArrayList<String>> filmList = new ArrayList<>();
	int i = 0;
	/**
	 * Method that adds a person given his line of info
	 * 
	 * @param info The string containing all the info
	 */
	public void addPeople(String info) {

		String[] atributes = info.split(","); // Separate all the attributes
		People p = new People(); // Create a new person that will be added to the list

		p.setId(atributes[0]); // Set each attribute to the corresponding place
		p.setName(atributes[1]); // ...
		p.setLastName(atributes[2]); // ...
		p.setBirthDate(atributes[3]);
		p.setGender(atributes[4]);
		p.setBirthPlace(atributes[5]);
		p.setHome(atributes[6]);
		p.setStudiedAt(atributes[7]);
		p.setWorkPlaces(atributes[8]);
		p.setFilms(atributes[9]);
		p.setGroupCode(atributes[10]);

		peopleList.add(p); // Finally add the person
	}

	/**
	 * Method that print all people and relationships in the network
	 * 
	 * @throws FileNotFoundException
	 */
	public void printNetwork(String s1) throws FileNotFoundException {

		System.out.println("\nThe users: \n");
		String pathh = s1;
		File path1 = new File(pathh);
		PrintWriter O1 = new PrintWriter(path1);

		for (People p : peopleList) { // Iterate all the people list

			System.out.println(p.toString()); // Print out each person

			O1.println(p.toString());
		}

		System.out.println("\nThe relationships: \n");

		for (String r : relationList) { // Iterate all the relationships list

			System.out.println(r); // Print out each relationship

			O1.println(r);
		}

		O1.close();
	}

	/**
	 * Method that adds a relation to the network
	 * 
	 * @param r the relation
	 */
	public void addRelation(String r) {

		relationList.add(r); // Add the relation
	}

	/**
	 * Method that returns the surname of the given ID
	 * @param id
	 * @return
	 */
	public String getSurnFromId(String id) {

		String s = "";

		for (People p1 : peopleList) {

			if (id.equals(p1.getId())) {

				s = p1.getLastName();
			}
		}

		return s;
	}

	/**
	 * Method that prints the people between the given years
	 * @param d1
	 * @param d2
	 */
	public void printPeopleFromDates(String d1, String d2) {

		String year;
		Vector<String> vector = new Vector<String>();
		String wholeName;

		for (People p : peopleList) {

			String[] date = p.getBirthDate().split("-");
			year = date[2];

			int givenYear = Integer.parseInt(year);
			int bottomLimit = Integer.parseInt(d1);
			int upperLimit = Integer.parseInt(d2);

			if (bottomLimit <= givenYear && givenYear <= upperLimit) { 

				wholeName = p.getLastName() + " " + p.getName();

				vector.add(wholeName);
			}
		}

		Collections.sort(vector);
		System.out.println("\nPeople between these tow dates sorted by lexicographic order:");

		for (int i = 0; i < vector.size(); i++) {

			System.out.println("\n-" + i + " " + vector.get(i));
		}
	}

	/**
	 * Method that prints the friends of the given person's surname
	 * @param sur
	 */
	public void printRelationsFromSurname(String sur) {
		String id1, id2;
		String sur1, sur2;

		for (String s : relationList) {

			String[] pair = s.split(",");
			id1 = pair[0]; // The Id of the first person in the relationslist
			id2 = pair[1]; // The corresponding friend

			sur1 = getSurnFromId(id1);
			sur2 = getSurnFromId(id2);

			if (sur1.equals(sur)) {
				
				System.out.println("\n(" + id1 + ")");
				System.out.println("\n	Friend's ID: " + id2);
				System.out.println("	Friend's Lastname: " + sur2 + "\n");
			}
		}
	}

	/**
	 * Method that print people from the given birthplace
	 * @param place
	 */
	public void printPeopleFromBirthplace(String place) {

		for (People p : peopleList) {

			if (p.getBirthPlace().equals(place)) {

				if (p.getGender().equals("female")) {

					System.out.println("\nHer ID: " + p.getId());
					System.out.println("Her surname: " + p.getLastName() + "\n");
				} else {

					System.out.println("\nHis ID: " + p.getId());
					System.out.println("His surname: " + p.getLastName() + "\n");
				}
			}
		}
	}

	public void homeTownMatchBirthPlacePeople(String homeTown) {

		for (People p1 : peopleList) {

			if (p1.getBirthPlace().equals(homeTown)) {

				System.out.println(p1.toString());

			}

		}

	}
	
	public void matchFilms(People p1) {
		boolean match=true;
		String s = p1.getFilms();
		
		String[] pair = s.split(";");
		for(String s1: pair) {
			for(People p2: peopleList) {
				if(!p2.getId().equals(p1.getId())) {
					
					String s2 = p2.getFilms();
					
					String[] pair2 = s2.split(";");
					ArrayList<String> PairList = new ArrayList<String>();
					for(String s3: pair2) {
						PairList.add(s3);
					}
					
					if(!PairList.contains(s1)) {
						System.out.println("They don match");
						match=false;
					}
				}
				
				
			}
		}
		
		if (match) {
			System.out.println("they match");
			
			
		}
	}

}