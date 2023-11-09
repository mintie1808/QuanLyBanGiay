package doan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDetail__tb")
public class OrderDetailModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderDetailID;
	private int OrderID;
	private int ProductID;
	private int Quantity;
	private String PaymentMethod;

	public String getPaymentMethod() {
		return PaymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}

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
