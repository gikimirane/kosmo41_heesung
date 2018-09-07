package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BGoogleCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int login = 1;
		String nick = request.getParameter("nick");
		String num1 = request.getParameter("login");
		 login = Integer.parseInt(num1); 
		
		HttpSession session = null;
		session = request.getSession();
		
		session.setAttribute("nick", nick);
		session.setAttribute("ValidMem", "yes");
		session.setAttribute("login", login);

	}

}
