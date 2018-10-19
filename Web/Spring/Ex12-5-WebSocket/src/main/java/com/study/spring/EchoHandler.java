package com.study.spring;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler{
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.printf("%s ���� ��\n",session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		System.out.printf("%s�κ��� [%s] ����\n",session.getId(), message.getPayload());
		session.sendMessage(new TextMessage("echo:" + message.getPayload()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session , CloseStatus status) throws Exception{
		System.out.printf("%s ���� ����\n", session.getId());
	}

}
