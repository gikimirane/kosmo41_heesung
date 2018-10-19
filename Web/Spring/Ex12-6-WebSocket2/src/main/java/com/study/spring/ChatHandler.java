package com.study.spring;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler{
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();

	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		log(session.getId()+"���� ��");
		users.put(session.getId(), session);
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		log(session.getId()+"�κ��� �޼��� ����: "+ message.getPayload());
		for(WebSocketSession s : users.values()) {
			s.sendMessage(message);
			log(s.getId()+"�� �޽��� �߼�: "+message.getPayload());
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session , CloseStatus status) throws Exception{
		log(session.getId()+"���� �����");
		users.remove(session.getId());
	} 
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
		log(session.getId()+" �ͼ��� �߻� : "+ exception.getMessage());
	}
	
	private void log(String logmsg) {
		System.out.println(new Date()+" : " + logmsg);
	}

}
