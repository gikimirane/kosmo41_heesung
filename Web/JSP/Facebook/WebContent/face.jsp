<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '238447546838563',
      cookie     : true,
      xfbml      : true,
      version    : 'v3.1'
    });

    FB.getLoginStatus(function(response) {
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
    document.location.href="aaa.jsp";
  }


</script>
</head>
<body>
	굿굿
	<div id="status"></div>
	<input type="button" onclick="fbLogout();" value="로그아웃" /><br>
</body>
</html>