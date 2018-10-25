package gcMidterm;

public class Product {
	private String name;
	private String category;
	private String description;
	private double price;
	
	
	public Product() {}
	
	public Product(String name, String category, String description, double price) {
		super();
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
		return "Product [name=" + name + ", category=" + category + ", description=" + description + ", price=" + price
				+ "]";
	}
	
	

}
