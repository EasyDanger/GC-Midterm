package gcMidterm;

import java.util.ArrayList;
import java.util.List;

public class Sort {
//Originally was to be sort by alphebetical order, but organizing the menu by category made more sense.
	public static List<Product> sortByAlpha(List<Product> billUnForm) {
		List<Product> billForm = new ArrayList<>();
		List<Product> drinks = new ArrayList<>();
		List<Product> foods = new ArrayList<>();
		for (Product drink : billUnForm) {
			if (drink.getCategory().equalsIgnoreCase("drink")) {
				drinks.add(drink);
			}
		}
		for (Product food : billUnForm) {
			if (food.getCategory().equalsIgnoreCase("food")) {
				foods.add(food);
			}
		}
		for (Product drink : drinks) {
			billForm.add(drink);
		}
		for (Product food : foods) {
			billForm.add(food);
		}

		return billForm;
	}

}
