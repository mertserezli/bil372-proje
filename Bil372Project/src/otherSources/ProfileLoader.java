package otherSources;

import java.util.ArrayList;

import dataAccess.UploadDAO;
import models.ProjectBean;
import models.UserBean;

public class ProfileLoader {

	public static String GetProfilePhoto(UserBean user){
		if(user.getImage()!=null){
			return " <img src=\"EmployeePictures\\"+user.getImage()+"\" class=\"img-circle\" style=\"width:300px;height:300px;\"> ";
		}
		return " <img src=\"EmployeePictures\\anonim\" class=\"img-circle\"> ";
	}
	
	public static String GetProjects(UserBean user){
		String html="";
		ArrayList<ProjectBean> projects=UploadDAO.loadProjects(user);
		for(ProjectBean project:projects){
			html+="<a href=\"project.jsp?pid="+project.getPid()+"\"style=\"display:block\">"+project.getTitle()+"</a>";
		}
		return html;
	}
}
