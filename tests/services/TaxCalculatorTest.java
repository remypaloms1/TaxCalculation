package services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Product;
import models.ProductType;

class TaxCalculatorTest {

	private static final String NAME_PRODUCT_1 = "Product1";
	private static final String NAME_PRODUCT_2 = "Product2";
	private static final String NAME_PRODUCT_3 = "Product3";
	private static final String NAME_PRODUCT_4 = "Product4";
	private static final String NAME_PRODUCT_5 = "Product5";
	private static final String NAME_PRODUCT_6 = "Product6";
	private static final String NAME_PRODUCT_7 = "Product7";
	private static final String NAME_PRODUCT_8 = "Product8";
	private static final String NAME_PRODUCT_9 = "Product9";
	private static final String NAME_PRODUCT_10 = "Product10";
	
	private static final int QUANTITY_PRODUCT_1 = 1;
	private static final int QUANTITY_PRODUCT_2 = 1;
	private static final int QUANTITY_PRODUCT_3 = 1;
	private static final int QUANTITY_PRODUCT_4 = 1;
	private static final int QUANTITY_PRODUCT_5 = 1;
	private static final int QUANTITY_PRODUCT_6 = 1;
	private static final int QUANTITY_PRODUCT_7 = 1;
	private static final int QUANTITY_PRODUCT_8 = 1;
	private static final int QUANTITY_PRODUCT_9 = 1;
	private static final int QUANTITY_PRODUCT_10 = 1;
	
	private static final float PRICE_PRODUCT_1 = 9.90f;
	private static final float PRICE_PRODUCT_2 = 10.00f;
	private static final float PRICE_PRODUCT_3 = 10.10f;
	private static final float PRICE_PRODUCT_4 = 10.20f;
	private static final float PRICE_PRODUCT_5 = 10.50f;
	private static final float PRICE_PRODUCT_6 = 14.99f;
	private static final float PRICE_PRODUCT_7 = 20.01f;
	private static final float PRICE_PRODUCT_8 = 9.75f;
	private static final float PRICE_PRODUCT_9 = 15.00f;
	private static final float PRICE_PRODUCT_10 = 15.00f;
	
	private static final ProductType TYPE_PRODUCT_1 = ProductType.AUTRE;
	private static final ProductType TYPE_PRODUCT_2 = ProductType.AUTRE;
	private static final ProductType TYPE_PRODUCT_3 = ProductType.AUTRE;
	private static final ProductType TYPE_PRODUCT_4 = ProductType.AUTRE;
	private static final ProductType TYPE_PRODUCT_5 = ProductType.AUTRE;
	private static final ProductType TYPE_PRODUCT_6 = ProductType.LIVRE;
	private static final ProductType TYPE_PRODUCT_7 = ProductType.MEDICAMENT;
	private static final ProductType TYPE_PRODUCT_8 = ProductType.NOURRITURE;
	private static final ProductType TYPE_PRODUCT_9 = ProductType.MEDICAMENT;
	private static final ProductType TYPE_PRODUCT_10 = ProductType.LIVRE;
	
	private static final boolean IS_IMPORTED_PRODUCT_1 = false;
	private static final boolean IS_IMPORTED_PRODUCT_2 = false;
	private static final boolean IS_IMPORTED_PRODUCT_3 = false;
	private static final boolean IS_IMPORTED_PRODUCT_4 = false;
	private static final boolean IS_IMPORTED_PRODUCT_5 = false;
	private static final boolean IS_IMPORTED_PRODUCT_6 = false;
	private static final boolean IS_IMPORTED_PRODUCT_7 = false;
	private static final boolean IS_IMPORTED_PRODUCT_8 = false;
	private static final boolean IS_IMPORTED_PRODUCT_9 = true;
	private static final boolean IS_IMPORTED_PRODUCT_10 = false;
	
	
	private Product product1;
	private Product product2;
	private Product product3;
	private Product product4;
	private Product product5;
	private Product product6;
	private Product product7;
	private Product product8;
	private Product product9;
	private Product product10;
	
	private List<Product> allProductsTest = new ArrayList<>();
	
