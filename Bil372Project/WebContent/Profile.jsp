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
<% UserBean currentUser = (UserBean)session.getAttribute("currentSessionUser");%>
</head>
<body>
 <ul>
  <li><a href="Profile.jsp"><%=currentUser.getUsername() %></a></li>
  <li><a href="#">Search</a></li>
  <li><a href="#">My projects</a></li>
  <li><a href="#">Logout</a></li>
</ul> 

  
<div class="card" style="text-align: center; border: thick;">
  <%=ProfileLoader.GetProfilePhoto(currentUser) %>
   <form action="UploadServlet" method="post" enctype="multipart/form-data" >
  <input type="file" name="file" accept="image/*" >
  <input type="submit" value="Change Picture">
</form> 
  <h1><%=currentUser.getLastName()+","+currentUser.getFirstName() %></h1>
  <h2><%=currentUser.getJobTitle() %></h2>
  

  <a href="Contact.jsp"></a>
</div>
</body>
</html>