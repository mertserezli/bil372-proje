package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserBean;

public class ForgotPasswordDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	public static UserBean getPassword(UserBean bean) {
		String username = bean.getUsername();
		String searchQuery = "select * from EMPLOYEE where Username=?";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps=currentCon.prepareStatement(searchQuery);
			ps.setString(1,username);
			rs=ps.executeQuery();
			boolean more = rs.next();
			if (!more) {
				bean.setEmail(null);
			}
			else if (more) {
				String email = rs.getString("Email");
				String password = rs.getString("Password");
				bean.setEmail(email);
				bean.setPassword(password);
			}
		}
		catch(Exception e){
			System.out.println("Sending password failed!"+e.getMessage());
		}
		finally {
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
