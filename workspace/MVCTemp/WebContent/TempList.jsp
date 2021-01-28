<%@page import="temp.Temp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1>
	<% List<Temp> list = (List<Temp>)request.getAttribute("templist");
	  for(Temp te : list) {
		  pageContext.setAttribute("n", te);
	%>
		<tr>
		<td><a href ="tempdetail?date=${n.date}">${n.date}</a></td>
		<td>${n.temp}</td>
		<td>${n.locid}</td>	
		<td>${n.writer}</td>
		<% } %>
		</tr>
		
	</table>
</body>
</html>