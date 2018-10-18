package com.study.spring.gm;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/list.json")
public class GuestMessageJSONList {
	
	private List<GuestMessage> messages;
	
	public GuestMessageJSONList(List<GuestMessage> messages) {
		this.messages = messages;
	}
	
	public List<GuestMessage> getMessages(){
		return messages;
	}

}
