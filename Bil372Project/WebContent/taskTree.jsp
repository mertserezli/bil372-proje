<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.UserBean"
    import="java.util.ArrayList"
    import="models.ProjectBean"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/profile.css">
		<link rel="stylesheet" type="text/css" href="css/tree.css" />
		<link rel="stylesheet" type="text/css" href="css/navBar.css" />
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<title>Task Tree</title>
		<% 
		UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
   		%>
	</head>
	<body>
		<div class="navbar">
			<ul>
			  <li><a href="profile.jsp?username=<%=currentUser.getUsername()%>"><%=currentUser.getUsername() %></a></li>
			  <li><a href="search.jsp">Search</a></li>
			  <li><a href="mytasks.jsp">My Tasks</a></li>
			  <li><a href="mymessages.jsp">My Messages</a></li>
			  <li><a href="index.jsp">Logout</a></li>
			</ul>
		</div>
		<div id="forest">
			<h3>Task Tree</h3>
			<% String html = (String) request.getAttribute("tree"); %>
			<%= html %>
		</div>
		
		<% ArrayList<UserBean> employeeList = (ArrayList<UserBean>)request.getAttribute("employeeList"); %>
		<% ProjectBean project = (ProjectBean)request.getAttribute("project"); %>
		<div id="employeeList">
			<h3>Employees working on this project</h3>
			 <ul class="list-group">
			 	<% for(UserBean employee :employeeList) { %>
	            	<li class="list-group-item"> <a href="profile.jsp?username=<%=employee.getUsername() %>" > <%= employee.getUsername() %></a> </li>
	    		<% } %>
			</ul>
		</div>
		<div id="forms">
			<h3>Add employee to task, click on employee and task to fill these inputs</h3>
			<form action="TaskTree" method="post">
				<label>task id</label>
				<input type="text" name="tid">
				<label>employee</label>
				<input type="text" name="username">
				<input type="hidden" name="action" value="addEmpToTask">				
				<input type="submit" value="Add">  
			</form>
			<h3>Add prerequisite to task</h3>
			<form action="TaskTree" method="post">
				<label>parent task id</label>
				<input type="text" name="ptid">
				<label>prerequisite taskid</label>
				<input type="text" name="ctid">
				<input type="hidden" name="action" value="addPreToTask">				
				<input type="submit" value="Add">  
			</form>
			<h3>Add new task</h3>
			<form action="TaskTree" method="post">
				<label>title</label>
				<input type="text" name="title">
				<label>description</label>
				<input type="text" name="description">
				<label>performance criteria</label>
				<input type="text" name="criteria">
				<label>performance upperbound</label>
				<input type="text" name="upperbound">
				<label>deadline</label>
				<input type="text" placeholder="ex:dd.MM.YYYY" name="deadline">
				<input type="hidden" name="pid" value="<%=project.getPid()%>">
				<input type="hidden" name="action" value="addTask">				
				<input type="submit" value="Add">  
			</form>
			<h3>Delete task</h3>
			<form action="TaskTree" method="post">
				<label>task id</label>
				<input type="text" name="tid">
				<input type="hidden" name="action" value="deleteTask">				
				<input type="submit" value="Delete">  
			</form>
			<h3>Remove employee from task</h3>
			<form action="TaskTree" method="post">
				<label>task id</label>
				<input type="text" name="tid">
				<label>username</label>
				<input type="text" name="username">
				<input type="hidden" name="action" value="removeEmployee">				
				<input type="submit" value="Delete">  
			</form>
		</div>

	</body>
	
	<style type="text/css">
	
		#employeeList {
			float: left; 
 			width: 1000px; 
	    }
	    
	    #forest {
	        float: left;
	        width: 1000px;
	    }
	    
	    #forms{
	    	float:left;
	    	width: 1000px;
	    }

		.task{
			background-color:#ff0000;
		}

		.employee{
			background-color:#0066ff;
		}	
	</style>
</html>