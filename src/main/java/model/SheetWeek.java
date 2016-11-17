package model;

import java.util.TreeMap;

public class SheetWeek implements Comparable<SheetWeek> {

	private int id;
	private TreeMap<Item, Double> items;
	private int order;

	public SheetWeek() {
		this.items = new TreeMap<Item, Double>();
	}

	public SheetWeek(int id, int order) {
		this.items = new TreeMap<Item, Double>();
		this.id = id;
		this.order = order;
	}

	public SheetWeek(int id, TreeMap<Item, Double> items, int order) {
		this.id = id;
		this.items = items;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TreeMap<Item, Double> getItems() {
		return items;
	}

	public void setItems(TreeMap<Item, Double> items) {
		this.items = items;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int compareTo(SheetWeek o) {
		return Integer.compare(this.order, o.getOrder());
	}

}
