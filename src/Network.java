import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that represents a network
 * 
 * @author ZSJ
 */
public class Network {

	public ArrayList<People> peopleList = new ArrayList<>(); // ArrayList for people
	public ArrayList<String> relationList = new ArrayList<>(); // ArrayList for relationships
	public ArrayList<ArrayList<People>> filmList = new ArrayList<>(); // ArrayList of arraylist that contains people
																		// that like the same sort of movies
	public Stack<People> aP = new Stack<People>();// Longest path
	public ArrayList<Stack<People>> allPaths = new ArrayList<Stack<People>>();
	public Graph G1;
	public int[][] AdjM;
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

	/**
	 * Method that print people if home town of identifier given is the same of
	 * people birthplace
	 * 
	 * @param identifier
	 */
	public void homeTownMatchBirthPlacePeople(String identifier) {

		int i = 0;
		boolean found = false;
		while (i < peopleList.size() && !found) {

			if (peopleList.get(i).getId().equals(identifier)) {

				found = true;
				for (People p2 : peopleList) {

					if (p2.getBirthPlace().equals(peopleList.get(i).getHome())
							&& peopleList.get(i).getId() != p2.getId()) {
						System.out.println(p2.toString());
					}
				}
			}
			i++;
		}
	}

	/**
	 * Method that groups people by the films they like
	 * 
	 * @param pIn
	 */
	public void matchFilms(People pIn) {

		boolean match = true;
		String films = pIn.getFilms(); // String with all favorite films

		String[] filmsIn = films.split(";"); // String array of the films

		for (People pTheOther : peopleList) { // For each other person

			if (!pTheOther.getId().equals(pIn.getId())) { // IFF they are not the same person

				String films2 = pTheOther.getFilms(); // String with the films of the other

				String[] filmsOther = films2.split(";"); // String array of the films
				ArrayList<String> filmsOtherAL = new ArrayList<String>(); // Auxiliary ArrayList

				for (String s : filmsOther) { // Convert from String[] to ArrayList
					filmsOtherAL.add(s); // to be able to use .contains()
				}

				for (String s : filmsIn) { // For each film

					if (!filmsOtherAL.contains(s)) // If one film does not coincide, they do not match
						match = false;
				}
				if (match) { // If the films match, add both people of the list

					filmList.add(new ArrayList<People>());
					filmList.get(i).add(pIn);
					filmList.get(i).add(pTheOther);
					i++; // Keep track of the main array index
				}
				match = true; // Reset the value
			}
		}
	}

	/*
	 * Method that retrieves the adyancency matrix that represent the graph of the
	 * relationList that has already been uploaded to the network.
	 */
	public int[][] ListToMatrix(int MAX) {

		int[][] M1 = new int[MAX][MAX];
		System.out.println(MAX);

		for (int x = 0; x < M1.length; x++) {
			for (int y = 0; y < M1.length; y++) {

				M1[x][y] = 0;
			}
		}

		for (int i = 0; i < peopleList.size(); i++) {

			String ID1 = peopleList.get(i).getId();

			for (int j = 0; j < peopleList.size(); j++) {

				String ID2 = peopleList.get(j).getId();

				for (int z = 0; z < relationList.size(); z++) {

					String Total = ID1 + "," + ID2;

					if (Total.equals(relationList.get(z))) {

						M1[i][j] = 1;
					}
				}
			}
		}
		return M1;
	}

	/**
	 * Create a graph from a matrix
	 * 
	 * @param M1
	 */
	public void matrixToGraph(int[][] M1) {

		G1 = new Graph(peopleList);

		for (int i = 0; i < M1.length; i++) {

			for (int j = 0; j < M1.length; j++) {

				if (M1[i][j] == 1) {

					G1.addEdge(peopleList.get(i), peopleList.get(j));
				}
			}
		}
	}

	public Stack<People> findLongPath(Graph G, People r, People d) {
		Boolean pP = false;
		int b = 0;
		Stack<People> StackEmaitza = new Stack<People>();

		for (@SuppressWarnings("unused") Stack<People> p1 : this.allPaths) {
			p1 = new Stack<People>();
		}

		try {

			BreathFirstPaths B1 = new BreathFirstPaths(G, r);
			if (B1.hasPathTo(G, d)) {
				System.out.println("");
				System.out.println("There is a possible path");
				pP = true;
			}

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		if (pP == true) {

			aP.push(r);

			for (People p1 : G.adjacentsToV(r)) {

				if (!aP.contains(p1)) {

					aP.push(p1);
					iterativeLP(G, p1, d);
				}
			}
			aP.pop();

			// =================================================================

			// System.out.println("");
			// System.out.println("those are all posible paths and them sizes:");
			// System.out.println("");

			for (Stack<People> s2 : allPaths) {
				// System.out.println(s2.toString());
				// System.out.println(s2.size());

				int a = s2.size();

				if (a > b) {

					b = a;
					StackEmaitza = s2;
				}
			}
			return StackEmaitza;
		}
		else {

			System.out.println("No posible path found");
			return null;
		}
	}

	// ==========================================================================

	public void iterativeLP(Graph G, People t, People d) {

		if (t.getId().equals(d.getId())) {

			Stack<People> newPath = new Stack<People>();
			newPath.addAll(aP);
			allPaths.add(newPath);

		} else {

			for (People p2 : G.adjacentsToV(t)) {

				if (!aP.contains(p2)) {

					aP.push(p2);
					iterativeLP(G, p2, d);
				}
			}
		}
		aP.pop();
	}
}