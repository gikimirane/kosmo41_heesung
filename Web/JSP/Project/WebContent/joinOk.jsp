<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.control.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="com.study.control.MDto"/>
<jsp:setProperty property="*" name="dto"/>
<%
	MDao dao = MDao.getInstance();
	if(dao.confirmId(dto.getId()) == MDao.MEMBER_EXISTENT){
%>
	<script language="javascript">
		alert("아이디가 이미 존재합니다.");
		history.back();
	</script>
<%
	}else{
		int ri = dao.join(dto);
		if(ri == MDao.MEMBER_JOIN_SUCCESS){
			session.setAttribute("id", dto.getId());
%>
	<script language="javascript">
		alert("회원가입 완료했습니다.!!!");
		document.location.href="startPage.jsp";
	</script>
<%
		}else{
%>
	<script language="javascript">
		alert("회원가입에 실패했습니다.");
		document.location.href="join.jsp";
	</script>

<%			
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