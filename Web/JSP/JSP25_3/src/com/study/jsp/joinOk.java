package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class joinOk implements Service {

	public joinOk() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		
		MemberDTO dto = new MemberDTO();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		MemberDAO dao = MemberDAO.getInstance(); 
		
		if (dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {

			System.out.println("아이디가 이미 존재 합니다.");
			writer.println("<script language=\"javascript\">\r\n" + 
					"			alert(\"아이디가 이미 존재 합니다.\");\r\n" + 
					"			history.back();\r\n" + 
					"		</script>");
			response.sendRedirect("join.jsp");

		} else {
			int ri = dao.insertMember(dto);
			System.out.print(ri);
			if (ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
				session.setAttribute("id", dto.getId());
				
				System.out.println("회원가입을 축하합니다.");
				writer.println("<script language=\"javascript\">\r\n" + 
						"			alert(\"회원가입을 축하합니다.\");\r\n" + 
						"			document.location.href=\"login.jsp\";\r\n" + 
						"		</script>");
				response.sendRedirect("login.jsp");

		} else {
			
			System.out.println("회원가입에 실패했습니다.");
			writer.println("<script language=\"javascript\">\r\n" + 
					"			alert(\"회원가입에 실패했습니다.\");\r\n" + 
					"			document.location.href=\"login.jsp\";\r\n" + 
					"		</script>");
			response.sendRedirect("login.jsp");

			}
		}	

	}

}
