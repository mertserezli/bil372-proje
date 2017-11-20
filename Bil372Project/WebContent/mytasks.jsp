<%@page import="models.TaskBean"%>
<%@page import="models.ProjectBean"%>
<%@page import="dataAccess.ProjectDAO"%>
<%@page import="dataAccess.TaskDAO"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.UserBean"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Tasks</title>
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<% 
	UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
	List<TaskBean> tasks = TaskDAO.searchTasksForUser(currentUser.getUsername());
	List<ProjectBean> projectNames=new ArrayList<ProjectBean>();
	for(int i=0;i<tasks.size();i++)
	{
		ProjectBean p = new ProjectBean();
		p.setPid(tasks.get(i).getPid());
		p=ProjectDAO.getProject(p);
		projectNames.add(p);
	}
	//List<MessageBean> m=MessageDAO.getUserMessages("cemsozens");
%>
</head>
<body>

<% for(int i = 0; i < tasks.size(); i+=1) { %>
<h1>Deadline:<%=tasks.get(i).getDeadline()%></h1>
<h2>Description:<%=tasks.get(i).getDescription()%></h2>
<h2>Project:<a href=project.jsp?pid=<%=tasks.get(i).getPid() %>><%=projectNames.get(i).getTitle()%></a></h2>
<h2>Performance Criteria:<%=tasks.get(i).getPerformanceCriteria()%></h2>
<h2>Performance UpperBound:<%=tasks.get(i).getPerformanceUpperbound()%></h3>
<h3>Performance Value:<%=tasks.get(i).getPerformanceValue()%></h3>
<h3>----------------------------------------></h3>
    <% } %>
</body>
</html>