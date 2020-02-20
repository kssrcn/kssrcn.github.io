package com.example;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.example.dao.Dao;
import com.example.dto.BoardDto;

public class ShowBoardCommand implements Command {

	@Override
	public void execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Dao dao = new Dao();
		BoardDto bdto = dao.showData(request);
		request.setAttribute("showList", bdto);
	}

}
