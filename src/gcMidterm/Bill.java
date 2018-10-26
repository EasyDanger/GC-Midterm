package gcMidterm;

import java.util.List;

public class Bill {
	
	
	public static List<Product> addProduct(List<Product> products, List<Product> bill, String str) {
		for (Product listProd: products) {
			if (listProd.checkName(str)) {
				System.out.println("this works!!");
				bill.add(listProd);		
			}
		}
	
		return bill;
		
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
