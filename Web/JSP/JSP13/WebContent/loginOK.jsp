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
		String id,pw,name;
	%>
	<%
		request.setCharacterEncoding("UTF-8");
		id=request.getParameter("id");
		pw=request.getParameter("pw");
		name=request.getParameter("name");
		
		if(id.equals("asd") && pw.equals("123456")){
			
			Cookie cookie = new Cookie("id",id);
			cookie.setMaxAge(60);
			response.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("name",name);
			cookie2.setMaxAge(60);
			response.addCookie(cookie2);
			
			response.sendRedirect("welcome.jsp");
		}else{
			response.sendRedirect("login.html");
		}
	%>
</body>
</html>