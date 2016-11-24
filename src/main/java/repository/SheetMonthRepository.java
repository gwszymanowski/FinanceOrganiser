package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import general.Database;
import model.SheetMonth;
import model.SheetRow;
import service.SheetRowService;

public class SheetMonthRepository {

	private SheetRowService sheetRowService;

	public SheetMonthRepository() {
		this.sheetRowService = new SheetRowService();
	}

	public void fill(int year) {

		if (getCountByYear(year) > 0)
			throw new IllegalStateException("Sheets for this year are already created");
		else {

			Calendar c = Calendar.getInstance();

			int month = c.get(Calendar.MONTH);

			for (int i = month; i < 12; i++) {
				c.set(Calendar.MONTH, i);
				c.set(Calendar.YEAR, year);
				add(c.toInstant());
			}

		}

	}

	private void add(Instant inst) {
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			String query = "INSERT INTO sheetmonth(current) VALUES(?);";

			stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, Timestamp.from(inst));

			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SheetMonth> getSheets(int year) {

		List<SheetMonth> sheets = new ArrayList<SheetMonth>();

		for (int i = 0; i < 12; i++)
			sheets.add(getByYearMonth(year, i));

		return sheets;
	}

	private SheetMonth getByYearMonth(int year, int month) {

		SheetMonth sheet = new SheetMonth();

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);

		Instant inst = c.toInstant();

		sheet.setDate(inst);

		Set<SheetRow> set = new TreeSet<SheetRow>();

		set = sheetRowService.getByYearMonth(year, month);

		sheet.setRows(set);

		return sheet;
	}

	private int getCountByYear(int year) {
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		int count = 0;
		try {

			String selectSql = "SELECT count(*) as count FROM sheetmonth s where YEAR(s.current)=?";
			stmt = conn.prepareStatement(selectSql);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			count = rs.getInt("count");

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

}
