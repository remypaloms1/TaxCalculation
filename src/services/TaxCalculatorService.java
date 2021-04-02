package services;

import java.util.List;

import models.Product;
import models.ProductType;

public class TaxCalculatorService {

	private static final float VALUE_ADDED_TAX = 0.10f;
	private static final float IMPORTED_PRODUCT_TAX = 0.05f;
	
	public TaxCalculatorService() {}
	
	/*
	 * Détermine le prix TTC d'un produit donné en paramètre
	 * 
	 */
	public static void taxForProduct(Product p) {
		float taxVA = 0.0f;
		float taxPI = 0.0f;
		float priceHT = p.getPht();
		
		// Taxe Valeur ajoutée 10%
		if (p.getType().equals(ProductType.AUTRE)) {
			float originalTaxVA = roundToThousandth(priceHT * VALUE_ADDED_TAX);
			
			// Arrondi au 5 centimes près
			taxVA = roundToHundredth(originalTaxVA);
			
			// Si l'on a arrondi en INF on rajoute 0.05 pour faire l'arrondi SUP
			if (originalTaxVA > taxVA) {
				taxVA += 0.05;
			}
		}
		
		// Taxe Produits Importés 5%
		if(p.isImported()) {
			float originalTaxPI = roundToThousandth(priceHT * IMPORTED_PRODUCT_TAX);
			
			// Arrondi au 5 centimes près
			taxPI = roundToHundredth(originalTaxPI);
			
			// Si l'on a arrondi en INF on rajoute 0.05 pour faire l'arrondi SUP
			if (originalTaxPI > taxPI) {
				taxPI += 0.05;
			}
		}
		// Met à jour le prix TTC du produit
		p.setPttc(roundToThousandth(priceHT + taxVA + taxPI));
	}
	
	public static float totalTaxCalculator(List<Product> products) {
		float res = 0.0f;
		
		for (Product p : products) {
			res = roundToThousandth(res) + ((p.getPttc() - p.getPht()) * p.getQuantity());
		}
		
		return roundToThousandth(res);
	}
	
	public static float totalValueCalculator(List<Product> products) {
		float res = 0.0f;
		
		for (Product p : products) {
			res = roundToThousandth(res) + (p.getPttc() * p.getQuantity());
		}
		return roundToThousandth(res);
	}
	
	/*
	 * Fonction faisant l'arrondi à 0.05 le plus proche
	 * Ex : roundToHalf(2.375) = 2.4
	 * 		roundToHalf(0.5625) = 0.55
	 * 		roundToHalf(1.02) = 1.
	 * 		roundToHalf(1.05) = 1.05
	 */
	public static float roundToHundredth(float d) {
	    return (float) Math.round(d * 20) / 20.0f;
	}
	
	/*
	 * Fonction faisant l'arrondi à 0.005 près
	 * Permet d'enlever les potentielles erreurs d'arrondi après une opération 
	 * entre float (addition/soustraction)
	 */
	public static float roundToThousandth(float d) {
	    return (float) Math.round(d * 200) / 200.0f;
	}
}
