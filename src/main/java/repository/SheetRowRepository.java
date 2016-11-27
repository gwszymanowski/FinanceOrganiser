package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import general.Database;
import model.Category;
import model.Item;
import model.Price;
import model.SheetRow;
import service.ItemService;

public class SheetRowRepository implements CrudRepositoryI<SheetRow> {

	public void add(SheetRow s) {
		System.out.println(s);
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO sheetrow");
			sb.append("(title, estimated, actual, modified_at, current, isStatic, category_id) ");
			sb.append("VALUES(?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, s.getTitle());
			stmt.setDouble(2, s.getPrice().getEstimated());
			stmt.setDouble(3, s.getPrice().getActual());
			stmt.setTimestamp(4, Timestamp.from(s.getLastModified()));
			stmt.setTimestamp(5, Timestamp.from(s.getCurrent()));
			stmt.setBoolean(6, s.isStatic());
			stmt.setInt(7, s.getCategory().getId());

			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(SheetRow s) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE sheetrow SET estimated=?, actual=?, modified_at=?, ");
			sb.append("category_id=? WHERE id=?");

			stmt = conn.prepareStatement(sb.toString());

			stmt.setDouble(1, s.getPrice().getEstimated());
			stmt.setDouble(2, s.getPrice().getActual());
			stmt.setDate(3, java.sql.Date.valueOf(s.getLastModified().toString()));
			stmt.setInt(5, s.getCategory().getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			String deleteSql = "DELETE FROM sheetrow WHERE id = ?";
			stmt = conn.prepareStatement(deleteSql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteAll() {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {
			String deleteSql = "DELETE FROM sheetrow";
			stmt = conn.prepareStatement(deleteSql);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(boolean isStatic) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			String deleteSql = "DELETE FROM sheetrow WHERE isStatic=?";
			stmt = conn.prepareStatement(deleteSql);
			stmt.setBoolean(1, isStatic);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<SheetRow> getAll() {

		List<SheetRow> categories = new ArrayList<SheetRow>();

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.id, s.title, s.order_num, s.estimated, s.actual, ");
			sb.append("c.id, c.title FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
			sb.append("WHERE c.isStatic=false");
			sb.append("ORDER BY s.order_num, c.title");

			stmt = conn.prepareStatement(sb.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String title = rs.getString(2);
				int order = rs.getInt(3);

				double estimated = rs.getDouble(4);
				double actual = rs.getDouble(5);
				Price p = new Price(estimated, actual);

				int categoryId = rs.getInt(6);
				String categoryTitle = rs.getString(7);
				Category c = new Category(categoryId, categoryTitle);

				SheetRow s = new SheetRow(id, title, order, c, p);

				categories.add(s);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public List<SheetRow> getAll(boolean isStatic) {

		List<SheetRow> categories = new ArrayList<SheetRow>();

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.id, s.title, s.order_num, s.estimated, s.actual, ");
			sb.append("c.id, c.title FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
			sb.append("WHERE c.isStatic=?");
			sb.append("ORDER BY s.order_num, c.title");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setBoolean(1, isStatic);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String title = rs.getString(2);
				int order = rs.getInt(3);

				double estimated = rs.getDouble(4);
				double actual = rs.getDouble(5);
				Price p = new Price(estimated, actual);

				int categoryId = rs.getInt(6);
				String categoryTitle = rs.getString(7);
				Category c = new Category(categoryId, categoryTitle);

				SheetRow s = new SheetRow(id, title, order, c, p);

				categories.add(s);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public int getCount() {
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		int count = 0;
		try {

			String selectSql = "SELECT count(*) as count FROM sheetrow";
			stmt = conn.prepareStatement(selectSql);

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

	public List<SheetRow> getByMonth(int month) {

		List<SheetRow> categories = new ArrayList<SheetRow>();

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.id, s.title, s.order_num, s.estimated, s.actual, ");
			sb.append("c.id, c.title FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
			sb.append("ORDER BY s.order_num, c.title");

			stmt = conn.prepareStatement(sb.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String title = rs.getString(2);
				int order = rs.getInt(3);

				double estimated = rs.getDouble(4);
				double actual = rs.getDouble(5);
				Price p = new Price(estimated, actual);

				int categoryId = rs.getInt(6);
				String categoryTitle = rs.getString(7);
				Category c = new Category(categoryId, categoryTitle);

				SheetRow s = new SheetRow(id, title, order, c, p);

				categories.add(s);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}

	public List<SheetRow> getByYearMonth(int year, int month, boolean isStatic) {

		List<SheetRow> sheetRowSet = new ArrayList<SheetRow>();

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.id, s.title, s.order_num, s.estimated, s.actual, ");
			sb.append("c.id, c.title FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
			sb.append("WHERE YEAR(s.current)=? AND MONTH(s.current)=? AND s.isStatic=?");

			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			stmt.setBoolean(3, isStatic);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int id = rs.getInt(1);
				String title = rs.getString(2);
				int order = rs.getInt(3);

				double estimated = rs.getDouble(4);
				double actual = rs.getDouble(5);
				Price p = new Price(estimated, actual);

				int categoryId = rs.getInt(6);
				String categoryTitle = rs.getString(7);
				Category cat = new Category(categoryId, categoryTitle);

				SheetRow s = new SheetRow(id, title, order, cat, p);

				sheetRowSet.add(s);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sheetRowSet;

	}

	public void fill(int lastYear) {

		ItemService itemService = new ItemService();
		List<Item> items = itemService.getAll();

		Instant lastDate = getLastAddedDate();

		ZonedDateTime zdt = ZonedDateTime.ofInstant(lastDate, ZoneId.systemDefault());
		int year = zdt.getYear();
		int month = zdt.getMonthValue();

		if (month != 0)
			month--;

		for (int i = year; i <= lastYear; i++) {

			for (int j = month; j < 12; j++) {

				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, i);
				c.set(Calendar.MONTH, j);
				Instant givenDate = c.toInstant();

				for (Item item : items) {

					SheetRow s = new SheetRow(item.getTitle(), item.getCategory(), item.getOrder(), true, givenDate);

					if (isAdded(s) == false)
						add(s);
				}
			}
			month = 0;
		}
	}

	private Instant getLastAddedDate() {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		Timestamp time = null;

		try {

			String select = "SELECT MAX(current) FROM sheetrow s where s.isStatic=true";
			stmt = conn.prepareStatement(select);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			time = rs.getTimestamp(1);

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (time == null)
			return Instant.now();

		return time.toInstant();
	}

	public Instant getEarliestDate() {
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		Timestamp time = null;

		try {

			String select = "SELECT MIN(current) FROM sheetrow s where s.isStatic=true";
			stmt = conn.prepareStatement(select);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			time = rs.getTimestamp(1);

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (time == null)
			return Instant.now();

		return time.toInstant();
	}

	private boolean isAdded(SheetRow s) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		int count = 0;

		try {

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(*) FROM sheetrow s WHERE ");
			sb.append("YEAR(s.current)=? AND MONTH(s.current)=? ");
			sb.append("AND isStatic=true AND title=? AND category_id=?");
			stmt = conn.prepareStatement(sb.toString());

			ZonedDateTime zdt = ZonedDateTime.ofInstant(s.getCurrent(), ZoneId.systemDefault());
			int year = zdt.getYear();
			int month = zdt.getMonthValue();

			stmt.setInt(1, year);
			stmt.setInt(2, month);
			stmt.setString(3, s.getTitle());
			stmt.setInt(4, s.getCategory().getId());

			ResultSet rs = stmt.executeQuery();
			rs.next();

			count = rs.getInt(1);

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (count == 0)
			return false;

		return true;
	}

}
