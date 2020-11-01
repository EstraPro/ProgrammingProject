import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
			System.out.println("5. Who are his/her friends?\n");
			System.out.println("6. Who was born at that place?\n");
			System.out.println("7. Who was born between those two dates?\n");
			System.out.println("8. Who ");
			System.out.println("4. End and log out");
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

			case 5:
				WhoAreFriends();
				break;

			case 6:
				WhoWasBornThere();
				break;

			case 7:
				WhoWasBornThisYears();
				break;

			case 3:
				try {
					PrintOutEveryone();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;

			case 4:
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

	/**
	 * Method that prints out the people born between the two given dates by calling
	 * printPeopleFromDates()
	 */
	public static void WhoWasBornThisYears() {

		String y1, y2;

		System.out.println("Find people between this years:");
		
		System.out.println("\nFrom:");
		y1 = sc.nextLine(); // From year y1

		System.out.println("\nTo:");
		y2 = sc.nextLine(); // To year y2

		net.printPeopleFromDates(y1, y2); // Print out the people
	}

	/**
	 * Method that prints out the people born at that given place
	 */
	public static void WhoWasBornThere() {

		System.out.print("\nEnter the name of the place: \n");
		String place = sc.nextLine(); // Read user input

		net.printPeopleFromBirthplace(place); // Print people
	}

	/**
	 * Method that returns the friends of the given person
	 */
	public static void WhoAreFriends() {

		boolean found = false;

		System.out.print("\nEnter the SURNAME of the person you want to stalk: \n");
		String lastName = sc.nextLine(); 

		// /media/917790/E431-CEB4/UNI/ProgrammingProject/files/peopleG612051.txt

		System.out.println("The given surname is: " + lastName); 
		System.out.println("\nHis friends are:\n");

		for (People po : net.peopleList) {

			if (po.getLastName().equals(lastName) && found == false) {

				found = true;
				net.printRelationsFromSurname(lastName); //Print friends
			}
		}
	}

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

			System.out.println("\nUploading files:");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("done!\n");

			fileScan.close();

		} catch (Exception e) {
			System.out.println("Error in file reading!");
			System.out.println(e.getMessage());
		}
	}

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

			System.out.println("\nUploading files:");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("done!\n");

		} catch (Exception e) {
			System.out.println("Error in file reading!");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method that prints both people and relationships from the1 network
	 * 
	 * @throws FileNotFoundException
	 */
	public static void PrintOutEveryone() throws FileNotFoundException {

		/// users/917790/git/ProgrammingProject/ProgrammingProject/files/peopleG612051.txt
		System.out.print("\nEnter the directory of the file where you want to write: \n");
		String choice = sc.nextLine();

		System.out.println("---------------------------------");
		net.printNetwork(choice);
		System.out.println("\ndone\n");
		System.out.println("---------------------------------");

	}

	public static void Search() {

	}

}