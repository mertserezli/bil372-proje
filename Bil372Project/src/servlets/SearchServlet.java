package servlets;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserBean;
import models.ProjectBean;
import dataAccess.searchDAO;

public class SearchServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		List<UserBean> users = null;
		List<ProjectBean> projects = null;
		String searchType = request.getParameter("searchType");
		String toSearch = request.getParameter("searchBar");
		PrintWriter pw = response.getWriter();
		if(searchType.equals("users")){
			try {
				users = searchDAO.searchForUser(toSearch);
			} catch (SQLException e) {
	            throw new ServletException("Error when getting users from DB", e);
	        }
			for(UserBean u : users)
			{
				pw.println(u.getUsername());
			}
		}
		else{
			try {
				projects = searchDAO.searchForProject(toSearch);
			} catch (SQLException e) {
	            throw new ServletException("Error when getting projects from DB", e);
	        }
			for(ProjectBean p : projects)
			{
				pw.println(p.getTitle());
			}
		}
	}
}
