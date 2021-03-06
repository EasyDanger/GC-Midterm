package gcMidterm;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

//Class defining the products we sell. Considered making an abstract class "Product" and subclasses "Drink" and "Food," but it seemed unnecessary.
public class Product implements Comparable<Product> {
	
	private String name;
	private String category;
	private String description;
	private double price;

	public Product() {
	}

	public Product(String name, String category, String description, double price) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
//toString override uses the DecimalFormat format() method to ensure prices are displayed with the correct number of digits.
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.CEILING);
		return String.format("$%-6s%-20s%-7s%s", df.format(price), name, category, description);
	}

	public int compareTo(Product other) {
		// TODO Auto-generated method stub
		return name.compareTo(other.name);
	}
//Turned out to not be as useful as we originally thought.
	public boolean checkName(String s) {
		return name.equalsIgnoreCase(s);
	}
//allows program to check and force that the user is inputting valid categories for items added to menu. Adapted from getString matching Regex validation method.
	public static String checkCategory(Scanner scnr, String prompt) {
		System.out.print(prompt);
		String cate;
		do {
			cate = scnr.nextLine();
			if (cate.equalsIgnoreCase("food")) {
				return "Food";
			} else if (cate.equalsIgnoreCase("drink")) {
				return "Drink";
			} else {
				System.out.println("Sorry, that's not a valid category. Please enter \"Food\" or \"Drink.\"");
			}
		} while (!cate.equalsIgnoreCase("food") && !cate.equalsIgnoreCase("drink"));

		return null;
	}
}
