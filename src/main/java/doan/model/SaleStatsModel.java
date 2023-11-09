package doan.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SaleStats __tb")
public class SaleStatsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Sale_id;
	private int User_id;
	private int Product_id;
	private Date Sale_date;
	private int Quantity_sold;

	public int getSale_id() {
		return Sale_id;
	}

	public void setSale_id(int sale_id) {
		Sale_id = sale_id;
	}

	public int getQuantity_sold() {
		return Quantity_sold;
	}

	public void setQuantity_sold(int quantity_sold) {
		Quantity_sold = quantity_sold;
	}

	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}

	public int getProduct_id() {
		return Product_id;
	}

	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}

	public Date getSale_date() {
		return Sale_date;
	}

	public void setSale_date(Date sale_date) {
		Sale_date = sale_date;
	}

}
