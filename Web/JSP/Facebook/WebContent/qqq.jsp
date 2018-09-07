<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>

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
      getINFO();
    } else {
      $('#login').css('display', 'block');
      $('#logout').css('display', 'none');
      $('#uname').html('');
    }
  }
	  
  function fbLogin () {
    FB.login(function(response){
      statusChangeCallback(response);
    }, {scope: 'public_profile, email'});
  }

  function getINFO() {
    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
      console.log(response);
      $('#login').css('display', 'none');
      $('#logout').css('display', 'block');
      $('#uname').html('[ ' + response.name + ' ]');
      
      alert("로그인 성공!");
      
      document.location.href="face.jsp?nick="+response.name;
      
      
    });
  }

</script>

</head>
<body>

<div class="fb-login-button" id="login" style="display: block;">
    <input type="button" onclick="fbLogin();" value="로그인" /><br>
</div>

<div id="logout" style="display: none;">
    <input type="button" onclick="fbLogout();" value="로그아웃" /><br>

    <span id="uname"></span>
</div>
<a type="button" href="#" onclick="fbLogin();">
	<img src="img/facebook.png" width="100px" height="30px">
</a>




</body>
</html>

