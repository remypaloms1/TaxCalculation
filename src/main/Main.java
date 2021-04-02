package main;

import java.util.List;
import java.util.Scanner;

import models.Product;
import services.CsvParserService;
import services.TaxCalculatorService;

public class Main {
	// ATTRIBUTES ===========================
	static final String FILE_PATH = System.getProperty("user.dir") + "/INPUTS/";
	static final String FILE_NAME_1 = "input1.csv";
	static final String FILE_NAME_2 = "input2.csv";
	static final String FILE_NAME_3 = "input3.csv";
	// ======================================
	
	// MAIN METHOD ==========================
	public static void main(String[] args) {
		// INPUT ======================================
		boolean correctInput = false;
		System.out.println("==> Quel panier souhaitez-vous ?");
		System.out.println("> Panier 1");
		System.out.println("> Panier 2");
		System.out.println("> Panier 3");
		int fileNumber = 0;
		Scanner sc = new Scanner(System.in);
		
		while(!correctInput) {
			try {
				System.out.print("==> Veuillez entrer le numéro du panier : ");
				fileNumber = Integer.parseInt(sc.nextLine());
				if (!(fileNumber > 0 && fileNumber < 4)) {
					System.out.println("[ERROR] Numéro invalide ! Veuillez recommencer :");
				}
				else {
					correctInput = true;
				}
			}
			catch (NumberFormatException n) {
				System.out.println("[ERROR] Entrez un nombre svp");
			}
		}
		sc.close();
		
		String fileName = "";
		if (fileNumber == 1) 
			fileName = FILE_NAME_1;
		if (fileNumber == 2)
			fileName = FILE_NAME_2;
		if(fileNumber == 3)
			fileName = FILE_NAME_3;
		
		// PARSING THE DATA
		List<Product> allProducts = CsvParserService.convertListToProducts(
				CsvParserService.csvReader(FILE_PATH, fileName));
		
		// TAX CALCULATOR
		allProducts.stream().forEach(p -> TaxCalculatorService.taxForProduct(p));
		
		// PRINT THE RESULT
		float totalTax = TaxCalculatorService.totalTaxCalculator(allProducts);
		float totalValue = TaxCalculatorService.totalValueCalculator(allProducts);
		
		
		printFinalBill(allProducts, totalTax, totalValue, fileNumber);
	}
	// =======================================
	
	
	static void printFinalBill(List<Product> allProducts, float totalTax, float totalValue, int fileNumber) {
		System.out.println();
		System.out.println();
		System.out.println("[INFO] Facture détaillée n°" + fileNumber + " :");
		allProducts.stream().forEach(p -> System.out.println("> "+p.getName()+" : "+p.getPttc()));
		System.out.println("==================");
		System.out.println("> Montant des taxes : "+totalTax);
		System.out.println("> Total : "+totalValue);
	}
}
