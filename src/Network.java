import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Class that represents a network
 * 
 * @author ZSJ
 */
public class Network {

	public ArrayList<People> peopleList = new ArrayList<>(); // ArrayList for people
	public ArrayList<String> relationList = new ArrayList<>(); // ArrayList for relationships
	public ArrayList<ArrayList<People>> filmList = new ArrayList<>();
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
	public void printNetwork(String s) throws FileNotFoundException {

		File path = new File(s);
		PrintWriter pw = new PrintWriter(path);

		for (People p : peopleList) { // Iterate all the people list

			pw.println(p.toString());
		}

		for (String r : relationList) { // Iterate all the relationships list

			pw.println(r);
		}

		System.out.println("\nprinting network to a file:");
		System.out.println("...");
		System.out.println("...");
		System.out.println("...");
		System.out.println("done!\n");

		pw.close();
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
	 * 
	 * @param id
	 * @return
	 */
	public String getSurnFromId(String id) {

		String s = "";

		for (People p : peopleList) {

			if (id.equals(p.getId())) {

				s = p.getLastName();
			}
		}

		return s;
	}

	/**
	 * Method that prints the people between the given years
	 * 
	 * @param d1
	 * @param d2
	 */
	public ArrayList<People> printPeopleFromDates(String d1, String d2) {

		String year;
		// <String> vector = new Vector<String>();
		ArrayList<People> peopleL = new ArrayList<People>();

		for (People p : peopleList) {

			String[] date = p.getBirthDate().split("-");
			year = date[2];

			int givenYear = Integer.parseInt(year);
			int bottomLimit = Integer.parseInt(d1);
			int upperLimit = Integer.parseInt(d2);

			if (bottomLimit <= givenYear && givenYear <= upperLimit) {

				peopleL.add(p);
			}
		}

		return peopleL;
	}

	/**
	 * Method that prints the friends of the given person's surname
	 * 
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
	 * 
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

		for (People p : peopleList) {

			if (p.getBirthPlace().equals(homeTown)) {

				System.out.println(p.toString());

			}

		}

	}

	/**
	 * Method that groups people by the films they like
	 * @param pIn
	 */
	public void matchFilms(People pIn) {

		boolean match = true;
		String films = pIn.getFilms(); 	//String with all favorite films

		String[] filmsIn = films.split(";");	//String array of the films

		for (People pTheOther : peopleList) {	//For each other person

			if (!pTheOther.getId().equals(pIn.getId())) {	//IFF they are not the same person

				String films2 = pTheOther.getFilms();	//String with the films of the other

				String[] filmsOther = films2.split(";");	//String array of the films
				ArrayList<String> filmsOtherAL = new ArrayList<String>();	//Auxiliary ArrayList

				for (String s : filmsOther) {	//Convert from String[] to ArrayList
					filmsOtherAL.add(s);		//to be able to use .contains()
				}

				for (String s : filmsIn) {				//For each film

					if (!filmsOtherAL.contains(s))	//If one film does not coincide, they do not match
						match = false;
				}

				if (match) {							//If the films match, add both people of the list
					
					filmList.add(new ArrayList<People>());
					
					filmList.get(i).add(pIn);
					filmList.get(i).add(pTheOther);		
					i++;								//Keep track of the main array index
				}

				match = true;		//Reset the value
			}

		}
	}

}