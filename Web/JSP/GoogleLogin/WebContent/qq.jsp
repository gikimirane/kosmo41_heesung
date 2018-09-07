<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>구글 아이디로 로그인하기 1</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>
    
    <script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="603925222698-u4nplidlhsrvp4iqvqq9oer7ja7du51q.apps.googleusercontent.com">

	<script>
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId());
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail());

    	alert(profile.getName())
    	
    	document.location.href="google.jsp?nick="+profile.getName();
	}
    	

	</script>
</head>
<body>
	<div id="login" class="g-signin2" data-onsuccess="onSignIn"></div>	

</body>
</html>

