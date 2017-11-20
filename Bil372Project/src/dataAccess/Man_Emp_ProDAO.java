package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.ProjectBean;
import models.UserBean;

public class Man_Emp_ProDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect=null;
	public static UserBean getManeger(ProjectBean project){
		UserBean manager=new UserBean();
		String query="select username from man_emp_pro where pid=?";
		try{
			connect=new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(query);
			ps.setInt(1,project.getPid());
			rs = ps.executeQuery();
			if(rs.next()){
				manager.setUserName(rs.getString("username"));
				ProfileDAO.loadUser(manager);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return manager;
	}
	public static boolean setManager(ProjectBean project,UserBean user){
		String query="insert into man_emp_pro (pid,username) values (?,?)";
		try{
			connect=new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(query);
			ps.setInt(1,project.getPid());
			ps.setString(2, user.getUsername());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		connect=null;
		currentCon=null;
		return true;
	}
}
