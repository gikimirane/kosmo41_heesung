<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");

	String nick = request.getParameter("nick");
	session.setAttribute("nick", nick);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    
   <script type='text/javascript' src='https://apis.google.com/js/platform.js' async defer></script>
	<meta name="google-signin-client_id" content="603925222698-u4nplidlhsrvp4iqvqq9oer7ja7du51q.apps.googleusercontent.com">

	<script>
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	    	console.log('User signed out.');

	    });
	    document.location.href="qq.jsp";
	}
	function onLoad() {
	      gapi.load('auth2', function() {
	        gapi.auth2.init();
	      });
	    }
	
	</script>
	<script src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
	<%=nick %> 굿굿 왜안됨
		<input type="button" onclick="signOut();" value="로그아웃" /><br>
		<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>