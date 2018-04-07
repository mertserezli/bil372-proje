package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserBean;

public class ProfileDAO extends DAO {
	static Connection currentConnection = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect = null;

	public static UserBean loadUser(UserBean user) {

		String query = "select * from employee where username=?";
		try {
			connect = new ConnectionManager();
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(query);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setEmail(rs.getString("email"));
				user.setFirstName(rs.getString("firstname"));
				user.setImage(rs.getString("image"));
				user.setJobTitle(rs.getString("jobtitle"));
				user.setLastName(rs.getString("lastname"));
				user.setMiddleName(rs.getString("middlename"));
				user.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return user;
	}
}
