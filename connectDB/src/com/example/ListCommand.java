package com.example;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.example.dao.Dao;
import com.example.dto.BoardDto;

public class ListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Dao dao = new Dao();
		ArrayList<BoardDto> bdtos = dao.loadData(request);
		request.setAttribute("boardList", bdtos);
	}

}
