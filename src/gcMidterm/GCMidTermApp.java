package gcMidterm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GCMidTermApp {

	public static boolean finished;
	static List<Product> bill = new ArrayList<>();
	static Scanner read = new Scanner(System.in);
	public static Path filePath = Paths.get("Inventory.txt");

	public static void main(String[] arg) throws IOException {
		ProductFile.fileExist(filePath);
		List<Product> pList = ProductFile.read(filePath);
		for (Product product : pList) {
			System.out.println(product);
		}

		do {
			System.out.println("1. Add\nAdd an item to the customer's order");
			System.out.println("2. Remove\nRemove an item from the customer's order");
			System.out.println("3. Checkout\nGo to checkout and review entire order");
			System.out.println("4. Create\nCreate a new menu item");
			String menuChoice = read.nextLine();
			if (menuChoice.equalsIgnoreCase("1") || menuChoice.equalsIgnoreCase("add")) {
				add();
				finished = Vali.checkYes(Vali.getString(read, "Would you like to add another item?"));
			} else if (menuChoice.equalsIgnoreCase("2") || menuChoice.equalsIgnoreCase("remove")) {
				remove();
				finished = Vali.checkYes(Vali.getString(read, "Would you like to add another item?"));
			} else if (menuChoice.equalsIgnoreCase("3") || menuChoice.equalsIgnoreCase("checkout")) {
				checkout();
				finished = true;
			} else if (menuChoice.equalsIgnoreCase("4") || menuChoice.equalsIgnoreCase("create")) {
				create();
				finished = Vali.checkYes(Vali.getString(read, "Are you finished with this POS?"));
			} else {
				System.out.println("Sorry, we didn't get that. Let's try again.\n\n");
				finished = false;
			}
		} while (!finished);

		read.close();
	}

	private static void create() {

	}

	// Still need to display the bill. Account for tips. Maybe an option to go back
	// to the first menu.
	private static void checkout() {
		// This variable will represent the customer's sub total, natch.
		double subTotal = 0;
		// iterates on all of the items placed on the customer's bill, pulling the
		// prices and summing them.
		for (Product product : bill) {
			subTotal += product.getPrice();
		}
		// tax is added here.
		double total = subTotal * 1.06;
		// should loop until customer has completed payment.
		do {
			// smashed menu to one line.
			String menuChoice = Vali.getString(read,
					"Pay by: \n1. Cash.\n2. Credit (Debit)\n3. Check? Seriously, A check?");
			// calls the getCash method for cash choice. Serves it the actual total and
			// receives change. Should be able to handle insufficient funds.
			if (menuChoice.equalsIgnoreCase("1") || menuChoice.equalsIgnoreCase("cash")) {
				double change = Payment.getCash(total);
				if (change > 0.0) {
					System.out.println("The customer will receive $" + change + " back as change.");
					finished = true;
				} else if (change < 0) {
					System.out.println("The customer still owes $" + Math.abs(change));
					finished = false;
				} else {
					System.out.println("The customer will receive no change.");
					finished = true;
				}
			}
			// This calls the getCredit method, which no longer accepts a string argument,
			// since everything is handled in that method. The validation process will serve
			// as an "approval" method. Basically, if they input a valid number, it'll be
			// approved, else not.
			else if (menuChoice.equalsIgnoreCase("2") || menuChoice.equalsIgnoreCase("credit")) {
				boolean approved = Payment.getCredit();
				if (approved) {
					System.out.println("The card was approved!");
					finished = true;
				} else {
					System.out.println("The card was not approved. Please provide another method of payment.");
					finished = false;
				}
			}
			// Lets them enter a check for some reason.
			else if (menuChoice.equalsIgnoreCase("3") || menuChoice.equalsIgnoreCase("check")) {
				Payment.getCheck();
				System.out.println("Thank you ... for paying ... with a check ... I guess?");
				finished = true;
			}
			// loops if user screws up the menu.
			else {
				System.out.println("Sorry, we didn't get that. Let's try again.");
				finished = false;
			}
		} while (!finished);

	}

	private static void remove() {
		// TODO Auto-generated method stub

	}

	private static void add() {
		// TODO Auto-generated method stub

	}
}
