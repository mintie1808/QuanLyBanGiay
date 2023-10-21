package doan.model;

public class OrderDetailModel {
	private int OrderDetailID;
	private int OrderID;
	private int ProductID;
	private int Quantity;

	public int getOrderDetailID() {
		return OrderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		OrderDetailID = orderDetailID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}
