<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<body background="img/고양이강아지.jpeg">
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>


	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"> <a
		class="navbar-brand" href="#" align="center">동물이야기</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">Home
					<span class="sr-only"></span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">채팅방</a></li>
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

	<br />
	<hr>
	<br />
	<form class="needs-validation" name="join" action="joinOk.jsp"
		method="post" style="background-color: white;">
		<div class="form-group container">
			<label for="id">* ID</label> <input type="text" class="form-control"
				id="id" name="id" placeholder="아이디를 입력하세요">
			<small class="form-text text-muted">중복 확인을 눌러 주세요.</small>
			<button value="중복확인" class="btn btn-primary" id="IDCheck"
				onclick="form_check();">중복확인</button>
		</div>
		<div class="form-group container">
			<label for="pw">* Password</label> <input type="password"
				class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요"> 
				<small class="form-text text-muted">0~8자리로 입력하세요.</small>
		</div>
		<div class="form-group container">
			<label for="pwc">Password Check</label> <input type="password"
				class="form-control" id="pwc" name="pwc"
				placeholder="Password Check">
		</div>
		<div class="form-group container">
			<label for="id">NAME</label> <input type="text" class="form-control"
				id="name" name="name" placeholder="이름을 입력하세요">
		</div>
		<div class="form-group container">
			<label for="id">HOME</label> <input type="text" class="form-control"
				id="home" name="home" placeholder="주소를 입력하세요"> <small
				class="form-text text-muted">예 : 인천 서구 석남동 금호아파트 202동</small>
		</div>
		<div class="form-group container">
			<label for="id">* Nick Name</label> <input type="text"
				class="form-control" id="nick" name="nick"
				placeholder="사용하실 닉네임을 입력하세요">
		</div>
		<input type="button" value="회원 가입" class="btn btn-lg btn-primary btn-block container"
			onclick="infoConfirme()">
	</form>

</body>
<script>
	function infoConfirme() {
		
		if (document.join.id.value.length == 0) {
			alert("아이디를 입력하세요");
			document.join.id.focus();
			return;
		}
		if (document.join.pw.value != document.join.pwc.value) {
			alert("비밀번호가 일치하지 않습니다.");
			document.join.pw.focus();
			return;
		}
		if (document.join.nick.value.length == 0) {
			alert("닉네임을 입력하세요");
			document.join.nick.focus();
			return;
		}
		//	alert("회원가입이 완료됐습니다.로그인 후 사용해주세요^^");
			document.join.submit();
	}
</script>
</html>