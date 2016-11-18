package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	
	private static Database instance;
	private static Connection con;

	private Database() {
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Connection getConnection() {

		if (con == null) {

			String driver = "com.mysql.jdbc.Driver";
			try {
				Class.forName(driver).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				String host = "jdbc:mysql://localhost:3306/finance_organiser";
				String username = "root";
				String password = "root";

				con = DriverManager.getConnection(host, username, password);
			} catch (SQLException ex) {
				Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return con;
	}
}
