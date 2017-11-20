package servlets;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;

import models.TaskBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.TaskDAO;


public class TaskServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		
		List<TaskBean> tasks = null;
		String username = request.getParameter("username");
		PrintWriter pw = response.getWriter();

		try{
			tasks=TaskDAO.searchTasksForUser(username);
		}
		catch(SQLException e)
		{
			 throw new ServletException("Error when getting tasks from DB", e);
		}
		request.setAttribute("tasks",tasks);
		RequestDispatcher rd= request.getRequestDispatcher("mytasks.jsp");
		rd.forward(request, response);
		/*
		pw.println(username);
		for(TaskBean t:tasks)
		{
			pw.println("<h1>" + t.getTitle() + "</hl><br/>");
		}*/
		
	}

}
