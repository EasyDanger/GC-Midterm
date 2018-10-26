package gcMidterm;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "$" + price + "\t" + name + "\t" + description;
	}

	public int compareTo(Product other) {
		// TODO Auto-generated method stub
		return name.compareTo(other.name);
	}

	public boolean checkName(String s) {
		return this.name.equals(s);
	}

}
