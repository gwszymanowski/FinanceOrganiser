package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import general.Database;
import model.Category;
import model.Item;

public class ItemRepository implements CrudRepositoryI<Item> {

	public void add(Item item) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			String insertSql = "INSERT INTO item(title, category_id) VALUES(?, ?)";

			stmt = conn.prepareStatement(insertSql);
			stmt.setString(1, item.getTitle());
			stmt.setInt(2, item.getCategory().getId());

			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(Item item) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			String updateSql = "UPDATE item SET title=?, category_id=? WHERE id=?";

			stmt = conn.prepareStatement(updateSql);

			stmt.setString(1, item.getTitle());
			stmt.setInt(2, item.getCategory().getId());
			stmt.setInt(3, item.getId());

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

			String deleteSql = "DELETE FROM item WHERE id = ?";
			stmt = conn.prepareStatement(deleteSql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Item> getAll() {

		List<Item> items = new ArrayList<Item>();

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT i.id, i.title, i.order_num, c.id, c.title FROM item i ");
			sb.append("INNER JOIN category c ON(i.category_id=c.id)");
			
			stmt = conn.prepareStatement(sb.toString());

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt(1);
				String title = rs.getString(2);
				int order = rs.getInt(3);
				int category_id = rs.getInt(4);
				String category_title = rs.getString(5);
				Category c = new Category(category_id, category_title);

				Item s = new Item(id, title, order, c);
				items.add(s);

			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}

	public int getCount() {
		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		int count = 0;
		try {

			String selectSql = "SELECT count(*) as count FROM item";

			stmt = conn.prepareStatement(selectSql);

			ResultSet rs = stmt.executeQuery();
			rs.next();

			count = rs.getInt("count");

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

}
