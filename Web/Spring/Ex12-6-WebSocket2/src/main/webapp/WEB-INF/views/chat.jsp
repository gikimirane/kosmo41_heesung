<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Echo</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
   var wsocket;
   
   function connect(){
	   wsocket = new WebSocket ("ws://localhost:8081/spring/chat-ws");
	   wsocket.onopen = onOpen;
	   wsocket.onmessage = onMessage;   
	   wsocket.onclose = onClose;
   }
   
   function disconnect(){
	   wsocket.close();
   }
   
   function onOpen(evt){
	   appendMessage("연결되었습니다.\n");
   }
   
   
   function onMessage(evt){
      var data = evt.data;
      if(data.substring(0,4) == "msg:"){
    	  appendMessage(data.substring(4));
      }
   }
   
   function onClose(evt){
      appendMessage("연결을 끊었습니다.");
   }
   
   function send(){
	   var nickname = $("#nickname").val();
	   var msg = $("#message").val();
	   wsocket.send("msg:"+nickname+":"+msg);
	   $("#message").val("");
   }
   
   function appendMessage(msg){
	   $("#chatMessageArea").append(msg+"\n");
	   var chatAreaHeight = $("#chatArea").height();
	   var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
	   $("#chatArea").scrollTop(maxScroll);
   }
   
   $(document).ready(function(){
	   $('#message').keypress(function(event){
		   var keycode = (event.keyCode ? event.keyCode : event.which);
		   if(ketcode == '13'){
			   send();
		   }
		   event.stopPropagation();
	   });
	   
	   $('#sendBtn').click(function(){send();});
	   $('#enterBtn').click(function(){connect();});
	   $('#exitBtn').click(function(){disconnect();});
   })

</script>
</head>
<body>

	이름 : <input type="text" id="nickname">
	<input type="button" id="enterBtn" value="입장">
	<input type="button" id="exitBtn" value="퇴장">
	
	<h1>대화 영역</h1>
	<div id="chatArea">
<!-- 		<div id="chatMessageArea"></div> -->
		<textarea rows="60" cols="60" id="chatMessageArea"></textarea>
	</div>
   <input type="text" id="message">
   <input type="button" id="sendBtn" value="전송">
</body>
</html>