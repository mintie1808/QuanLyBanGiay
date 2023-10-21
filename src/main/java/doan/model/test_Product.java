package doan.model;

public class test_Product {
    private int ProductID;
    private String ProductName;
    private double Price;
	private String Color;
	private int SupplierID;
	private String Type;

	public test_Product(int ProductID, String ProductName, double price) {
		// TODO Auto-generated constructor stub
		this.ProductID = ProductID;
		this.ProductName = ProductName;
		this.Price = price;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public int getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
}
