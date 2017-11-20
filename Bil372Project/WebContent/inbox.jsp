<%@page import="models.MessageBean"%>
<%@page import="otherSources.MessageLoader"%>
<%@page import="dataAccess.MessageDAO"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@page import="models.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inbox</title>
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<% 
	UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
	List<MessageBean> m=MessageLoader.getMessages(currentUser);
	//List<MessageBean> m=MessageDAO.getUserMessages("cemsozens");
%>
</head>
<body>

<% for(int i = 0; i < m.size(); i+=1) { %>
<h1>Sender:<%=m.get(i).getSender()%></h1>
<h2>Date:<%=m.get(i).getSender()%></h2>
<h2>Receivers:<%=Arrays.toString(m.get(i).getReceiver())%></h2>
<h2>Title:<%=m.get(i).getTitle()%></h3>
<h3>Content:<%=m.get(i).getContent() %></h3>
<h3>----------------------------------------></h3>
    <% } %>
</body>
</html>