<%@page import="join.Join"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	</head>
	<body>
	
	
	<table border=1>
	<tr>
		<td>지역</td>
		<td>날짜</td>
		<td>이름</td>
		<td>적</td>
		<td>내용</td>
	</tr>
		<c:forEach var="n" items ="${list}">
		<tr>
			<td>${n.location}</td>
			<td>${n.date}</td>
			<td>${n.name}</td>
			<td>${n.enemy}</td>	
			<td>${n.content}</td>
		</tr>
		</c:forEach>
	</table>
	
	<form action="joinlist">
	<table>
		<tr>
			<td>
				<select name="field" >
    				<option value="l.LOCNAME">지역</option>
    				<option value="date">날짜</option>
    				<option value="name">이름</option>
    				<option value="enemy">적</option>
    				<option value="content">내용</option>
  			</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type ="text" name="query">
			</td>
		</tr>
		<tr>
			<td>
				<input type ="submit" value="검색">
			</td>
		</tr>
		
	</table>
</form>

 		<c:set var="page" value ="${empty param?1:param.p}"></c:set>
		<c:set var="startNum" value ="${page-(page-1)%5}"></c:set>
		<ul>
			<c:forEach var="i" begin = "0" end ="4">
				<a href=?p="${startNum+i}"$t=&q=">${startNum+i} </a>
			</c:forEach>
		</ul>


	</body>
</html>