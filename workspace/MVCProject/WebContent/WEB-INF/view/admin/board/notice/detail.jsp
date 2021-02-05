<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


   <table border=1>
      <tr>
         <td>제목</td>
          <td colspan="3">${nt.title}</td>
      </tr>
      <tr>
         <td>작성일</td>
         <td colspan="3">
         <fmt:formatDate value="${nt.regdate}" pattern="yyyy.MM.dd. hh.mm.ss"></fmt:formatDate>
         </td>
      </tr>
         <tr>
         <td>작성자</td>
          <td>${nt.writerID}</td>
          <td>조회수</td>
         <td><fmt:formatNumber value="${nt.hit}" type="number" pattern="###,###"></fmt:formatNumber></td>
      	 </tr>
      	 <tr>
       		<td>첨부파일</td>
         	<td colspan="3">
         		<c:forTokens var="filename" items="${nt.files}" delims="," varStatus="t">
         			<c:set var="style" value=""></c:set>
         				<c:if test="${fn:endsWith(filename,'.rar')}">
         					<c:set var="style" value="font-weight:bold;color:red;"></c:set>
         				</c:if>
         			<a href="{filename}" style="${style}">${fn:toUpperCase(filename)}</a>
         				<c:if test="${!t.last}">/</c:if>
         		</c:forTokens>
         		</td>
         </tr>
        <tr>
         	<td colspan="4">${nt.content}</td>
        </tr>
   </table>
   
   
   <!-- 목록 버튼 -->
   필드 : ${param.field}<br>
   쿼리 : ${param.query}<br>
   페이지 : ${param.p}<br>
   <input type="button" onclick="location.href='list?f=${param.f}&q=${param.q}&p=${param.p}'" value="목록">
</body>
</html>