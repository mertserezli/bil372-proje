package servlets;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.Calendar;

import dataAccess.MessageDAO;
import models.MessageBean;
import models.UserBean;

public class MessageServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, java.io.IOException {
			try {
				PrintWriter pw = response.getWriter();
				UserBean currentUser = (UserBean)request.getSession().getAttribute("currentSessionUser");
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				MessageBean message = new MessageBean();
				message.setSender(currentUser.getUsername());
				message.setReceiver(request.getParameter("receivers").split(","));
				message.setDate(date);
				message.setTitle(request.getParameter("title"));
				message.setContent(request.getParameter("content"));
				
				boolean success=MessageDAO.sendMessage(message);
				if(success){
					pw.println("Message is sent succesfully");
					return;
				}
				pw.println("Message couldn't sent");
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	
	

}