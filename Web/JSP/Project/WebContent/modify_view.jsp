<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nick = (String)session.getAttribute("nick");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<title>Insert title here</title>
</head>
<script>
	function form_check(){

	    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		document.modify_form.submit();
	}
</script>

<body background="img/고양이강아지.jpeg">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"> <a
		class="navbar-brand" href="main.jsp" align="center">동물이야기</a>
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
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
	</nav>
	<br/>
	<hr>
	<br/>
	<table width="1000" cellpadding="0" cellspacing="0" border="1" class="container" style="background-color: white;">
		<form name="modify_form" action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId }">
			<input type="hidden" name="table" value="<%= session.getAttribute("ctable")%>">
			<tr>
				<td>번호</td>
				<td>${content_view.bId }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${content_view.bHit }</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="bName" value="${content_view.bName }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${content_view.bTitle }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="bContent" id="editor1" rows="10" cols="80">
              	  This is my textarea to be replaced with CKEditor.
         	    </textarea>
         		 <script>
           		    CKEDITOR.replace( 'editor1' );
           		 </script></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="수정완료">&nbsp;&nbsp;
				<a href="content_view.do?bId=${content_view.bId }">취소</a>&nbsp;&nbsp;
				<a href="list.do?page=<%= session.getAttribute("cpage")%>&table=${ctable}&search=<%= session.getAttribute("csearch")%>">목록보기</a> &nbsp;&nbsp;
			</tr>
		</form>
	</table>
</body>
</html>