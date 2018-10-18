<%@page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 목록 보기</h2>
	<ul>
		<c:forEach items="${fileMap }" var="file">
			<li>
				파일명 : <a href="download?fileName=${file.key }&oriFileName=test.png">${file.key }</a>&nbsp;&nbsp;
				파일 크기 : ${file.value }
			</li>
		</c:forEach>
	</ul>
</body>
</html>