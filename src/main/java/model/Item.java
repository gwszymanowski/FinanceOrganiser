package model;

public class Item implements Comparable<Item> {

	private int id;
	private String title;
	private int order;

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

	public int compareTo(Item e) {
		return Integer.compare(this.order, e.getOrder());
	}

}
