package model;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class SheetMonth implements Comparable<SheetMonth> {

	private int id;
	private Instant date;
	private List<SheetRow> rows;

	public SheetMonth() {
		this.rows = new LinkedList<SheetRow>();
	}

	public SheetMonth(int id, Instant date) {
		this.rows = new LinkedList<SheetRow>();
		this.id = id;
		this.date = date;
	}

	public SheetMonth(Instant date) {
		this.rows = new LinkedList<SheetRow>();
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<SheetRow> getRows() {
		return rows;
	}

	public void setRows(List<SheetRow> rows) {
		this.rows = rows;
	}

	public void addToSet(SheetRow row) {
		this.rows.add(row);
	}

	public int compareTo(SheetMonth s) {

		Calendar c = Calendar.getInstance();

		java.util.Date d1 = Date.from(date);
		c.setTime(d1);
		int curr1 = c.get(Calendar.MONTH);

		java.util.Date d2 = Date.from(s.getDate());
		c.setTime(d2);
		int curr2 = c.get(Calendar.MONTH);

		return Integer.compare(curr1, curr2);

	}

	public boolean checkMonths(int month) {

		java.util.Date d = Date.from(date);

		Calendar c = Calendar.getInstance();

		c.setTime(d);

		int curr = c.get(Calendar.MONTH);

		if (curr == month)
			return true;
		else
			return false;

	}

}
