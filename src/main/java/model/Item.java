package model;

public class Item implements Comparable<Item> {

	private int id;
	private String name;
	private int order;

	public Item() {
	}

	public Item(String name, int order) {
		this.name = name;
		this.order = order;
	}

	public Item(int id, String name, int order) {
		this.id = id;
		this.name = name;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
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
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		return true;
	}

	public int compareTo(Item e) {
		return Integer.compare(this.order, e.getOrder());
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", order=" + order + "]";
	}
}
