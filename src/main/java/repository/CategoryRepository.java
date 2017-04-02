package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import general.Database;
import model.Category;

public class CategoryRepository implements CrudRepositoryI<Category> {

	public void add(Category cat) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			String insertSql = "INSERT INTO category(title) VALUES(?)";

			stmt = conn.prepareStatement(insertSql);
			stmt.setString(1, cat.getTitle());

			stmt.executeUpdate();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Category cat) {

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();

		try {

			String updateSql = "UPDATE category SET title=? WHERE id=?";

			stmt = conn.prepareStatement(updateSql);

			stmt.setString(1, cat.getTitle());
			stmt.setInt(2, cat.getId());

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

			String deleteSql = "DELETE FROM category WHERE id = ?";
			stmt = conn.prepareStatement(deleteSql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Category> getAll() {

		List<Category> categories = new ArrayList<Category>();

		PreparedStatement stmt = null;
		Database db = Database.getInstance();
		Connection conn = db.getConnection();
		try {

			String selectSql = "SELECT * FROM category";

			stmt = conn.prepareStatement(selectSql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Integer id = rs.getInt("id");
				String title = rs.getString("title");

				Category s = new Category().id(id).title(title);
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

			String selectSql = "SELECT count(*) as count FROM category";

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
