package otherSources;

import java.sql.SQLException;
import java.util.*;

import models.UserBean;
import models.MessageBean;
import dataAccess.MessageDAO;
public class MessageLoader {
	
	public static List<MessageBean> getMessages(UserBean bean) throws SQLException
	{
		List<MessageBean> messages = MessageDAO.getUserMessages(bean.getUsername());
		return messages;
	}
	public static List<MessageBean> getOutbox(UserBean bean) throws SQLException
	{
		List<MessageBean> messages = MessageDAO.getUserOutbox(bean.getUsername());
		return messages;
	}
}