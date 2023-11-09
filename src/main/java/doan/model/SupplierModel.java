package doan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier__tb")
public class SupplierModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SupplierID;
	private String SupplierName;
	private String Address;
	private String ProductsSupplied;
	private String PaymentTerms;

	public int getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getProductsSupplied() {
		return ProductsSupplied;
	}

	public void setProductsSupplied(String productsSupplied) {
		ProductsSupplied = productsSupplied;
	}

	public String getPaymentTerms() {
		return PaymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		PaymentTerms = paymentTerms;
	}

}
