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

import dataAccess.TaskDAO;
import models.TaskBean;

public class TaskDescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TaskBean task = new TaskBean();
		RequestDispatcher dispatcher;
		ServletContext context = getServletContext();
		int tid = Integer.parseInt(request.getParameter("tid"));
		task.setTid(tid);
		try {
			task = TaskDAO.getTask(task);
			request.setAttribute("task", task);
			dispatcher = context.getNamedDispatcher("task");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		int value = Integer.parseInt(request.getParameter("value"));
		boolean success = TaskDAO.updatePerformanceValue(tid, value);
		if (success)
			response.getWriter().append("Task updated succesfully");
	}

}
