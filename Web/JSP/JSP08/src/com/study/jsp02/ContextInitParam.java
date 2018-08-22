package com.study.jsp02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextInitParam
 */
@WebServlet("/ContextInitParam")
public class ContextInitParam extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		System.out.println("doGet");
		
		
		String id = getServletContext().getInitParameter("database");
		String pw = getServletContext().getInitParameter("connect");
		String path = getServletContext().getInitParameter("path2");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		out.println("<html><head></head><body>");
		out.println("아이디 :"+id+"<br>");
		out.println("비밀번호 :"+pw+"<br>");
		out.println("path :"+path);
		out.println("</body></html>");
		
		out.close();
	}
}
