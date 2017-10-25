package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.UserBean;

public class SignUpDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;

	public static UserBean signUpCheck(UserBean bean) {
		String username = bean.getUsername();
		String usermail=bean.getEmail();
		String searchQuery = "select * from EMPLOYEE where Username=? or email=?";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps=currentCon.prepareStatement(searchQuery);
			ps.setString(1,username);
			ps.setString(2,usermail);
			rs=ps.executeQuery();
			boolean more = rs.next();
			if (!more) {//username ve email unique olacağı için bu koşul doğru olmalı
				bean.setValid(true);
			}
			else{
				bean.setValid(false);
			}
			return bean;
		}
		catch(Exception e){
			e.printStackTrace();
			bean.setValid(false);
			return bean;
		}
		
		
	}
	public static boolean signUp(UserBean bean) {
		String insertQuery= "insert into employee(username,firstname,lastname,email,password,jobtitle) values (?,?,?,?,?,?)";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps=currentCon.prepareStatement(insertQuery);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getFirstName());
			ps.setString(3, bean.getLastName());
			ps.setString(4, bean.getEmail());
			ps.setString(5, bean.getPassword());
			ps.setString(6, bean.getJobTitle());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
}
