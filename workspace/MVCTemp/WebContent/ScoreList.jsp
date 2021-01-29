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
		<td>지역번호</td>
		<td>적</td>
		<td>날짜</td>
		<td>Hiter</td>
		<td>내용</td>
		<td>메모</td>
	</tr>
		<c:forEach var="n" items ="${list}">
		<tr>
			<td>${n.locid}</td>
			<td>${n.enemy}</td>
			<td>${n.regdate}</td>
			<td>${n.hiter}</td>	
			<td>${n.content}</td>
			<td>${n.memo}</td>
		</tr>
		</c:forEach>
	</table>
	
	<form action="scorelist">
	<table>
		<tr>
			<td>
				<select name="field" >
    			<option value="locid">지역번호</option>
    			<option value="enemy">적</option>
    			<option value="regdate">날짜</option>
    			<option value="hiter">히터</option>
    			<option value="content">내용</option>
    		    <option value="memo">메모</option>
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


	</body>
</html>