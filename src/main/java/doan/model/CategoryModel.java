package doan.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category_tb")
public class CategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	private String Seotitle;
	private String category_name;
	private LocalDateTime CreatedDate;
	private boolean Status;
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getSeotitle() {
		return Seotitle;
	}
	public void setSeotitle(String seotitle) {
		Seotitle = seotitle;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}
	public boolean isStatus() {
		return Status;
	}
	public void setStatus(boolean status) {
		Status = status;
	}

	

}
