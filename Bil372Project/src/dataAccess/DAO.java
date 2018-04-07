package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	public static void finalizeConnection(Connection connection, PreparedStatement preparedStatement,
			ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
			resultSet = null;
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
			}
			preparedStatement = null;
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
			connection = null;
		}
	}

	public static void finalizeConnection(Connection connection, PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
			}
			preparedStatement = null;
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
			connection = null;
		}
	}

}
