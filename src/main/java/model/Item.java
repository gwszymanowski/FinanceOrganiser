package model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item implements Comparable<Item>, Serializable, Titleable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private Category category;

	public Item() {
	}

	public Item(String title) {
		this.title = title;
	}

	public Item(int id, String title, Category category) {
		this.id = id;
		this.title = title;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	@XmlElement
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Item other = (Item) obj;
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
		return true;
	}

	public int compareTo(Item e) {
		return Integer.compare(getTitle().charAt(0), e.getTitle().charAt(0));
	}

	@Override
	public String toString() {
		return title;
	}

}
