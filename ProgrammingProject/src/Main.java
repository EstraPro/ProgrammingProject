import java.io.FileReader;
import java.util.Scanner;

public class Main {

	private static Network n = new Network();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int sel1;
		boolean exit = false;

		while (!exit) {
			
			System.out.println("_______________------Menu------_______________\n");
			System.out.println("1. Load 'people' in the network\n");
			System.out.println("2. Load 'relationships' in the network\n");
			System.out.println("3. Print out the list of our network users\n");
			System.out.println("4. End and log out");
			System.out.println("______________________________________________\n");
			System.out.println("Your election:");
			sel1 = sc.nextInt();

			switch (sel1) {
			
				case 1:	LoadPeopleToNetwork(n);
				break;

				case 2:	LoadRelationshipToNetwork(n);
				break;

				case 3:	PrintOutEveryone(n);
				break;

				case 4:	exit = true;
						System.out.println("----------You have loged out----------");
				break;
			}

			if (!exit) {
				System.out.println("---------------------------------");
				System.out.println("Do you want to continue? (1)YES / (2)NO");
				System.out.println("---------------------------------");
				
				if (sc.nextInt() == 2) {
					exit = true;
					System.out.println("----------You have loged out----------");
				}
				
			}
		}
		
		sc.close();
	}

	public static void LoadPeopleToNetwork(Network net) {

		System.out.print("\nEnter the directory of the file: \n");

		String fileName = sc.nextLine();

		try {
			// BufferedReader creation
			// BufferedReader br = new BufferedReader(new FileReader(fileName));
			Scanner scan = new Scanner(new FileReader(fileName));
			// Leer la primera línea, guardando en un String
			scan.nextLine();

			while (scan.hasNext()) {

				String p = scan.nextLine();
				// Repetir mientras no se llegue al final del fichero

				net.addPeople(p);

				// System.out.println(p);
			}
			System.out.println("");
			System.out.println("Uploading files");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("done");
			System.out.println("");

		} catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
	}

	public static void LoadRelationshipToNetwork(Network net) {
		System.out.println("");
		System.out.print("Enter the route of the file in your directory you want to upload: ");
		System.out.println("");

		Scanner scanner = new Scanner(System.in);

		String fileName = scanner.nextLine();

		// String fileName = "C:\\Users\\Iker Sancho\\Desktop\\peopleG612051.txt";
		// //**Must be able to load several people files!!

		try {
			// BufferedReader creation
			// BufferedReader br = new BufferedReader(new FileReader(fileName));
			Scanner scan = new Scanner(new FileReader(fileName));
			// Leer la primera línea, guardando en un String
			scan.nextLine();

			while (scan.hasNext()) {

				String r = scan.nextLine();
				// Repetir mientras no se llegue al final del fichero

				net.addRelation(r);
				// System.out.println(p);

			}
			System.out.println("");
			System.out.println("Uploading files");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("done");
			System.out.println("");

		} catch (Exception e) {
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		}
	}

	public static void PrintOutEveryone(Network net) {
		System.out.println("---------------------------------");
		net.printNetworkToFile();
		System.out.println("");
		System.out.println("done");
		System.out.println("");
		System.out.println("---------------------------------");
	}

	public static void Search() {

	}

}
