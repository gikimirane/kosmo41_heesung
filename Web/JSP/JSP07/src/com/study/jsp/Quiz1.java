package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz1")
public class Quiz1 extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		
		request.setCharacterEncoding("UTF-8");
		
		String number1 = request.getParameter("number1");
		String number2 = request.getParameter("number2");
		
		
		String major = request.getParameter("major");
		
		int answer = 0;
		if(major.equals("+")) 
		{
			answer = (Integer.parseInt(number1))+(Integer.parseInt(number2));
		}
		else if(major.equals("-")) 
		{
			answer = (Integer.parseInt(number1))-(Integer.parseInt(number2));
		}
		else if(major.equals("*")) 
		{
			answer = (Integer.parseInt(number1))*(Integer.parseInt(number2));
		}
		else if(major.equals("/")) 
		{
			answer = (Integer.parseInt(number1))/(Integer.parseInt(number2));
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		out.println("<html><head></head><body>");
		out.println("첫번째 수 :"+number1+"<br>");
		out.println("두번째 수 :"+number2+"<br>");
		out.println("계산 :"+major+"<br>");
		out.println("답안:"+answer);
		out.println("</body></html>");
		
//		out.println("<script>");
//		out.println("$(document).ready(function(){");
//		out.println("alert("+answer+");});");
//		out.println("</script>");
	}

}
