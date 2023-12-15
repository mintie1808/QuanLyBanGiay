package doan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product__tb")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProductID;
	private String ProductName;
	private int Price;
	private String Seotitle;
	private String Color;
	private int SupplierID;
	private int category_id;
	private boolean Status;
	private boolean Hot;
	private String Description;
	private String Detail;
	private boolean SALE;
	private String Img;
	private int Amount;
	private int Sold;
	public boolean isHot() {
		return Hot;
	}
	public void setHot(boolean hot) {
		Hot = hot;
	}

	public boolean isSALE() {
		return SALE;
	}

	public void setSALE(boolean sALE) {
		SALE = sALE;
	}



	public String getSeotitle() {
		return Seotitle;
	}

	public void setSeotitle(String seotitle) {
		Seotitle = seotitle;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}


	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public int getSold() {
		return Sold;
	}

	public void setSold(int sold) {
		Sold = sold;
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

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
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

}
