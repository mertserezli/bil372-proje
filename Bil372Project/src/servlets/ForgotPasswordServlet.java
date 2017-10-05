package servlets;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import otherSources.SendMail;
import models.UserBean;
import dataAccess.ForgotPasswordDAO;
import dataAccess.loginDAO;

public class ForgotPasswordServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3654422292042474714L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		PrintWriter pw=response.getWriter();
		try {
			UserBean user = new UserBean();
			String username=request.getParameter("username");
			user.setUsername(username);
			user=ForgotPasswordDAO.getPassword(user);
			if (user.getEmail()==null) {
				pw.println("<h1>No such user exists!</h1> <br/>");
			} else{
				SendMail.sendMail(user);
				pw.println("<h1>Mail has been sent to your email! "+user.getEmail()+"</h1><br/>");
			}
				
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
