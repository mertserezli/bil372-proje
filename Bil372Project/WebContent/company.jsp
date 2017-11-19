<%@page import="dataAccess.CompanyDAO"%>
<%@page import="models.CompanyBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.CompanyBean"%>
	<%CompanyBean company = CompanyDAO.getCompany(request.getParameter("username")); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%company.getName(); %></title>
</head>
<body>
<h2><%=company.getDescription()%></h2>
</body>
</html>