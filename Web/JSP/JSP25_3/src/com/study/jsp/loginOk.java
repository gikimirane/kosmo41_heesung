package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {

	public loginOk() {

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		MemberDAO dao = MemberDAO.getInstance();
		int checkNum = dao.userCheck(id, pw);
		if (checkNum == -1) {
			writer.println("<script language=\"javascript\">\r\n" + "			alert(\"아이디가 존재하지 않습니다.\");\r\n"
					+ "			history.go(-1);\r\n" + "		</script>");
			response.sendRedirect("login.jsp");

		} else if (checkNum == 0) {
			writer.println("<script language=\"javascript\">\r\n" + "			alert(\"비밀번호가 틀립니다.\");\r\n"
					+ "			history.go(-1);\r\n" + "		</script>");
			response.sendRedirect("login.jsp");

		} else if (checkNum == 1) {
			MemberDTO dto = dao.getMember(id);

			if (dto == null) {
				writer.println("<script language=\"javascript\">\r\n" + "			alert(\"존재하지 않는 회원입니다..\");\r\n"
						+ "			history.go(-1);\r\n" + "		</script>");
				response.sendRedirect("login.jsp");

			} else {
				String name = dto.getName();
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("ValidMem", "yes");

				response.sendRedirect("main.jsp");
			}
		}

	}

}
