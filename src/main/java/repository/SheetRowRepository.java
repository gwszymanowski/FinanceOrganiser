package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import general.Database;
import interfaces.CrudI;
import model.Category;
import model.SheetRow;

public class SheetRowRepository implements CrudI<SheetRow> {

	public void add(SheetRow s) {
		
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO category");
			sb.append("(estimated, actual, created_at, modified_at, item_id, category_id) ");
			sb.append("VALUES(?, ?, ?, ?, ?, ?)");
		
			stmt = conn.prepareStatement(sb.toString());
			stmt.setDouble(1, s.getPrice().getEstimated());
			stmt.setDouble(2, s.getPrice().getActual());
			stmt.setDate(3, java.sql.Date.valueOf(s.getCreatedAt().toString()));
			stmt.setDate(4, java.sql.Date.valueOf(s.getLastModified().toString()));
			stmt.setInt(5, s.getItem().getId());
			stmt.setInt(6, s.getCategory().getId());

			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			sb.append("item_id=?, category_id=? WHERE id=?");
			
			stmt = conn.prepareStatement(sb.toString());

			stmt.setDouble(1, s.getPrice().getEstimated());
			stmt.setDouble(2, s.getPrice().getActual());
			stmt.setDate(3, java.sql.Date.valueOf(s.getLastModified().toString()));
			stmt.setInt(4, s.getItem().getId());
			stmt.setInt(5, s.getCategory().getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			sb.append("SELECT s FROM sheetrow s ");
			sb.append("INNER JOIN category c ON(s.category_id=c.id) ");
			sb.append("INNER JOIN item i ON(s.item_id=i.id)");
		
			stmt = conn.prepareStatement(sb.toString());

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}
	
	public int getCount() {
		return 0;
	}

}
