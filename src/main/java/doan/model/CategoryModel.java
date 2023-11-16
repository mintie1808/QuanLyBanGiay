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

	public String getSeotitle() {
		return Seotitle;
	}

	public void setSeotitle(String seotitle) {
		Seotitle = seotitle;
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

	public int getCategoryId() {
		return category_id;
	}

	public void setCategoryId(int categoryId) {
		this.category_id = categoryId;
	}

	public String getCategoryName() {
		return category_name;
	}

	public void setCategoryName(String categoryName) {
		this.category_name = categoryName;
	}

}
