package dataAccess;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Calendar;

import models.ProjectBean;

public class ProjectDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect=null;
	
	public static ProjectBean getProject(ProjectBean project){
		int pid=project.getPid();
		String query="select * from project where pid=?";
		try{
			connect=new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(query);
			ps.setInt(1,pid);
			rs = ps.executeQuery();
			boolean more = rs.next();
			if(more){
				project.setCreationDate(rs.getDate("creation_date"));
				project.setDescription(rs.getString("description"));
				//project.setState(rs.get);
				project.setTitle(rs.getString("title"));
				project.setVotenum(rs.getInt("votenum"));
				project.setTags((String[])rs.getArray("tags").getArray());
				project.setMeeting_dates((Date[])rs.getArray("meeting_dates").getArray());
			}
			else{
				project.setTitle("Project Not Found");
			}
		}
		catch(Exception e){
			
		}
		return project;
	}
	
	public static ProjectBean setNewMeeting(ProjectBean project,String date){
		
		return project;
	}
	public static boolean createProject(ProjectBean project){
		String insertQuery="insert into project (title,description,tags,creation_date) values (?,?,?,?)";
		try{
			connect=new ConnectionManager();
			currentCon=connect.getConnection();
			ps=currentCon.prepareStatement(insertQuery);
			ps.setString(1, project.getTitle());
			ps.setString(2, project.getDescription());
			Array tags=currentCon.createArrayOf("TEXT", project.getTags());
			ps.setArray(3,tags);
			ps.setDate(4,new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
