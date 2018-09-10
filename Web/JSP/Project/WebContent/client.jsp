<%@page import="org.apache.catalina.ant.SessionsTask"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.server.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat Client</title>
</head>
<body>
	<%
		String id = (String)session.getAttribute("nick");
	
		if(id==null){
	%>
	<jsp:forward page="startPage.jsp"/>
	<%
		}else{
		session.setAttribute("uid", id);
		}
	%>
	<div>
		사용자 아이디 : <%= id %>
	</div>
	<div>
		<input type="text" id="messageinput" size="100"/><button type="button" onclick="send();">메세지 보내기</button>
	</div>
	<div>
		<button type="button" onclick="openSocket();">채팅방 입장</button>
		<button type="button" onclick="closeSocket();">채팅방 나가기</button>
	</div>
	
	<div id="messages"></div>
	
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		var nick = "<%= id %>";
		
		function openSocket(){
			if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opened.");
				return;
			}
			webSocket = new WebSocket("ws://localhost:8081/Project/websocketendpoint2");
			
			webSocket.onopen = function(event){
				login();
				if(event.data === undefined)
					return;
				writeResponse(event.data);
			};
			//서버에서 나한테 온 메세지. 이벤트데이터가 그것임.
			webSocket.onmessage = function(event){
				writeResponse(event.data);
			};
			webSocket.onclose = function(event){
				writeResponse("Connecton closed");
			};		
		}

		function send(){
			var id = "<%= id %>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(id+":"+text);
			document.getElementById("messageinput").value = "";
		}
		
		function closeSocket(){
			webSocket.close();
		}
		function login(){
			var id = "<%= id %>";
			webSocket.send(id+":");
		}
		function writeResponse(text){
			messages.innerHTML += "<br/>"+text;
		}
	</script>
</body>
</html>