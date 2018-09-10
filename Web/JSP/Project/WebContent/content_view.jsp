<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String nick = (String) session.getAttribute("nick");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<title>Insert title here</title>
<script>
	function onDownload(bId) {

		var o = document.getElementById("ifrm_filedown");

		o.src = "download.do?bId=" + bId;

	}
</script>
</head>
<body>
	<iframe id="ifrm_filedown"
		style="position: absolute; z-index: 1; visibility: hidden;"></iframe>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"> <a
		class="navbar-brand" href="main.jsp" align="center">Kosmo</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="main.jsp">Home
					<span class="sr-only"></span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="client.jsp">채팅방</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 게시판 </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="list.do?table=0">한식게시판</a>
					<a class="dropdown-item" href="list.do?table=1">중식게시판</a> 
					<a class="dropdown-item" href="list.do?table=2">분식게시판</a> 
					<a class="dropdown-item" href="list.do?table=3">양식게시판</a> 
					<a class="dropdown-item" href="list.do?table=4">일식게시판</a>
				</div></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">오늘의
					추천 메뉴</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
	</nav>
	<br />
	<hr>
	<br />
	<table class="table container">
		<input type="hidden" name="table"
			value="<%=session.getAttribute("ctable")%>">
		<tr>
			<th scope="row">번호</th>
			<td>${content_view.bId }</td>
			<th scope="row">조회수</th>
			<td>${content_view.bHit }</td>
		</tr>
		<tr>
			<th scope="row">닉네임</th>
			<td>${content_view.bName }</td>
		</tr>
		<tr>
			<th scope="row">제목</th>
			<td>${content_view.bTitle }</td>
		</tr>
		<tr>
			<th scope="row">내용</th>
			<td>${content_view.bContent }</td>
		</tr>
		<tr>
			<th scope="row">첨부파일</th>
			<c:choose>
			<c:when test="${content_view.filename == null }">
				<td><a href="#" onclick="onDownload('${content_view.bId}')">${content_view.filename }</a></td>
			</c:when>
			<c:otherwise>
				<td><a href="#" onclick="onDownload('${content_view.bId}')">${content_view.filename }</a>
				<img src="./upload/${content_view.filename }" width="50px" height="50px"></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td colspan="2">
				<c:choose>
				<c:when test="${content_view.bName == nick }">
					<a href="modify_view.do?bId=${content_view.bId }&table=${ctable}">수정</a>&nbsp;&nbsp;
					<a href="list.do?page=<%= session.getAttribute("cpage") %>&table=${ctable}&search=<%= session.getAttribute("csearch")%>">목록보기</a> &nbsp;&nbsp;
					<a href="delete.do?bId=${content_view.bId }&table=${ctable}">삭제</a>&nbsp;&nbsp;
					<a href="reply_view.do?bId=${content_view.bId }&table=${ctable}">답변</a></td>
				</c:when>
				<c:otherwise>
					<a href="list.do?page=<%= session.getAttribute("cpage") %>&table=${ctable}&search=<%= session.getAttribute("csearch")%>">목록보기</a> &nbsp;&nbsp;
					<a href="reply_view.do?bId=${content_view.bId }&table=${ctable}">답변</a>
			</td>
				</c:otherwise>
				</c:choose>
		</tr>
	</table>
</body>
</html>