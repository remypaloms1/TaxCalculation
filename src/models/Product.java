package models;

public class Product {
	// ATTRIBUTES ============================
	private String name;
	private int quantity;
	private float pht;
	private float pttc;
	private ProductType type;
	private boolean isImported;
	// ======================================
	
	// CONSTRUCTORS =========================
	public Product() {};
	
	public Product(String name, int quantity, float price, ProductType type, boolean isImported) {
		this.name = name;
		this.quantity = quantity;
		this.pht = price;
		this.type = type;
		this.isImported = isImported;
	}
	// ======================================

	// GETTERS & SETTERS ====================
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPht() {
		return pht;
	}

	public void setPht(float price) {
		this.pht = price;
	}

	public float getPttc() {
		return pttc;
	}
	
	public void setPttc(float pttc) {
		this.pttc = pttc;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

}
