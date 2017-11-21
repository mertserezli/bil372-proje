package otherSources;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.catalina.User;

import dataAccess.Man_Emp_ProDAO;
import dataAccess.ProjectDAO;
import dataAccess.Work_Emp_ProDAO;
import models.CommentBean;
import models.ProjectBean;
import models.UserBean;

public class ProjectLoader {

	public static String getUpcomingMeetings(ProjectBean project) {
		String html = "";
		Date[] meetings = project.getMeeting_dates();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		if (meetings != null) {
			for (Date d : meetings) {
				if (d.compareTo(date) > 0) {
					html += d + ", ";
				}
			}
			html = html.substring(0, html.lastIndexOf(","));
		}
		return html;
	}

	public static String getWorkers(ProjectBean project) {
		String html = "";

		ArrayList<UserBean> workers = Work_Emp_ProDAO.getWorkers(project);
		for (UserBean worker : workers) {
			html += "<a href=\"profile.jsp?username=" + worker.getUsername() + "\">" + worker.getUsername() + "</a>"
					+ "<br>";
		}
		return html;
	}

	public static String getInviteLink(ProjectBean project, UserBean user) {
		String html = "";
		UserBean manager = Man_Emp_ProDAO.getManeger(project);
		if (user.getUsername().equals(manager.getUsername())) {
			html += "<form action=\"ProjectServlet\" method=\"post\">" + "<label>Add New Employee:</label>"
					+ "<input type=\"text\" placeholder=\"username\" name=\"username\">"
					+ "<input type=\"submit\" value=\"Add Employee\" name=\"click\">" + "</form>";
		}
		return html;
	}

	public static String getComments(ProjectBean project) {
		ArrayList<CommentBean> comments = ProjectDAO.getComments(project);
		String html = "";
		for (CommentBean comment : comments) {
			html += "<tr>" + "<td>" + comment.getCid() + "</td>" + "<td>" + comment.getUsername() + "</td>" + "<td>"
					+ comment.getContent() + "</td>" + "</tr>";
		}
		return html;
	}

	public static String getAddCommentButton(ProjectBean project, UserBean user) {
		String html = "";
		ArrayList<UserBean> workers = Work_Emp_ProDAO.getWorkers(project);
		for (UserBean worker : workers)
			if (user.getUsername().equals(worker.getUsername())) {
				html += "<div>" + "<form action=\"ProjectServlet\" method=\"post\">" + "<label>Add New Comment:</label>"
						+ "<textarea rows=\"4\" cols=\"50\" style=\"margin-top:2cm\" name=\"content\">" + "</textarea>"
						+ "<input type=\"submit\" value=\"Add Comment\" name=\"click\">" + "</form>" + "</div>";
				break;
			}
		return html;
	}
}
