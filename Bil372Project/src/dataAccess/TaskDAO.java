package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.TaskBean;

public class TaskDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps=null;
	
	public static List<TaskBean> searchTasksForUser(String username) throws SQLException
	{
		List<TaskBean> tasks = new ArrayList<TaskBean>();
		String searchQuery="Select * From TASK LEFT JOIN PREREQUI_TASK USING(TID) Where Username=? ORDER BY deadline";
		try
		{
            ConnectionManager connect = new ConnectionManager();
            currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setString(1,username);
			rs = ps.executeQuery();
			while(rs.next())
			{
				TaskBean task = new TaskBean();
				task.setUsername(username);
				task.setTid(rs.getInt("TID"));
				task.setPid(rs.getInt("PID"));
				task.setDescription(rs.getString("description"));
				task.setPerformanceCriteria(rs.getString("performancecriteria"));
				task.setDeadline(rs.getDate("deadline"));
				task.setPrerequisite(rs.getInt("PRETID"));
				tasks.add(task);
			}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return tasks;
		}
		return tasks;
		
	}
}