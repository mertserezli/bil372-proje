package servlets;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.ProjectDAO;
import models.ProjectBean;

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
			boolean success=ProjectDAO.createProject(project);
			if(success){
				pw.println("New Project Successfully Created");
				return;
			}
			pw.println("New project Could Not Been Created");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
