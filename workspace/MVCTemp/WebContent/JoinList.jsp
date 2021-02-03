<%@page import="join.Join"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	</head>
	<body>
	
	
	<table border=1>
	<tr>
		<td>힛터번호</td>	
		<td>지역</td>
		<td>날짜</td>
		<td>이름</td>
		<td>적</td>
		<td>내용</td>
	</tr>
		<c:forEach var="n" items ="${list}">
		<tr>
			<td>${n.seq}</td>
			<td>${n.location}</td>
			<td><fmt:formatDate value="${n.date}" pattern="yyyy년 MM월 dd일"/></td>
			<td>${n.name}</td>
			<td>${n.enemy}</td>	
			<td><a href="joindetail?seq=${n.seq}">${n.content}</a></td>
		</tr>
		</c:forEach>
	</table>

	<form>
	<table>
		<tr>
			<td>
				<select name="field" >
    				<option ${(param.field == "locname")?"selected":""} value="locname">지역</option>
    				<option ${(param.field == "date")?"selected":""} value="date">날짜</option>
    				<option ${(param.field == "name")?"selected":""} value="name">이름</option>
    				<option ${(param.field == "enemy")?"selected":""} value="enemy">적</option>
    				<option ${(param.field == "content")?"selected":""} value="content">내용</option>
  			</select>
  			<input type ="text" name="query" value="${param.query}">
  			<input type ="submit" value="검색">
  			 현재 페이지 :: <span>${(empty param.p)?1:param.p}</span>/ ${fn:substringBefore(Math.ceil(count/4), '.')}
			</td>
		</tr>	
	</table>
</form>

	   <!-- startnum 변수 선언 및 값 할당 -->
   <c:set var="page" value="${(empty param)?1:param.p}"></c:set>
   <c:set var="startNum" value="${page-(page-1)%10}"></c:set>
   <c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/4), '.')}"></c:set>
  
   <!-- 페이지 이동 -->
   
   <!-- Prev 버튼 -->
   <c:if test="${startNum > 1}">
      <a href="?p=${startNum-1}&field=${param.field}&query=${param.query}">이전</a>
   </c:if>
      <c:if test="${startNum <= 1}">
      <a href="#" onclick="alert('첫번째 페이지 입니다.');">이전</a>
   </c:if>

   <!-- 페이징 -->
      <c:forEach var="i" begin="0" end="9">
             <c:if test="${param.p == (startNum+i)}">
         	   <c:set var="style" value="font-weight:bold;color:red;"></c:set>
         	</c:if>
         	<c:if test="${param.p != (startNum+i)}">
         		<c:set var="style" value=""></c:set>
         	</c:if>
         	<c:if test="${(startNum+i) <= lastNum}">
       			<a style ="${style}" href="joinlist?p=${startNum+i}&field=${param.field}&query=${param.query}">${startNum+i}</a>
       </c:if>
      </c:forEach>
   
   <!-- Next 버튼 -->
   <c:if test="${startNum+10 <= lastNum}">
      <a href="?p=${startNum+10}&field=${param.field}&query=${param.query}">다음</a>
   </c:if>
      <c:if test="${startNum+10 > lastNum}">
      <a href="#" onclick="alert('마지막 페이지 입니다.');">다음</a>
   </c:if>


	</body>
</html>