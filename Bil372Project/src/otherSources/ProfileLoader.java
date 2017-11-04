package otherSources;

import models.UserBean;

public class ProfileLoader {

	public static String GetProfilePhoto(UserBean user){
		if(user.getImage()!=null){
			return " <img src=\"EmployeePictures\\"+user.getImage()+"\" class=\"img-circle\" style=\"width:300px;height:300px;\"> ";
		}
		return " <img src=\"EmployeePictures\\anonim\" class=\"img-circle\"> ";
	}	
}
