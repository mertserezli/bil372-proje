package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.ProjectBean;
import models.UserBean;

public class Work_Emp_ProDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	static ConnectionManager connect=null;

	public static ArrayList<UserBean> getWorkers(ProjectBean project) {
		ArrayList<UserBean> workers=new ArrayList<>();
		int pid=project.getPid();
		String query= "select username from work_emp_pro where pid=?";
		try{
			connect=new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(query);
			ps.setInt(1,pid);
			rs=ps.executeQuery();
			
			while(rs.next()){
				UserBean worker=new UserBean();
				worker.setUserName(rs.getString("username"));
				workers.add(worker);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return workers;
	}
	public static boolean addEmployee(UserBean user,ProjectBean project){
		String query="insert into work_emp_pro (pid,username) values(?,?)";
		try{
			connect=new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(query);
			ps.setInt(1,project.getPid());
			ps.setString(2, user.getUsername());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();return false;
		}
		return true;
	}
}
