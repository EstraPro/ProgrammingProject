import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;


/**
 * Main class that executes the program
 * 
 * @author ZSJ
 */
public class Main {

	private static Network net = new Network(); // Network creation
	private static Scanner sc = new Scanner(System.in); // Main scanner for console

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		boolean exit = false;
		int sel;

		while (!exit) { // while not want to exit the program

			System.out.println("_______________------Menu------_______________\n");
			System.out.println("1. Load 'people' in the network\n");
			System.out.println("2. Load 'relationships' in the network\n");
			System.out.println("3. Print out the list of our network users\n");
			System.out.println("4. Who are his/her friends?\n");
			System.out.println("5. Who was born at that place?\n");
			System.out.println("6. Who was born between those two dates?\n");
			System.out.println("7. Who was born in town that is the same of hometown?\n");
			System.out.println("8. Sort people by movie collections\n");
			System.out.println("9. Create the Adjacency matrix and graph that represent the friendList on the network\n");
			System.out.println("10. Find the Longest path btween to Persons\n");
			System.out.println("11. Find the Shortest path btween to Persons\n");
			System.out.println("12. Find all cliques in the network\n");
			System.out.println("13. End and log out");
			System.out.println("______________________________________________\n");
			System.out.println("Your election:");
			sel = sc.nextInt(); // save selection
			sc.nextLine();

			switch (sel) { // Depending on the option selected

			case 1:
				LoadPeopleToNetwork();
				break;

			case 2:
				LoadRelationshipToNetwork();
				break;

			case 3:
				try {
					PrintOutEveryone();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			
			case 4:
				WhoAreFriends();
				break;

			case 5:
				WhoWasBornThere();
				break;

			case 6:
				WhoWasBornThisYears();
				break;

			case 7:
				
				WhoWasBornSameHomeTown();
				break;
			
			case 8:
				sortForMovies();
				break;
				
			case 9:
				createAdjacencyMatrixandGraph();
				break;
			
			case 10:
				longestPath();
				break;
				
			case 11:
				shortestPath();
				break;
				
			case 12:
				cliques();
				break;

			case 13:
				exit = true; // Exit the program
				System.out.println("----------You have loged out----------");
				break;

			}

			if (!exit) { // Only if exit is not selected
				System.out.println("---------------------------------");
				System.out.println("Do you want to continue? (1)YES / (2)NO");
				System.out.println("---------------------------------");

				if (sc.nextInt() == 2) { // If option NO selected
					exit = true;
					System.out.println("----------You have loged out----------");
				}
				sc.nextLine();
			}
		}

		sc.close();
	}
	
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	
	/**
	 * Method that sorts the people in the network for the movies they like
	 */
	public static void sortForMovies() {

		for (People p1 : net.peopleList) {
			net.matchFilms(p1);
		}

		for (People p1 : net.peopleList) {

			int kont = 0;

			for (int i = 0; i < net.filmList.size(); i++) {

				if (net.filmList.get(i).contains(p1)) {

					kont++;

					if (kont > 1)net.filmList.get(i).remove(net.filmList.get(i).indexOf(p1));
				}
			}
		}

		for (ArrayList<People> L1 : net.filmList) {

			if (L1.size() != 0) {
				System.out.println(L1.get(0).getFilms());
				//System.out.println(L1.toString());
				
				for(People p: L1) {
					
					System.out.println(p.getId());
					
				}
				
				System.out.println("");
			}
		}

	}

	
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	
	/**
	 * Method that retrieves all the people born in the same home town
	 * of the person you choose
	 */
	private static void WhoWasBornSameHomeTown() {
		
		String path = "files/residential.txt";
		
		try {
			Scanner scan = new Scanner(new FileReader(path));
			
			while(scan.hasNext()) {
				
				String identifier = scan.nextLine();
								
				net.homeTownMatchBirthPlacePeople(identifier);
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("Error in file reading!");
			e.printStackTrace();
		}
		
	}
	
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	

	/**
	 * Method that prints out the people born between the two given dates by calling
	 * printPeopleFromDates()
	 */
	public static void WhoWasBornThisYears() {

		ArrayList<People> al = new ArrayList<People>();
		String y1, y2;

		System.out.println("Find people between this years:");

		System.out.println("\nFrom:");
		y1 = sc.nextLine(); // From year y1

		System.out.println("\nTo:");
		y2 = sc.nextLine(); // To year y2

		System.out.println("\n---The people born between these dates---\n");
		
		for (People p : net.printPeopleFromDates(y1, y2)) {
			
			System.out.println(p.toString());
			al.add(p);
		}

		System.out.println("\n---Same people but sorted by birthplace, surname and name---\n");
		Collections.sort(al, new PeopleChainedComparator(new BirthPlaceComparator(), new SurnameComparator(), new NameComparator()));

		for (People p1 : al) {

			System.out.println(p1.toString());

		}
	}


	
	
	//==================================================================	
	//==================================================================		
	//==================================================================

	/**
	 * Method that prints out the people born at that given place
	 */
	public static void WhoWasBornThere() {

		System.out.print("\nEnter the name of the place: \n");
		String place = sc.nextLine(); // Read user input

		net.printPeopleFromBirthplace(place); // Print people

	}
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================

	/**
	 * Method that returns the friends of the given person
	 */
	public static void WhoAreFriends() {

		boolean found = false;

		System.out.print("\nEnter the SURNAME of the person you want to stalk: \n");
		String lastName = sc.nextLine();

		// /media/917790/E431-CEB4/UNI/ProgrammingProject/files/peopleG612051.txt

		System.out.println("The given surname is: " + lastName);
		System.out.println("\nHis/Her friends are:\n");

		for (People p : net.peopleList) {

			if (p.getLastName().equals(lastName) && found == false) {

				found = true;
				net.printRelationsFromSurname(lastName); // Print friends
			}
		}
	}

	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	
	/**
	 * Method that loads all the people from the given file to the network
	 */
	public static void LoadPeopleToNetwork() {

		System.out.print("\nEnter the directory of the file: \n");
		String fileName = sc.nextLine(); // Directory of the file

		try {

			Scanner fileScan = new Scanner(new FileReader(fileName)); // create the fileReader
			fileScan.nextLine(); // Ignore the first line

			while (fileScan.hasNext()) { // While not EOF

				String p = fileScan.nextLine(); // Scan each person info
				net.addPeople(p); // Add the person to the network
			}

			System.out.println("\nUploading files: \n...\n...\n...\nDone!\n");

			fileScan.close();

		} catch (Exception e) {
			System.out.println("Error in file reading!");
			System.out.println(e.getMessage());
		}
	}
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	

	/**
	 * Method that load relations of the given file to the network
	 */
	public static void LoadRelationshipToNetwork() {

		System.out.print("\nEnter the directory of the file: \n");
		String fileName = sc.nextLine(); // Directory of the file

		try {
			
			Scanner fileScan = new Scanner(new FileReader(fileName));
			fileScan.nextLine(); // Ignore first line

			while (fileScan.hasNext()) { // While not EOF

				String r = fileScan.nextLine(); // Scan each relationship
				net.addRelation(r); // Add the relationship to the network
			}

			for(String s1: net.relationList) {
				System.out.println(s1);
			}
			
			Collections.sort(net.relationList);
			
			System.out.println("\nUploading files: \n...\n...\n...\nDone!\n");

			for(String s1: net.relationList) {
				System.out.println(s1);
			}
			
		} catch (Exception e) {
			System.out.println("Error in file reading!");
			System.out.println(e.getMessage());
		}
		
	}

	//==================================================================	
	//==================================================================		
	//==================================================================
	
	/**
	 * Method that prints both people and relationships from the1 network
	 * 
	 * @throws FileNotFoundException
	 */
	public static void PrintOutEveryone() throws FileNotFoundException {
		System.out.println("\nDo you want to print the network on a file or just in the console?");
		System.out.println("[1] Print on the console \n[2] Print on a file\n");
		System.out.println("Enter your choice:");
		int sel = sc.nextInt(); // save selection
		sc.nextLine();

		if (sel == 2) {
			System.out.print("\nEnter the directory of the file where you want to write: \n");
			String choice = sc.nextLine();

			System.out.println("---------------------------------");
			net.printNetwork(choice);
			System.out.println("\nDone\n");
			System.out.println("---------------------------------");
		} else {
			System.out.println("");
			for (People p : net.peopleList) {
				System.out.println(p.toString());
			}
			System.out.println("");
			for (String s : net.relationList) {
				System.out.println(s);
			}
		}
	}

	//==================================================================	
	//==================================================================		
	//==================================================================
	
	/**
	 * Method that retrieves the longest path between two persons
	 */
	public static void longestPath() {
		int sell;

		boolean done=false;
		
		while(!done) {
			
			System.out.println("\nFor finding the cliques in the graph you must have previously");
			System.out.println("\ncreated the graph representation of the network");
			System.out.println("\nDo you want to create it?");
			System.out.println("\n  Yes [1]");
			System.out.println("\n  No (I have previously created it) [2]");
			System.out.println("");
			System.out.println("\nEnter your choice:");
			
			sell = sc.nextInt(); // save selection
			sc.nextLine();
			switch (sell) { // Depending on the option selected

			case 1:
				createAdjacencyMatrixandGraph();
				break;

			case 2:
				done = true;
				break;
			
			}
			
			System.out.println("___________________________");
			System.out.println("");
			
		}
		
		System.out.println("From (first person's ID):");
		String sel = sc.nextLine(); // save selection
		System.out.println("To (second person's ID):");
		String sel2 = sc.nextLine(); // save selection
		
		People M=new People();
		People I= new People();
		
		
		for(People p1: net.peopleList) {
			if(sel.equals(p1.getId())) {
				M=p1;
			}
			
			if(sel2.equals(p1.getId())) {
				I=p1;
			}
		}
		
		Stack<People> s1 = net.findLongPath(net.G1, M, I);
	
		System.out.println("");
		System.out.println("This is one of the possible longest path between "+ M.getId() +" and "+ I.getId());
		System.out.println("The size of the path is:" + s1.size());
		System.out.println("and that path is:\n");
		for(People p1: s1) {
			
			System.out.println(p1.getId());
			
		}
		
				
		
	}
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	public static void shortestPath(){
		int sell;

		boolean done=false;
		
		while(!done) {
			
			System.out.println("\nFor finding the cliques in the graph you must have previously");
			System.out.println("\ncreated the graph representation of the network");
			System.out.println("\nDo you want to create it?");
			System.out.println("\n  Yes [1]");
			System.out.println("\n  No (I have previously created it) [2]");
			System.out.println("");
			System.out.println("\nEnter your choice:");
			
			sell = sc.nextInt(); // save selection
			sc.nextLine();
			switch (sell) { // Depending on the option selected

			case 1:
				createAdjacencyMatrixandGraph();
				break;

			case 2:
				done = true;
				break;
			
			}
			
			System.out.println("___________________________");
			System.out.println("");
			
		}
		
		System.out.println("From (first person's ID):");
		String sel = sc.nextLine(); // save selection
		System.out.println("To (second person's ID)");
		String sel2 = sc.nextLine(); // save selection
		
		People M=new People();
		People I= new People();
		
		for(People p1: net.peopleList) {
			if(sel.equals(p1.getId())) {
				M=p1;
			}
			
			if(sel2.equals(p1.getId())) {
				I=p1;
			}
		}
		
		try {
			BreathFirstPaths B1 = new BreathFirstPaths(net.G1, M);
			if(B1.hasPathTo(net.G1, I)) {

				System.out.println("This is one of the possible shortest path between" + " " + M.getId() + " and " + I.getId());
				System.out.println("");
				System.out.println("The size of the path is:" + B1.distTo(net.G1, I));
				System.out.println("and that path is:\n");
				
				Stack<People> L1 =  B1.pathTo(net.G1, I);
				
				for(People p1: L1) {
					
					System.out.println(p1.getId());
				}
				
				L1= new Stack<People>();
			}
			else {
				System.out.println("There is no path");

			}
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	
	public static void cliques() {
		int sel;

		boolean done=false;
		
		while(!done) {
			
			System.out.println("\nFor finding the cliques in the graph you must have previously");
			System.out.println("\ncreated the graph representation of the network");
			System.out.println("\nDo you want to create it?");
			System.out.println("\n  Yes [1]");
			System.out.println("\n  No (I have previously created it) [2]");
			System.out.println("");
			System.out.println("\nEnter your choice:");
			
			sel = sc.nextInt(); // save selection
			sc.nextLine();
			switch (sel) { // Depending on the option selected

			case 1:
				createAdjacencyMatrixandGraph();
				break;

			case 2:
				done = true;
				break;
			
			}
			
			System.out.println("___________________________");
			System.out.println("");
			
		}
		
		
	//=====================================================
		
		ArrayList<Stack<People>> allP = new ArrayList<Stack<People>>();
		
		for(@SuppressWarnings("unused") Stack<People> L: allP) {
			L= new Stack<People>();
		}
	
	//=========For each node in the graph==================	
		
		for(People p1: net.peopleList) {
				
			Cliques C1 = new Cliques();
	
			C1.fCliques(net.G1, p1);
		
	//=======For each Cycle this node may have=============
			
			for(Stack<People> s: C1.allPaths) {
				
				s.pop();//take out the repeated one					
				
	//==========Probe if the cycle is a clique=============
				
				boolean b1= false;
				
	//========If the cycle is a clique, save it===========
				
				if(fullyConnect(s)) {
				
					b1= true;
					
					}
				
				if(b1) {
					
					if(!check(allP, s)) {
						cpSolution(s, allP);
					}
					
				}
	//====================================================
		
			}
		
		}
		
		//print all the cliques that have been found in the network:
		
		System.out.println("These are all the cliques:");
		System.out.println("");
		
		for(Stack<People> s: allP) {
		
			for(People p1: s) {
				
				System.out.println(p1.getId());
				
			}
			
			System.out.println("");
			
		}
		
		
	}
	
	/**
	 * Method to store the solution in the solution array
	 * @param s
	 * @param allP
	 */
	public static void cpSolution(Stack<People> s, ArrayList<Stack<People>> allP) {

		Stack<People> Path = new Stack<People>();
		
		Path.addAll(s);
						
		allP.add(Path);
	}
	
	/**
	 * Method to prove if the cycle is fully connected
	 * @param s
	 * @return
	 */
	public static boolean fullyConnect(Stack<People> s) {
		
		boolean b1 = true;
		
		for(People p: s) {
			
			int i = net.G1.getList().indexOf(p);
			
			for(People p2: s) {
				
				int j = net.G1.getList().indexOf(p2);
				
				if(i!=j) {
					
					if(!net.G1.adj[i].contains(p2)) {
					
						b1=false;
					
					}
				}
			}
			
		}
		
		return b1;
	}
	
	
	/**
	 * This method returns true if the Stack that is going to be stored is already in the Array of 
	 * solutions but in another order
	 * 
	 * @param Al
	 * @param s
	 * @return
	 */
	public static boolean check(ArrayList<Stack<People>> Al, Stack<People> s) {
		boolean em = false;
		
		if(Al.isEmpty()) {
			em=false;
		}else{
			for(Stack<People> s1: Al) {
			
				if(s1.containsAll(s) && s1.size()==s.size()) {
					em=true;
				}
			
			}
		}
		return em;
	}
	
	
	//==================================================================	
	//==================================================================		
	//==================================================================
	
	/**
	 * method to create an Adjacency matrix and a graph from a Adjacency matrix
	 */
	public static void createAdjacencyMatrixandGraph() {
		int sel;
		int sel2;
		int sel3;

		boolean done=false;
		
		
		while(!done) {
			System.out.println("\nFor creating the Adjacency matrix and graph that represent the the network, you must have");
			System.out.println("\npreviously uploaded both friend and people list");
			System.out.println("\nif you want to upload people list press [1]");
			System.out.println("\nif you want to upload friends list press [2]");
			System.out.println("\nif you want to skip [3]");
			System.out.println("\nEnter your choice:");
			
			System.out.println("\nYour election:");
			sel = sc.nextInt(); // save selection
			sc.nextLine();
			switch (sel) { // Depending on the option selected

			case 1:
				LoadPeopleToNetwork();
				break;

			case 2:
				LoadRelationshipToNetwork();
				break;
			
			case 3:
				done=true;
			}
			
			System.out.println("___________________________");
			System.out.println("\nHave you finished?");
			System.out.println("\nyes[1]		No[2]");
			sel2 = sc.nextInt(); // save selection
			if(sel2==1) {
				done=true;				
			}
		}
		
			System.out.println("___________________________");
			System.out.println("\nnow we are going to create the adjacency matrix of the graph that represents the network");
			net.AdjM = net.ListToMatrix(net.peopleList.size());
			System.out.println("\nCreating the matrix:\n...\n...\n...\nAlmost done... \n...\n...\n...\nDone!\n");
			
			
			System.out.println("\nand now, the graph that represents the network");
			net.matrixToGraph(net.AdjM);
			System.out.println("\nCreating the matrix:\n...\n...\n...\nAlmost done... \n...\n...\n...\nDone!\n");
			
			System.out.println("\nDo you want to see the adjancency matrix and the graph?");
			System.out.println("\nyes[1]		No[2]");
			sel3 = sc.nextInt(); // save selection
			
			
			if(sel3==1) {

				//print the matrix
				for (int x = 0; x < net.AdjM.length; x++) {
					
					for (int y = 0; y < net.AdjM[x].length; y++) {
						
						System.out.print(net.AdjM[x][y] + " ");
				
					}
					
					System.out.println();
				}				
			
			System.out.println("");
			System.out.println("==========================================");
			System.out.println("");
			
			int a1 = net.peopleList.size();
			for (int i = 0; i < a1; i++) {
				People v = net.peopleList.get(i);
				
				System.out.println(v.getId() +"'s friends");
				for (People p1 : net.G1.adjacentsToV(v)) {
					
					System.out.println(p1.getId());
				}
				System.out.println("");
				
			}
			
			}
	}

  }