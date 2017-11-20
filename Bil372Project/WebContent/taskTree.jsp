<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.UserBean"
    import="java.util.ArrayList"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <link rel="stylesheet" type="text/css" href="css/tree.css" />
		 <link rel="stylesheet" type="text/css" href="css/navBar.css" />
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		 <script src="jquery-3.2.1.js"></script>
		<title>Task Tree</title>
		<% 
		UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
   		UserBean currentProfile=new UserBean();
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
		<div id="employeeList">
			<h3>Employees working on this project</h3>
			 <ul class="list-group">
			 	<% for(UserBean employee :employeeList) { %>
	            	<li class="list-group-item"> <a href="profile.jsp?username="<%=employee.getUsername() %> > <%= employee.getFirstName() %></a> </li>
	    		<% } %>
			</ul>
		</div>
		<div id="forms">
			<h3>Add employee to task</h3>
			<form action="addEmployeeToTask" method="get"><!--TODO: burayi duzelt--> 
				<label>task title</label>
				<input type="text" name="title">
				<label>employee</label>
				<input type="text" name="description">
				
				<input type="submit" value="Create">  
			</form>
		</div>

	<script type="text/javascript">
		
	</script>
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