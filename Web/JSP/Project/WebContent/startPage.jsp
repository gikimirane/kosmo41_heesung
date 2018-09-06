<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem")!=null){ %>
	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="603925222698-u4nplidlhsrvp4iqvqq9oer7ja7du51q.apps.googleusercontent.com">
	

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>


    <!-- Custom styles for this template -->
    <link href="floating-labels.css" rel="stylesheet">
    <script>
		function onSignIn(googleUser) {
			var profile = googleUser.getBasicProfile();
			console.log('ID: ' + profile.getId());
			console.log('Name: ' + profile.getName());
			console.log('Image URL: ' + profile.getImageUrl());
			console.log('Email: ' + profile.getEmail());


	    	$('#nick').attr('value',profile.getName());
	    	
	    	document.zz.submit();
		}

	</script>
  </head>

<body>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
 	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <form name="zz" class="form-signin" action="loginOk.jsp" method="post">
    	<div class="text-center mb-4">
        	<h1 class="h3 mb-3 font-weight-normal">어서오세요!</h1>
        	<p>저희 사이트는 회원들만 이용할 수 있습니다.<br>회원이 아니면 <code><a href="join.jsp">회원가입</a></code> 버튼을 눌러 가입해 주세요.</a></p>
      	</div>

      	<div class="form-label-group">
			<input type="text" id="id" name="id" class="form-control" 
					 required autofocus>
      		<label for="id">ID</label>
      	</div>

      	<div class="form-label-group">
			<input type="password" id="pw" name="pw" class="form-control" required autofocus>
        	<label for="pw">Password</label>
      	</div>

      	<div class="checkbox mb-3">
        	<label>
        	  <input type="checkbox" value="remember-me"> ID 저장
        	</label>
      	</div>
      	<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button><br/>
		<div id="login" class="g-signin2" data-onsuccess="onSignIn"></div>
		<p class="mt-5 mb-3 text-muted text-center">&copy; 2018</p>	
	</form>
</body>
</html>
