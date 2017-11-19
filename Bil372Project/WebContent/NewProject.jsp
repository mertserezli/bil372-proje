<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Project</title>
</head>
<body>
<form action="NewProjectServlet" method="get">
<label>Project Title</label>
<input type="text" name="title">
<label>Description</label>
<input type="text" name="description">
<label>Tags</label>
<input type="text" name="tags" placeholder="java,c++">
<input type="submit" value="Create">  
</form>
</body>
</html>