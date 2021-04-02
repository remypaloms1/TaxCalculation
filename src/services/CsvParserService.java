package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import models.Product;
import models.ProductType;

public class CsvParserService {
	
	static final String CSV_SEPARATOR = ";";
	
	static final int POSITION_ATTR_NAME = 0;
	static final int POSITION_ATTR_QUANTITY = 1;
	static final int POSITION_ATTR_PRICE = 2;
	static final int POSITION_ATTR_TYPE = 3;
	static final int POSITION_ATTR_IS_IMPORTED = 4;

	public CsvParserService() {}
	
	/* 
	* Méthode permettant de lire les informations contenues dans le fichier CSV
	* et de les convertir en Listes de String
	*/
	public static List<List<String>> csvReader(String filePath, String fileName) {
		List<List<String>> allProducts = new ArrayList<>();
				
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath+fileName));
			
			// Non prise en compte de la première ligne (avec le nom des attributs)
			String line = br.readLine(); 
		    
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(CSV_SEPARATOR);
		        allProducts.add(Arrays.asList(values));
		    }
		    br.close();
		    		    
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] Fichier introuvable");
		} catch (IOException e) {
			System.out.println("[ERROR] " + e.toString());
		}
		
		return allProducts;
	}
	
	public static List<Product> convertListToProducts(List<List<String>> listProducts) {
		List<Product> res = new LinkedList<>();
		
		// Construit un objet de type Produit pour chaque lignes parsées
		listProducts.stream().forEach(l -> createProductFromStrings(res, l));
		
		return res;
	}

	private static void createProductFromStrings(List<Product> products, List<String> l) {
		Product p = new Product();
		
		for (int i = 0; i<l.size(); i++) {
			switch (i) {
				case POSITION_ATTR_NAME:
					p.setName(l.get(i));
					break;
				case POSITION_ATTR_QUANTITY:
					p.setQuantity(Integer.parseInt(l.get(i)));
					break;
				case POSITION_ATTR_PRICE:
					p.setPht(Float.parseFloat(l.get(i)));
					break;
				case POSITION_ATTR_TYPE:
					ProductType productType = findTypeFromString(l.get(i));
					p.setType(productType);
					break;
				case POSITION_ATTR_IS_IMPORTED:
					p.setImported(Boolean.parseBoolean(l.get(i)));
					break;
					
				default:
					break;
			}
		}
		
		// Ajout du produit créé à la liste des Produits
		products.add(p);
	}

	private static ProductType findTypeFromString(String type) {
		ProductType res;
		
		switch (type) {
			case "AUTRE":
				res = ProductType.AUTRE;
				break;
			case "LIVRE":
				res = ProductType.LIVRE;
				break;
			case "MEDICAMENT":
				res = ProductType.MEDICAMENT;
				break;
			case "NOURRITURE":
				res = ProductType.NOURRITURE;
				break;

			default:
				res = null;
				break;
		}
		
		return res;
	}
}
