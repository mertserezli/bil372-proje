package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e2) {

		}
		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:postgresql://projectmanagementdb.c7iazxagbuhz.eu-central-1.rds.amazonaws.com/projectmanagementdb";
			String username = "mertserezli";
			String password = "55numara";
			// create a connection to the database
			conn = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
				e.printStackTrace();
		}
		if (conn == null)
			System.out.println("hata conn null olmus");
		return conn;
	}
}