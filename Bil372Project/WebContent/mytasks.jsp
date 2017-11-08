
<%@page import="otherSources.ProfileLoader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.UserBean"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>My tasks</title>
		<link rel="stylesheet" href="css/profile.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<% UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");%>

	</head>
	<body>
		
	</body>
</html>