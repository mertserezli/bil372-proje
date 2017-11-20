package servlets;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.ProjectDAO;
import models.ProjectBean;
import models.UserBean;

public class NewProjectServlet extends HttpServlet  {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		try {
			PrintWriter pw = response.getWriter();
			ProjectBean project=new ProjectBean();
			project.setTitle(request.getParameter("title"));
			project.setDescription(request.getParameter("description"));
			String[] tags=request.getParameter("tags").split(",");
			project.setTags(tags);
			boolean success=ProjectDAO.createProject(project,(UserBean) request.getSession().getAttribute("currentSessionUser"));
			if(success){
				pw.println("New Project Successfully Created");
				return;
			}
			pw.println("New Project Could Not Be Created");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
