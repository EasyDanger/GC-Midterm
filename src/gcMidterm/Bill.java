package gcMidterm;

import java.util.List;

public class Bill {
	
	
	public List<Product> add(List<Product> products, Product product) {
		
		for (Product listProd: products) {
			if (listProd.equals(product)) {
				System.out.println("this works!!");
			}
		}
		products.add(product);
		
		return products;
		
	}

}
