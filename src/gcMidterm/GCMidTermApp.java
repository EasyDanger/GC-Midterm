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
		List<Product> menuList = ProductFile.read(filePath);
			Product product2 = new Product();
			product2.setName("Mocha");
			product2.setPrice(3.75);
			product2.setCategory("Drink");
			product2.setDescription("Bittersweet mocha sauce, espresso and steamed whole milk topped with foam.");
			
		for (Product listProd: menuList) {

			if (listProd == product2) {
				System.out.println("this works!!");
			}
		}
		
		for (Product product : menuList) {
			System.out.println(product);
		}
		
//Additional things to consider:
		// method to sort menu list by drinks or food.?
		// coupon codes? (stolen form other group. Consider something else)
		// Separate list of "specials"?
		// gift cards?

		do {
			System.out.println("1. Add\nAdd an item to the customer's order");
			System.out.println("2. Remove\nRemove an item from the customer's order");
			System.out.println("3. Checkout\nGo to checkout and review entire order");
			System.out.println("4. Create\nCreate a new menu item");
			String menuChoice = read.nextLine();
			if (menuChoice.equalsIgnoreCase("1") || menuChoice.equalsIgnoreCase("add")) {
				System.out.println("What item would you like to add?");
				String itemAdded = read.nextLine();
				
				bill = Bill.addProduct(menuList, bill, itemAdded);
			
				System.out.println("This is the bill " + bill);
			//	Bill.addProduct(menuList, menuList.indexOf(itemAdded));
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
			newProduct.setCategory(Vali.getString(read, "Is " + newProductName + " a food or drink item?"));
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
			else if (menuChoice.equalsIgnoreCase("4") || menuChoice.equalsIgnoreCase("Apple Pay")) {
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

	//private static void remove() {
		// Should be able to simply call an item in the bill list and remove it.
		// Must display bill list before that can happen.

	//}

	private static void add() {
		// Should be able to simply call the index of an item in the pList list and add
		// it to the bill list.
		// Maybe display pList again?

	}
}
