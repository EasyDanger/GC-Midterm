package gcMidterm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GCMidTermApp {
//Boolean used to control while looping.
	public static boolean finished;
	// bill variable lists items added to customer's invoice.
	static List<Product> bill = new ArrayList<>();
	// A scanner!!!
	static Scanner read = new Scanner(System.in);
	// Variable to store the location of the complete menu list text file.
	public static Path filePath = Paths.get("Inventory.txt");
	// Variable to store format for prices.
	static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] arg) throws IOException {
		// Do-while loop for the menu.
		do {
			// Header for menu aesthetics.
			String header = "\n\n=============================== menu ==================================";
			System.out.println(header);
			// Creates the menu from the text file.
			List<Product> menuList = ProductFile.read(filePath);
			// Calls the sorting method form the Sort class to keep menu organized.
			menuList = Sort.sortByAlpha(menuList);
			// Prints out the menu item by item.
			int i = 0;
			for (Product product : menuList) {
				i++;
				System.out.println(String.format("%-4s%s", i + ". ", product));
			}
			// Menu display for user interfacing.
			System.out.println("\n1. Add						2.Remove"
					+ "\nAdd an item to the customer's order		Remove an item from the customer's order");
			System.out.println("\n3. Checkout					4. Create"
					+ "\nGo to checkout and review entire order		Create a new menu item");
			String menuChoice = read.nextLine();
			if (menuChoice.equalsIgnoreCase("1") || menuChoice.equalsIgnoreCase("add")) {
				int indexChoice;
				System.out.println("What item would you like to add?");
				String itemAdded = read.nextLine();
				if (itemAdded.matches("\\d+")) {
					indexChoice = Integer.parseInt(itemAdded);
					bill = Bill.addProduct(menuList, bill, menuList.get(indexChoice - 1).getName());
				} else {
					bill = Bill.addProduct(menuList, bill, itemAdded);
				}
				System.out.println("Current bill:");
				for (Product product : bill) {
					System.out.println("$" + df.format(product.getPrice()) + "\t" + product.getName());
				}
				double subTotal = 0;
				for (Product product : bill) {
					subTotal += product.getPrice();
				}
				System.out.println("Subtotal: $" + df.format(subTotal));
				// Bill.addProduct(menuList, menuList.indexOf(itemAdded));
				finished = Vali.checkYes(Vali.getString(read, "Are you ready to checkout?"));
				if (finished) {
					checkout();
				}
			} else if (menuChoice.equalsIgnoreCase("2") || menuChoice.equalsIgnoreCase("remove")) {
				System.out.println("What item would you like to remove?");
				String itemRemoved = read.nextLine();
				bill = Bill.removeProduct(menuList, bill, itemRemoved);
				finished = Vali.checkYes(Vali.getString(read, "Are you ready to checkout?"));
				if (finished) {
					checkout();
				}
			} else if (menuChoice.equalsIgnoreCase("3") || menuChoice.equalsIgnoreCase("checkout")) {
				checkout();
				finished = true;
			} else if (menuChoice.equalsIgnoreCase("4") || menuChoice.equalsIgnoreCase("create")) {
				create();
				finished = Vali.checkYes(Vali.getString(read, "Are you ready to checkout?"));
			} else {
				System.out.println("Sorry, we didn't get that. Let's try again.\n\n");
				finished = false;
			}
		} while (!finished);

		read.close();
	}

	private static void create() throws IOException {
		// Copy country creation method from previous lab, change names of variables and
		// add the forth attribute. Account for the difference in types.
		String newProductName = Vali.getString(read, "What item would you like to add?");
		Product newProduct = new Product();
		newProduct.setName(newProductName);
		newProduct.setCategory(Product.checkCategory(read, "Is " + newProductName + " a food or drink item?"));
		newProduct.setPrice(Vali.getDouble(read, "How much does " + newProductName + " cost?"));
		newProduct.setDescription(Vali.getString(read, "Describe " + newProductName + "."));
		ProductFile.writeApp(filePath, newProduct);
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
					System.out.println("The customer will receive $" + df.format(change) + " back as change.");
					finished = true;
				} else if (change < 0) {
					System.out.println("The customer still owes $" + df.format(Math.abs(change)));
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
			} else if (menuChoice.equalsIgnoreCase("4") || menuChoice.equalsIgnoreCase("Apple Pay")) {
				Payment.getApplePay();
				System.out.println("Thank you for your purchase.");
				finished = true;
			}
			// loops if user screws up the menu.
			else {
				System.out.println("Sorry, we didn't get that. Let's try again.");
				finished = false;
			}
		} while (!finished);

	}

}
