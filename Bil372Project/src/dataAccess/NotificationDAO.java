package dataAccess;
import models.NotificationBean;
import models.UserBean;
import models.ProjectBean;
import models.TaskBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	
	public static List<NotificationBean> getNotifications(String usernameRequest) throws SQLException{
		List<NotificationBean> result = new ArrayList<NotificationBean>();
		String searchQuery = "Select * From emp_notification where username=?";

		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(searchQuery);
			ps.setString(1,usernameRequest);
			rs = ps.executeQuery();

			while (rs.next()) {
				NotificationBean n = new NotificationBean();
				n.setNid(rs.getInt("nid"));
				n.setNotification(rs.getString("notification"));
				n.setUsername(rs.getString("username"));
				n.setDate(rs.getDate("date"));
				result.add(n);
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
	public static List<NotificationBean> getUpcomingMeetings(String usernameRequest)
	{	
		List<NotificationBean> result = new ArrayList<NotificationBean>();
		java.sql.Date dateNow = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		UserBean user = new UserBean();
		user.setUserName(usernameRequest);
		ArrayList<ProjectBean> projects=UploadDAO.loadProjects(user);

		for(ProjectBean p:projects)
		{
			ProjectDAO.getProject(p);
			if(p.getMeeting_dates()!=null)
			{
				for(Date d:p.getMeeting_dates())
				{
					if(dateNow.getMonth()==d.getMonth() && dateNow.getYear()==d.getYear() && d.getDay()-dateNow.getDay()<=7)
					{
						int day=d.getDay()-dateNow.getDay();
						NotificationBean n = new NotificationBean();
						n.setDate(dateNow);
						n.setNotification("(Project) "+p.getTitle()+" adli projenizin "+d+" tarihli meetingine "+day+" gün kalmistir");
						n.setUsername(usernameRequest);
						result.add(n);
					}
				}
			}
		}
		return result;
	}
	public static List<NotificationBean> getUpcomingDeadlines(String usernameRequest) throws SQLException
	{
		List<NotificationBean> result = new ArrayList<NotificationBean>();
		java.sql.Date dateNow = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		List<TaskBean> tasks=TaskDAO.searchTasksForUser(usernameRequest);
		for(TaskBean t:tasks)
		{
			Date d=t.getDeadline();
			if(d.getYear()==dateNow.getYear() && d.getMonth()==dateNow.getMonth() && d.getDay()-dateNow.getDay()<=7)
			{
				int day=d.getDay()-dateNow.getDay();
				NotificationBean n = new NotificationBean();
				n.setDate(dateNow);
				n.setUsername(usernameRequest);
				n.setNotification("(Task) "+t.getTitle()+" adli taskinizin "+d+" tarihli deadline tarihine "+day+" gün kalmistir");
				result.add(n);
			}
		}
		return result;
	}
	public static void sendNotification(NotificationBean n)
	{
		String insertQuery= "insert into emp_notification(username,notification,date) values(?,?,?)";
		try {
			ConnectionManager connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps=currentCon.prepareStatement(insertQuery);
			//ps.setString(1,mes.getmID());
			ps.setString(1,n.getUsername());
			ps.setString(2,n.getNotification());
			ps.setDate(3,n.getDate());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
}	

