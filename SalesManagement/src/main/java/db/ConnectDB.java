package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;

	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public void connect() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		con = DriverManager.getConnection(DBConfiguation.databaseURL);
	}
	

	public void disconnect() {
		if (con == null) {
			return;
		}
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return con;
	}
}
