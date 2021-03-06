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
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		if (searchType.equals("users")) {
			List<UserBean> users = null;
			String category = request.getParameter("userCategory");
			if (category.equals("username")) {
				try {
					users = SearchDAO.searchForUser(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting users from DB", e);
				}
			} else if (category.equals("jobtitle")) {
				try {
					users = SearchDAO.searchForUserJobTitle(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting users from DB", e);
				}
			} else if (category.equals("qualifications")) {
				try {
					users = SearchDAO.searchForUserWithQualifications(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting users from DB", e);
				}
			}
			for (UserBean u : users) {
				pw.println("<a href=\"profile.jsp?username=" + u.getUsername() + "\"style=\"display:block\">"
						+ u.getUsername() + "</a>");
			}
		} else if (searchType.equals("projects")) {
			List<ProjectBean> projects = null;
			String category = request.getParameter("projectCategory");
			if (category.equals("title")) {
				try {
					projects = SearchDAO.searchForProject(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting projects from DB", e);
				}
			} else if (category.equals("description")) {
				try {
					projects = SearchDAO.searchForProjectDescription(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting projects from DB", e);
				}
			} else if (category.equals("tags")) {
				try {
					projects = SearchDAO.searchForProjectWithTags(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting projects from DB", e);
				}
			}
			for (ProjectBean p : projects) {
				pw.println("<a href=\"project.jsp?pid=" + p.getPid() + "\"style=\"display:block\">" + p.getTitle()
						+ "</a>");
			}
		} else if (searchType.equals("companies")) {
			List<CompanyBean> companies = null;
			String category = request.getParameter("companyCategory");
			if (category.equals("name")) {
				try {
					companies = SearchDAO.searchForCompany(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting  companies from DB", e);
				}
			} else if (category.equals("description")) {
				try {
					companies = SearchDAO.searchForCompanyDescription(toSearch);
				} catch (SQLException e) {
					throw new ServletException("Error when getting  companies from DB", e);
				}
			}
			for (CompanyBean c : companies) {
				pw.println("<a href=\"company.jsp?name=" + c.getName() + "\"style=\"display:block\">" + c.getName()
						+ "</a>");
			}
		}
	}
}
