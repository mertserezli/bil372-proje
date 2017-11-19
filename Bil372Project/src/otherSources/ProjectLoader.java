package otherSources;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.catalina.User;

import dataAccess.Work_Emp_ProDAO;
import models.ProjectBean;
import models.UserBean;

public class ProjectLoader {

	public static String getUpcomingMeetings(ProjectBean project){
		String html="";
		Date[] meetings=project.getMeeting_dates();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(date);
		if(meetings!=null){
			for(Date d:meetings){
				if(d.compareTo(date)>0){
					html+=d+", ";
				}
			}
			html=html.substring(0, html.lastIndexOf(","));
		}
		return html;
	}
	
	public static String getWorkers(ProjectBean project){
		String html="";

		ArrayList<UserBean> workers = Work_Emp_ProDAO.getWorkers(project);
		for(UserBean worker:workers){
			html+=worker.getUsername()+"<br>";
		}
		return html;
	}
}
