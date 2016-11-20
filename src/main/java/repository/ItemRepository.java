package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import general.Database;
import model.Item;

public class ItemRepository implements CrudRepositoryI<Item> {

	public void add(Item item) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			String insertSql = "INSERT INTO item(title) VALUES(?)";

			stmt = conn.prepareStatement(insertSql);
			stmt.setString(1, item.getTitle());

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

			String updateSql = "UPDATE item SET title=? WHERE id=?";

			stmt = conn.prepareStatement(updateSql);

			stmt.setString(1, item.getTitle());
			stmt.setInt(2, item.getId());

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

			String selectSql = "SELECT * FROM item";

			stmt = conn.prepareStatement(selectSql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String title = rs.getString("title");
				int order = rs.getInt("order_num");

				Item s = new Item(id, title, order);
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
