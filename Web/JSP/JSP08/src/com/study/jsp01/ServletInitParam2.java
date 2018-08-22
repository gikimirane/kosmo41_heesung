package com.study.jsp01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/ServletInitParam2"},
			initParams= {@WebInitParam(name="id",value="홍길동"),
						@WebInitParam(name="pw",value="123456"),
						@WebInitParam(name="path",value="D:\\qwe\\asd")
			})
public class ServletInitParam2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		System.out.println("doGet");
		
		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		String path = getInitParameter("path");
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		out.println("<html><head></head><body>");
		out.println("아이디 :"+id+"<br>");
		out.println("비밀번호 :"+pw+"<br>");
		out.println("path :"+path);
		out.println("</body></html>");
	}

}
