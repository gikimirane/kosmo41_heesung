package com.study.jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BCommand;
import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BDownCommand;
import com.study.jsp.command.BGoogleCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommand;
import com.study.jsp.command.BWriteCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String id; String pw; String name; String eMail; String address;
	
    public FrontController() {
        super();
       
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		HttpSession session = null;
		session = request.getSession();
		int curPage=1;
		if(session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}
		int table=1;
		if(session.getAttribute("ctable") != null) {
			table = (int)session.getAttribute("ctable");
		}
		
		if (com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		}else if (com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request,response);
			viewPage = "list.do?table="+table;
		}else if (com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request,response);
			viewPage = "list.jsp";
		}else if (com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		}else if (com.equals("/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
		}else if (com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request,response);
			
			command = new BContentCommand();
			command.execute(request,response);
			viewPage = "content_view.jsp";
		}else if (com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do?page="+curPage+"table="+table;
		}else if (com.equals("/reply_view.do")) {
			command = new BReplyViewCommand();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
		}else if (com.equals("/reply.do")) {
			command = new BReplyCommand();
			command.execute(request, response);
			viewPage = "list.do?page="+curPage+"table="+table;
		}else if (com.equals("/start.do")) {
			viewPage = "startPage.jsp";
		}else if (com.equals("/download.do")) {
			command = new BDownCommand();
			command.execute(request, response);
		}else if (com.equals("/google.do")) {
			command = new BGoogleCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
				
	}
}
