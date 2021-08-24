<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/mail/sendMail.do" method="post">
	<p align = "center"><span style="border: 3px dashed #C6C6C6; padding: 0.6em"><b>Email</b></span></p>
	<table border="1" bordercoler="blue" align = "center" width = "300" height="300">
	<th>받는사람</th>
	<th><input type="text" placeholder="donghyup56@naver.com" name="toMail"></th>
	<tr align = "center">
	<td><b>메일제목</b></td>
	<td><input type="text" placeholder="메일 제목입니다." name="title"></td>
	</tr>
	<tr align= "center">
	<td><b>메일내용</b></td>
	<td><input type="text" placeholder="메일내용입니다."name="contents"></td>
	</tr>
	</table>
	<div align="center">
	<button type="submit" >메일전송</button>
	<button type="reset">내용초기화</button>
	</div>
	</form>
</body>
</html>