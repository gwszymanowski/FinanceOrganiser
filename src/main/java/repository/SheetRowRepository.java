package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import general.Database;
import model.SheetRow;

public class SheetRowRepository implements CrudRepositoryI<SheetRow> {

	public void add(SheetRow s) {
		
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO category");
			sb.append("(title, estimated, actual, created_at, modified_at, category_id) ");
			sb.append("VALUES(?, ?, ?, ?, ?, ?)");
		
			stmt = conn.prepareStatement(sb.toString());
			stmt.setString(1, s.getItem());
			stmt.setDouble(2, s.getPrice().getEstimated());
			stmt.setDouble(3, s.getPrice().getActual());
			stmt.setDate(4, java.sql.Date.valueOf(s.getCreatedAt().toString()));
			stmt.setDate(5, java.sql.Date.valueOf(s.getLastModified().toString()));
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
			sb.append("SELECT * FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
		
			stmt = conn.prepareStatement(sb.toString());
			System.out.println(sb.toString());
			ResultSet rs = stmt.executeQuery();
			

//			while (rs.next()) {
//
//				Integer id = rs.getInt("id");
//				String title = rs.getString("title");
//				int order = rs.getInt("order_num");
//
//				SheetRow s = new SheetRow();
//
//				categories.add(s);
//
//			}
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

}
