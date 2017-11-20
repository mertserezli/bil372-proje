package dataAccess;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import models.CommentBean;
import models.ProjectBean;
import models.UserBean;

public class ProjectDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static ConnectionManager connect = null;

	public static ProjectBean getProject(ProjectBean project) {
		int pid = project.getPid();
		String query = "select * from project where pid=?";
		try {
			connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			boolean more = rs.next();
			if (more) {
				project.setCreation_Date(rs.getDate("creation_date"));
				project.setDescription(rs.getString("description"));
				// project.setState(rs.get);
				project.setTitle(rs.getString("title"));
				project.setVotenum(rs.getInt("votenum"));
				try {
					project.setTags((String[]) rs.getArray("tags").getArray());

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					project.setMeeting_dates((Date[]) rs.getArray("meeting_dates").getArray());
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				project.setTitle("Project Not Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	public static ProjectBean setNewMeeting(ProjectBean project, String date) {
		int i;
		Date[] meetings = null;
		String query = "update project set meeting_dates=? where pid=?";
		String day = date.substring(0, date.indexOf("."));
		date = date.substring(date.indexOf(".") + 1);
		String month = date.substring(0, date.indexOf("."));
		date = date.substring(date.indexOf(".") + 1);
		String year = date;
		Date newMeeting = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1, Integer.parseInt(day));

		if (project.getMeeting_dates() != null) {
			meetings = new Date[project.getMeeting_dates().length + 1];

			for (i = 0; i < project.getMeeting_dates().length; i++) {
				meetings[i] = project.getMeeting_dates()[i];
			}
			meetings[i] = newMeeting;
		} else {
			meetings = new Date[1];
			meetings[0] = newMeeting;
		}

		try {
			connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setArray(1, currentCon.createArrayOf("DATE", meetings));
			ps.setInt(2, project.getPid());
			ps.executeUpdate();
			project.setMeeting_dates(meetings);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	public static boolean createProject(ProjectBean project) {
		String insertQuery = "insert into project (title,description,tags,creation_date) values (?,?,?,?)";
		try {
			connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(insertQuery);
			ps.setString(1, project.getTitle());
			ps.setString(2, project.getDescription());
			Array tags = currentCon.createArrayOf("TEXT", project.getTags());
			ps.setArray(3, tags);
			ps.setDate(4, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static ArrayList<UserBean> getEmployees(ProjectBean project) {
		int pid = project.getPid();
		ArrayList<UserBean> result = new ArrayList<UserBean>();
		String query = "select * from work_emp_pro w, employee e where w.pid=? AND w.username=e.username";
		try {
			connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserBean employee = new UserBean();

				employee.setUserName(rs.getString("username"));
				employee.setEmail(rs.getString("email"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setJobTitle(rs.getString("jobtitle"));
				employee.setLastName(rs.getString("lastname"));
				employee.setMiddleName(rs.getString("middlename"));
				result.add(employee);
			}
		} catch (Exception ex) {
			System.out.println("Failed: An Exception has occurred! " + ex);
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
	public static ArrayList<CommentBean> getComments(ProjectBean project){
		int pid=project.getPid();
		ArrayList<CommentBean> comments=new ArrayList<>();
		String query="select * from project_comments where pid=?";
		try {
			connect = new ConnectionManager();
			currentCon = connect.getConnection();
			ps = currentCon.prepareStatement(query);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			while(rs.next()){
				CommentBean comment=new CommentBean();
				comment.setCid(rs.getInt("cid"));
				comment.setContent(rs.getString("content"));
				comment.setPid(rs.getInt("pid"));
				comment.setUsername(rs.getString("username"));
				comments.add(comment);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
		return comments;
		
	}

}
