<%@page import="dataAccess.ProfileDAO"%>
<%@page import="otherSources.ProfileLoader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="models.UserBean"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Page</title>
<link rel="stylesheet" href="css/profile.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<% UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");
   UserBean currentProfile=new UserBean();
   currentProfile.setUserName(request.getParameter("username"));
   request.setAttribute("currentProfile",currentProfile);
   ProfileDAO.loadUser(currentProfile);%>
</head>
<body>
 <ul>
  <li><a href="profile.jsp?username=<%=currentUser.getUsername()%>"><%=currentUser.getUsername() %></a></li>
  <li><a href="search.jsp">Search</a></li>
  <li><a href="mytasks.jsp">My Tasks</a></li>
  <li><a href="mymessages.jsp">My Messages</a></li>
  <li><a href="index.jsp">Logout</a></li>
</ul> 

  
<div class="card" style="text-align: center; border: thick;">
  <%=ProfileLoader.GetProfilePhoto(currentProfile) %>
   <%=ProfileLoader.GetButtons(currentUser, currentProfile) %>

  <h1><%=currentProfile.getLastName()+","+currentProfile.getFirstName() %></h1>
  <h2><%=currentProfile.getJobTitle() %></h2>
  <a href="Contact.jsp"></a>
</div>
<div>
  <h1>MY PROJECTS</h1>
  <%=ProfileLoader.GetProjects(currentProfile)%>
  </div>
 <%=ProfileLoader.getButtons2(currentUser, currentProfile) %>
</body>
</html>