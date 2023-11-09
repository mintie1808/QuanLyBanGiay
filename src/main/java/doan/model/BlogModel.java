package doan.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Blog__tb")
public class BlogModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDblog;
	private String Title;
	private String Detail;
	private String Description;
	private String img;
	private LocalDateTime CreatedDate;
	private String SeoTitle;
	private int UserID;

	public int getIDblog() {
		return IDblog;
	}

	public void setIDblog(int iDblog) {
		IDblog = iDblog;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public LocalDateTime getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		CreatedDate = createdDate;
	}

	public String getSeoTitle() {
		return SeoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		SeoTitle = seoTitle;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

}
