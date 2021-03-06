package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import models.UserBean;

public class LoginDAO extends DAO {
	static Connection currentConnection = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect = new ConnectionManager();
	public static UserBean login(UserBean bean) {
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from EMPLOYEE where Username=? AND Password=?";
		try {
			
			currentConnection = connect.getConnection();
			ps = currentConnection.prepareStatement(searchQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			boolean more = rs.next();
			if (!more) {
				bean.setValid(false);
			} else if (more) {
				String jobTitle = rs.getString("JobTitle");
				String image = rs.getString("Image");
				String firstName = rs.getString("FirstName");
				String middleName = rs.getString("MiddleName");
				String lastName = rs.getString("LastName");
				String email = rs.getString("Email");
				bean.setJobTitle(jobTitle);
				bean.setImage(image);
				bean.setFirstName(firstName);
				bean.setMiddleName(middleName);
				bean.setLastName(lastName);
				bean.setEmail(email);

				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} finally {
			finalizeConnection(currentConnection, ps, rs);
		}
		return bean;
	}
}
