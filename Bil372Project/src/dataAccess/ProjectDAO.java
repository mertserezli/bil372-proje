package dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				project.setCreation_Date(rs.getDate("creation_date"));
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
	
}
