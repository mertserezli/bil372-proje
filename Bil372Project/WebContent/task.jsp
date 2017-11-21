<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.TaskBean"
    import="models.UserBean"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<title>Task</title>
	</head>
	<body>
		<% 
			UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
   		%>
		<div class="navbar">
			<ul>
			  <li><a href="profile.jsp?username=<%=currentUser.getUsername()%>"><%=currentUser.getUsername() %></a></li>
			  <li><a href="search.jsp">Search</a></li>
			  <li><a href="mytasks.jsp">My Tasks</a></li>
			  <li><a href="mymessages.jsp">My Messages</a></li>
			  <li><a href="index.jsp">Logout</a></li>
			</ul>
		</div>
		<% 
			TaskBean task = (TaskBean)request.getAttribute("task");
   		%>
		<h3>The Task</h3>
		<table>
			<tr>
				<th>task id</th>
				<th>project id</th> 
				<th>title</th>
				<th>description</th>
				<th>deadline</th> 
				<th>performance criteria</th>
				<th>performance upperbound</th>
				<th>performance value</th> 
			</tr>
			<tr>
				<td><%=task.getTid() %></td>
				<td><%=task.getPid() %></td>
				<td><%=task.getTitle() %></td>
				<td><%=task.getDescription() %></td>
				<td><%=task.getDeadline() %></td>
				<td><%=task.getPerformanceCriteria() %></td>
				<td><%=task.getPerformanceUpperbound() %></td>
				<td><%=task.getPerformanceValue() %></td> 
			</tr>
		</table>
		
		<h4>Update performance value</h3>
			<form action="Task" method="post">
				<label>value</label>
				<input type="text" name="value">
				<input type="hidden" name="tid" value="<%=task.getTid()%>">				
				<input type="submit" value="Update">  
			</form>
	</body>
	<style>
		table, th, td {
		    border: 1px solid black;
		    border-collapse: collapse;
		}
</style>
</html>