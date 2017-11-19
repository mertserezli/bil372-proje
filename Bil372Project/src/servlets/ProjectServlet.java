package servlets;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.ProfileDAO;
import dataAccess.Work_Emp_ProDAO;
import dataAccess.ProjectDAO;
import models.ProjectBean;
import models.UserBean;

public class ProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		PrintWriter pw = response.getWriter();
		UserBean currentUser = (UserBean)request.getSession().getAttribute("currentSessionUser");
		String date=request.getParameter("date");
		ProjectBean currentProject=(ProjectBean) request.getSession().getAttribute("currentProject");
		if(date==null){
			pw.println("Date cannot be empty!");
			return;
		}
		ArrayList<UserBean> workers=Work_Emp_ProDAO.getWorkers(currentProject);
		boolean found=false;
		for(UserBean worker: workers){
			if(!worker.getUsername().equals(currentUser.getUsername())){
				found=true;
				break;
			}
		}
		if(!found){
			pw.println("You cannot setup a meeting!");
			return;
		}
		ProjectDAO.setNewMeeting(currentProject,date);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		String username=request.getParameter("username");
		PrintWriter pw = response.getWriter();
		ProjectBean currentProject=(ProjectBean) request.getSession().getAttribute("currentProject");
		UserBean user=new UserBean();
		user.setUserName(username);
		ProfileDAO.loadUser(user);
		boolean succes=Work_Emp_ProDAO.addEmployee(user,currentProject);
		if(succes){
			pw.println("New Employee has been added");
		}
		else{
			pw.println("New Employee could not been added");
		}
		
	}

}
