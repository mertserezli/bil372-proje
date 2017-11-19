<%@page import="dataAccess.CompanyDAO"%>
<%@page import="models.CompanyBean"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="models.CompanyBean"%>
	<%CompanyBean company = new CompanyBean();
	company.setName(request.getParameter("name"));
	CompanyDAO.getCompany(company); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%company.getName();%></title>
</head>
<body>
<h1><%=company.getName() %></h1>
<h2><%=company.getDescription()%></h2>
<h3>Company administrators:<br><%=Arrays.toString(company.getAdministratorUserName()) %></h3>
</body>
</html>