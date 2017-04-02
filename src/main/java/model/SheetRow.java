package model;

import java.io.Serializable;
import java.time.Instant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SheetRow implements Comparable<SheetRow>, Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private Category category;
	private Price price;
	private boolean isStatic;
	private Instant createdAt;
	private Instant lastModified;
	private Instant current;

	public SheetRow() {
		this.price = new Price(0, 0);
		this.createdAt = Instant.now();
		this.lastModified = Instant.now();
		this.isStatic = false;
	}

	public SheetRow id(int id) {
		this.id = id;
		return this;
	}

	public SheetRow title(String title) {
		this.title = title;
		return this;
	}

	public SheetRow category(Category category) {
		this.category = category;
		return this;
	}

	public SheetRow price(Price price) {
		this.price = price;
		return this;
	}

	public SheetRow state(boolean isStatic) {
		this.isStatic = isStatic;
		return this;
	}

	public SheetRow currentTime(Instant current) {
		this.current = current;
		return this;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.lastModified = Instant.now();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		this.lastModified = Instant.now();
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
		this.lastModified = Instant.now();
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getLastModified() {
		return lastModified;
	}

	public Instant getCurrent() {
		return current;
	}

	public void setCurrent(Instant current) {
		this.current = current;
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

	public int compareTo(SheetRow s) {
		return Integer.compare(getTitle().charAt(0), s.getTitle().charAt(0));
	}

	@Override
	public String toString() {
		return "SheetRow [id=" + id + ", title=" + title + ", category=" + category.getFullString() + ", price=" + price
				+ ", isStatic=" + isStatic + ", createdAt=" + createdAt + ", lastModified=" + lastModified
				+ ", current=" + current + "]";
	}

}
