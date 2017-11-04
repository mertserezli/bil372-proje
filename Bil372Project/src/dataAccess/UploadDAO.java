package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserBean;

public class UploadDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;

	
	public static boolean uploadImage(UserBean bean) {
		String insertQuery= "UPDATE employee set image=? where username=?";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps=currentCon.prepareStatement(insertQuery);
			ps.setString(1,bean.getImage());
			ps.setString(2, bean.getUsername());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
}
