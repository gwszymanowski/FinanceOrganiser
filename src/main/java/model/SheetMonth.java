package model;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

public class SheetMonth implements Comparable<SheetMonth> {

	private int id;
	private Instant date;
	private Set<SheetRow> rows;

	public SheetMonth() {
		this.rows = new TreeSet<SheetRow>();
	}

	public SheetMonth(int id, Instant date) {
		this.rows = new TreeSet<SheetRow>();
		this.id = id;
		this.date = date;
	}

	public SheetMonth(Instant date) {
		this.rows = new TreeSet<SheetRow>();
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

	public Set<SheetRow> getRows() {
		return rows;
	}

	public void setRows(Set<SheetRow> rows) {
		this.rows = rows;
	}

	public void addToSet(SheetRow row) {
		this.rows.add(row);
	}

	public int compareTo(SheetMonth s) {

		return 0;

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
