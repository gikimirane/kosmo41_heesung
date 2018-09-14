<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="main.jsp" align="center">동물이야기</a>
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
						<a class="dropdown-item" href="list.do?table=0">강아지 아빠 게시판</a>
						<a class="dropdown-item" href="list.do?table=1">고양이 집사 게시판</a> 
						<a class="dropdown-item" href="list.do?table=2">그 외 동물들 게시판</a> 
						<a class="dropdown-item" href="list.do?table=3">분양 게시판</a> 
						<a class="dropdown-item" href="list.do?table=4">꿀팁 게시판</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">게임방</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" name="search" action="list.do">
				<select class="custom-select" id="table" name="table">
					<option selected>검색</option>
					<option value="6">닉네임</option>
					<option value="7">제목</option>
					<option value="8">내용</option>
				</select>&nbsp;
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search" id="search" name="search">
				<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<br/>
	<hr>
	<br/>
	<div class="text-center mb-4">
		<h1>게시판</h1>
	</div>
	<table class="table container">
		<thead class = "thead-dark">
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">제목</th>
				<th scope="col">날짜</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		<c:forEach items="${list}" var="dto">
		<tr>
			<th scope="row">${dto.bId }</th>
			<td>${dto.bName }</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent }">└[답변]</c:forEach>
				<a href="content_view.do?bId=${dto.bId }">${dto.bTitle }</a>
			</td>
			<td>${dto.bDate }</td>
			<td>${dto.bHit }</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="write_view.do">글작성</a></td>
		</tr>
		<tr>
		<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
  		<div class="btn-group mr-2" role="group" aria-label="First group">
			<!-- ?가 파라미터 page=파라미터 이름  =뒤에 넣을값.-->
			<td colspan="5" align="center">
			<!-- 처음 -->
			<c:choose>
			<c:when test="${(page.curPage -1)<1 }">
				<a class="btn btn-secondary">&lt;&lt;</a>&nbsp;
			</c:when>
			<c:otherwise>
				<a class="btn btn-secondary" href="list.do?page=1&table=${ctable}&search=<%= session.getAttribute("csearch")%>">&lt;&lt;</a>&nbsp;
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage -1)<1 }">
				<a class="btn btn-secondary">&lt;</a>&nbsp;
			</c:when>
			<c:otherwise>
				<a class="btn btn-secondary" href="list.do?page=${page.curPage-1 }&table=${ctable}&search=<%= session.getAttribute("csearch")%>">&lt;</a>&nbsp;
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 -->
			<c:forEach begin="${page.startPage }" end="${page.endPage }" step="1" var="page2">
			
			<c:choose>
			<c:when test="${page.curPage == page2}">
				 ${page2 }&nbsp;
			</c:when>
			<c:otherwise>
   				<a class="btn btn-secondary" href="list.do?page=${page2 }&table=${ctable}&search=<%= session.getAttribute("csearch")%>">${page2 }</a>
			</c:otherwise>
			</c:choose>
			</div>
   			</div>	
			</c:forEach>
			
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage + 1)> page.totalPage }">
				<a class="btn btn-secondary">&gt;</a>&nbsp;
			</c:when>
			<c:otherwise>
				<a class="btn btn-secondary" href="list.do?page=${page.curPage+1 }&table=${ctable}&search=<%= session.getAttribute("csearch")%>">&gt;</a>&nbsp;
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage }">
				<a class="btn btn-secondary">&gt;&gt;</a>&nbsp;
			</c:when>
			<c:otherwise>
				<a class="btn btn-secondary" href="list.do?page=${page.totalPage}&table=${ctable}&search=<%= session.getAttribute("csearch")%>">&gt;&gt;</a>&nbsp;
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</table>
</body>
</html>