package gcMidterm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GCMidTermApp {

	public static boolean finished;
	static Scanner read = new Scanner(System.in);
	public static Path filePath = Paths.get("Inventory.txt");
	public static void main (String[] arg) throws IOException {
		ProductFile.fileExist(filePath);

		System.out.println(ProductFile.read(filePath));

		do {
			System.out.println("1. Add\nAdd an item to the customer's order");
			System.out.println("2. Remove\nRemove an item from the customer's order");
			System.out.println("3. Checkout\nGo to checkout and review entire order");
			System.out.println("4. Create\nCreate a new menu item");
			String menuChoice = read.nextLine();
			if (menuChoice.equalsIgnoreCase("1")||menuChoice.equalsIgnoreCase("add")) {
				add();
				finished = Vali.checkYes(Vali.getString(read, "Would you like to add another item?"));
			}
			else if (menuChoice.equalsIgnoreCase("2")||menuChoice.equalsIgnoreCase("remove")) {
				remove();
				finished = Vali.checkYes(Vali.getString(read, "Would you like to add another item?"));
				}
			else if (menuChoice.equalsIgnoreCase("3")||menuChoice.equalsIgnoreCase("checkout")){
				checkout();
			}
			else if (menuChoice.equalsIgnoreCase("4")||menuChoice.equalsIgnoreCase("create")){
				create();
			}
		} while (!finished);
		read.close();
	}
	private static void create() {
		// TODO Auto-generated method stub
		
	}
	private static void checkout() {
		// TODO Auto-generated method stub
		
	}
	private static void remove() {
		// TODO Auto-generated method stub
		
	}
	private static void add() {
		// TODO Auto-generated method stub
		
	}
}
