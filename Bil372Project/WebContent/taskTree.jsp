<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.UserBean"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tree.css" />
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<title>Task Tree</title>
		<% UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
   			UserBean currentProfile=new UserBean();%>
	</head>
	<body>
		<ul>
		  <li><a href="profile.jsp?username=<%=currentUser.getUsername()%>"><%=currentUser.getUsername() %></a></li>
		  <li><a href="search.jsp">Search</a></li>
		  <li><a href="mytasks.jsp">My Tasks</a></li>
		  <li><a href="mymessages.jsp">My Messages</a></li>
		  <li><a href="index.jsp">Logout</a></li>
		</ul>
		<% String html = (String) request.getAttribute("tree"); %>
		<%= html %>
		<br>
		
		<br>
		<form action="addEmployeeToTask" method="get"><!--TODO: burayi duzelt--> 
			<label>task title</label>
			<input type="text" name="title">
			<label>employee</label>
			<input type="text" name="description">
			
			<input type="submit" value="Create">  
		</form>
	</body>
	<style type="text/css">

		.task{
			background-color:#ff0000;
		}

		.employee{
			background-color:#0066ff;
		}	
	</style>
</html>