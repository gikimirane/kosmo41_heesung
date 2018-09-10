package com.study.server;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocketendpoint2")

	public class WsServer2<WebSocketSession>{
	DataSource dataSource = null;
	
	public WsServer2() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private static final java.util.Map<String, Session> user =
			java.util.Collections.synchronizedMap(new java.util.HashMap<String, Session>());
	
	private static final java.util.Set<Session> sessions =
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	
	public void open(String nick,String session) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into chat "+
						" (nick, sessions, room) "+
						"values "+
						"(?, ?, '대기실') ";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nick);
			pstmt.setString(2, session);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sendSoloSessionToMessage(Session self, String message) {
		try {
			String msg[] = message.split(":| ");
			String nick = msg[2].toString();
			String msgput = "";
			self = user.get(nick);
			System.out.println(self);
			for(Session session : WsServer2.sessions) {
				if(session.getId().equals(self.getId())) {
					for(int i=3;i<msg.length;i++) {
						msgput += msg[i];
					}
					session.getBasicRemote().sendText(msgput);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Open session id :" + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("대기실에 입장하셨습니다.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		sessions.add(session);
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("Session "+session.getId()+"has ended");
		sessions.remove(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from "+session.getId()+":"+message);
		final Basic basic = session.getBasicRemote();
		try {
			String nick[] = message.split(":| ");
			String nickname = nick[0].toString();
			boolean containsKey = user.containsKey(nickname);
			System.out.println("111");
			if(containsKey == true) {
				if(nick[1].equals("/r")) {
						sendSoloSessionToMessage(session,message);			
					}else {
						basic.sendText(message);
						sendAllSessionToMessage(session, message);
					}
				System.out.println("2222");
			}else if(containsKey == false) {
				user.put(nickname,session);
				open(nick[0],session.getId());
				sendAllSessionToMessage(session, nick[0]+"님이 입장하셨습니다.");
				System.out.println("333");
				System.out.println(user.keySet().toString());
			}
			//내가 보낸 메세지.
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//메시지 받는곳
	}
	private void sendAllSessionToMessage(Session self, String message) {
		try {
			for(Session session : WsServer2.sessions) {
				if(! self.getId().equals(session.getId()))
					//여기가 메세지 클라이언트쪽에 보내주는거//메세지 받는거.
					session.getBasicRemote().sendText(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		
	}
	
}
