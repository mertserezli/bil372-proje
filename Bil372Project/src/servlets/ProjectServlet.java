package servlets;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.CommentsDAO;
import dataAccess.ProfileDAO;
import dataAccess.Work_Emp_ProDAO;
import dataAccess.ProjectDAO;
import models.CommentBean;
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
			pw.println("You cannot set up a meeting!");
			return;
		}
		ProjectDAO.setNewMeeting(currentProject,date);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		String click=request.getParameter("click");
		PrintWriter pw = response.getWriter();
		
		if(click.equals("Add Employee")){
			String username=request.getParameter("username");
			ProjectBean currentProject=(ProjectBean) request.getSession().getAttribute("currentProject");
			UserBean user=new UserBean();
			user.setUserName(username);
			ProfileDAO.loadUser(user);
			boolean success=Work_Emp_ProDAO.addEmployee(user,currentProject);
			if(success){
				pw.println("New Employee has been added");
			}
			else{
				pw.println("New Employee could not be added");
			}
		}
		else if(click.equals("Add Comment")){
			UserBean user=(UserBean) request.getSession().getAttribute("currentuser");
			ProjectBean currentProject=(ProjectBean) request.getSession().getAttribute("currentProject");
			String content=request.getParameter("content");
			CommentBean comment=new CommentBean();
			comment.setContent(content);
			comment.setPid(currentProject.getPid());
			comment.setUsername(user.getUsername());
			boolean success=CommentsDAO.addComment(currentProject, user,comment);
			if(success){
				pw.println("New Comment has been added");
			}
			else{
				pw.println("New Comment could not be added");
			}
		}
		else if (click.equals("upvote")) {
			ProjectBean currentProject=(ProjectBean) request.getSession().getAttribute("currentProject");
			boolean success=ProjectDAO.upvote(currentProject);
			if(success){
				pw.println("Upvoted Project");
			}
			else{
				pw.println("Could not upvote Project");
			}
		}
		else if(click.equals("downvote")){
			ProjectBean currentProject=(ProjectBean) request.getSession().getAttribute("currentProject");
			boolean success=ProjectDAO.downvote(currentProject);
			if(success){
				pw.println("Downvoted Project");
			}
			else{
				pw.println("Could not Downvote Project");
			}
		}
		
		
	}

}
