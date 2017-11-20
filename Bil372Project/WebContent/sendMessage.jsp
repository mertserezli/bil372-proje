<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MessageServlet" method="get">
<label>Receiver</label>
<input type="text" name="receivers">
<label>Title</label>
<input type="text" name="title">
<label>Content</label>
<input type="text" name="content">
<input type="submit" value="Send">  
</body>
</html>