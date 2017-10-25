package servlets;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.SignUpDAO;
import models.UserBean;

public class SignUpServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, java.io.IOException{
		PrintWriter pw=response.getWriter();
		try {
			UserBean user = new UserBean();
			user.setFirstName(request.getParameter("firstname"));
			user.setUsername(request.getParameter("username"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setJobTitle(request.getParameter("jobtitle"));
			SignUpDAO.signUpCheck(user);	
			if(!user.isValid()){
				pw.println("<h1>This username or Email already exists!</h1> <br/>");
			}
			else if(SignUpDAO.signUp(user)){
				pw.println("<h1>New user Successfully created!</h1> <br/>");
			}
			else
				pw.println("<h1>New user could not been created!</h1> <br/>");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
