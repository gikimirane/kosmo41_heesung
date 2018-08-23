<%@ page import = "java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		String id,name;
	%>
	<%
		request.setCharacterEncoding("UTF-8");
		//이건 따로따로원하는것만 삭제가능!
		session.removeAttribute("id");
		session.removeAttribute("pw");
		session.removeAttribute("name");
		
		//이건 한번에 삭제 가능!
	//	session.invalidate();
		
		response.sendRedirect("login.html");
	%>
</body>
</html>