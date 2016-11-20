package model;

public class Category {

	private int id;
	private String title;
	private int order;

	public Category() {
	}

	public Category(String title) {
		this.title = title;
	}

	public Category(String title, int order) {
		this.title = title;
		this.order = order;
	}

	public Category(int id, String title, int order) {
		this.id = id;
		this.title = title;
		this.order = order;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order;
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
		if (order != other.order)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", title=" + title + ", order=" + order + "]";
	}

}
