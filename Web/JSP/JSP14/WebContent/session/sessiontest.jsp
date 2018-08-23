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
		String sessionID = session.getId();
		out.println("sessionID : "+sessionID+"<br>");
		int sessionInter = session.getMaxInactiveInterval();
		out.println("sessionInter : "+sessionInter+"<br>");
		
		Enumeration enumeration1 = session.getAttributeNames();
		while(enumeration1.hasMoreElements()){
			String name = enumeration1.nextElement().toString();
			if(name.equals("pw")){
				continue;
			}else{
				String value = session.getAttribute(name).toString();
				out.println(name+":"+value+"<br>");
			}
		}
	%>
</body>
</html>