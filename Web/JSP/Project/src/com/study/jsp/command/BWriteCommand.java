package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.study.jsp.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MultipartRequest multi = null;
		
		int sizeLimit= 10*1024*1024;
		
		@SuppressWarnings("deprecation")
		String savePath = request.getRealPath("/upload");
		try {
			multi = new MultipartRequest(request,savePath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String bName = multi.getParameter("bName");
		String bTitle = multi.getParameter("bTitle");
		String bContent = multi.getParameter("bContent");
		int table = 0;
		String num = multi.getParameter("table");
		table = Integer.parseInt(num);
		String filename = multi.getFilesystemName("filename");
		
		
		BDao dao = BDao.getInstance();
		dao.write(bName, bTitle, bContent,table,filename);

	}

}
