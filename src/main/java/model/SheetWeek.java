package model;

import java.util.LinkedHashSet;

public class SheetWeek implements Comparable<SheetWeek> {

	private int id;
	private LinkedHashSet<SheetRow> items;
	private int order;

	public SheetWeek() {
		this.items = new LinkedHashSet<SheetRow>();
	}

	public SheetWeek(int id, int order) {
		this.items = new LinkedHashSet<SheetRow>();
		this.id = id;
		this.order = order;
	}

	public SheetWeek(int id, LinkedHashSet<SheetRow> items, int order) {
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

	public LinkedHashSet<SheetRow> getItems() {
		return items;
	}

	public void setItems(LinkedHashSet<SheetRow> items) {
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
