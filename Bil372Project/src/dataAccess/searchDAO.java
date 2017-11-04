package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import models.UserBean;
import models.ProjectBean;

public class searchDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	
	public static List<UserBean> searchForUser(String usernameRequest) throws SQLException {
        
		List<UserBean> result = new ArrayList<UserBean>();
        PreparedStatement ps = null;
        String searchQuery = "Select * From EMPLOYEE Where UPPER(Username) LIKE UPPER('%" + usernameRequest + "%')";
        
        try {
            ConnectionManager connect = new ConnectionManager();
            currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			rs = ps.executeQuery();
			
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserName(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setJobTitle(rs.getString("JobTitle"));
                user.setImage(rs.getString("Image"));
                user.setFirstName(rs.getString("FirstName"));
                user.setMiddleName(rs.getString("MiddleName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setValid(true);
                result.add(user);
            }
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

        return result;
    }
}
