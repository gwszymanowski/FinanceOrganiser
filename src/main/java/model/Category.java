package model;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;

	public Category() {
	}

	public Category(String title) {
		this.title = title;
	}

	public Category(int id, String title) {
		this.id = id;
		this.title = title;
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
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Category other = (Category) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getFullString() {
		return "Category [id=" + id + ", title=" + title + "]";
	}

	@Override
	public String toString() {
		return title;
	}

}
