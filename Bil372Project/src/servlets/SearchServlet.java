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
import models.CompanyBean;
import models.ProjectBean;
import dataAccess.SearchDAO;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		String searchType = request.getParameter("searchType");
		String toSearch = request.getParameter("searchBar");
		PrintWriter pw = response.getWriter();
		if (searchType.equals("users")) {
			List<UserBean> users = null;
			try {
				users = SearchDAO.searchForUser(toSearch);
			} catch (SQLException e) {
				throw new ServletException("Error when getting users from DB", e);
			}
			for (UserBean u : users) {
				pw.println("<h1>" + u.getUsername() + "</hl><br/>");
			}
		} else if (searchType.equals("projects")) {
			List<ProjectBean> projects = null;
			try {
				projects = SearchDAO.searchForProject(toSearch);
			} catch (SQLException e) {
				throw new ServletException("Error when getting projects from DB", e);
			}
			for (ProjectBean p : projects) {
				pw.println("<h1>" + p.getTitle() + "</hl><br/>");
			}
		} else if (searchType.equals("companies")) {
			List<CompanyBean> companies = null;
			try {
				companies = SearchDAO.searchForCompany(toSearch);
			} catch (SQLException e) {
				throw new ServletException("Error when getting projects from DB", e);
			}
			for (CompanyBean c : companies) {
				pw.println("<h1>" + c.getName() + "</hl><br/>");
			}
		}
	}
}
