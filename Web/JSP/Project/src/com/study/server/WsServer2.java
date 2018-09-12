package com.study.server;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	List<String> roomlist = new ArrayList<>();
	
	private static final java.util.Map<String, Session> user =
			java.util.Collections.synchronizedMap(new java.util.HashMap<String, Session>());
	
	private static final java.util.Set<Session> sessions =
			java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
	
	public void open(String nick,String session) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into chat "+
						" (nick, sessions, room,roomnumber,roompw,super) "+
						"values "+
						"(?, ?, '대기실',0,0,0) ";
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
	
	public void roomcreate(String nick,String session,String roomname, String roompw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update chat set "+
						" room='"+roomname+"', roompw='"+roompw+"', super='super' "+
						" where nick='"+nick+"'";
		String query1 = "insert into roomlist (roomlist) values (?)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt = con.prepareStatement(query1);
			pstmt.setString(1, roomname);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("방만들떄에러");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void roomlist(String nick,Session session) {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		String roomlist ="";
		
		String query = "select * from roomlist";
		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(query);
			while(resultSet.next()) {
				roomlist += resultSet.getString(1)+"\n";
				System.out.println(roomlist);
			}
			System.out.println("11111");
			session.getBasicRemote().sendText("/roomlist"+":"+roomlist);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("방리스트에러");
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void roomvisit(String nick,String session,String roomname) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update chat set "+
						" room='"+roomname+"'"+
						" where nick='"+nick+"'";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("방만들떄에러");
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void roomtalk(String nick,Session session,String msg) {
		Connection con = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		String nickname ="";
		String room = "";

		try {
			con = dataSource.getConnection();
			stmt = con.createStatement();
			resultSet = stmt.executeQuery("select room from chat where nick='"+nick+"'");
			if(resultSet.next()) {
				room = resultSet.getString(1);
			}
			resultSet1 = stmt.executeQuery("select nick from chat where room='"+room+"'");
			while(resultSet1.next()) {
				nickname = resultSet1.getString(1);		
				session = user.get(nickname);
				session.getBasicRemote().sendText(nick+" : "+msg);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("방채팅");
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(resultSet1 != null) resultSet1.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close(String nick,Session session) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from chat where nick='"+nick+"'";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("close");
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
			String me = msg[0].toString();
			String nick = msg[2].toString();
			String msgput = "";
			Session selfs = user.get(nick);
			for(Session session : WsServer2.sessions) {
				if(session.getId().equals(selfs.getId())) {
					for(int i=3;i<msg.length;i++) {
						msgput += msg[i];
					}
					self.getBasicRemote().sendText(nick+"님에게 :"+msgput);
					session.getBasicRemote().sendText(me+"님의 귓속말 :"+msgput);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void AllMsg(Session self, String message) {
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
		Set set = user.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			String key = (String)iterator.next();
			Session a = user.get(key);
			if(session.getId().equals(a.getId())) {
				roomtalk(key,session,"님이 퇴장하셨습니다.");
				close(key,session);
				user.remove(key);
				break;
			}
		}
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("Message from "+session.getId()+":"+message);
		try {
			String nick[] = message.split(":| ");
			String nickname = nick[0].toString();
			String msg = "";
			for(int i=1; i <nick.length;i++) {
				msg +=nick[i];
			}
			boolean containsKey = user.containsKey(nickname);
			if(containsKey == true) {
				if(nick[1].equals("/r")) {
					sendSoloSessionToMessage(session,message);			
				}
				else if(nick[1].equals("/roomcreate")) {
					roomcreate(nickname,session.getId(),nick[2],nick[3]);
				}
				else if(nick[1].equals("/roomlist")) {
					roomlist(nickname,session);
				}
				else if(nick[1].equals("/roomvisit")) {
					roomvisit(nickname,session.getId(),nick[2]);
				}
				else {
					roomtalk(nickname,session,msg);
				}
			}else if(containsKey == false) {
				user.put(nickname,session);
				open(nick[0],session.getId());
				roomtalk(nickname,session,"님이 퇴장하셨습니다.");
			}
			//내가 보낸 메세지.
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//메시지 받는곳
	}
	
	
	@OnError
	public void onError(Throwable e, Session session) {
		
	}
	
}
