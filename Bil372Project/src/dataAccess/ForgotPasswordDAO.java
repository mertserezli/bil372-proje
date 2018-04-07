package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserBean;

public class ForgotPasswordDAO extends DAO {

	static Connection currentConnection = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect = new ConnectionManager();

	public static UserBean getPassword(UserBean bean) {
		String username = bean.getUsername();
		String searchQuery = "select * from EMPLOYEE where Username=?";
		try {

			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();
			boolean more = rs.next();
			if (!more) {
				bean.setEmail(null);
			} else if (more) {
				String email = rs.getString("Email");
				String password = rs.getString("Password");
				bean.setEmail(email);
				bean.setPassword(password);
			}
		} catch (Exception e) {
			System.out.println("Sending password failed!" + e.getMessage());
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return bean;
	}
}
