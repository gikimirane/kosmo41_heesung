package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.BDao;
import com.study.jsp.BDto;
import com.study.jsp.BPageInfo;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int nPage = 1;
		int table = 0;
		int choose = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch (Exception e) {
		}
		//게시판 구분
		String num = request.getParameter("table");
		table = Integer.parseInt(num);
		
		String search = request.getParameter("search");
	
		
		
		BDao dao = BDao.getInstance();
		BPageInfo pinfo = dao.articlePage(nPage,table,search);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("ctable", table);
		session.setAttribute("csearch", search);
		
		ArrayList<BDto> dtos = dao.list(nPage, table,search);
		request.setAttribute("list", dtos);
		
		

	}

}
