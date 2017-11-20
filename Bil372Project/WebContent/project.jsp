<%@page import="otherSources.ProjectLoader"%>
<%@page import="java.util.Arrays"%>
<%@page import="dataAccess.ProjectDAO"%>
<%@page import="models.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%ProjectBean project=new ProjectBean();
      project.setPid(Integer.parseInt(request.getParameter("pid")));
      ProjectDAO.getProject(project);
	  session.setAttribute("currentProject",project);%>
	  <% UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");%>
	  <%session.setAttribute("currentuser", currentUser); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="css/navBar.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/Project.css">
<title><%=project.getTitle()%></title>
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
<h1><%=project.getTitle()%></h1>
<h2><%=project.getDescription()%></h2>
<h2>Vote number:<%=project.getVotenum() %></h2>
<form action="ProjectServlet" method="post">
<input type="submit" name="click" value="upvote">
<input type="submit" name="click" value="downvote">
</form>
<h3>This project contains:<br><%=Arrays.toString(project.getTags())%></h3>
<h3>Upcoming meeting dates:<br><%=ProjectLoader.getUpcomingMeetings(project) %></h3>
<h3>People who work on this project:<br><%=ProjectLoader.getWorkers(project) %></h3>
<h3>Task tree:<br><a href="TaskTree?pid=<%=project.getPid() %>">Click to see</a></h3>
<form action="ProjectServlet" method="get">
	<label>Add new meeting date:</label>
	<input type="text" placeholder="ex:dd.MM.YYYY" name="date">
	<input type="submit" value="Add meeting">
</form>
<%=ProjectLoader.getInviteLink(project,currentUser) %>
<%=ProjectLoader.getAddCommentButton(project, currentUser) %>
<section>
  <!--for demo wrap-->
  <h1>Comments</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>Comment ID</th>
          <th>User</th>
          <th>Content</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <%=ProjectLoader.getComments(project) %>
      </tbody>
    </table>
  </div>
</section>
</body>
</html>