package com.study.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifyOk implements Service {

	public modifyOk() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = new MemberDTO();
		
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
		
		request.setCharacterEncoding("UTF-8");
		
		id = (String)session.getAttribute("id");
		dto.setId(id);
		
		MemberDAO dao = MemberDAO.getInstance();
		response.setContentType("text/html; charset=UTF-8");

		int ri = dao.updateMember(dto);
		System.out.println(ri);
		if(ri == 1) {
			response.sendRedirect("main.jsp");
//		<script language="javascript">
//			alert("정보가 수정되었습니다.");
//			document.location.href="main.jsp"
//		</script>
		
		} else {
			response.sendRedirect("modify.jsp");
//		<script language="javascript">
//			alert("정보수정에 실패했습니다.");
//			history.go(-1);
//		</script>
		}

	}

}
