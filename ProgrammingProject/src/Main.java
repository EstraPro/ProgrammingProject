import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Main class that executes the program
 * @author ZSJ
 */
public class Main {

	private static Network net = new Network();				//Network creation
	private static Scanner sc = new Scanner(System.in);		//Main scanner for console

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {

		int sel;
		boolean exit = false;

		while (!exit) {		//while not want to exit the program
			
			System.out.println("_______________------Menu------_______________\n");
			System.out.println("1. Load 'people' in the network\n");
			System.out.println("2. Load 'relationships' in the network\n");
			System.out.println("3. Print out the list of our network users\n");
			System.out.println("5. Who are his friends?\n");
			System.out.println("6. Who was born at that palce?\n");
			System.out.println("7. Who was born between those two dates?\n");
			System.out.println("8. Who ");
			System.out.println("4. End and log out");
			System.out.println("______________________________________________\n");
			System.out.println("Your election:");
			sel = sc.nextInt();	//save selection
			sc.nextLine();
			
			switch (sel) {	//Depending on the option selected
			
				case 1:	LoadPeopleToNetwork();
				break;

				case 2:	LoadRelationshipToNetwork();
				break;
				
				case 5: WhoareHisFriends();	//Exit the program
				
				break;
				
				case 6: WhoWasBronThere();	//Exit the program
				
				break;
				
				case 7: WhowasBornAtThisData();	//Exit the program
				
				break;

				case 3:	try {
					PrintOutEveryone();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

				case 4:	exit = true;	//Exit the program
						System.out.println("----------You have loged out----------");
				break;
					
			}

			if (!exit) {	//Only if exit is not selected
				System.out.println("---------------------------------");
				System.out.println("Do you want to continue? (1)YES / (2)NO");
				System.out.println("---------------------------------");
				
				if (sc.nextInt() == 2) {	//If option NO selected
					exit = true;
					System.out.println("----------You have loged out----------");
				}
				sc.nextLine();
			}
		}
		
		sc.close();
	}

	public static void WhowasBornAtThisData() {
		String a1="";
		String a2="";
		
		System.out.println("\nEnter two data values to find the persons born between those two:");
		
		System.out.println("\nThe first one:");
		
		String Data1 = sc.nextLine();  // Read user input
			
			a1= Data1;
			
		System.out.println("\nThe second one:");
		
		String Data2 = sc.nextLine();  // Read user input
		
			a2= Data2;
			
		net.returnPeoplefromDatas(a1, a2);
	}

	
	
	public static void WhoWasBronThere() {
		
		System.out.print("\nEnter the name of the place: \n");
		
		String Place = sc.nextLine();  // Read user input
		
		net.returnIdaAndLastNameFromBirthplace(Place);
	}
	
	
	public static void WhoareHisFriends () {
		boolean f1=false;
		
		System.out.print("\nEnter the Surname of the person you want to stalk: \n");
		
		String LastName = sc.nextLine();  // Read user input
		
		// /media/917790/E431-CEB4/UNI/ProgrammingProject/files/peopleG612051.txt
		
		System.out.println("LastName is: " + LastName);  // Output user input
		 
		 System.out.println("");
		 System.out.println("His friends are:");
		 System.out.println("");
		 	 
		 for (People po: net.peopleList) {
			
			 if(po.getLastName().equals(LastName) && f1==false) {
					
				 f1=true;
				 
				 net.returnRelationsFromSurname(LastName);
				 	System.out.println("");
			 }
		 }
	}
	/**
	 * Method that loads all the people 
	 * from the given file to the network
	 */
	public static void LoadPeopleToNetwork() {

		System.out.print("\nEnter the directory of the file: \n");
		String fileName = sc.nextLine();	//Directory of the file

		try {
			
			Scanner fileScan = new Scanner(new FileReader(fileName));	//create the fileReader
			fileScan.nextLine();	//Ignore the first line

			while (fileScan.hasNext()) {	//While not EOF

				String p = fileScan.nextLine();	//Scan each person info
				net.addPeople(p);	//Add the person to the network
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
	 * Method that load relations of the
	 * given file to the network
	 */
	public static void LoadRelationshipToNetwork() {
		
		System.out.print("\nEnter the directory of the file: \n");
		String fileName = sc.nextLine();	//Directory of the file

		try {
			
			Scanner fileScan = new Scanner(new FileReader(fileName));
			fileScan.nextLine();	//Ignore first line

			while (fileScan.hasNext()) {	//While not EOF

				String r = fileScan.nextLine();		//Scan each relationship
				net.addRelation(r);		//Add the relationship to the network
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
	 * Method that prints both people and 
	 * relationships from the1 network
	 * @throws FileNotFoundException 
	 */
	public static void PrintOutEveryone() throws FileNotFoundException {
		
		///users/917790/git/ProgrammingProject/ProgrammingProject/files/peopleG612051.txt
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