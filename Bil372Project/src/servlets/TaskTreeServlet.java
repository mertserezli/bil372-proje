package servlets;

import java.io.IOException;
import java.sql.Date;
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
				request.setAttribute("project", project);
				dispatcher = context.getNamedDispatcher("taskTree");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equals("addEmpToTask")) {
			int tid = Integer.parseInt(req.getParameter("tid"));
			String username = req.getParameter("username");
			boolean success = TaskDAO.addEmpToTask(username, tid);
			if (success)
				res.getWriter().append("Employee added succesfully");
		} else if (action.equals("addPreToTask")) {
			int ptid = Integer.parseInt(req.getParameter("ptid"));
			int ctid = Integer.parseInt(req.getParameter("ctid"));
			TaskDAO.addPreToTask(ptid, ctid);
			res.getWriter().append("Task prerequisite added succesfully");
		} else if (action.equals("addTask")) {
			TaskBean task = new TaskBean();
			task.setTitle(req.getParameter("title"));
			task.setDescription(req.getParameter("description"));
			task.setPerformanceCriteria(req.getParameter("criteria"));
			int upperbound = Integer.parseInt(req.getParameter("upperbound"));
			task.setPerformanceUpperbound(upperbound);
			String deadline = req.getParameter("deadline");
			String day = deadline.substring(0, deadline.indexOf("."));
			deadline = deadline.substring(deadline.indexOf(".") + 1);
			String month = deadline.substring(0, deadline.indexOf("."));
			deadline = deadline.substring(deadline.indexOf(".") + 1);
			String year = deadline;
			Date deadlineDate = new Date(Integer.parseInt(year) - 1900, Integer.parseInt(month) - 1,
					Integer.parseInt(day));
			task.setDeadline(deadlineDate);
			int pid = Integer.parseInt(req.getParameter("pid"));
			task.setPid(pid);
			TaskDAO.addTask(task);
			res.getWriter().append("Task added successfully");
		} else if (action.equals("deleteTask")) {
			int tid = Integer.parseInt(req.getParameter("tid"));
			TaskDAO.deleteTask(tid);
			res.getWriter().append("Task deleted successfully");
		} else if (action.equals("removeEmployee")) {
			int tid = Integer.parseInt(req.getParameter("tid"));
			String username = req.getParameter("username");
			TaskDAO.removeEmployee(tid, username);
			res.getWriter().append("Employee removed successfully");
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
		html += "<a href=\"task?tid=" + parentTask.getTid() + "\" class=\"task\">" + parentTask.getTid() + " "
				+ parentTask.getTitle() + "</a>";

		if (!employees.isEmpty() || !childTasks.isEmpty())
			html += "<ul>";

		if (!employees.isEmpty()) {
			for (UserBean employee : employees) {
				html += "<li>" + "<a href=profile.jsp?username=" + employee.getUsername() + " class=\"employee\">"
						+ employee.getUsername() + "</a> </li>";
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
