package com.example;

import javax.servlet.http.HttpServletRequest;

import com.example.dao.Dao;

public class MemberDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Dao dao = new Dao();
		dao.deleteData(request);
	}

}
