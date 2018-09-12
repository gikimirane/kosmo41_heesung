<%@page import="org.apache.catalina.ant.SessionsTask"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.study.server.*" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat Client</title>
<style>
	.left-box{
		position: absolute;
		top: 50; left: 0;
	}
	.right-box{
		position: absolute;
		top: 50; right: 0;
	}
</style>
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
	<div class="container">
	<table style="width: 900px;">
		<tr>
			<td align="center" style="width:600px;">
				<div id="chatname" style="display: block;">
					<h2>대기 방</h2>
				</div>
				<div id="chatname1" style="display: none;">
					<h2 id="chatroom"></h2>
				</div>
			</td>
			<td align="center" style="width:200px">
				<button type="button" onclick="openSocket();">접속</button>&nbsp;
				<button type="button" onclick="closeSocket();">종료</button>
			</td>
			<td align="center" style="width:200px">
				<button type="button" onclick="roomcreate();">방 만들기</button>&nbsp;
				<button type="button" onclick="roomnamelist();">방 목록보기</button>
			</td>
		</tr>
	</table>
	<table aling="center" style="height: 600px; width: 900px; margin:0; paddin:0;">
		<col width="400px"><hr>
		<col width="300px">
		<col width="300px">
		<tr>
			<td style="width: 550px" colspan="2">
				<textarea id="messages" cols="60" rows="30" redonly="ture" ></textarea>
				<input type="text" id="messageinput" size="60" onkeypress="if(event.keyCode==13){send();}"/>
				<button type="button" onclick="send();">메세지 보내기</button>
			</td>
			<td style="width: 300px">
				<div id="room" style="display: none;">
					<h2>방 생성</h2>
					<input type="text" id="roomid" size="15" placeholder="방제목" required autofocus>
					<input type="password" id="roompw" size="15" placeholder="비밀번호"><br>
					<button type="button" onclick="roomc();" align="center">확인</button>&nbsp;
					<button type="button" onclick="roomn();">취소</button>
				</div>
				<h2>방 목록</h2>
				<textarea id="roomlist" cols="20" rows="10" value="" placeholder="방 목록을 보시려면 방목록 버튼을 눌러주세요"></textarea>
				<input type="text" id="roomname" size="20" onkeypress="if(event.keyCode==13){send();}"/>
				<button type="button" onclick="roomvisit()">방 입장하기</button>
			</td>
	</table>
	</div>
	
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
		
		//방 관련 -----------------------------------------------------
	 	function roomcreate(){
			$('#room').css('display','block');
		}
	 	function roomc(){
	 		var id = "<%= id %>";
	 		var roomid = document.getElementById("roomid").value;
	 		if(roomid==""){
	 			alert("방 제목을 입력하세요");
	 			return;
	 		}
	 		
	 		var roompw = document.getElementById("roompw").value;
	 		if(roompw==""){
	 			roompw= 0;
	 		}
	 		alert(roomid+" 방을 개설하셨습니다.");
	 		$('#chatname').css('display','none');
	 		$('#chatname1').css('display','block');
	 		document.getElementById("chatroom").innerHTML=roomid+" 방";
	 		document.getElementById("messages").innerHTML="";
	 		$('#room').css('display','none');
	 		webSocket.send(id+":"+"/roomcreate"+":"+roomid+":"+roompw);
	 		document.getElementById("roompw").value="";
	 		document.getElementById("roomid").value="";
	 	}
	 	function roomn(){
	 		$('#room').css('display','none');
	 	}
	 	
	 	function roomnamelist(){
	 		document.getElementById("roomlist").innerHTML="";
	 		var id = "<%= id %>";
	 		webSocket.send(id+":"+"/roomlist");
	 	}
	 	
	 	function roomvisit(){
	 		var id = "<%= id %>";
	 		var roomname = document.getElementById("roomname").value;
	 		alert(roomname+" 방에 입장하셨습니다.")
	 		$('#chatname').css('display','none');
	 		$('#chatname1').css('display','block');
	 		document.getElementById("chatroom").innerHTML=roomname+" 방";
	 		document.getElementById("messages").innerHTML="";
	 		webSocket.send(id+":"+"/roomvisit"+":"+roomname);
	 		document.getElementById("roomname").value = "";
	 	}
		//------------------------------------------------------------
		
		
		function send(){
			var id = "<%= id %>";
			var text = document.getElementById("messageinput").value;
			if(text==""){
				text = "　";
			}
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
			var roomliste = text.split(":");
			if(roomliste[0]=="/roomlist"){
				roomlist.innerHTML += roomliste[1]+"\n";
			}else{
				messages.innerHTML += text+"\n";
				messages.scrollTop = messages.scrollHeight;
			}
		}
	</script>
</body>
</html>