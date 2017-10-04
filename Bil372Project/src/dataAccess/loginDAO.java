package dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import models.UserBean;

public class loginDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean bean) {
		Statement stmt = null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from EMPLOYEE where Username='" + username + "' AND Password='" + password + "'";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
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
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return bean;
	}
}
