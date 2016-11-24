package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import general.Database;
import model.Category;
import model.Price;
import model.SheetRow;

public class SheetRowRepository implements CrudRepositoryI<SheetRow> {

	public void add(SheetRow s) {
		
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO sheetrow");
			sb.append("(title, estimated, actual, created_at, modified_at, category_id) ");
			sb.append("VALUES(?, ?, ?, ?, ?, ?)");
		
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, s.getTitle());
			stmt.setDouble(2, s.getPrice().getEstimated());
			stmt.setDouble(3, s.getPrice().getActual());
			stmt.setTimestamp(4, Timestamp.from(s.getCreatedAt()));
			stmt.setTimestamp(5, Timestamp.from(s.getLastModified()));
			stmt.setInt(6, s.getCategory().getId());

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
	
	public List<SheetRow> getByMonth(int month){ 
		
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
	
	public Set<SheetRow> getByYearMonth(int year, int month) {
		
		Set<SheetRow> sheetRowSet = new TreeSet<SheetRow>();
		
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {
	
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.id, s.title, s.order_num, s.estimated, s.actual, ");
			sb.append("c.id, c.title FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
			sb.append("WHERE YEAR(s.current)=? AND MONTH(s.current)=?");
	
			stmt = conn.prepareStatement(sb.toString());
			stmt.setInt(1, year);
			stmt.setInt(2, month);
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
	

}
