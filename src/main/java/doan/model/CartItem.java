package doan.model;

public class CartItem {
	 	private ProductModel Product;
	    private int quantity;
	    private double price;

	    
	    public CartItem() {}

		public CartItem(ProductModel product, int quantity, double price) {
			super();
			Product = product;
			this.quantity = quantity;
			this.price = price;
		}


		public ProductModel getProduct() {
			return Product;
		}


		public void setProduct(ProductModel product) {
			Product = product;
		}


		public int getQuantity() {
			return quantity;
		}


		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}


		public double getPrice() {
			return price;
		}


		public void setPrice(double price) {
			this.price = price;
		}
	   
}
