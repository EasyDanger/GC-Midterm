package gcMidterm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductFile {
	public static List<String> toList(List<Product> pList) {
		List<String> lines = new ArrayList<>();
		for (Product product : pList) {
			String[] parts = { product.getName(), Double.toString(product.getPrice()), product.getCategory(), product.getDescription()};
			lines.add(parts[0] + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3]);
		}
		return lines;
	}
	
	public static void fileExist(Path filePath) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
	}
	
	public static List<Product> read(Path filePath) throws IOException {
		try {
			List<String> lines = Files.readAllLines(filePath);
			List<Product> products = new ArrayList<>();
			for (String line : lines) {
				String[] parts = line.split("[\t]+");
				Product p = new Product();
				p.setName(parts[0]);
				p.setPrice(Double.parseDouble(parts[1]));
				p.setCategory(parts[2]);
				p.setDescription(parts[3]);
				products.add(p);
			}
			return products;

		} catch (NoSuchFileException ex) {
			return new ArrayList<>();
		} catch (IOException ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static void writeApp(Path filePath, Product p) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
		String line = p.getName()+ "\t" + Double.toString(p.getPrice()) + "\t" + p.getCategory() + "\t" + p.getDescription();
		List<String> linesToAdd = Arrays.asList(line);
		Files.write(filePath, linesToAdd, StandardOpenOption.APPEND);
	}
	public static void writeTrun(Path filePath, List<Product> pList) throws IOException {
		if (Files.notExists(filePath)) {
			Files.createFile(filePath);
		}
		List<String> linesToAdd = toList(pList);
		Files.write(filePath, linesToAdd, StandardOpenOption.TRUNCATE_EXISTING);

	}
	
}
