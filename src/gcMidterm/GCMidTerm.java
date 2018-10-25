package gcMidterm;

import java.util.Scanner;

public class GCMidTerm {

	public static boolean finished;
	static Scanner read = new Scanner(System.in);
	public static void main (String[] arg) {
		//Display the menu
		do {
			System.out.println("1. Add\nAdd an item to the customer's order");
			System.out.println("2. Remove\nRemove an item from the customer's order");
			System.out.println("3. Checkout\nGo to checkout and review entire order");
			String menuChoice = read.nextLine();
			if (menuChoice.equalsIgnoreCase("1")||menuChoice.equalsIgnoreCase("add")) {
				add();
				finished = Vali.checkYes(Vali.getString(read, "Would you like to add another item?"));
			}
			else if (menuChoice.equalsIgnoreCase("2")||menuChoice.equalsIgnoreCase("remove")) {
				remove();
				}
			else if (menuChoice.equalsIgnoreCase("3")||menuChoice.equalsIgnoreCase("checkout")){
				checkout();
			}
		} while (!finished);
		read.close();
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
