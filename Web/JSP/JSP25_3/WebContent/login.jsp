<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		* {
				background-color: black;
				font-size: large; font-family: fantasy; 
				color: pink;
			}
	</style>	
</head>
<body>
	<form action="loginOk.do" method="post">
		아이디 : <input type="text" name="id" value="<%
				if(session.getAttribute("id") != null)
					out.println(session.getAttribute("id"));
				%>"> <br>
		비밀번호 : <input type="password" name="pw"><br><p>
		<input type="submit" value="로그인"> &nbsp;&nbsp;	
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
	</form>
</body>
</html>