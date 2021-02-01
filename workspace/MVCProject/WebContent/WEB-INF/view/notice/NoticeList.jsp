<%@page import="com.jdbc.app.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form action="list">
   <table>
      <tr>
         <td>
         <input type="radio" name="filed" value="title">타이틀 
         <input type="radio" name="filed" value="writer_id">글쓴이
         </td>

      </tr>
      <tr>
         <td><input type="text" name="query"></td>
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
   <!-- 페이지 이동 -->
   
   <!-- startnum 변수 선언 및 값 할당 -->
   <c:set var="page" value="${empty param?1:param.p}"></c:set>
   <c:set var="startNum" value="${page-(page-1)%5}"></c:set>
   <c:set var="lastNum" value="19"></c:set>
 
   <!-- Prev 버튼 -->
   <c:if test="${startNum > 1}">
      <a href="?p=${startNum-1}&t=&q=">Prev</a>
   </c:if>
      <c:if test="${startNum <= 1}">
      <a href="#" onclick="alert('첫번째 페이지 입니다.');">Prev</a>
   </c:if>

   <!-- 페이징 -->
   <ul>
      <c:forEach var="i" begin="0" end="4">
         <li><a href=?p= "${startNum+i}&t=&q=">${startNum+i}</a></li>
      </c:forEach>
   </ul>
   
   <!-- Next 버튼 -->
   <c:if test="${startNum+5 < lastNum}">
      <a href="?p=${startNum+5}&t=&q=">Next</a>
   </c:if>
      <c:if test="${startNum+5 > lastNum}">
      <a href="#" onclick="alert('마지막 페이지 입니다.');">Next</a>
   </c:if>
   
   
</body>
</html>