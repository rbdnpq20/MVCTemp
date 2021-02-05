<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>바보</title>
</head>
<body>
	<h2>Score를 등록해봅시다아아아아아아아아</h2>
<form method = "post" action ="scoreinsert">
	<table border =1>
		<tr>
			<td>지역</td>
			<td>적</td>
			<td>히터(DVA,MONKEY,MORISON)</td>
			<td>내용</td>
			<td>메모</td>
		</tr>
		<tr>
			<td>
			<select name="locid">
    			<option value="1">감시기지</option>
    			<option value="2">호라이즌</option>
    			<option value="3">서울</option>
    			<option value="4">브리자드월드</option>
    			<option value="5">부산</option>
  			</select>
  			</td>
			<td><input type ="text" name ="enemy" /></td>
			<td><input type ="text" name ="hiter" /></td>
			<td><input type ="text" name ="content" /></td>
			<td><input type ="text" name ="memo" /></td>
		</tr>
	</table>
	<input type="submit" value="등록">
	<a href = "joinlist">취소</a>
	</form>
</body>
</html>