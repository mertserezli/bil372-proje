package otherSources;

import models.UserBean;

public class ProfileLoader {

	public static String GetProfilePhoto(UserBean user){
		if(user.getImage()!=null){
			return " <img src=\"EpmloyeePictures\""+user.getUsername()+" class=\"img-rounded\" alt=\"Cinque Terre\"> ";
		}
		return "";
	}
	
}
