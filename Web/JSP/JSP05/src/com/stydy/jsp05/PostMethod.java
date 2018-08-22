package com.stydy.jsp05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostMethod")
public class PostMethod extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("Get방식입니다.<br> 따라서 doGet메서드가 호출되었습니다.");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		out.println("POST방식입니다.<br> 따라서 doPost메서드가 호출되었습니다.");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
