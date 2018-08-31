<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
 <script src="https://cdn.ckeditor.com/4.10.0/standard/ckeditor.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Kosmo-HeeSung</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
	    	<ul class="navbar-nav mr-auto">
	     		 <li class="nav-item active">
	        		<a class="nav-link" href="list.do?page=<%= session.getAttribute("cpage") %>">Home <span class="sr-only">(current)</span></a>
	      		</li>
	      		<li class="nav-item">
	        		<a class="nav-link" href="#">Link</a>
	      		</li>
	      		<li class="nav-item dropdown">
	        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	        			  Dropdown
	        		</a>
	        		<div class="dropdown-menu" aria-labelledby="navbarDropdown">
	        			<a class="dropdown-item" href="#">Action</a>
	        			<a class="dropdown-item" href="#">Another action</a>
	        			<div class="dropdown-divider"></div>
	        			<a class="dropdown-item" href="#">Something else here</a>
	        		</div>
	      		</li>
	      		<li class="nav-item">
	        		<a class="nav-link disabled" href="#">Disabled</a>
	      		</li>
	    	</ul>
	    <form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	    </form>
	  </div>
	</nav>
	<br/>
	<hr>
	<br/>
	<table width="700" cellpadding="0" cellspacing="0" border="1" class="container">
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId }">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup }">
			<input type="hidden" name="bStep" value="${reply_view.bStep }">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent }">
			<input type="hidden" name="bName" value="${reply_view.bName }">
			<tr>
				<td>번호</td>
				<td>${reply_view.bId }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${reply_view.bHit }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${reply_view.bName }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${reply_view.bTitle }"></td>
			</tr>
			<tr>
				<td>원문 내용</td>
				<td>${reply_view.bContent }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="bContent" id="editor1" rows="10" cols="80">
              	  This is my textarea to be replaced with CKEditor.
         	    </textarea></td>
         		 <script>
           		    CKEDITOR.replace( 'editor1' );
           		 </script>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="답변">
				<a href="list.do?page=<%= session.getAttribute("cpage")%>">목록보기</a></td>
			</tr>
		</form>
	</table>
</body>
</html>