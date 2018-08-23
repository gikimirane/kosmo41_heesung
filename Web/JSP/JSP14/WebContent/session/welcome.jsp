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
	<%
		request.setCharacterEncoding("UTF-8");		
		//Object obj1 = session.getAttribute("id");
		//바로 변환 가능.
		String id = (String)session.getAttribute("id");
		
		if(id==null){
			response.sendRedirect("login.html");
		}else{
			out.println(id+" 님 어서오세요 <br>");
		}
	%>
	<button>
		<a href="logout.jsp">로그아웃</a>
	</button>
	<button>
		<a href="sessiontest.jsp">쿠키테스트</a>
	</button>
</body>
</html>