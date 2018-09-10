<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nick = (String)session.getAttribute("nick");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
</head>
<body>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"> <a class="navbar-brand" href="main.jsp" align="center">Kosmo</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="main.jsp">Home
					<span class="sr-only"></span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="client.jsp">채팅방</a></li>
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> 게시판 </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="list.do?table=0">한식게시판</a>
					<a class="dropdown-item" href="list.do?table=1">중식게시판</a> 
					<a class="dropdown-item" href="list.do?table=2">분식게시판</a> 
					<a class="dropdown-item" href="list.do?table=3">양식게시판</a> 
					<a class="dropdown-item" href="list.do?table=4">일식게시판</a>
				</div>
			</li>
			<li class="nav-item">
				<a class="nav-link disabled" href="#">오늘의 추천 메뉴</a>
			</li>
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
	<table width="500" cellpadding="0" cellspacing="0" border="1" class="container">
		<form name="write" action="write.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="table" value="<%= session.getAttribute("ctable")%>">
			<tr>
				<td>공개여부</td>
				<td><input type="radio" name="secret" value="1" checked>공개&nbsp;
					<input type="radio" name="secret" value="2">비공개
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" neme="pw">
				<small class="form-text text-muted">* 비공개 선택시에만 적용됩니다.</small>
				</td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="hidden" name="bName" value="<%= nick%>"><%=nick %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" siez="50">
					<small class="form-text text-muted">* 반드시 입력해 주세요</small>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
				<textarea name="bContent" id="editor1" rows="10" cols="80">
              	  This is my textarea to be replaced with CKEditor.
         	    </textarea>
         		 <script>
           		    CKEDITOR.replace( 'editor1' );
           		 </script>
				</td>
			</tr>
			<tr>
				<td>첨부</td>
				<td><input type="file" name="filename"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="입력" onclick="check()"> &nbsp;&nbsp;
					<a href="list.do?page=<%= session.getAttribute("cpage")%>&table=${ctable}">목록보기</a>
				</td>
			</tr>
		</form>
	</table>
</body>
<script>
	function check() {
			alert("게시물이 등록되었습니다.")
			document.write.submit();
	}
</script>
</html>