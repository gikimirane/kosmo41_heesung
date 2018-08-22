<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!  //변수/메소드/선언시 ! 붙힐것.
	int i=10;
	String str = "ABCDEFG";
	
	public int sum (int a, int b){
		return a+b;
	}
%>
	숫자 i의 값은 <%= i %> 입니다.<br/>
	문자변수에는 <%= str %> 값이 저장되어 있습니다. <br/>
	두 수의 합은 <%= sum(5,4) %>입니다.
</body>
</html>