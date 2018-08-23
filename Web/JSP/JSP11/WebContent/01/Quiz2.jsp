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
	
		String number1 = request.getParameter("number1");
		String number2 = request.getParameter("number2");
	
	
		String major = request.getParameter("major");
		
		int answer = 0;
		if(major.equals("+")) 
		{
			answer = (Integer.parseInt(number1))+(Integer.parseInt(number2));
		}
		else if(major.equals("-")) 
		{
			answer = (Integer.parseInt(number1))-(Integer.parseInt(number2));
		}
		else if(major.equals("*")) 
		{
			answer = (Integer.parseInt(number1))*(Integer.parseInt(number2));
		}
		else if(major.equals("/")) 
		{
			answer = (Integer.parseInt(number1))/(Integer.parseInt(number2));
		}	
	%>
	
	
	첫번째 수 <%=number1 %><br/>
	두번째 수 <%=number2 %><br/>
	계산 식 <%=major %><br/>
	결과 <%=answer %><br/>
</body>
</html>