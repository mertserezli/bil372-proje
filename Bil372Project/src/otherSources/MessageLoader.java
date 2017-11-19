package otherSources;
import java.sql.SQLException;
import java.util.*;

import models.UserBean;
import models.MessageBean;
import dataAccess.MessageDAO;
public class MessageLoader {
	
	public static String getMessages(UserBean bean) throws SQLException
	{
		String html="";
		List<MessageBean> messages = MessageDAO.getUserMessages(bean.getUsername());
		for(MessageBean m:messages)
		{
			html+=m.getSender()+"-"+m.getDate()+"-"+m.getTitle()+"-"+m.getContent()+",";
			html+="\n";
		}
		html=html.substring(0,html.lastIndexOf(","));
		return html;
	}
}