package servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dataAccess.UploadDAO;
import models.UserBean;


@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
             maxFileSize=1024*1024*10,      // 10MB
             maxRequestSize=1024*1024*50)
public class UploadServlet extends HttpServlet {

	
	String path="/home/cem/bil372project/Bil372Project/WebContent/EmployeePictures";
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, java.io.IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		
		Part part=request.getPart("file");
        String fileName=extractFileName(part);
        
        part.write(path+File.separator+fileName);
        
        UserBean current=(UserBean)request.getSession().getAttribute("currentSessionUser");
        current.setImage(fileName);
        UploadDAO.uploadImage(current);
	}
	
	private String extractFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length()-1);
	        }
	    }
	    return "";
	}
}
