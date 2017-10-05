package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import models.UserBean;

public class loginDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean bean) {
		//Statement stmt = null;
		PreparedStatement ps=null;
		String username = bean.getUsername();
		String password = bean.getPassword();
		String searchQuery = "select * from EMPLOYEE where Username=? AND Password=?";//prepared statement icin query degistirildi (cem) 5.11.2017
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			//prepared statment eklentisi yapıldı (cem) 5.11.2017
			ps=currentCon.prepareStatement(searchQuery);
			ps.setString(1,username);
			ps.setString(2, password);
			//stmt = currentCon.createStatement(); cikarildi (cem) 5.11.2017
			rs=ps.executeQuery();
			//rs = stmt.executeQuery(searchQuery); cikarildi (cem) 5.11.2017
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
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
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
