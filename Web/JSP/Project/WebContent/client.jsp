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
		String num = "";
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
			<td align="center" style="width:150px">
				<button type="button" onclick="openSocket();">접속</button>&nbsp;
				<button type="button" onclick="closeSocket();">종료</button>
			</td>
			<td align="center" style="width:150px">
				<button type="button" id="rc" style="display: block;"  onclick="roomcreate();">방 만들기</button>
				<button type="button" id="re" style="display: none;" onclick="roomout();">방 나가기</button>
			</td>
			<td style="width:200px">
				<button type="button" id="rl" style="display: block;" onclick="roomnamelist();">방 목록</button>
			</td>
		</tr>
	</table>
	<table aling="center" style="height: 600px; width: 900px; margin:0; paddin:0;">
		<col width="400px"><hr>
		<col width="300px">
		<col width="300px">
		<tr>
			<td style="width: 550px" colspan="2">
				<textarea id="messages" cols="60" rows="30" readonly></textarea>
				<input type="text" id="messageinput" size="60" onkeypress="if(event.keyCode==13){send();}"/>
				<button type="button" onclick="send();">메세지 보내기</button>
				<div id="super" style="display: none;">
					<input type="text" id="user" size="10" placeholder="강퇴할 닉네임">
					<button type="button" onclick="userout();">강퇴</button>
					<input type="text" id="invite" size="10" placeholder="초대할 닉네임">
					<button type="button" onclick="userinvite();">초대</button>
					<button type="button" onclick="roomend();">채팅방 닫기</button>		
				</div>
			</td>
			<td style="width: 300px">
				<div id="room" style="display: none;">
					<h2>방 생성</h2>
					<input type="text" id="roomid" size="15" placeholder="방제목" required autofocus>
					<input type="password" id="roompw" size="15" placeholder="비밀번호"><br>
					<h5>인원 수</h5>
					<input type="radio" name="human" value="2" checked>2명
					<input type="radio" name="human" value="4" >4명
					<input type="radio" name="human" value="6" >6명
					<input type="radio" name="human" value="8" >8명<br>
					<small>* 미체크시 2명 고정입니다.</small>
					<button type="submit" onclick="roomc();">확인</button>&nbsp;
					<button type="button" onclick="roomn();">취소</button>
				</div>
				<div>
					<h2>전체유저 리스트</h2>
					<textarea id="userlist" cols="20" rows="10" readonly></textarea>
				</div>
				<div id="rooml" style="display: block;">
					<h2>방 목록</h2>
					<textarea id="roomlist" cols="20" rows="10" placeholder="방 목록을 보시려면 방목록 버튼을 눌러주세요" readonly></textarea>
					<input type="text" id="roomname" size="20" onkeypress="if(event.keyCode==13){send();}"/>
					<button type="button" onclick="roomvisit()">방 입장하기</button>
				</div>
				<div id="userl" style="display: none;">
					<h2>방 유저리스트</h2>
					<textarea id="roomuserl" cols="20" rows="10" readonly></textarea>			
				</div>
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
	 		var humannum = $('input[name="human"]:checked').val();
	 		
	 		alert(roomid+" 방을 개설하셨습니다.");
	 		$('#chatname').css('display','none');
	 		$('#chatname1').css('display','block');
	 		document.getElementById("chatroom").innerHTML=roomid+" 방";
	 		document.getElementById("messages").innerHTML="";
	 		$('#room').css('display','none');
	 		$('#super').css('display','block');
	 		$('#rc').css('display','none');
	 		$('#re').css('display','block');
	 		$('#rl').css('display','none');
	 		$('#rooml').css('display','none');
	 		$('#userl').css('display','block');
	 		webSocket.send(id+":"+"/roomcreate"+":"+roomid+":"+roompw+":"+humannum);
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
	 	function roomvisitcheck(texted){
	 		alert(texted+" 방에 입장하셨습니다.")
	 		$('#chatname').css('display','none');
	 		$('#chatname1').css('display','block');
	 		$('#rc').css('display','none');
	 		$('#re').css('display','block');
	 		$('#rl').css('display','none');
	 		$('#rooml').css('display','none');
	 		$('#userl').css('display','block');
	 		document.getElementById("chatroom").innerHTML=texted+" 방";
	 		document.getElementById("messages").innerHTML="";
	 		document.getElementById("roomname").value = "";
	 		
	 	}
	 	function roomvisit(){
	 		var id = "<%= id %>";
	 		var roomname = document.getElementById("roomname").value;
	 		webSocket.send(id+":"+"/roomvisit"+":"+roomname);
	 	}
	 	
	 	function userout(){
	 		var id = "<%= id %>";
	 		var user = document.getElementById("user").value;
	 		webSocket.send(id+":"+"/out"+":"+user);
	 	}
	 	
	 	function outalert(texted){
	 		alert(texted);
	 		document.getElementById("messages").innerHTML="";
	 		$('#rc').css('display','block');
	 		$('#re').css('display','none');
	 		$('#rl').css('display','block');
	 		$('#chatname').css('display','block');
	 		$('#chatname1').css('display','none');
	 		$('#rooml').css('display','block');
	 		$('#userl').css('display','none');
	 	}
	 	
	 	function roomend(){
	 		var id = "<%= id %>";
	 		webSocket.send(id+":"+"/roomend");
	 	}
	 	
	 	function roomoutre(texted){
	 		alert(texted);
	 		document.getElementById("messages").innerHTML="";
	 		$('#rc').css('display','block');
	 		$('#re').css('display','none');
	 		$('#rl').css('display','block');
	 		$('#rooml').css('display','block');
	 		$('#userl').css('display','none');
	 	}
	 	
	 	function roomout(){
	 		var id = "<%= id %>";
	 		webSocket.send(id+":"+"/roomout");
	 		document.getElementById("messages").innerHTML="";
	 		$('#rc').css('display','block');
	 		$('#re').css('display','none');
	 		$('#rl').css('display','block');
	 		$('#rooml').css('display','block');
	 		$('#userl').css('display','none');
	 		$('#chatname').css('display','block');
	 		$('#chatname1').css('display','none');
	 		$('#super').css('display','none');
	 	}
	 	
	 	function superblock(){
	 		alert("방장이 되셨습니다.");
	 		$('#super').css('display','block');
	 	}
	 	
	 	function userinvite(){
	 		alert("초대메세지를 보냈습니다.");
	 		var id = "<%= id %>";
	 		var invite = document.getElementById("invite").value;
	 		webSocket.send(id+":"+"/userinvite"+":"+invite);
	 	}
	 	function invitecheck(texted){
	 		var text = texted;
	 		var dd = confirm(text+". 수락은 확인 거절은 취소를 눌러주세요");
	 		
	 		if(dd){
	 			yes(text);
	 		}else{
	 			no(text);
	 		}
	 	}
	 	
	 	function yes(text){
	 		var id = "<%= id %>";
	 		var textedS = text.split(" ");
	 		webSocket.send(id+":"+"/roomvisit"+":"+textedS[2]);
	 	}
	 	
	 	function no(text){
	 		var id = "<%= id %>";
	 		var textedS = text.split(" ");
	 		webSocket.send(id+":"+"/no"+":"+textedS[0]);
	 	}
	 	
	 	function roomuserlistcheck(texted){
	 		document.getElementById("roomuserl").innerHTML="";
	 		roomuserl.innerHTML += texted;
	 	}
		//------------------------------------------------------------
		
		function userlistcheck(texted){
			document.getElementById("userlist").innerHTML="";
			userlist.innerHTML += texted;
		}
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
			document.getElementById("userlist").value = "";
			webSocket.close();
		}
		function login(){
			var id = "<%= id %>";
			webSocket.send(id+":");
		}
		function writeResponse(text){
			var texted = text.split(":");
			if(texted[0]=="/roomlist"){
				roomlist.innerHTML += texted[1]+"\n";
			}
			else if(texted[0]=="/userlist"){
				userlistcheck(texted[1]);
			}
			else if(texted[0]=="/roomuserlist"){
				roomuserlistcheck(texted[1]);
			}
			else if(texted[0]=="/userout"){
				outalert(texted[1]);
			}
			else if(texted[0]=="/roomend"){
				roomoutre(texted[1]);
			}
			else if(texted[0]=="/super"){
				superblock();
			}
			else if(texted[0]=="/humanend"){
				alert("방에 인원이 다찼습니다.")
			}
			else if(texted[0]=="/humanok"){
				roomvisitcheck(texted[1]);
			}
			else if(texted[0]=="/invite"){
				invitecheck(texted[1]);
			}
			else if(texted[0]=="/no"){
				alert(texted[1]+" 님이 거절하셨습니다.")
			}
			else{
				messages.innerHTML += text+"\n";
				messages.scrollTop = messages.scrollHeight;
			}
		}
	</script>
</body>
</html>