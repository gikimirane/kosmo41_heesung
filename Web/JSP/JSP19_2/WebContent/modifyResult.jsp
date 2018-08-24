<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function form_check() 
	{
		alert("로그아웃 하셨습니다.")
		window.location.replace("login.jsp");
	}
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= session.getAttribute("name") %>님의 회원정보 수정이 정상 처리 되었습니다.<br><p>
	
	<input type="button" value="로그아웃" onclick="form_check()"> &nbsp;
	<button>
		<a href="modify.jsp">정보수정</a>
	</button>
</body>
</html>