    @BeforeEach                                         
    public void setUp() throws Exception {
        product1 = new Product(
        		NAME_PRODUCT_1,
        		QUANTITY_PRODUCT_1,
        		PRICE_PRODUCT_1,
        		TYPE_PRODUCT_1,
        		IS_IMPORTED_PRODUCT_1);
        
        product2 = new Product(
        		NAME_PRODUCT_2,
        		QUANTITY_PRODUCT_2,
        		PRICE_PRODUCT_2,
        		TYPE_PRODUCT_2,
        		IS_IMPORTED_PRODUCT_2);
        
        product3 = new Product(
        		NAME_PRODUCT_3,
        		QUANTITY_PRODUCT_3,
        		PRICE_PRODUCT_3,
        		TYPE_PRODUCT_3,
        		IS_IMPORTED_PRODUCT_3);
        
        product4 = new Product(
        		NAME_PRODUCT_4,
        		QUANTITY_PRODUCT_4,
        		PRICE_PRODUCT_4,
        		TYPE_PRODUCT_4,
        		IS_IMPORTED_PRODUCT_4);
        
        product5 = new Product(
        		NAME_PRODUCT_5,
        		QUANTITY_PRODUCT_5,
        		PRICE_PRODUCT_5,
        		TYPE_PRODUCT_5,
        		IS_IMPORTED_PRODUCT_5);
        
        product6 = new Product(
        		NAME_PRODUCT_6,
        		QUANTITY_PRODUCT_6,
        		PRICE_PRODUCT_6,
        		TYPE_PRODUCT_6,
        		IS_IMPORTED_PRODUCT_6);
        
        product7 = new Product(
        		NAME_PRODUCT_7,
        		QUANTITY_PRODUCT_7,
        		PRICE_PRODUCT_7,
        		TYPE_PRODUCT_7,
        		IS_IMPORTED_PRODUCT_7);
        
        product8 = new Product(
        		NAME_PRODUCT_8,
        		QUANTITY_PRODUCT_8,
        		PRICE_PRODUCT_8,
        		TYPE_PRODUCT_8,
        		IS_IMPORTED_PRODUCT_8);
        
        product9 = new Product(
        		NAME_PRODUCT_9,
        		QUANTITY_PRODUCT_9,
        		PRICE_PRODUCT_9,
        		TYPE_PRODUCT_9,
        		IS_IMPORTED_PRODUCT_9);
        
        product10 = new Product(
        		NAME_PRODUCT_10,
        		QUANTITY_PRODUCT_10,
        		PRICE_PRODUCT_10,
        		TYPE_PRODUCT_10,
        		IS_IMPORTED_PRODUCT_10);
        
        allProductsTest.add(product1);
        allProductsTest.add(product2);
        allProductsTest.add(product3);
        allProductsTest.add(product4);
        allProductsTest.add(product5);
        allProductsTest.add(product6);
        allProductsTest.add(product7);
        allProductsTest.add(product8);
        allProductsTest.add(product9);
        allProductsTest.add(product10);
        
        allProductsTest.stream().forEach(p -> TaxCalculatorService.taxForProduct(p));
    }
    
    /*
     * Tests portant sur la règle d'arrondi au 5 centimes sup
     */
	@Test
	void taxIsCorrectlyRounded() {
		assert(allProductsTest.get(0).getPttc() == 10.90f);
		assert(allProductsTest.get(1).getPttc() == 11.00f);
		
		assert(allProductsTest.get(2).getPttc() == 11.15f);
		assert(allProductsTest.get(3).getPttc() == 11.25f);
		assert(allProductsTest.get(4).getPttc() == 11.55f);
	}
	
	/*
	 * Tests vérifiant si la Taxe sur la Valeur ajoutée de 10% est appliquée
	 * au bon type de produit
	 */
	@Test
	void taxOfAddedValueIsAplliedForRightProducts() {
		assert(allProductsTest.get(4).getPttc() > allProductsTest.get(4).getPht());
		assert(allProductsTest.get(5).getPttc() == allProductsTest.get(5).getPht());
		assert(allProductsTest.get(6).getPttc() == allProductsTest.get(6).getPht());
		assert(allProductsTest.get(7).getPttc() == allProductsTest.get(7).getPht());
	}
	
	/*
	 * Tests vérifiant si la Taxe sur les produits importés est correctement
	 * appliquée
	 */
	@Test
	void taxOfImportedProductIsCorrectlyApllied() {
		assert(allProductsTest.get(8).getPttc() == 15.75f);
		assert(allProductsTest.get(9).getPttc() == 15.00f);
	}
	
	/*
	 * Tests vérifiant si le montant total des taxes calculées et le montant total des
	 * articles est correctement calculé
	 */
	@Test
	void totalCostAndTotalTax() {
		float totalTax = TaxCalculatorService.totalTaxCalculator(allProductsTest);
		float totalValue = TaxCalculatorService.totalValueCalculator(allProductsTest);
		
		assert(totalTax == 5.9f);
		assert(totalValue == 131.35f);
	}

}
