package doan.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Invoice__tb")
public class InvoiceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int InvoiceID;
	private int OrderID;
	private String Status;
	private LocalDateTime InvoiceDate;

	public LocalDateTime getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	public int getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		InvoiceID = invoiceID;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
}
