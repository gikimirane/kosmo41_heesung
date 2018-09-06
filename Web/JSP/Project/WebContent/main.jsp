<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("ValidMem") == null){
%>
	<jsp:forward page = "startPage.jsp"/>
<%
	}
	String nick = (String)session.getAttribute("nick");
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="603925222698-u4nplidlhsrvp4iqvqq9oer7ja7du51q.apps.googleusercontent.com">
 	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">
    <link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
		crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script>
		function out() {
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.out().then(function () {
		    	console.log('User signed out.');
	
		    	$('#nick').attr('value', '');
		    });
		    
		    document.location.href="logout.jsp";
		}
	</script>
<title>Insert title here</title>
</head>
<body>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"> <a
		class="navbar-brand" href="#" align="center">Kosmo</a>
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
			<li class="nav-item"><a class="nav-link" href="#">채팅방</a></li>
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
		<form class="form-inline my-2 my-lg-0" action="list.do">
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
	<form class="form-signin container" action="loginOk.jsp" method="post">
    	<div class="text-center mb-4">
        	<h1 class="h3 mb-3 font-weight-normal"> <%= nick %> 님 어서오세요!</h1>
        	<p>저희 사이트는 여러분들의 경험을 나누는 곳입니다.<br> 위 상단 메뉴에 음식별로 게시판이 있으니 서로 정보를 공유하세요!<br> 그 외에도 서로의 정보를
        		나눌 수 있는 채팅방<br> 오늘의 메뉴를 추천해주는 기능 등 여러가지 기능도 있으니 사용하여 주세요!</p>
        	<a type="button" href="logout.jsp">로그아웃</a>
      	</div>
    </form>	
</body>
</html>