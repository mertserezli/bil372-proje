package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.ProjectDAO;
import dataAccess.TaskDAO;
import models.ProjectBean;
import models.TaskBean;
import models.UserBean;

public class TaskTreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		ProjectBean project = new ProjectBean();
		RequestDispatcher dispatcher;
		ServletContext context = getServletContext();
		int pid = Integer.parseInt(request.getParameter("pid"));
		project.setPid(pid);
		try {
			project = ProjectDAO.getProject(project);
			if (!project.getTitle().equals("Project Not Found")) {
				String tree = getTreeHTML(project);
				ArrayList<UserBean> employeeList = ProjectDAO.getEmployees(project);
				request.setAttribute("employeeList", employeeList);
				request.setAttribute("tree", tree);
				dispatcher = context.getNamedDispatcher("taskTree");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getTreeHTML(ProjectBean project) {
		String html = "";
		ArrayList<TaskBean> roots = TaskDAO.getRootTasks(project);
		// for every prerequisite=null task(not prerequisite of anything) do
		for (TaskBean task : roots) {
			html += "<div class=\"tree\"> <ul>";
			// get tasks and employees that is child of that task(recursive)
			html = appendChilds(task, html);
			// return string
			html += "</ul> </div>";
		}
		return html;
	}

	private String appendChilds(TaskBean parentTask, String html) {
		ArrayList<UserBean> employees = TaskDAO.getEmployeesWorksOn(parentTask);
		ArrayList<TaskBean> childTasks = TaskDAO.getChildTasks(parentTask);

		html += "<li>";
		html += "<a href=\"task/" + parentTask.getTid() + "\" class=\"task\">" + parentTask.getTitle() + "</a>";

		if (!employees.isEmpty() || !childTasks.isEmpty())
			html += "<ul>";

		if (!employees.isEmpty()) {
			for (UserBean employee : employees) {
				html += "<li>" + "<a href=\"" + employee.getUsername() + "\" class=\"employee\">"
						+ employee.getFirstName() + "</a> </li>";
			}
		}

		if (!childTasks.isEmpty()) {
			for (TaskBean task : childTasks) {
				html = appendChilds(task, html);
			}
		}
		html += "</li>";

		if (!employees.isEmpty() || !childTasks.isEmpty())
			html += "</ul>";
		return html;
	}
}
