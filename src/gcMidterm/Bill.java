package gcMidterm;

import java.util.List;

public class Bill {
	
	
	public static List<Product> addProduct(List<Product> products, Product product) {
		
		for (Product listProd: products) {
			if (listProd.equals(product)) {
				System.out.println("this works!!");
			}
		}
		products.add(product);
		
		return products;
		
	}
	
	public static List<Product> removeProduct(List<Product> products, Product product) {
		
		for (Product listProd: products) {
			if (listProd.equals(product)) {
				System.out.println("removed from list!");
			}
		}
		products.remove(product);
		
		return products;
		
	}

}
