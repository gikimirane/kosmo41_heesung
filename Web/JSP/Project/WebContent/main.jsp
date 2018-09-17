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
	int login = (int)session.getAttribute("login");
%>
<!DOCTYPE html>
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
	function signOut() {
		
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');

		});
		document.location.href = "logout.jsp";
	}
	function onLoad() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
	}
</script>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '238447546838563',
      cookie     : false,
      xfbml      : false,
      version    : 'v3.1'
    });

    FB.getLoginStatus(function(unknown) {
    	console.log(response);
      statusChangeCallback(response);
    });
  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  function statusChangeCallback(response) {
    if (response.status === 'connected') {
    } else {
      $('#login').css('display', 'block');
      $('#logout').css('display', 'none');
      $('#uname').html('');
    }
  }

  function fbLogout () {
    FB.logout(function(response) {
      statusChangeCallback(response);
    });
    document.location.href="logout.jsp";
  }


</script>
<title>Insert title here</title>
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
	<form class="form-signin container" action="loginOk.jsp" method="post" style="background-color: white;">
    	<div class="text-center mb-4">
        	<h1 class="h3 mb-3 font-weight-normal"> <%= nick %> 님 어서오세요!</h1>
        	<p>저희 사이트는 애완동물들 키우는 사람들을 위한 곳입니다.<br> 위 상단 메뉴에 게시판 별로 구분되어 있으니 서로 정보를 공유하세요!<br> 그 외에도 서로의 정보를
        		나눌 수 있는 채팅방과<br> 지루하지 않게 재밌는 게임들도 있으니 많은 이용 바랍니다.</p>
        	<%if(login == 1){ %>
        	<a type="button" href="#" onclick="signOut();">로그아웃</a>
        	<%}else if(login == 2){ %>
        	<input type="button" onclick="fbLogout();" value="로그아웃" />
        	<%} %>
      	</div>
    </form>
    <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>	
</body>
</html>