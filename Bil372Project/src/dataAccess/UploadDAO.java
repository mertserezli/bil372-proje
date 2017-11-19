package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.ProjectBean;
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
	
	public static ArrayList<ProjectBean> loadProjects(UserBean user){
		ArrayList<ProjectBean> projects=new ArrayList<>();
		ArrayList<Integer> projectIDs=new ArrayList<>();
		String username=user.getUsername();
		String SearchEmp_Project="select pid from emp_project where username=?"; 
		String SearchProject="select * from project where pid=?";
		try{
			ConnectionManager connect= new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(SearchEmp_Project);
			ps.setString(1, username);
			rs=ps.executeQuery();
			while(rs.next()){
				projectIDs.add(rs.getInt("pid"));
			}
			ps=currentCon.prepareStatement(SearchProject);
			for(int pid:projectIDs){
				ps=currentCon.prepareStatement(SearchProject);
				ps.setInt(1, pid);
				rs=ps.executeQuery();
				while(rs.next()){
					ProjectBean project=new ProjectBean();
					project.setPid(rs.getInt("pid"));
					project.setState(rs.getString("state"));
					project.setTitle(rs.getString("title"));
					project.setDescription(rs.getString("description"));
					project.setCreationDate(rs.getDate("creation_date"));
					project.setVotenum(rs.getInt("votenum"));
					projects.add(project);
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return projects;
	}
}
