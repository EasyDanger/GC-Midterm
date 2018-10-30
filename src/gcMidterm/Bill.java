package gcMidterm;

import java.util.List;
//methods to deal with adding and removing items from the order.
public class Bill {

	public static List<Product> addProduct(List<Product> products, List<Product> bill, String str) {
		for (Product listProd : products) {
			if (listProd.checkName(str)) {
				bill.add(listProd);
			}
		}
		return bill;
	}


	public static List<Product> removeProduct(List<Product> bill, String str) {
		Product removable = new Product();
		
		
		//		List<Product> bills = bill;
		for (Product listProd: bill) {
			if (listProd.checkName(str)) {
				removable = listProd;
				break;
//				System.out.println("removed from list!");
//				int i = bill.indexOf(listProd);
//				bills.remove(i);
			}
		}
//		}
			bill.remove(removable);
			System.out.println("Product has been removed!");
			return bill;
		
	}

}
