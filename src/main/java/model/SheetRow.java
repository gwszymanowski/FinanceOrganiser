package model;

import java.util.Date;

public class SheetRow {

	private int id;
	private String title;
	private Category category;
	private int order;
	private Price price;
	private Date createdAt;
	private Date lastModified;

	public SheetRow() {
		this.price = new Price(0, 0);
		this.createdAt = new Date();
		this.lastModified = new Date();
	}

	public SheetRow(String title, Category category, Price price) {
		this.title = title;
		this.category = category;
		this.price = price;
		this.createdAt = new Date();
		this.lastModified = new Date();
	}

	public SheetRow(int id, String title, int order, Category category, Price price) {
		this.id = id;
		this.title = title;
		this.order = order;
		this.category = category;
		this.price = price;
		this.createdAt = new Date();
		this.lastModified = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.lastModified = new Date();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		this.lastModified = new Date();
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
		this.lastModified = new Date();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getLastModified() {
		return lastModified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SheetRow other = (SheetRow) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SheetRow [title=" + title + ", category=" + category + ", price=" + price + ", createdAt=" + createdAt
				+ ", lastModified=" + lastModified + "]";
	}

}
