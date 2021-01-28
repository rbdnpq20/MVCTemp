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
      <tr>
         <td>
            ${tempdetail.date}
         </td>
      </tr>
      <tr>
         <td>
            ${tempdetail.temp}
         </td>
       </tr>
         <tr>
         <td>
            ${tempdetail.locid}
         </td>
      </tr>
         <tr>
         <td>
            ${tempdetail.writer}
         </td>
        </tr>
   </table>
</body>
</html>