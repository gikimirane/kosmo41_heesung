<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.control.*" %>
<% 
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MDao dao = MDao.getInstance();
	int checkNum = dao.userCheck(id, pw);
	if(checkNum == -1){
%>
	<script languge="javascript">
		alert("잘못된 아이디 입니다.");
		history.go(-1);
	</script>
<%
	}else if(checkNum == 0){
%>
	<script languge="javascript">
		alert("잘못된 비빌번호 입니다.");
		history.go(-1);
	</script>
<%
	}else if(checkNum == 1){
		MDto dto = dao.getMember(id);
		if(dto == null){
%>
	<script languge="javascript">
		alert("없는 회원 입니다.");
		history.go(-1);
	</script>	
<%
		}else{
			String nick = dto.getNick();
			session.setAttribute("id", id);
			session.setAttribute("nick", nick);
			session.setAttribute("ValidMem", "yes");
			response.sendRedirect("main.jsp");
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>