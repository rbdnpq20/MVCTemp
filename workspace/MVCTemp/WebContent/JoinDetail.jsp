<%@page import="score.Score"%>
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
		<td>히터번호</td>
		<td>지역번호</td>
		<td>적</td>
		<td>날짜</td>
		<td>Hiter</td>
		<td>내용</td>
		<td>메모</td>
	</tr>
		<tr>
			<td>${list.seq}</td>
			<td>${list.locid}</td>
			<td>${list.enemy}</td>
			<td>${list.regdate}</td>
			<td>${list.hiter}</td>	
			<td>${list.content}</td>
			<td>${list.memo}</td>
		</tr>
	</table>
	
	
	</body>
</html>