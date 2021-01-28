<%@page import="user.User"%>
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
<% List<User> list = (List<User>)request.getAttribute("userlist"); 
	for (User us : list) {
		pageContext.setAttribute("userlist", us);

%>
${userlist.seq}
${userlist.id}
${userlist.name}
${userlist.email}
${userlist.hp}
${userlist.regdate}
${userlist.flag}

<% } %>

<form>
	<input type="text" name="name" size="10" maxlength="50">
	<input type="submit" value="마법의 버튼" formaction="userdetail" >
</form>

</body>
</html>