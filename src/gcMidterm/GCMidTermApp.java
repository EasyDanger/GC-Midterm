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
	// A scanner!!!
	static Scanner read = new Scanner(System.in);
	// Variable to store the location of the complete menu list text file.
	public static Path filePath = Paths.get("Inventory.txt");
	// Variable to store format for prices.
	static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] arg) throws IOException {
		System.out.println("Welcome to ... \n\ncoffee®");
		// Outer do-while loop allows for additional orders to be taken without quitting
		// the program.
		do {
			// bill list variable moved to main method and loop to allow it to be "cleared"
			// (really, a new object) for a new order.
			List<Product> bill = new ArrayList<>();
			// Do-while loop for the menu.
			do {
				// Header for menu aesthetics.
				String header = "\n=============================== coffee® menu ==================================";
				System.out.println(header);
				// Creates the menu from the text file.
				List<Product> menuList = ProductFile.read(filePath);
				// Calls the sorting method form the Sort class to keep menu organized.
				menuList = Sort.sortByAlpha(menuList);
				// variable to add a number to menu items for ordering later.
				int i = 0;
				// Prints out each menu item in sequence.
				for (Product product : menuList) {
					i++;
					System.out.println(String.format("%-4s%s", i + ". ", product));
				}
				// Menu display for user interfacing.
				System.out.println("\n1. Add						2. Remove"
						+ "\nAdd an item to the customer's order		Remove an item from the customer's order");
				System.out.println("\n3. Checkout					4. Create"
						+ "\nGo to checkout and review entire order		Create a new menu item");
				// Variable to accept user Input from the menu.
				String menuChoice = read.nextLine();
				// boolean will help check for valid input for adding to order choices.
				boolean onMenu = false;
				// If-else statements parse userinput to make menu choice. Accepts menu number
				// or keyword.

				if (menuChoice.equalsIgnoreCase("1") || menuChoice.equalsIgnoreCase("add")) {
					// indexChoice is where the menu item integer will be stored if user enters
					// number to choose menu item.
					int indexChoice;
					System.out.println("What item would you like to add?");
					// itemAdded stores user input.
					String itemAdded = read.nextLine();

					// statement checks whether user input is an integer. If it is, it adds that
					// item's index to the bill.
					if (itemAdded.matches("\\d+")) {
						indexChoice = Integer.parseInt(itemAdded);
						if (indexChoice < menuList.size()) {
							int x = howMany(menuList.get(indexChoice - 1).getName());
							for (int i1 = 0; i1 < x; i1++) {
								bill = Bill.addProduct(menuList, bill, menuList.get(indexChoice - 1).getName());
							}							
							
							bill = Bill.addProduct(menuList, bill, menuList.get(indexChoice - 1).getName());
							onMenu = true;
						} else {
							onMenu = false;
						}
					} else {
						// boolean will return false if item is not on menu. Otherwise, will return true
						// and add item to bill.
						onMenu = false;
						for (Product product : menuList) {
							if (product.getName().equalsIgnoreCase(itemAdded)) {
								onMenu = product.getName().equalsIgnoreCase(itemAdded);
								int x = howMany(product.getName());
								for (int i1 = 0; i1 < x; i1++) {
									bill = Bill.addProduct(menuList, bill, itemAdded);
								}
							}
						}
					}
					// Message about missing items from menu.
					if (!onMenu) {
						System.out.println("Sorry, that item isn't on the menu. Please choose another.");
					}
					// Prints current bill item with subtotal.
					double subTotal = 0;
					System.out.println("Current bill:");
					for (Product product : bill) {
						System.out.println("$" + df.format(product.getPrice()) + "\t" + product.getName());
						subTotal += product.getPrice();
					}
					System.out.println("Subtotal: $" + df.format(subTotal));
					// checks if the user is ready to checkout and end program.
					finished = Vali.checkYes(Vali.getString(read, "Are you ready to checkout?"));
					if (finished) {
						// Originally, bill was declared as a static variable. But with the decision to
						// allow multiple orders without restarting the program, we would need to clear
						// the bill. So, bill was moved into the main method and passed along to the
						// checkout() instead.
						checkout(bill);
					}
					// Allows the user to remove items from the order. Largely a stripped down,
					// reversing of above option.
				} else if (menuChoice.equalsIgnoreCase("2") || menuChoice.equalsIgnoreCase("remove")) {
					System.out.println("What item would you like to remove?");
					String itemRemoved = read.nextLine();
					bill = Bill.removeProduct(menuList, bill, itemRemoved);
					finished = Vali.checkYes(Vali.getString(read, "Are you ready to checkout?"));
					if (finished) {
						checkout(bill);
					}
					// calls the checkout() method to handle payment.
				} else if (menuChoice.equalsIgnoreCase("3") || menuChoice.equalsIgnoreCase("checkout")) {
					checkout(bill);
					// after checkout,user will be released from this menu.
					finished = true;
				}
				// allows user to add menu items to the menu.
				else if (menuChoice.equalsIgnoreCase("4") || menuChoice.equalsIgnoreCase("create")) {
					create();
					finished = false;
				}
				// loops for invalid input.
				else {
					System.out.println("Sorry, we didn't get that. Let's try again.\n\n");
					finished = false;
				}
			} while (!finished);
			// combines with outer loop to allow additional orders with a fresh bill.
			finished = Vali.checkYes(Vali.getString(read, "Do you want to take another order?"));
		} while (finished);
		read.close();
		System.out.println("Thanks for using this POS. Enjoy your coffee® day.");
	}

	private static void create() throws IOException {
		// Method to add items to the menu text file.
		String newProductName = Vali.getString(read, "What item would you like to add?");
		Product newProduct = new Product();
		newProduct.setName(newProductName);
		newProduct.setCategory(Product.checkCategory(read, "Is " + newProductName + " a food or drink item?"));
		newProduct.setPrice(Vali.getDouble(read, "How much does " + newProductName + " cost?"));
		newProduct.setDescription(Vali.getString(read, "Describe " + newProductName + "."));
		ProductFile.writeApp(filePath, newProduct);
		System.out.println("\nYour item has been added to the menu!\n");
	}

	private static void checkout(List<Product> bill) {
		// This variable will represent the customer's sub total, natch.
		double subTotal = 0;
		// iterates on all of the items placed on the customer's bill, pulling the
		// prices and summing them.
		for (Product product : bill) {
			subTotal += product.getPrice();
		}
		// tax is added here. The format method is again called to deal with any double
		// decimal point shenanigans.
		System.out.println("Tax: " + df.format(subTotal * 0.06));
		double total = subTotal * 1.06;
		System.out.println("Total: " + df.format(total));
		// should loop until customer has completed payment.
		do {
			// smashed menu to one line.
			String menuChoice = Vali.getString(read, "\n1. Cash						2. Credit (Debit"
					+ "\n3. Apple Pay					4. Check? Seriously, a check?");
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
			else if (menuChoice.equalsIgnoreCase("4") || menuChoice.equalsIgnoreCase("check")) {
				Payment.getCheck();
				System.out.println("Thank you ... for paying ... with a check ... I guess?");
				finished = true;
			}
			// Apple Pay is a thing that people have. I'm being a team player by not making
			// this an iPhone v Android thing.
			// -Ed
			else if (menuChoice.equalsIgnoreCase("3") || menuChoice.equalsIgnoreCase("Apple Pay")) {
				Payment.getApplePay();
				System.out.println("Thank the customer for their purchase.");
				finished = true;
			}
			// loops if user screws up the menu.
			else {
				System.out.println("Sorry, we didn't get that. Let's try again.");
				finished = false;
			}
		} while (!finished);
	}

	// Method to allow user to add multiple of items at once.
	private static int howMany(String key) {
		System.out.println("How many " + key + "s would you like to order?");
		String count = read.nextLine();
		int countInt = 1;
		if (count.matches("\\d+")) {
			countInt = Integer.parseInt(count);
		} else {
			System.out.println("Sorry, we need you to enter a whole number.");
			howMany(key);
		}
		return countInt;
	}
}
