<%@page import="com.jdbc.app.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form>
   <table>
      <tr>
         <td>
			<select name="field">
    			<option ${(param.field == "title")?"selected":""} value="title">제목</option>
    			<option ${(param.field == "writer_id")?"selected":""} value="writer_id">글쓴이</option>
  			</select>
         </td>
      </tr>
      <tr>
         <td><input type="text" name="query" value="${param.query}"></td>
      </tr>
      <tr>
         <td><input type="submit" value="조회"></td>
      </tr>
   </table>
   </form>

   <table border=1>
      <tr>
         <td>번호</td>
         <td>제목</td>
         <td>작성자</td>
         <td>작성일</td>
         <td>조회수</td>
      </tr>
      <c:forEach var="n" items="${list}" varStatus="t">
         <tr>
            <td>${n.id}</td>
            <td><a href="detail?id=${n.id}">${n.title}</a></td>
            <td>${n.writerID}</td>
            <td><fmt:formatDate value="${n.regdate}" pattern="yyyy.MM.dd."></fmt:formatDate></td>
            <td><fmt:formatNumber value="${n.hit}" type="number"></fmt:formatNumber></td>
         </tr>
      </c:forEach>
   </table>
   
   <!-- startnum 변수 선언 및 값 할당 -->
   <c:set var="page" value="${empty param?1:param.p}"></c:set>
   <c:set var="startNum" value="${page-(page-1)%5}"></c:set>
   <c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.')}"></c:set>
 	
 	lastnum : ${fn:substringBefore(Math.ceil(count/10), '.')}
 	
   <!-- 페이지 이동 -->
   		<div>
   			<h3> 현재 페이지 </h3>
   			<div><span>﻿${empty param?1:param.p}﻿ </span>/ ${fn:substringBefore(Math.ceil(count/10), '.')} pages</div>
   		</div>
   
   <!-- Prev 버튼 -->
   <c:if test="${startNum > 1}">
      <a href="?p=${startNum-1}&field=${param.field}&query=${param.query}">Prev</a>
   </c:if>
      <c:if test="${startNum <= 1}">
      <a href="#" onclick="alert('첫번째 페이지 입니다.');">Prev</a>
   </c:if>

   <!-- 페이징 -->
   <ul>
      <c:forEach var="i" begin="0" end="4">
            <c:if test="${param.p == (startNum+i)}">
         	   <c:set var="style" value="font-weight:bold;color:red;"></c:set>
         	</c:if>
         	<c:if test="${param.p != (startNum+i)}">
         		<c:set var="style" value=""></c:set>
         	</c:if>
         	<c:if test="${(startNum+i) <= lastNum}">
         	  <li><a style ="${style}" href="list?p=${startNum+i}&field=${param.field}&query=${param.query}">${startNum+i}</a></li>
         	</c:if>
         	
  
      </c:forEach>
   </ul>
   
   <!-- Next 버튼 -->
   <c:if test="${startNum+5 <= lastNum}">
      <a href="?p=${startNum+5}&field=${param.field}&query=${param.query}">Next</a>
   </c:if>
      <c:if test="${startNum+5 > lastNum}">
      <a href="#" onclick="alert('마지막 페이지 입니다.');">Next</a>
   </c:if>
   
   
</body>
</html>