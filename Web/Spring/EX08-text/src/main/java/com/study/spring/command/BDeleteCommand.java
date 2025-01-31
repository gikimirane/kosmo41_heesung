package com.study.spring.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.study.spring.dao.BDao;

@Component
public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		BDao dao = BDao.getInstance();
		dao.delete(bId);

	}

}
