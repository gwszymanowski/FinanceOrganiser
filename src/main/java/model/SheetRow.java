package model;

import java.util.Date;

public class SheetRow {

	private String item;
	private Category category;
	private Price price;
	private Date createdAt;
	private Date lastModified;

	public SheetRow() {
		this.price = new Price(0, 0);
		this.createdAt = new Date();
		this.lastModified = new Date();
	}

	public SheetRow(String item, Category category, Price price) {
		this.item = item;
		this.category = category;
		this.price = price;
		this.createdAt = new Date();
		this.lastModified = new Date();
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
