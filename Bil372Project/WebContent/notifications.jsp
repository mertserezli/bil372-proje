<%@page import="models.NotificationBean"%>
<%@page import="dataAccess.NotificationDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@page import="models.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notifications</title>
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<% 
	UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
	List<NotificationBean> m= NotificationDAO.getNotifications(currentUser.getUsername());
	List<NotificationBean> hatirlatici=NotificationDAO.getUpcomingMeetings(currentUser.getUsername());
	List<NotificationBean> taskHatirlatici=NotificationDAO.getUpcomingDeadlines(currentUser.getUsername());
	//List<MessageBean> m=MessageDAO.getUserMessages("cemsozens");
%>
</head>
<body>
<% for(int i = taskHatirlatici.size()-1; i >=0; i-=1) { %>
<h2><%=taskHatirlatici.get(i).getDate()%>-<%=taskHatirlatici.get(i).getNotification()%></h2>
<% } %>
<% for(int i = hatirlatici.size()-1; i >=0; i-=1) { %>
<h2><%=hatirlatici.get(i).getDate()%>-<%=hatirlatici.get(i).getNotification()%></h2>
<% } %>
<% for(int i = m.size()-1; i >=0; i-=1) { %>
<h2><%=m.get(i).getDate()%>-<%=m.get(i).getNotification()%></h2>
<% } %>
</body>
</html>