<%@page import="otherSources.ProjectLoader"%>
<%@page import="java.util.Arrays"%>
<%@page import="dataAccess.ProjectDAO"%>
<%@page import="models.ProjectBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%ProjectBean project=new ProjectBean();
      project.setPid(Integer.parseInt(request.getParameter("pid")));
      ProjectDAO.getProject(project);
      request.setAttribute("currentProject",project);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=project.getTitle()%></title>
</head>
<body>
<h1><%=project.getTitle()%></h1>
<h2><%=project.getDescription()%></h2>
<h3>This project contains:<%=Arrays.toString(project.getTags())%></h3>
<h3>Upcoming meeting dates:<%=ProjectLoader.getUpcomingMeetings(project) %></h3>
<h3>People who work on this project:<%=ProjectLoader.getWorkers(project) %></h3>
<form action="ProjectServlet" method="get">
	<label>Add new meeting date:</label>
	<input type="text" placeholder="ex:dd.MM.YYYY" name="date">
	<input type="submit" value="Add meeting">
</form>

</body>
</html>