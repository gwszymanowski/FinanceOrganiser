package model;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

public class SheetMonth implements Comparable<SheetMonth> {

	private int id;
	private Date date;
	private TreeSet<SheetWeek> weeks;

	public SheetMonth() {
		this.weeks = new TreeSet<SheetWeek>();
	}

	public SheetMonth(int id, Date date, TreeSet<SheetWeek> weeks) {
		this.id = id;
		this.date = date;
		this.weeks = weeks;
	}

	public SheetMonth(int id, Date date) {
		this.weeks = new TreeSet<SheetWeek>();
		this.id = id;
		this.date = date;
	}

	public SheetMonth(Date date) {
		this.weeks = new TreeSet<SheetWeek>();
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TreeSet<SheetWeek> getWeeks() {
		return weeks;
	}

	public void setWeeks(TreeSet<SheetWeek> weeks) {
		this.weeks = weeks;
	}

	public int compareTo(SheetMonth e) {

		if (this.date == null || e.getDate() == null)
			throw new NullPointerException("Object cannot be compared if date field is null");

		Calendar cal = Calendar.getInstance();
		
		cal.setTime(this.date);
		int thismonth = cal.get(Calendar.MONTH);

		cal.setTime(e.getDate());
		int givenMonth = cal.get(Calendar.MONTH);

		return Integer.compare(thismonth, givenMonth);
	}

}
